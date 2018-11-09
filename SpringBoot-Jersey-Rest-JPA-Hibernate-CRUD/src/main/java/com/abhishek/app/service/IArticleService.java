package com.abhishek.app.service;

import java.util.List;

import com.abhishek.app.entity.Article;

public interface IArticleService {

	List<Article> getAllArticles();
	Article getArticlebyId(int articleId);
	boolean addArticle(Article article);
	void updateArticle(Article article);
	void deleteArticle(int articleId);
}
