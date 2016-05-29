package br.furb.webservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jws.WebService;
import javax.swing.JOptionPane;

import org.omg.CORBA.ORBPackage.InvalidName;

import br.furb.corba.FileStorage.FileStorage;
import br.furb.model.Music;
import br.furb.model.MusicDto;
import br.furb.rmi.DatabaseStorage;
import br.furb.webservice.clientcorba.CorbaClient;
import br.furb.webservice.clientrmi.RmiClient;

@WebService(endpointInterface = "br.furb.webservice.StreamingInterface")
public class StreamingImpl implements StreamingInterface {
    
	private DatabaseStorage databaseStorage;
	private FileStorage fileStorage;
	private Music lastPlayedMusic;
	
	public StreamingImpl() throws MalformedURLException, NotBoundException, InvalidName {
		fileStorage = CorbaClient.getClient("FileStorage", new String[0]);
		try {
			databaseStorage = RmiClient.getClient("//localhost/DatabaseStorage");
		} catch (RemoteException re) {
			JOptionPane.showMessageDialog(null, "Could not connect to RMI server!");
		}
	}
	
	@Override
	public Music getLastPlayedMusic() {
		return this.lastPlayedMusic;
	}
	
	@Override
	public Music[] listMusicsByName(String name) throws RemoteException {
		return databaseStorage.listMusicsByName(name);
	}

	@Override
	public Music[] listMusicsByArtist(String artist) throws RemoteException {
		return databaseStorage.listMusicsByArtist(artist);
	}

	@Override
	public boolean updateMusic(int musicId, MusicDto musicDto) throws RemoteException {
		return databaseStorage.updateMusic(musicId, musicDto);
	}

	@Override
	public boolean removeMusic(int musicId) throws RemoteException {
		return databaseStorage.removeMusic(musicId);
	}

	@Override
	public String listMusicsInFolder(String path) {
		return fileStorage.getMusicsInFolder(path);
	}
	
	@Override 
	public Music getMusicById(int id) throws RemoteException {
		Music music = databaseStorage.getMusicById(id);
		setLastPlayedMusic(music);
		return getLastPlayedMusic();
	}

	public void setLastPlayedMusic(Music lastPlayedMusic) {
		this.lastPlayedMusic = lastPlayedMusic;
	}

	@Override
	public double getMusicSize(String path) {
		return fileStorage.getMusicSize(path);
	}
	
}