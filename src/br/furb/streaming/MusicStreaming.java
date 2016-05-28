package br.furb.streaming;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.furb.model.Music;
import br.furb.webservice.StreamingInterface;

public class MusicStreaming {
	
	private static final String DEFAULT_PATH = "./resources/";
	private static StreamingInterface clientWS;
	
	public static void main(String[] args) throws MalformedURLException, RemoteException {
		final Scanner in = new Scanner(System.in);
		clientWS = getClientWS();
		int option;
		do {
			option = showMenuPrincipal(in);
			if (option == 0) break;
			
			if (option == 1) {
				showMessage("Digite o nome da música que deseja buscar:");
				String param = String.valueOf(in.nextLine());
				showMusicList(clientWS.listMusicsByName(param));
			} else if (option == 2) {
				showMessage("Digite o nome do artista que deseja buscar:");
				String param = String.valueOf(in.nextLine());
				showMusicList(clientWS.listMusicsByArtist(param));
			} else if (option == 3) {
				showListInFolder(DEFAULT_PATH);
			} else if (option == 4) {
				showMessage("Digite o diretório:");
				String param = String.valueOf(in.nextLine());
				showListInFolder(param);
			}
			int secondOption = showMenuConsultas(in);
		} while(option != 0);
	}
	
	private static void showMusicList(Music[] musics) {
		for (Music music : musics) {
			System.out.println(music.toString());
		}
	}
	
	private static void showMessage(String message) {
		System.out.println(message);
	}
	
	private static int showMenuConsultas(Scanner in) {
		System.out.println("------------MENU-----------------");
		System.out.println("1 - Atualizar informações da música;");
		System.out.println("2 - Remover música;");
		System.out.println("0 - Voltar ao menu principal;");
		return Integer.parseInt(in.nextLine());
	}

	private static int showMenuPrincipal(Scanner in) {
		System.out.println("------------MENU-----------------");
		System.out.println("Escolha a opção que desejar:");
		System.out.println("1 - Consultar música por nome;");
		System.out.println("2 - Consultar música por artista;");
		System.out.println("3 - Consultar músicas no diretório padrão;");
		System.out.println("4 - Consultar músicas em outro diretório;");
		System.out.println("0 - Sair.");
		return Integer.parseInt(in.nextLine());
	}

	private static void showListInFolder(String path) {
		for (File file : clientWS.listMusicsInFolder(path)) {
			System.out.println(file.getName());
		}
	}
	
	private static StreamingInterface getClientWS() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:9876/streaming?wsdl");
	    QName qname = new QName("http://webservice.furb.br/","StreamingImplService");
	    Service ws = Service.create(url, qname);
	    return ws.getPort(StreamingInterface.class);
	}
	
}
