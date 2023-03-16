package org.olhas.librarysystem.server.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A message producer which sends the file to ActiveMQ Broker.
 */
public class QueueMessageProducer {

    private static final String DESTINATION_NAME = "LIBRARY_MESSAGE_QUEUE";
    private static final String ACTIVE_MQ_BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static Session session;
    private static Destination destination;

    private void run() throws JMSException {
        // Create a connection factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", ACTIVE_MQ_BROKER_URL);
        // Create a connection
        Connection connection = factory.createConnection();
        connection.start();
        // Initialize a session
        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // Initialize a destination
        destination = session.createQueue(DESTINATION_NAME);
    }

    public void sendDataInXML() throws JMSException, IOException {
        run();
        // create a path to a file to read xml data from (library_for_sending_to_activemq.xml)
        Path path = Paths.get("src/main/resources/xml/library_for_sending_to_activemq.xml");
        // read all lines of the file and save them to List
        List<String> lines = Files.readAllLines(path);
        // save the whole file contents as a one string
        String xmlData = String.join("\n", lines);

        // Create a message producer
        MessageProducer messageProducer = session.createProducer(destination);
        // Create a text message to be sent
        TextMessage textMessage = session.createTextMessage(xmlData);
        // Send a text message
        messageProducer.send(textMessage);
        System.out.println("\nsendDataInXML: Sent a message");
    }
}
