package org.olhas.librarysystem.server.jms;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A class used to read and write a file via bytes array.
 */
public class FileAsByteArrayManager {

    /**
     * A method used to read a file as bytes.
     *
     * @param file file to read
     * @return a byte array
     * @throws IOException if some error happen during the reading operation
     */
    public byte[] readFileAsBytes(File file) throws IOException {
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "r")) {
            byte[] bytes = new byte[(int) accessFile.length()];
            accessFile.readFully(bytes);
            return bytes;
        }
    }

    /**
     * A method used to write to file an array of bytes.
     *
     * @param bytes array of bytes to be written to file
     * @param fileName the name of a file
     * @throws IOException if some error happen during the writing operation
     */
    public void writeFile(byte[] bytes, String fileName) throws IOException {
        File file = new File(fileName);
        try (RandomAccessFile accessFile = new RandomAccessFile(file, "rw")) {
            accessFile.write(bytes);
        }
    }
}
