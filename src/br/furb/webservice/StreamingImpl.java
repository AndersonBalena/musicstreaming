package br.furb.webservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.swing.JOptionPane;

import br.furb.model.Music;
import br.furb.model.MusicDto;
import br.furb.rmi.DatabaseStorage;
import br.furb.webservice.clientrmi.RmiClient;

@WebService(endpointInterface = "br.furb.webservice.StreamingInterface")
public class StreamingImpl implements StreamingInterface {
    
	private DatabaseStorage databaseStorage;
	private Music lastPlayedMusic;
	
	public StreamingImpl() throws MalformedURLException, NotBoundException {
		try {
			databaseStorage = RmiClient.getClient("//localhost/MusicStorage");
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Could not connect to RMI server!");
		}
	}
	
	
	@Override
	public Music getLastPlayedMusic() {
		return this.lastPlayedMusic;
	}
	
	@Override
	public ArrayList<Music> listMusicsByName(String name) {
		return databaseStorage.listMusicsByName(name);
	}

	@Override
	public ArrayList<Music> listMusicsByArtist(String artist) {
		return databaseStorage.listMusicsByArtist(artist);
	}

	@Override
	public String getMusicID3(Music music) {
		// TODO Implementar chamada do método do corba
		return null;
	}

	@Override
	public boolean updateMusic(int musicId, MusicDto musicDto) {
		return databaseStorage.updateMusic(musicId, musicDto);
	}

	@Override
	public boolean removeMusic(Music music) {
		return databaseStorage.removeMusic(music);
	}

	@Override
	public ArrayList<Music> listMusicsInFolder(String path) {
		// TODO Implementar chamada do método do RMI
		return null;
	}
	
	@Override 
	public Music getMusicById(int id) {
		Music music = databaseStorage.getMusicById(id);
		setLastPlayedMusic(music);
		return getLastPlayedMusic();
	}

	public void setLastPlayedMusic(Music lastPlayedMusic) {
		this.lastPlayedMusic = lastPlayedMusic;
	}
	
}