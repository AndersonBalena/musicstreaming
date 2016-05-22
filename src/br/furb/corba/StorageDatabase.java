package br.furb.corba;

import java.util.HashMap;
import java.util.Map;
import org.omg.CORBA.Object;
import br.furb.corba.DatabaseStorage.DatabaseStoragePOA;
import br.furb.model.Music;

public class StorageDatabase extends DatabaseStoragePOA {

	private static Map<Integer, Music> musicas = new HashMap<>();
	
	static {
		musicas.put(1, new Music(1, "Foo - Paranoid - Black Sabbath", 2.00, "./src/resources/Saideira.mp3", "Artist 1"));
		musicas.put(2, new Music(2, "Smoke on the Water - Deep Purple", 3.00, "./src/resources/Saideira.mp3", "Artist 2"));
		musicas.put(3, new Music(3, "Foo - Bark at the Moon - Ozzy Osbourne", 2.00, "./src/resources/Saideira.mp3", "Artist 3"));
		musicas.put(4, new Music(4, "Foo - Saideira - Skank", 8.00, "./src/resources/Saideira.mp3", "Artist 4"));
	}
	
	@Override
	public Object[] listMusicsByName(String name) {
		int index = 0;
		Object[] musics = new Object[musicas.size()]; 
		for (int i = 0; i < musicas.size(); i++) {
			if (musicas.get(i + 1).getName().contains(name)) {
				java.lang.Object obj = musicas.get(i + 1);
				musics[index] = (Object) obj;
			}
		}
		return musics;
	}

}
