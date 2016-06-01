package br.furb.webservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import javax.xml.ws.Endpoint;

import org.omg.CORBA.ORBPackage.InvalidName;

public class TimeServerPublisher {
	public static void main(String[] args) throws MalformedURLException, NotBoundException, InvalidName {
		Endpoint.publish("http://127.0.0.1:9877/time", new TimeServerImpl());
		System.out.println("Time Server WS no Ar...");
	}
}