package ru.rsreu.Zhilenko0904.xml.parser;

public enum Tag {
	MEDICINE("Medicine"), NAME("name"), PHARM("pharm"), GROUP("group"), VERSIONS(
			"versions"), ANALOGS("analogs"), ANALOG("analog"), PACKAGE(
			"package"), TYPE("type"), COUNT("count"), PRICE("price"), CERTIFICATE(
			"certificate"), NUMBER("number"), DATEOFISSUE("date-of-issue"), EXPIRATIONDATE(
			"expiration-date"), ORGANIZATION("organization"), DOSAGE("dosage"), FREQUENCYOFADMISSION(
			"frequency-of-admission"), EMPTY("");
	public String value;

	private Tag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
