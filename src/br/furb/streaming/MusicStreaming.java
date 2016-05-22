package br.furb.streaming;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import br.furb.corba.DatabaseStorage.DatabaseStorage;
import br.furb.corba.DatabaseStorage.DatabaseStorageHelper;
import br.furb.model.Music;

public class MusicStreaming {
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
			String name = "DatabaseStorage";
			DatabaseStorage server = DatabaseStorageHelper.narrow(ncRef.resolve_str(name));
			
			Object[] musics = server.listMusicsByName("Foo");
			for (Object m : musics) {
				Music music = (Music) m;
				System.out.println(music.getName());
			}
	    } catch (Exception e) {
	        System.out.println("ERROR : " + e) ;
	    }
	}
}
