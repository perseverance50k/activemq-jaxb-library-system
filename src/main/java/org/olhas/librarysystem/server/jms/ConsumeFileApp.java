package org.olhas.librarysystem.server.jms;

import javax.jms.JMSException;

/**
 * Class that consumes messages from ActiveMQ Broker.
 * Represents a server.
 */
public class ConsumeFileApp {

    public static void main(String[] args) {

        QueueMessageConsumer queueMessageConsumer = new QueueMessageConsumer();

        try {
            queueMessageConsumer.run();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
