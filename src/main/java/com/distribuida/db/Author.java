package com.distribuida.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
	public class Author {
		@Getter @Setter private long id;
		@Getter @Setter private String first_name;
		@Getter @Setter private String last_name;
		
		public Author(String first_name, String last_name) {
		this.first_name = first_name;
		this.last_name = last_name;
		
		}
		public Author() {
		}
}