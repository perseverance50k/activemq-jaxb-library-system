package org.olhas.librarysystem.server.jms;

import org.apache.activemq.ActiveMQConnection;

public class Constants {
    public static final String FILE_INPUT_DIRECTORY = "src/main/resources/input";
    public static final String FILE_NAME = "fileName";

    public static final String FILE_OUTPUT_BYTE_DIRECTORY = "src/main/resources/output/bytes/";

    public static final String QUEUE_NAME = "LIBRARY_MESSAGE_QUEUE";
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static final String ADMIN = "admin";
}
