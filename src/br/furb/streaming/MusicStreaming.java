package br.furb.streaming;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import br.furb.model.Music;
import br.furb.model.MusicDto;
import br.furb.webservice.StreamingInterface;

public class MusicStreaming {
	
	private static final String DEFAULT_PATH = "/home/balena/Música/Queen/";
	private static StreamingInterface clientWS;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		final Scanner in = new Scanner(System.in);
		clientWS = getClientWS();
		int option;
		do {
			option = showMenuPrincipal(in);
			if (option == 0) break;
			
			if (option == 1) 
				listByName(in);
			else if (option == 2) 
				showListInFolder(DEFAULT_PATH);
			else if (option == 3)
				listOtherFolder(in);
			else if (option == 4)
				getMusicSize(in);
			else if (option == 5)
				playMusic(in);
			else if (option == 6)
				updateMusic(in);
			else if (option == 7)
				removeMusic(in);
		} while(option != 0);
	}

	private static void removeMusic(final Scanner in) throws RemoteException {
		println("Digite o id da música a ser alterada/excluída:");
		int id = Integer.parseInt(in.nextLine());
		clientWS.removeMusic(id);
		println("Música removida com sucesso!");
	}

	private static void updateMusic(final Scanner in) throws RemoteException {
		println("Digite o id da música a ser alterada/excluída:");
		int id = Integer.parseInt(in.nextLine());
		MusicDto musicDto = getMusicFromForm(in, clientWS.getMusicById(id));
		if (clientWS.updateMusic(id, musicDto)) {
			println("Música alterada com sucesso!");
		}
	}

	private static void getMusicSize(final Scanner in) {
		println("Digite o caminho para a música:");
		String param = String.valueOf(in.nextLine());
		double size = clientWS.getMusicSize(param);
		println("Tamanho da música: " + size);
	}

	private static void listOtherFolder(final Scanner in)
			throws RemoteException {
		println("Digite o diretório:");
		String param = String.valueOf(in.nextLine());
		showListInFolder(param);
	}

	private static void listByName(final Scanner in) throws RemoteException {
		println("Digite o nome da música que deseja buscar:");
		String param = String.valueOf(in.nextLine());
		showMusicList(clientWS.listMusicsByName(param));
	}

	private static void playMusic(final Scanner in) throws IOException, InterruptedException {
		println("Digite o id da música:");
		int id = Integer.parseInt(in.nextLine());
		Music music = clientWS.getMusicById(id);
		Runtime.getRuntime().exec("gst123 " + music.getLocation() + " &");
	}
	
	private static MusicDto getMusicFromForm(Scanner in, Music music) {
		println("Digite o novo nome da música:");
		String name = String.valueOf(in.nextLine());
		println("Digite o novo local da música:");
		String location = String.valueOf(in.nextLine());
		return new MusicDto(music.getId(), name, location);
	}

	private static void showMusicList(Music[] musics) {
		for (Music music : musics) {
			println(music.toString());
		}
	}
	
	private static void println(String message) {
		System.out.println(message);
	}
	
	private static int showMenuPrincipal(Scanner in) {
		println("------------MENU-----------------");
		println("Escolha a opção que desejar:");
		println("1 - Consultar música por nome;");
		println("2 - Carregar músicas do diretório padrão;");
		println("3 - Carregar músicas de outro diretório;");
		println("4 - Consultar tamanho da música;");
		println("5 - Tocar música;");
		println("6 - Alterar dados da música;");
		println("7 - Excluir música;");
		println("0 - Sair.");
		return Integer.parseInt(in.nextLine());
	}

	private static void showListInFolder(String path) throws RemoteException {
		println(clientWS.listMusicsInFolder(path));
	}
	
	private static StreamingInterface getClientWS() throws MalformedURLException {
		try {
			URL url = new URL("http://127.0.0.1:9876/streaming?wsdl");
		    QName qname = new QName("http://webservice.furb.br/","StreamingImplService");
		    Service ws = Service.create(url, qname);
		    return ws.getPort(StreamingInterface.class);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor WebService!");
		}
		return null;
	}
	
}
