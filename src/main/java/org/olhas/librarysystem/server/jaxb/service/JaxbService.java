package org.olhas.librarysystem.server.jaxb.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.olhas.librarysystem.models.Library;

import java.io.File;

public class JaxbService {
    /**
     * Prefix of the relative path to XML file.
     */
    private static final String XML_FILEPATH_PREFIX = "src/main/resources/output/bytes/";

    /**
     * Transforms passed {@link Library} object to XML and outputs it to the console.
     *
     * @param object {@link Library} object to be marshalled
     * @throws JAXBException if some error happens
     */
    public void transformToXML(Library object) throws JAXBException {
        /* init jaxb marshaller */
        JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        /* set this flag to true to format the output */
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        /* marshalling of java object into xml (output to standard output) */
        jaxbMarshaller.marshal(object, System.out);
    }

    /**
     * Transforms given XML file to {@link Library} object.
     * If the file doesn't already exist, then it will be automatically created.
     *
     * @param xmlFile XML file to be unmarshalled into {@link Library} object.
     * @return {@link Library} object
     * @throws JAXBException if some error happens
     */
    public Library convertToPOJO(String xmlFile) throws JAXBException {

        File file = new File(XML_FILEPATH_PREFIX + xmlFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Library library = (Library) jaxbUnmarshaller.unmarshal(file);

        return library;
    }
}
