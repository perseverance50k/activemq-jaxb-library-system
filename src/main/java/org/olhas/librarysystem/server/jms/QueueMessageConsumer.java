package org.olhas.librarysystem.server.jms;

import jakarta.xml.bind.JAXBException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.olhas.librarysystem.models.Library;
import org.olhas.librarysystem.server.jaxb.service.JaxbService;

import javax.jms.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A message consumer which receives the file from ActiveMQ Broker.
 */
public class QueueMessageConsumer implements MessageListener {

    private static final String ACTIVE_MQ_BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String DESTINATION_NAME = "LIBRARY_MESSAGE_QUEUE";
    private static final JaxbService jaxbService = new JaxbService();

    /**
     * Configures and sets up the ActiveMQ factory, connection, session, destination,
     * message consumer, and message listener.
     *
     * @throws JMSException if some error happens
     */
    public void run() throws JMSException {
        // Create a connection factory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", ACTIVE_MQ_BROKER_URL);
        // Create a connection
        Connection connection = factory.createConnection();
        connection.start();
        // Create a session
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        // Create a destination (queue or topic)
        Destination destination = session.createQueue(DESTINATION_NAME);
        // Create a message consumer
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(this); // set message listener to asynchronously fetch messages from ActiveMQ

        System.out.printf("QueueMessageConsumer Waiting for messages at queue='%s' broker='%s'%n",
                DESTINATION_NAME, ACTIVE_MQ_BROKER_URL);
    }

    /**
     * This method asynchronously fetches messages from ActiveMQ and processes them.
     *
     * @param message message that came from ActiveMQ
     */
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ActiveMQTextMessage) {
                handleTextMessage((ActiveMQTextMessage) message);
            }

            message.acknowledge();
        } catch (JMSException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles a text message that come from the ActiveMQ Broker.
     *
     * @param message came from ActiveMQ
     *
     * @throws JMSException if some error happens during fetching the message contents
     * @throws JAXBException if XML is not valid
     */
    private void handleTextMessage(ActiveMQTextMessage message) throws JMSException, JAXBException {
        System.out.printf("\nReceived ActiveMQTextMessage of the length %s%n", message.getSize());

        // Create a file to which the contents of the message will be written
        File file = new File("src/main/resources/xml/library_received_from_activemq.xml");

        // Create FileWriter to write the contents of the message into file
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(message.getText());
        } catch (IOException e) {
            System.out.println("Some error happened during the write operation.");
            e.printStackTrace();
        }

        // Unmarshal newly creates library_received_from_activemq.xml file with contents of the message to POJO
        Library library = jaxbService.convertToPOJO(file.getAbsolutePath());
        System.out.println("\nlibrary_received_from_activemq.xml was unmarshalled into Library POJO:\n" + library.toString());

        // Marshall Library POJO back to XML
        System.out.println("\nLibrary POJO marshalled back to XML:");
        jaxbService.transformToXML(library);
    }
}
