package br.furb.corba;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import br.furb.corba.DatabaseStorage.DatabaseStorage;
import br.furb.corba.DatabaseStorage.DatabaseStorageHelper;

public class CorbaServer {
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);

			StorageDatabase impl = new StorageDatabase();

			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
			DatabaseStorage href = DatabaseStorageHelper.narrow(ref);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

			String name = "DatabaseStorage";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);

			System.out.println("Servidor aguardando requisicoes ....");

			orb.run();
			
		} catch (Exception e) {
			System.err.println("ERRO: " + e);
			e.printStackTrace();
		}
		System.out.println("Encerrando o Servidor.");
	}
}
