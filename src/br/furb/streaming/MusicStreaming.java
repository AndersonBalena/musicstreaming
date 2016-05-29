package br.furb.streaming;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import br.furb.model.Music;
import br.furb.model.MusicDto;
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
				println("Digite o nome da música que deseja buscar:");
				String param = String.valueOf(in.nextLine());
				showMusicList(clientWS.listMusicsByName(param));
			} else if (option == 2) {
				println("Digite o nome do artista que deseja buscar:");
				String param = String.valueOf(in.nextLine());
				showMusicList(clientWS.listMusicsByArtist(param));
			} else if (option == 3) {
				showListInFolder(DEFAULT_PATH);
			} else if (option == 4) {
				println("Digite o diretório:");
				String param = String.valueOf(in.nextLine());
				showListInFolder(param);
			} else if (option == 5) {
				println("Digite o caminho para a música:");
				String param = String.valueOf(in.nextLine());
				double size = clientWS.getMusicSize(param);
				println("Tamanho da música: " + size);
			}
			int secondOption = showMenuConsultas(in);
			if (secondOption == 1 || secondOption == 2) {
				println("Digite o id da música a ser alterada/excluída:");
				int id = Integer.parseInt(in.nextLine());
				if (secondOption == 1) {
					MusicDto musicDto = getMusicFromForm(in, clientWS.getMusicById(id));
					if (clientWS.updateMusic(id, musicDto)) {
						println("Música alterada com sucesso!");
					} 
				} else if (secondOption == 2) {
					clientWS.removeMusic(id);
					println("Música removida com sucesso!");
				}
			}
				
		} while(option != 0);
	}
	
	private static MusicDto getMusicFromForm(Scanner in, Music music) {
		println("Digite o novo nome da música:");
		String name = String.valueOf(in.nextLine());
		println("Digite o novo tempo de duração da música:");
		double duration = Double.parseDouble(in.nextLine());
		println("Digite o novo local da música:");
		String location = String.valueOf(in.nextLine());
		println("Digite o nome do novo artista da música:");
		String artist = String.valueOf(in.nextLine());
		return new MusicDto(music.getId(), name, duration, location, artist);
	}

	private static void showMusicList(Music[] musics) {
		for (Music music : musics) {
			println(music.toString());
		}
	}
	
	private static void println(String message) {
		System.out.println(message);
	}
	
	private static int showMenuConsultas(Scanner in) {
		println("------------MENU-----------------");
		println("1 - Atualizar informações da música;");
		println("2 - Remover música;");
		println("0 - Voltar ao menu principal;");
		return Integer.parseInt(in.nextLine());
	}

	private static int showMenuPrincipal(Scanner in) {
		println("------------MENU-----------------");
		println("Escolha a opção que desejar:");
		println("1 - Consultar música por nome;");
		println("2 - Consultar música por artista;");
		println("3 - Consultar músicas no diretório padrão;");
		println("4 - Consultar músicas em outro diretório;");
		println("5 - Consultar tamanho da música;");
		println("0 - Sair.");
		return Integer.parseInt(in.nextLine());
	}

	private static void showListInFolder(String path) {
		println(clientWS.listMusicsInFolder(path));
	}
	
	private static StreamingInterface getClientWS() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:9876/streaming?wsdl");
	    QName qname = new QName("http://webservice.furb.br/","StreamingImplService");
	    Service ws = Service.create(url, qname);
	    return ws.getPort(StreamingInterface.class);
	}
	
}
