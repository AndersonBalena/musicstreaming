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
		musicas.put(1, new Music(1, "Foo - Paranoid - Black Sabbath", 2.00, "./src/resources/Saideira.mp3", "Artist 1"));
		musicas.put(2, new Music(2, "Smoke on the Water - Deep Purple", 3.00, "./src/resources/Saideira.mp3", "Artist 2"));
		musicas.put(3, new Music(3, "Foo - Bark at the Moon - Ozzy Osbourne", 2.00, "./src/resources/Saideira.mp3", "Artist 3"));
		musicas.put(4, new Music(4, "Foo - Saideira - Skank", 8.00, "./src/resources/Saideira.mp3", "Artist 4"));
	}
	
	protected DatabaseStorageServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws MalformedURLException {
		runServer("//localhost/MusicStorage");
	}

	@Override
	public ArrayList<Music> listMusicsByName(String name) {
		ArrayList<Music> musics = new ArrayList<>();
		for (int i = 1; i < musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music.getName().contains(name)) {
				musics.add(music);
			}
		}
		return musics;
	}

	@Override
	public ArrayList<Music> listMusicsByArtist(String artist) {
		ArrayList<Music> musics = new ArrayList<>();
		for (int i = 1; i < musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music.getArtist().contains(artist)) {
				musics.add(music);
			}
		}
		return musics;
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
	public boolean removeMusic(Music music) {
		try {
			musicas.remove(music);
			return true;
		} catch (Exception e) {
			System.out.println("Não removeu a música");
			return false;
		}
		
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
	
	private static void runServer(String url) throws MalformedURLException {
		try {
			DatabaseStorage server = new DatabaseStorageServer();
			Naming.rebind(url, server);
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Could not run RMI server!");
			re.printStackTrace();
		}
	}
}
