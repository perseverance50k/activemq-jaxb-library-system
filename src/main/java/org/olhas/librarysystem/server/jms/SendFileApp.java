package org.olhas.librarysystem.server.jms;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class used to send files to ActiveMQ Broker.
 * Represents a client.
 */
public class SendFileApp {

    public static void main(String[] args) {
        try {
            QueueMessageProducer queueMessageProducer = new QueueMessageProducer();

            System.out.println("The XML data will be taken from library_for_sending_to_activemq.xml file and sent to ActiveMQ." +
                    "Do you want to proceed? (1 - yes, 0 - no)");

            try (Scanner scanner = new Scanner(System.in)) {
                int answer = scanner.nextInt();
                if (answer == 1) {
                    queueMessageProducer.sendDataInXML();
                } else {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input was entered. Try to re-run the program.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
