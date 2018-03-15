package ru.rsreu.Zhilenko0904;

import java.util.ResourceBundle;

public class Resourcer {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.config");

	private Resourcer() {
	}

	public static String getString(String key) {
		return resourceBundle.getString(key);
	}
}
