package org.olhas.librarysystem.server.jms;

import jakarta.xml.bind.JAXBException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.olhas.librarysystem.models.Library;
import org.olhas.librarysystem.server.jaxb.service.JaxbService;

import javax.jms.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

/**
 * A message consumer which receives the file from ActiveMQ Broker.
 */
public class QueueMessageConsumer implements MessageListener {

    private final String activeMqBrokerUri;
    private final String username;
    private final String password;
    private String destinationName;
    private final FileAsByteArrayManager fileManager = new FileAsByteArrayManager();
    private static final JaxbService jaxbService = new JaxbService();

    public QueueMessageConsumer(String activeMqBrokerUri, String username, String password) {
        super();
        this.activeMqBrokerUri = activeMqBrokerUri;
        this.username = username;
        this.password = password;
    }

    public void run() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(username, password, activeMqBrokerUri);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Destination destination = session.createQueue(destinationName);

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(this);

        System.out.printf("QueueMessageConsumer Waiting for messages at queue='%s' broker='%s'%n",
                destinationName, this.activeMqBrokerUri);
    }

    @Override
    public void onMessage(Message message) {
        try {
            String filename = message.getStringProperty(Constants.FILE_NAME);
            Instant start = Instant.now();

            if (message instanceof ActiveMQTextMessage) {
                handleTextMessage((ActiveMQTextMessage) message);
            } else if (message instanceof ActiveMQBytesMessage) {
                handleBytesMessage((ActiveMQBytesMessage) message, filename);
            } else {
                System.out.println("test");
            }

            Instant end = Instant.now();
            System.out.println("Consumed message with filename [" + filename
                    + "], took " + Duration.between(start, end));
            printFileContentsInPOJO_and_XML();
            message.acknowledge();
        } catch (JMSException | IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks whether there are an unmarshalling_library.xml file received from the ActiveMQ Broker.
     * If the file exists, we unmarshall it to POJO, output, then marshall POJO back to XML and output
     * the result as well. Otherwise, we just say that there are no files in the queue.
     *
     * @throws JAXBException if some error happens during marshalling/unmarshalling operation
     */
    private static void printFileContentsInPOJO_and_XML() throws JAXBException {
        if (new File("src/main/resources/output/bytes/unmarshalling_library.xml").exists()) {
            Library library = jaxbService.convertToPOJO("unmarshalling_library.xml");
            System.out.println("POJO Library instance:\n" + library);
            System.out.println("\nConverting POJO Library instance back to XML...");
            System.out.println("XML representation:");
            jaxbService.transformToXML(library);
        } else {
            System.out.println("There are no files in the queue!");
        }
    }

    private void handleBytesMessage(ActiveMQBytesMessage message, String filename) throws IOException {
        String outputFileName = Constants.FILE_OUTPUT_BYTE_DIRECTORY + filename;
        fileManager.writeFile(message.getContent().getData(), outputFileName);
        System.out.println("Received ActiveMQBytesMessage message");
    }

    private void handleTextMessage(ActiveMQTextMessage message) throws JMSException {
        String msg = String.format("Received ActiveMQTextMessage [ %s ]", message.getText());
        System.out.println(message);
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }
}
