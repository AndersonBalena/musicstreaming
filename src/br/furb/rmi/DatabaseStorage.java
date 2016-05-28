package br.furb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import br.furb.model.Music;
import br.furb.model.MusicDto;

public interface DatabaseStorage extends Remote {
	public Music[] listMusicsByName(String name) throws RemoteException;
	public Music[] listMusicsByArtist(String artist) throws RemoteException;
	public boolean updateMusic(int musicId, MusicDto musicDto) throws RemoteException;
	public boolean removeMusic(Music music) throws RemoteException;
	public Music getMusicById(int id) throws RemoteException;
}
