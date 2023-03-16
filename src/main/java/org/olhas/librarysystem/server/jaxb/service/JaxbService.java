package org.olhas.librarysystem.server.jaxb.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.olhas.librarysystem.models.Library;

import javax.xml.transform.Result;
import java.io.File;
import java.io.StringWriter;

/**
 * Service used to provide marshalling/unmarshalling operations.
 */
public class JaxbService {
    /**
     * Transforms passed {@link Library} object to XML and outputs it to the console.
     *
     * @param object {@link Library} object to be marshalled
     * @return a string with XML representation of passed {@link Library} object.
     * @throws JAXBException if some error happens
     */
    public String transformToXML(Library object) throws JAXBException {
        /* init jaxb marshaller */
        JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        /* set this flag to true to format the output */
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        /* save the result of marshalling into StringWriter and return it as String */
        StringWriter writer = new StringWriter();
        jaxbMarshaller.marshal(object, writer);

        return writer.toString();
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

        File file = new File(xmlFile);
        JAXBContext jaxbContext = JAXBContext.newInstance(Library.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Library library = (Library) jaxbUnmarshaller.unmarshal(file);

        return library;
    }
}
