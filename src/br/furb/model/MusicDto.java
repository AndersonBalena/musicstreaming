package br.furb.model;

import java.io.Serializable;

public class MusicDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private double duration;
	private String location;
	private String artist;
	
	public MusicDto() {
	}
	
	public MusicDto(int id, String name, double duration, String location, String artist) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
}
