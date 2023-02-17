package com.distribuida.conections;
import com.distribuida.db.Author;

import com.distribuida.db.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AuthorConection implements AuthorService {


private AuthorService authorConection;

CloseableHttpClient httpclient = HttpClients.createDefault();

String urlAuthor = "http://localhost:3002/authors";

public AuthorConection() {
	Map<String, String> env = System.getenv();
	for (String envName : env.keySet()) {
		if (envName.equals("URL_AUTHOR")) {
			urlAuthor = env.get(envName);
		}
	}
	System.out.println("URL_AUTHOR**********: " + urlAuthor);
}

@Override
public List<Author> findAll() {
	try {
		HttpGet httpGet = new HttpGet(urlAuthor);
		var response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println(entity);
		ObjectMapper obtectMapper = new ObjectMapper();
		Author[] auu = obtectMapper.readValue(EntityUtils.toString(entity), Author[].class);
		return List.of(auu);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Collections.EMPTY_LIST;
}

@Override
public Author getOne(Integer id) {
	
	try {
		HttpGet httpGet = new HttpGet(urlAuthor + "/" + id);
		var response = httpclient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		System.out.println(entity);
		ObjectMapper obtectMapper = new ObjectMapper();
		Author auu = obtectMapper.readValue(EntityUtils.toString(entity), Author.class);
		return auu;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

@Override
public Author addOne(Author author) {
	HttpPost httpPost = new HttpPost(urlAuthor);
	try {
		StringEntity entity = new StringEntity(new JSONObject(author).toString());
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		var response = httpclient.execute(httpPost);
		HttpEntity entity1 = response.getEntity();
		System.out.println(entity1);
		ObjectMapper obtectMapper = new ObjectMapper();
		Author aut = obtectMapper.readValue(EntityUtils.toString(entity1), Author.class);
		return aut;
	} catch (Exception e) {
		e.printStackTrace();
		return new Author();
	}
	
	
}

@Override
public Boolean editOne(Integer id, Author author) {
	HttpPut httpPut = new HttpPut(urlAuthor + "/" + id);
	try {
		StringEntity entity = new StringEntity(new JSONObject(author).toString());
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
	
	HttpDelete httpDelete = new HttpDelete(urlAuthor + "/" + id);
	try {
		var response = httpclient.execute(httpDelete);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	
}
}
