package io.rajivsingh.moviecatalogservice;

public class Movie {

	private String name;
	private String movieId;
	public Movie(String name, String movieId) {
		super();
		this.name = name;
		this.movieId = movieId;
	}
	public Movie()
	{
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	//commit 1
	
}
