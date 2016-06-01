package br.furb.corba;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import br.furb.rmi.DatabaseStorage;

public class ClientRmi {
	
	public static DatabaseStorage getClient(String url) throws MalformedURLException, RemoteException, NotBoundException {
		return (DatabaseStorage) Naming.lookup(url);
	}
}
