package org.compression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		RePair compression = new RePair();

		File dir = new File("data");
		for (File file : dir.listFiles()) {
			try {
				String text = readFile(file.getAbsolutePath());
				byte[] compressed = compression.encode(text.getBytes());
				byte[] back = compression.decode(compressed);
				System.out.println("Soubor: " + file.getName() + ", Ration: "
						+ ((double) compressed.length / text.getBytes().length)
						* 100.0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static boolean isSame(byte[] b1, byte[] b2) {
		if (b1.length != b2.length) {
			System.out.println(b1.length + " vs " + b2.length);
			return false;
		}
		for (int i = 0; i < b1.length; i++)
			if (b1[i] != b2[i])
				return false;
		return true;
	}

	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
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
}
