package br.furb.rmi;

import java.rmi.Remote;
import java.util.ArrayList;

import br.furb.model.Music;
import br.furb.model.MusicDto;

public interface DatabaseStorage extends Remote {
	public ArrayList<Music> listMusicsByName(String name);
	public ArrayList<Music> listMusicsByArtist(String artist);
	public boolean updateMusic(int musicId, MusicDto musicDto);
	public boolean removeMusic(Music music);
	public Music getMusicById(int id);
}
