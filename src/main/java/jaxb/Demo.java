package jaxb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {
	private static final String FILE_NAME = "person.xml";
	private static final Logger logger = LoggerFactory.getLogger(Demo.class);

	public static void main(String[] args) throws JAXBException, FileNotFoundException {
		Demo demo = new Demo();
		Person person = demo.createPerson();
		demo.marshalPerson(person);
		Person loadedPerson = demo.unmarshalPerson();
		assert loadedPerson.equals(person);
		logger.info("The End");
	}

	private Person createPerson() {
		Address address = new Address("..gade 11/1a", "Denmark", "2100");
		Person person = new Person(1, address);
		return person;
	}

	private void marshalPerson(Person person) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
		marshaller.marshal(person, fileOutputStream);
	}

	private Person unmarshalPerson() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Person.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Person) unmarshaller.unmarshal(new File(FILE_NAME));
	}
}