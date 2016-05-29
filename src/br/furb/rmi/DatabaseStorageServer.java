package br.furb.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import br.furb.model.Music;
import br.furb.model.MusicDto;

@SuppressWarnings("serial")
public class DatabaseStorageServer extends UnicastRemoteObject implements DatabaseStorage {

	private static Map<Integer, Music> musicas = new HashMap<>();
	
	static {
		musicas.put(1, new Music(1, "We will rock you", 2.00, "./resources/We will rock you.mp3", "Queen"));
		musicas.put(2, new Music(2, "We are the champions", 3.00, "./resources/We are the champions.mp3", "Queen"));
		musicas.put(3, new Music(3, "Radio gaga", 2.00, "./resources/Radio gaga.mp3", "Queen"));
		musicas.put(4, new Music(4, "I want to break free", 8.00, "./resources/I want to break free.mp3", "Queen"));
		musicas.put(5, new Music(5, "Don't stop me now", 8.00, "./resources/Don't stop me now.mp3", "Queen"));
		musicas.put(6, new Music(6, "A kind of magic", 8.00, "./resources/A kind of magic.mp3", "Queen"));
	}
	
	protected DatabaseStorageServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws MalformedURLException {
		runServer("//localhost/DatabaseStorage");
	}

	@Override
	public Music[] listMusicsByName(String name) {
		ArrayList<Music> matchs = new ArrayList<>();
		for (int i = 1; i <= musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music == null) continue;
			
			if (music.getName().toLowerCase().contains(name.toLowerCase())) {
				matchs.add(music);
			}
		}
		return parseToArray(matchs);  
	}

	@Override
	public Music[] listMusicsByArtist(String artist) {
		ArrayList<Music> musics = new ArrayList<>();
		for (int i = 1; i < musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music.getArtist().contains(artist)) {
				musics.add(music);
			}
		}
		return parseToArray(musics);
	}

	@Override
	public boolean updateMusic(int musicId, MusicDto musicDto) {
		Music music = getMusicById(musicId);
		if (music != null) {
			music = copyFromDto(musicDto, music);
		} else 
			System.out.println("Música não encontrada!");
		return music != null;
	}

	@Override
	public boolean removeMusic(int musicId) {
		Music music = getMusicById(musicId);
		if (music != null) {
			musicas.remove(musicId);
			return true;
		} else 
			return false;
	}
	
	@Override
	public Music getMusicById(int id) {
		for (int i = 1; i < musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music.getId() == id) 
				return music;
		}
		return null;
	}
	
	private Music copyFromDto(MusicDto musicDto, Music music) {
		music.setName(musicDto.getName());
		music.setDuration(musicDto.getDuration());
		music.setLocation(musicDto.getLocation());
		music.setArtist(musicDto.getArtist());
		return music;
	}
	
	private Music[] parseToArray(ArrayList<Music> matchs) {
		Music[] musics = new Music[matchs.size()];
		for (int i = 0; i < matchs.size(); i++) {  
		    musics[i] = matchs.get(i);  
		}
		return musics;
	}
	
	private static void runServer(String url) throws MalformedURLException {
		try {
			DatabaseStorage server = new DatabaseStorageServer();
			Naming.rebind(url, server);
			System.out.println("Servidor RMI no ar!");
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Could not run RMI server!");
			re.printStackTrace();
		}
	}
}
