package org.olhas.librarysystem.server.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * A message producer which sends the file to ActiveMQ Broker.
 */
public class QueueMessageProducer {

    private String activeMqBrokerUrl;
    private final String username;
    private final String password;

    private ActiveMQSession session;
    private MessageProducer messageProducer;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    private final FileAsByteArrayManager fileManager = new FileAsByteArrayManager();

    public QueueMessageProducer(String activeMqBrokerUrl, String username, String password) {
        super();
        this.activeMqBrokerUrl = activeMqBrokerUrl;
        this.username = username;
        this.password = password;
    }

    private void setup() throws JMSException {
        connectionFactory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUrl);
        connection = connectionFactory.createConnection();
        connection.start();
        session = (ActiveMQSession) connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
    }

    private void close() {
        try {
            if (messageProducer != null) {
                messageProducer.close();
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Throwable ignore) {

        }
    }

    public void sendBytesMessages(String queueName) throws JMSException, IOException {
        setup();
        messageProducer = session.createProducer(session.createQueue(queueName));

        File[] files = new File(Constants.FILE_INPUT_DIRECTORY).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                sendFileAsBytesMessage(file);
            }
        }
    }

    private void sendFileAsBytesMessage(File file) throws JMSException, IOException {
        Instant start = Instant.now();
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.setStringProperty(Constants.FILE_NAME, file.getName());
        bytesMessage.writeBytes(fileManager.readFileAsBytes(file));
        messageProducer.send(bytesMessage);
        Instant end = Instant.now();
        System.out.println("sendFileAsBytesMessage for [" + file.getName() + "], took " + Duration.between(start, end));
    }
}
