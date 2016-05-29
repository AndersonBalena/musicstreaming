package br.furb.corba;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import br.furb.corba.FileStorage.FileStorage;
import br.furb.corba.FileStorage.FileStorageHelper;

public class FileStorageServer {
  public static void main(String args[]) {
    try{
      // Cria e inicializa o ORB
      ORB orb = ORB.init(args, null);

      // Cria a implementa��o e registra no ORB
      FileStorageImpl impl = new FileStorageImpl();

      // Ativa o POA
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // Pega a refer�ncia do servidor
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
      FileStorage href = FileStorageHelper.narrow(ref);
	  
      // Obt�m uma refer�ncia para o servidor de nomes
      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // Registra o servidor no servico de nomes
      String name = "FileStorage";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("Servidor CORBA está no ar!");
      orb.run();
    } catch (Exception e) {
        System.err.println("ERRO: " + e);
        e.printStackTrace(System.out);
    }
    System.out.println("Encerrando o Servidor.");
  }
}
