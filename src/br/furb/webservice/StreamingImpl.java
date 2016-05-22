package br.furb.webservice;

import java.util.ArrayList;
import javax.jws.WebService;
import br.furb.model.Music;
import br.furb.model.MusicDto;

@WebService(endpointInterface = "br.furb.webservice.StreamingInterface")
public class StreamingImpl implements StreamingInterface {
    
//	private static Map<Integer, Music> musicas = new HashMap<>();
	
	@Override
	public ArrayList<Music> listMusicsByName(String name) {
//    	ArrayList<Music> musics = new ArrayList<>();
//    	for (int i = 0; i < musics.size(); i++) {
//    		Music music = musicas.get(i + 1);
//    		if (music.getName().contains(name)) {
//    			musics.add(music);
//    		}
//    	}
//    	return musics;
		// TODO Implementar chamada do método do corba.
		return null;
	}

	@Override
	public ArrayList<Music> listMusicsByArtist(String artist) {
		// TODO Implementar chamada do método do corba.
		return null;
	}

	@Override
	public String getMusicContent(Music music) {
		// TODO Implementar chamada do método do RMI
		return null;
	}

	@Override
	public int updateMusic(MusicDto musicDto) {
		// TODO Implementar chamada do método do corba.
		return 0;
	}

	@Override
	public int removeMusic(Music music) {
		// TODO Implementar chamada do método do corba.
		return 0;
	}

	@Override
	public ArrayList<Music> listMusicsInFolder(String path) {
		// TODO Implementar chamada do método do RMI
		return null;
	}

}