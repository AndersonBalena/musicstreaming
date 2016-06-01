package br.furb.corba;

import java.io.File;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import br.furb.corba.FileStorage.FileStoragePOA;
import br.furb.rmi.DatabaseStorage;
import br.furb.streaming.MusicStreaming;

public class FileStorageImpl extends FileStoragePOA {

	private DatabaseStorage databaseStorage;
	
	public FileStorageImpl() throws MalformedURLException, RemoteException, NotBoundException {
		databaseStorage = ClientRmi.getClient("//localhost/DatabaseStorage");
	}
	
	@Override
	public String getMusicsInFolder(String path) throws RemoteException {
		System.out.println("Lendo músicas no diretório " + path);
		MusicStreaming.printTime();
		
		File folder = new File(path);
		System.out.println(folder);
		StringBuilder strBuilder = new StringBuilder();
		for (File file : folder.listFiles()) {
			System.out.println("Lendo " + file.getName());
			databaseStorage.addMusic(file);
			strBuilder.append(file.getName()).append("\n");
		}
		System.out.println("Fechando leitura de músicas na pasta " + path);
		return strBuilder.toString();
	}

	@Override
	public double getMusicSize(String path) {
		File file = new File(path);
		return Double.parseDouble(String.valueOf(file.length() / 1000));
	}


}
