package org.compression;

public interface Compression {
	public String encode(String plainText);

	public String decode(String compressText);
}
