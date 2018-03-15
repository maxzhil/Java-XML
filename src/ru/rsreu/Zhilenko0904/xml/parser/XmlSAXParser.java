package ru.rsreu.Zhilenko0904.xml.parser;

import ru.rsreu.Zhilenko0904.model.Medicine;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class XmlSAXParser {
	public List<Medicine> parse(String xmlPath) throws Exception {
		SAXParser parser = createSAXParser();
		SAXMedicineParser saxMedicineParser = new SAXMedicineParser();
		parser.parse(new File(xmlPath), saxMedicineParser);
		return saxMedicineParser.getMedications();
	}

	public List<Medicine> parseString(String xmlString) throws Exception {
		SAXParser parser = createSAXParser();
		InputStream source = new ByteArrayInputStream(xmlString.getBytes());
		SAXMedicineParser saxMedicineParser = new SAXMedicineParser();
		parser.parse(source, saxMedicineParser);
		return saxMedicineParser.getMedications();
	}

	private SAXParser createSAXParser() throws ParserConfigurationException,
			SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		return factory.newSAXParser();
	}

	public void validate(String xmlPath, String xsdPath) throws SAXException,
			IOException {
		SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
				.newSchema(new StreamSource(xsdPath)).newValidator()
				.validate(new StreamSource(xmlPath));
	}

}
