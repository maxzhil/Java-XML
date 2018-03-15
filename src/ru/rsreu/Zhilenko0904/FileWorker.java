package ru.rsreu.Zhilenko0904;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileWorker {

	private FileWorker() {

	}

	public static String readFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try {
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line).append(
						Resourcer.getString("fileWorker.separator"));
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static String getList(List<?> objects) {
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {
			sb.append(object).append(
					Resourcer.getString("fileWorker.separator"));
		}
		return sb.toString();
	}

}
