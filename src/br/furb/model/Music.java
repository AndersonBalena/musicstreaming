package br.furb.model;

import java.io.Serializable;

public class Music implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String location;
	
	public Music() {}
	
	public Music(int id, String name, String location) {
		this.setId(id);
		this.setName(name);
		this.setLocation(location);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nome=" + name + ", localização=" + location + "]";
	}
}
