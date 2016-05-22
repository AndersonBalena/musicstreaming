package br.furb.webservice;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import javax.xml.ws.Endpoint;

public class StreamingServerPublisher {
	public static void main(String[] args) throws MalformedURLException, NotBoundException {
		Endpoint.publish("http://127.0.0.1:9876/streaming", new StreamingImpl());
		System.out.println("Servidor no Ar!!!");
	}
}