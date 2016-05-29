package br.furb.corba;

import java.io.File;

import br.furb.corba.FileStorage.FileStoragePOA;

public class FileStorageImpl extends FileStoragePOA {

	@Override
	public String getMusicsInFolder(String path) {
		File folder = new File(path);
		StringBuilder strBuilder = new StringBuilder();
		for (File file : folder.listFiles()) {
			strBuilder.append(file.getName()).append("\n");
		}
		return strBuilder.toString();
	}

	@Override
	public double getMusicSize(String path) {
		File file = new File(path);
		return Double.parseDouble(String.valueOf(file.length() / 1000));
	}


}
