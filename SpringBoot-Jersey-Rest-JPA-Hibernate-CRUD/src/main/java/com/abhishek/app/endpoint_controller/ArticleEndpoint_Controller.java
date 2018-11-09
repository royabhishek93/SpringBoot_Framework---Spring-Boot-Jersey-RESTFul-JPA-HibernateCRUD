package com.abhishek.app.endpoint_controller;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.abhishek.app.entity.Article;
import com.abhishek.app.service.ArticleService;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.ws.rs.PathParam;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response.Status;

@Component
@Path("/article")
public class ArticleEndpoint_Controller {

	private static final Logger logger= LoggerFactory.getLogger(ArticleEndpoint_Controller.class);
	@Autowired
	private ArticleService articleService;
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllArticles() {
		List<Article> list= articleService.getAllArticles();
		return Response.ok(list).build();
	}
	@GET
	@Path("/getArticleById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleById(@PathParam("id") Integer id) {
		System.out.println("id "+id);
		Article article =articleService.getArticlebyId(id);
		System.out.println("article  "+article);
		return Response.ok(article).build();
	}
	
	@POST
	@Path("/addArticle")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Article article) {
		System.out.println("article "+article.getTitle()+"****"+article.getCategory()+"***"+article.getArticleId());
		boolean isAdded=articleService.addArticle(article);
		if(!isAdded) {
			logger.info("Article already exits.");
			Response.status(Status.CONFLICT).build();
		}
		return Response.created(URI.create("/SpringWebservice/article/"+article.getArticleId())).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/updateArticle")
	public Response updateArticle(Article article) {
		System.out.println("article "+article);
		articleService.updateArticle(article);
		return Response.ok(article).build();
	}
	
	@DELETE
	@Path("/deleteArticleById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteArticle(@PathParam ("id") int id) {
		System.out.println("id "+id);
		articleService.deleteArticle(id);
		return Response.noContent().build();
	}
}
