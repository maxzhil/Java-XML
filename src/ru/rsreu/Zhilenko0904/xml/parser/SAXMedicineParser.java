package ru.rsreu.Zhilenko0904.xml.parser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ru.rsreu.Zhilenko0904.model.*;
import ru.rsreu.Zhilenko0904.model.Package;

public class SAXMedicineParser extends DefaultHandler {
	private static final String EMPTY_STRING = "";

	private List<Medicine> medications;
	private Medicine medicine;
	private Pharm pharm;
	private Certificate certificate;
	private Package packet;
	private Dosage dosage;
	private String tagName = EMPTY_STRING;
	private String attribute = EMPTY_STRING;
	private Calendar calendar = Calendar.getInstance();
	private Tag tag = null;

	public SAXMedicineParser() {
	}

	@Override
	public void startDocument() throws SAXException {
		medications = new ArrayList<Medicine>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagName = qName;
		if (attributes.getLength() != 0) {
			attribute = attributes.getValue(0);
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		tagName = EMPTY_STRING;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length).trim();

		tag = checkTag(tagName);

		switch (tag) {
		case MEDICINE:
			medicine = new Medicine();
			medicine.setId(Integer.parseInt(attribute));
			break;
		case NAME:
			medicine.setName(value);
			break;
		case PHARM:
			pharm = new Pharm();
			break;
		case CERTIFICATE:
			certificate = new Certificate();
			break;
		case NUMBER:
			certificate.setNumber(Integer.parseInt(value));
			break;
		case DATEOFISSUE:
			calendar.set(Calendar.YEAR, Integer.parseInt(value));
			certificate.setDateOfIssue(calendar);
			break;
		case EXPIRATIONDATE:
			calendar.set(Calendar.YEAR, Integer.parseInt(value));
			certificate.setExpirationDate(calendar);
			break;
		case ORGANIZATION:
			certificate.setOrganization(value);
			pharm.setCertificat(certificate);
			certificate = null;
		case PACKAGE:
			packet = new Package();
			break;
		case TYPE:
			packet.setType(value);
			break;
		case COUNT:
			packet.setCount(Integer.parseInt(value));
			break;
		case PRICE:
			packet.setPrice(Integer.parseInt(value));
			pharm.setPacket(packet);
			packet = null;
			break;
		case DOSAGE:
			dosage = new Dosage();
			break;
		case FREQUENCYOFADMISSION:
			dosage.setFrequencyOfAdmission(Integer.parseInt(value));
			pharm.setDosage(dosage);
			dosage = null;
			medicine.setPharm(pharm);
			break;
		case GROUP:
			medicine.setGroup(Group.valueOf(value));
			break;
		case ANALOG:
			medicine.addAnalog(value);
			break;
		case VERSIONS:
			medicine.setVersions(Versions.valueOf(value));
			medications.add(medicine);
			medicine = null;
			break;
		default:
			break;
		}
	}

	private Tag checkTag(String tagName) {
		for (Tag tag : Tag.values()) {
			if (tag.value.equals(tagName)) {
				return tag;
			}
		}
		return Tag.EMPTY;
	}

	public List<Medicine> getMedications() {
		return medications;
	}
}
