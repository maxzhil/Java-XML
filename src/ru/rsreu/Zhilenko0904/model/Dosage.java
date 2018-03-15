package ru.rsreu.Zhilenko0904.model;

public class Dosage {
	private int frequencyOfAdmission;

	public Dosage() {

	}

	public Dosage(int frequencyOfAdmission) {
		this.frequencyOfAdmission = frequencyOfAdmission;
	}

	public int getFrequencyOfAdmission() {
		return frequencyOfAdmission;
	}

	public void setFrequencyOfAdmission(int frequencyOfAdmission) {
		this.frequencyOfAdmission = frequencyOfAdmission;
	}

	@Override
	public String toString() {
		return "Dosage [frequencyOfAdmission=" + frequencyOfAdmission
				+ " in a day]";
	}

}
