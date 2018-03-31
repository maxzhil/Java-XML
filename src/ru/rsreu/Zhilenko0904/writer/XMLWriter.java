package ru.rsreu.Zhilenko0904.writer;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.rsreu.Zhilenko0904.Resourcer;
import ru.rsreu.Zhilenko0904.model.Medicine;

public class XMLWriter {
	private static final String INDENT = "{http://xml.apache.org/xslt}indent-amount";
	private static final String XML_NAMESPACE = "xmlns";
	Document document;

	public XMLWriter() {

	}

	public void write(List<Medicine> medications, String path) throws Exception {
		document = createEmptyDocument();
		Element element = createMedicationsElement(medications);
		element.setAttribute(XML_NAMESPACE,
				Resourcer.getString("xml.namespace"));
		document.appendChild(element);
		saveDocument(path, document);
	}

	private Document createEmptyDocument() throws ParserConfigurationException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();
		documentFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		return documentBuilder.newDocument();
	}

	private Element createMedicationsElement(List<Medicine> medications) {
		Element medicineElement = document.createElement(Resourcer
				.getString("tag.medications"));

		for (Medicine medicine : medications) {
			medicineElement.appendChild(createMedicineElement(medicine));
		}
		return medicineElement;
	}

	private Element createComplexElement(String tagName) {
		Element element = document.createElement(tagName);
		return element;
	}

	private void initializeCertificateElement(Element certificateElement,
			Medicine medicine) {
		createSimpleElement(
				certificateElement,
				Resourcer.getString("tag.number"),
				Integer.toString(medicine.getPharm().getCertificat()
						.getNumber()));
		createSimpleElement(
				certificateElement,
				Resourcer.getString("tag.dateOfIssue"),
				Integer.toString(medicine.getPharm().getCertificat()
						.getDateOfIssue().get(Calendar.YEAR)));
		createSimpleElement(
				certificateElement,
				Resourcer.getString("tag.expirationDate"),
				Integer.toString(medicine.getPharm().getCertificat()
						.getExpirationDate().get(Calendar.YEAR)));
		createSimpleElement(certificateElement,
				Resourcer.getString("tag.organization"), medicine.getPharm()
						.getCertificat().getOrganization());
	}

	private void initializePacketElement(Element packetElement,
			Medicine medicine) {
		createSimpleElement(packetElement, Resourcer.getString("tag.type"),
				medicine.getPharm().getPacket().getType());
		createSimpleElement(packetElement, Resourcer.getString("tag.count"),
				Integer.toString(medicine.getPharm().getPacket().getCount()));
		createSimpleElement(packetElement, Resourcer.getString("tag.price"),
				Integer.toString(medicine.getPharm().getPacket().getPrice()));
	}

	private void initializePharmElement(Element pharmElement, Medicine medicine) {

		Element certificateElement = createComplexElement(Resourcer
				.getString("tag.certificate"));
		initializeCertificateElement(certificateElement, medicine);

		pharmElement.appendChild(certificateElement);
		Element packetElement = createComplexElement(Resourcer
				.getString("tag.packet"));
		initializePacketElement(packetElement, medicine);

		pharmElement.appendChild(packetElement);
		Element dosageElement = createComplexElement(Resourcer
				.getString("tag.dosage"));
		createSimpleElement(
				dosageElement,
				Resourcer.getString("tag.frequencyOfAdmission"),
				Integer.toString(medicine.getPharm().getDosage()
						.getFrequencyOfAdmission()));
		pharmElement.appendChild(dosageElement);
	}

	private void initializeanalogsElement(Element analogsElement,
			Medicine medicine) {
		for (String value : medicine.getAnalogs()) {
			createSimpleElement(analogsElement,
					Resourcer.getString("tag.analog"), value);
		}
	}

	private void createSimpleElement(Element parent, String tagName, String text) {
		Element element = document.createElement(tagName);
		element.setTextContent(text);
		parent.appendChild(element);
	}

	private Element createMedicineElement(Medicine medicine) {
		Element medicationElement = document.createElement(Resourcer
				.getString("tag.medicine"));
		medicationElement
				.setAttribute("id", Integer.toString(medicine.getId()));

		createSimpleElement(medicationElement, Resourcer.getString("tag.name"),
				medicine.getName());
		Element pharmElement = createComplexElement(Resourcer
				.getString("tag.pharm"));
		initializePharmElement(pharmElement, medicine);

		medicationElement.appendChild(pharmElement);

		createSimpleElement(medicationElement,
				Resourcer.getString("tag.group"), medicine.getGroup()
						.toString());
		Element analogsElement = createComplexElement(Resourcer
				.getString("tag.analogs"));
		initializeanalogsElement(analogsElement, medicine);
		medicationElement.appendChild(analogsElement);

		createSimpleElement(medicationElement,
				Resourcer.getString("tag.versions"), medicine.getVersions()
						.toString());

		return medicationElement;
	}

	private void saveDocument(String path, Document document)
			throws TransformerException {
		Transformer transformer = createTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	private Transformer createTransformer()
			throws TransformerConfigurationException {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING,
				Resourcer.getString("writer.encoding"));
		transformer.setOutputProperty(INDENT,
				Resourcer.getString("xml.indent.amount"));
		transformer.setOutputProperty(OutputKeys.INDENT,
				Resourcer.getString("xml.indent"));
		return transformer;
	}
}
