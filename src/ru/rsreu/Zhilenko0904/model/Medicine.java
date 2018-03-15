package ru.rsreu.Zhilenko0904.model;

import java.util.ArrayList;
import java.util.List;

public class Medicine {

	private int id;
	private String name;
	private Pharm pharm;
	private Group group;
	private List<String> analogs;
	private Versions versions;

	public Medicine() {
		analogs = new ArrayList<String>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pharm getPharm() {
		return pharm;
	}

	public void setPharm(Pharm pharm) {
		this.pharm = pharm;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<String> getAnalogs() {
		return analogs;
	}

	public void setAnalogs(List<String> analogs) {
		this.analogs = analogs;
	}

	public Versions getVersions() {
		return versions;
	}

	public void setVersions(Versions versions) {
		this.versions = versions;
	}

	public void addAnalog(String analog) {
		analogs.add(analog);

	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", pharm=" + pharm
				+ ", group=" + group + ", analogs=" + analogs.toString()
				+ ", versions=" + versions + "]";
	}

}
