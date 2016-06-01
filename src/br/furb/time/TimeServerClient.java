package br.furb.time;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.furb.webservice.TimeServerInterface;

public class TimeServerClient {
	
	public static Date getServerDate(){
		long start = new Date().getTime();
		TimeServerInterface clientWS = null;
		try {
			clientWS = getClientWS();
			long timeServer = clientWS.getTimeServer();
			long end = new Date().getTime();
			return new Date(timeServer + (end - start));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	private static TimeServerInterface getClientWS() throws MalformedURLException {
		try {
			URL url = new URL("http://127.0.0.1:9877/time?wsdl");
		    QName qname = new QName("http://webservice.furb.br/","TimeServerImplService");
		    Service ws = Service.create(url, qname);
		    return ws.getPort(TimeServerInterface.class);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao WebService!");
		}
		return null;
	}

}
