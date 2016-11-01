package io.rulai.core.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	public static void createDir(String dir) {
		File d = new File(dir);
		if (!d.exists()) {
			d.mkdirs();
		}
	}

	public static String[] readLines(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> res = new ArrayList<String>();
		try {
			String line = br.readLine();

			while (line != null) {
				res.add(line);
				line = br.readLine();
			}
			return res.toArray(new String[0]);
		} finally {
			br.close();
		}
	}

	public static void writeLines(String[] lines, String file)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));

		for (int i = 0, n = lines.length; i < n; i++) {
			bw.write(lines[i]);
			if (i < (n - 1)) {
				bw.write("\n");
			}
		}

		bw.close();
	}

	public static void writeTextContents(String content, String file)
			throws IOException {
		byte[] data = content.getBytes("utf-8");
		writeFileContent(data, file);
	}

	public static String readTextContents(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	public static byte[] readFileContent(String absFilePath) throws IOException {
		File fp = new File(absFilePath);
		FileInputStream fis = new FileInputStream(fp);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		byte[] rdfr = new byte[1024];
		int r = -1;
		while ((r = fis.read(rdfr, 0, rdfr.length)) != -1) {
			baos.write(rdfr, 0, r);
		}
		fis.close();
		baos.flush();
		baos.close();
		return baos.toByteArray();
	}

	public static void writeFileContent(byte[] data, String absFilePath)
			throws IOException {
		File fp = new File(absFilePath);
		FileOutputStream fis = new FileOutputStream(fp);
		ByteArrayInputStream baos = new ByteArrayInputStream(data);
		byte[] rdfr = new byte[1024];
		int r = -1;
		while ((r = baos.read(rdfr, 0, rdfr.length)) != -1) {
			fis.write(rdfr, 0, r);
		}
		baos.close();
		fis.flush();
		fis.close();
	}

	public static byte[] inputStreamToByteArray(InputStream is)
			throws IOException {
		byte[] bbfr = new byte[512];
		ByteArrayOutputStream bbos = new ByteArrayOutputStream(512);
		int l = 0;
		while ((l = is.read(bbfr, 0, bbfr.length)) >= 0) {
			bbos.write(bbfr, 0, l);
		}
		bbos.flush();
		return bbos.toByteArray();
	}
}
