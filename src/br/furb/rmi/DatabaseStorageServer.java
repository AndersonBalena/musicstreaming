package br.furb.rmi;

import java.io.File;
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
import br.furb.streaming.MusicStreaming;

@SuppressWarnings("serial")
public class DatabaseStorageServer extends UnicastRemoteObject implements DatabaseStorage {

	private static Map<Integer, Music> musicas = new HashMap<>();
	
	protected DatabaseStorageServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) throws MalformedURLException {
		runServer("//localhost/DatabaseStorage");
	}

	@Override
	public void addMusic(File file) {
		musicas.put(musicas.size(), new Music(musicas.size(), file.getName(), file.getAbsolutePath().toString()));
		System.out.println("Music: " + file.getName() + " adicionada à base.");
	}
	
	@Override
	public Music[] listMusicsByName(String name) {
		ArrayList<Music> matchs = new ArrayList<>();
		for (int i = 0; i <= musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music == null) continue;
			
			if (music.getName().toLowerCase().contains(name.toLowerCase())) {
				matchs.add(music);
			}
		}
		return parseToArray(matchs);  
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
		for (int i = 0; i < musicas.size(); i++) {
			Music music = musicas.get(i);
			if (music.getId() == id) 
				return music;
		}
		return null;
	}
	
	private Music copyFromDto(MusicDto musicDto, Music music) {
		music.setName(musicDto.getName());
		music.setLocation(musicDto.getLocation());
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
			MusicStreaming.printTime();
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Could not run RMI server!");
			re.printStackTrace();
		}
	}
}
