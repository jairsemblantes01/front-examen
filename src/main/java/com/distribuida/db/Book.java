package com.distribuida.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
	public class Book {

	@Getter @Setter private String id;
	@Getter @Setter private int author;
	@Getter @Setter private String title;
	@Getter @Setter private String isbn;
@Getter @Setter private int price;


public Book(int author, String title, String isbn, int price) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.price = price;
		
	}
	public Book() {
	}
}