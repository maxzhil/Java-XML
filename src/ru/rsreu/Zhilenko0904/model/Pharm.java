package ru.rsreu.Zhilenko0904.model;

public class Pharm {

	private Certificate certificat;
	private Package packet;
	private Dosage dosage;

	public Pharm() {

	}

	public Pharm(Certificate certificate, Package packet, Dosage dosage) {
		this.certificat = certificate;
		this.packet = packet;
		this.dosage = dosage;
	}

	public Certificate getCertificat() {
		return certificat;
	}

	public void setCertificat(Certificate certificat) {
		this.certificat = certificat;
	}

	public Package getPacket() {
		return packet;
	}

	public void setPacket(Package packet) {
		this.packet = packet;
	}

	public Dosage getDosage() {
		return dosage;
	}

	public void setDosage(Dosage dosage) {
		this.dosage = dosage;
	}

	@Override
	public String toString() {
		return "Pharm [certificat=" + certificat + ", packet=" + packet
				+ ", dosage=" + dosage + "]";
	}

}
