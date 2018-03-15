package ru.rsreu.Zhilenko0904.model;

import java.util.Calendar;

public class Certificate {
	private int number;
	private Calendar dateOfIssue;
	private Calendar expirationDate;
	private String organization;

	public Certificate() {

	}

	public Certificate(int number, Calendar dateOfIssue,
			Calendar expirationDate, String organization) {
		this.number = number;
		this.dateOfIssue = dateOfIssue;
		this.expirationDate = expirationDate;
		this.organization = organization;

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Calendar getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(Calendar dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Certificate [number=" + number + ", dateOfIssue="
				+ dateOfIssue.get(Calendar.YEAR) + ", expirationDate="
				+ expirationDate.get(Calendar.YEAR) + ", organization="
				+ organization + "]";
	}

}
