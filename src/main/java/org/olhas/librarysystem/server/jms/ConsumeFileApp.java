package org.olhas.librarysystem.server.jms;

import jakarta.xml.bind.JAXBException;

import javax.jms.JMSException;

/**
 * Class that consumes messages from ActiveMQ Broker.
 */
public class ConsumeFileApp {

    public static void main(String[] args) throws JAXBException {
        QueueMessageConsumer queueMessageConsumer = new QueueMessageConsumer(Constants.BROKER_URL,
                                                                             Constants.ADMIN,
                                                                             Constants.ADMIN);
        queueMessageConsumer.setDestinationName(Constants.QUEUE_NAME);

        try {
            queueMessageConsumer.run();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
