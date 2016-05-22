package br.furb.model;

public class Music {
	
	private int id;
	private String name;
	private double duration;
	private String location;
	private String artist;
	
	public Music(int id, String name, double duration, String location, String artist) {
		this.setId(id);
		this.setName(name);
		this.setDuration(duration);
		this.setLocation(location);
		this.setArtist(artist);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
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
	public String getArtist() {
		return this.artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
}
