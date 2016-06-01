package br.furb.webservice;

import javax.swing.JOptionPane;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import br.furb.corba.FileStorage.FileStorage;
import br.furb.corba.FileStorage.FileStorageHelper;


public class CorbaClient {
	public static FileStorage getClient(String serviceName, String[] args) throws InvalidName {
		try {
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			return FileStorageHelper.narrow(ncRef.resolve_str(serviceName));
		} catch (NotFound e) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar o servidor Corba.");
			e.printStackTrace();
		} catch (CannotProceed e) {
			JOptionPane.showMessageDialog(null, "Não foi possível iniciar o client Corba.");
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			JOptionPane.showMessageDialog(null, "Não foi possível encontrar o servidor Corba, nome inválido.");
			e.printStackTrace();
		}
		return null;
	}
}
