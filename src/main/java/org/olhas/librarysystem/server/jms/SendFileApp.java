package org.olhas.librarysystem.server.jms;

import java.util.Scanner;

/**
 * Class used to send files to ActiveMQ Broker.
 */
public class SendFileApp {

    public static void main(String[] args) {
        try {
            QueueMessageProducer queueMessageProducer = new QueueMessageProducer(Constants.BROKER_URL,
                                                                                 Constants.ADMIN,
                                                                                 Constants.ADMIN);

            System.out.println("Enter message type for transferring file: \n\t1 – File as BytesMessage");

            try (Scanner scanner = new Scanner(System.in)) {
                String inputFileType = scanner.nextLine();
                switch (inputFileType) {
                    case "1":
                        queueMessageProducer.sendBytesMessages(Constants.QUEUE_NAME);
                        break;
                    default:
                        System.out.println("Wrong input");;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
