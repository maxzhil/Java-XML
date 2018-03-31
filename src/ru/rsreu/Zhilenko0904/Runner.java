package ru.rsreu.Zhilenko0904;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import ru.rsreu.Zhilenko0904.model.Medicine;
import ru.rsreu.Zhilenko0904.writer.HtmlWriter;
import ru.rsreu.Zhilenko0904.writer.XMLWriter;
import ru.rsreu.Zhilenko0904.xml.parser.XmlSAXParser;

public class Runner {

	private Runner() {
	}

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		String xsdPath = Resourcer.getString("runner.xsd");
		String xmlPath = Resourcer.getString("runner.xml");
		String xmlOutput = Resourcer.getString("runner.output.xml");
		String htmlOutput = Resourcer.getString("runner.output.html");

		XmlSAXParser parser = new XmlSAXParser();
		try {
			parser.validate(xmlPath, xsdPath);
			System.out.println(Resourcer
					.getString("runner.message.validate.true"));
		} catch (Exception e) {
			System.out.println(Resourcer
					.getString("runner.message.validate.false"));
			e.printStackTrace();
		}
		String xmlString = null;
		try {
			xmlString = FileWorker.readFile(xmlPath);
		} catch (IOException e) {
			System.out
					.println(Resourcer.getString("runner.message.read.false"));
			e.printStackTrace();
		}
		sb.append("\n").append(Resourcer.getString("runner.message.xmlFile"))
				.append('\n').append(xmlString)
				.append(Resourcer.getString("runner.message.objects"))
				.append('\n');
		List<Medicine> medications = null;
		try {
			medications = parser.parseString(xmlString);
		} catch (Exception e) {

			e.printStackTrace();
		}
		sb.append(FileWorker.getList(medications)).append('\n')
				.append(Resourcer.getString("runner.message.sorted"))
				.append('\n');
		Collections.sort(medications, Comparators.sortByPrice);
		sb.append(FileWorker.getList(medications)).append('\n');
		HtmlWriter htmlWriter = new HtmlWriter();
		try {
			htmlWriter.write(medications, htmlOutput);
			sb.append(Resourcer.getString("runner.message.write.html.true"))
					.append('\n');
		} catch (Exception e) {
			System.out.println(Resourcer
					.getString("runner.message.write.html.false"));
			e.printStackTrace();
		}
		XMLWriter xmlWriter = new XMLWriter();
		try {
			xmlWriter.write(medications, xmlOutput);
			sb.append(Resourcer.getString("runner.message.write.xml.true"))
					.append('\n');
		} catch (Exception e) {
			System.out.println(Resourcer
					.getString("runner.message.write.xml.false"));
			e.printStackTrace();
		}

		try {
			parser.validate(xmlOutput, xsdPath);
			sb.append(Resourcer.getString("runner.message.validate.true"))
					.append('\n');
		} catch (Exception e) {
			System.out.println(Resourcer
					.getString("runner.message.validate.false"));
			e.printStackTrace();
		}
		String xmlOutputString = null;
		try {
			xmlOutputString = FileWorker.readFile(xmlOutput);
		} catch (IOException e) {
			System.out
					.println(Resourcer.getString("runner.message.read.false"));
			e.printStackTrace();
		}
		sb.append("\n").append(Resourcer.getString("runner.message.xmlFile"))
				.append('\n').append(xmlOutputString);
		System.out.println(sb.toString());
	}
}
