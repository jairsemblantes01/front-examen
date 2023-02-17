package com.distribuida.conections;
import com.distribuida.db.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.annotation.JsonbAnnotation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookConection implements BookService {


CloseableHttpClient httpclient = HttpClients.createDefault();
String urlBook = "http://localhost:3001/books";


public BookConection() {
	Map<String, String> env = System.getenv();
	for (String envName : env.keySet()) {
		if (envName.equals("URL_BOOK")) {
			urlBook = env.get(envName);
		}
	}
	System.out.println("URL_BOOK**********: " + urlBook);
}

@Override
@JsonbAnnotation
public List<Book> findAll() {
	try {
		HttpGet httpGet = new HttpGet(urlBook);
		var response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println(entity);
		ObjectMapper obtectMapper = new ObjectMapper();
		Book[] boo = obtectMapper.readValue(EntityUtils.toString(entity), Book[].class);
		return List.of(boo);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Collections.EMPTY_LIST;
}


@Override
public Book getOne(Integer id) {
	try {
		HttpGet httpGet = new HttpGet(urlBook + "/" + id);
		var response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println(entity);
		ObjectMapper obtectMapper = new ObjectMapper();
		Book book = obtectMapper.readValue(EntityUtils.toString(entity), Book.class);
		return book;
	} catch (Exception e) {
		e.printStackTrace();
		return new Book();
	}
}


@Override
public Book addOne(Book book) {
	HttpPost httpPost = new HttpPost(urlBook);
	try {
		StringEntity entity = new StringEntity(new JSONObject(book).toString());
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		var response = httpclient.execute(httpPost);
		HttpEntity entity1 = response.getEntity();
		System.out.println(entity1);
		ObjectMapper obtectMapper = new ObjectMapper();
		Book book1 = obtectMapper.readValue(EntityUtils.toString(entity1), Book.class);
		return book1;
	} catch (Exception e) {
		e.printStackTrace();
		return new Book();
	}
}


@Override
public Boolean editOne(Integer id, Book book) {
	HttpPut httpPut = new HttpPut(urlBook + "/" + id);
	try {
		StringEntity entity = new StringEntity(new JSONObject(book).toString());
		httpPut.setEntity(entity);
		httpPut.setHeader("Accept", "application/json");
		httpPut.setHeader("Content-type", "application/json");
		var response = httpclient.execute(httpPut);
		HttpEntity entity1 = response.getEntity();
		System.out.println(entity1);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
}




	@Override
	public Boolean deleteOne(Integer id) {
		HttpDelete httpDelete = new HttpDelete(urlBook + "/" + id);
		try {
			var response = httpclient.execute(httpDelete);
			HttpEntity entity1 = response.getEntity();
			System.out.println(entity1);
			ObjectMapper obtectMapper = new ObjectMapper();
			Boolean book1 = obtectMapper.readValue(EntityUtils.toString(entity1), Boolean.class);
			return book1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
