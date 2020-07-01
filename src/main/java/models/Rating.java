package models;


import java.util.Date;

public class Rating {
	
	/*
	- Author
	- Score, from 0-100
	- Description
	- Date
	 */
	private String author;
	private int rating;
	private String description;
	private Date date;
	
	public Rating() {
	}
	
	public Rating(String author, int rating, String description, Date date) {
		this.author = author;
		this.rating = rating;
		this.description = description;
		this.date = date;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
