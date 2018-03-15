package ru.rsreu.Zhilenko0904.writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ru.rsreu.Zhilenko0904.FileWorker;
import ru.rsreu.Zhilenko0904.Resourcer;
import ru.rsreu.Zhilenko0904.model.Medicine;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Calendar;
import java.util.List;

public class HtmlWriter {

	private static final String INDENT = "{http://xml.apache.org/xslt}indent-amount";
	private Document document;

	public HtmlWriter() {
	}

	private Document createEmptyDocument() throws ParserConfigurationException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();
		documentFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		return documentBuilder.newDocument();
	}

	private Transformer createTransformer()
			throws TransformerConfigurationException {

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING,
				Resourcer.getString("writer.encoding"));
		transformer.setOutputProperty(INDENT,
				Resourcer.getString("writer.indent"));
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		return transformer;
	}

	private Element createHead() {
		Element head = document.createElement("head");
		Element title = document.createElement("title");
		title.setTextContent("Flowers");
		Element style = document.createElement("style");
		style.setTextContent("td, th {" + "    text-align: center;\n"
				+ "    padding: 10px;\n" + "    vertical-align: middle;" + "}"
				+ "h1 { text-align: center; }"
				+ "table { width: 100%;border-collapse: collapse; }");
		head.appendChild(style);
		head.appendChild(title);
		return head;
	}

	private Element createBody(List<Medicine> elements)
			throws TransformerException, ParserConfigurationException {
		Element element = createTable(elements);
		Element h1 = document.createElement("h1");
		h1.setTextContent("Flowers");
		Element body = document.createElement("body");
		body.appendChild(h1);
		body.appendChild(element);
		return body;
	}

	private void pushTh(Element tr, String text, String rowspan, String colspan) {
		Element th = document.createElement("th");
		th.setAttribute("rowspan", rowspan);
		th.setAttribute("colspan", colspan);
		th.setTextContent(text);
		tr.appendChild(th);
	}

	private void createTableHeader(Document document, Element table) {
		Element topTr = document.createElement("tr");
		Element bottomTr = document.createElement("tr");
		Element middleTr = document.createElement("tr");
		pushTh(topTr, "Name", "3", "1");
		pushTh(topTr, "Pharm", "1", "8");
		pushTh(middleTr, "Certificate", "1", "4");
		pushTh(bottomTr, "Numder", "1", "1");
		pushTh(bottomTr, "Date of issue", "1", "1");
		pushTh(bottomTr, "Expiration date", "1", "1");
		pushTh(bottomTr, "Organization", "1", "1");
		pushTh(middleTr, "Package", "1", "3");
		pushTh(bottomTr, "Type", "1", "1");
		pushTh(bottomTr, "Count", "1", "1");
		pushTh(bottomTr, "Price", "1", "1");
		pushTh(middleTr, "Dosage", "1", "1");
		pushTh(bottomTr, "Frequency of admission", "1", "1");
		pushTh(topTr, "Group", "3", "1");
		pushTh(topTr, "Analogs", "3", "1");
		pushTh(topTr, "Versions", "3", "1");

		table.appendChild(topTr);
		table.appendChild(middleTr);
		table.appendChild(bottomTr);
	}

	private Element createTable(List<Medicine> medicines)
			throws ParserConfigurationException, TransformerException {
		Element table = document.createElement("table");
		table.setAttribute("border", "1");
		createTableHeader(document, table);

		for (Medicine medicine : medicines) {
			table.appendChild(createMedicineTr(document, medicine));
		}

		return table;
	}

	private void pushMedicineTd(Element tr, String value) {
		Element td = document.createElement("td");
		td.setTextContent(value);
		tr.appendChild(td);
	}

	private Element createMedicineTr(Document document, Medicine medicine) {
		Element tr = document.createElement("tr");
		tr.setAttribute("id", Integer.toString(medicine.getId()));

		pushMedicineTd(tr, medicine.getName());
		pushMedicineTd(
				tr,
				Integer.toString(medicine.getPharm().getCertificat()
						.getNumber()));
		pushMedicineTd(
				tr,
				Integer.toString(medicine.getPharm().getCertificat()
						.getDateOfIssue().get(Calendar.YEAR)));
		pushMedicineTd(
				tr,
				Integer.toString(medicine.getPharm().getCertificat()
						.getExpirationDate().get(Calendar.YEAR)));
		pushMedicineTd(tr, medicine.getPharm().getCertificat()
				.getOrganization());
		pushMedicineTd(tr, medicine.getPharm().getPacket().getType());
		pushMedicineTd(tr,
				Integer.toString(medicine.getPharm().getPacket().getCount()));
		pushMedicineTd(tr,
				Integer.toString(medicine.getPharm().getPacket().getPrice()));
		pushMedicineTd(
				tr,
				Integer.toString(medicine.getPharm().getDosage()
						.getFrequencyOfAdmission()));
		pushMedicineTd(tr, medicine.getGroup().toString());
		pushMedicineTd(tr, FileWorker.getList(medicine.getAnalogs()));
		pushMedicineTd(tr, medicine.getVersions().toString());
		return tr;
	}

	private void saveDocument(Document document, String path)
			throws TransformerException {
		Transformer transformer = createTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	public void write(List<Medicine> elements, String path) throws Exception {
		this.document = createEmptyDocument();
		Element html = document.createElement("html");
		html.appendChild(createHead());
		html.appendChild(createBody(elements));
		document.appendChild(html);
		saveDocument(document, path);
	}
}
