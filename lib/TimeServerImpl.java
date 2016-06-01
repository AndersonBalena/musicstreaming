package br.furb.motan;

import java.util.Date;

public class TimeServerImpl implements TimeServer {

	@Override
	public long getTime() {
		return new Date().getTime();
	}

}
