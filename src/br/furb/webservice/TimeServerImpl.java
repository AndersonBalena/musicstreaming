package br.furb.webservice;

import java.util.Date;

import javax.jws.WebService;

@WebService(endpointInterface = "br.furb.webservice.TimeServerInterface")
public class TimeServerImpl implements TimeServerInterface {
   
	@Override
	public long getTimeServer() {
		System.out.println("Recebendo requisição de tempo...");
		return new Date().getTime();
	}
	
}