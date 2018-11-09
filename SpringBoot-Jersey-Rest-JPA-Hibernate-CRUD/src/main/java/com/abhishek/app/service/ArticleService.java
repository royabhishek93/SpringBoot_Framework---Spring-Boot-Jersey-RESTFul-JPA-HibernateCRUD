package com.abhishek.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhishek.app.dao.ArticleDAO;
import com.abhishek.app.entity.Article;
@Service
public class ArticleService implements IArticleService{

	@Autowired
	private ArticleDAO  articleDAO;
	
	@Override
	public List<Article> getAllArticles() {
		// TODO Auto-generated method stub
		return articleDAO.getAllArticles();
	}

	@Override
	public Article getArticlebyId(int articleId) {
		// TODO Auto-generated method stub
		return articleDAO.getArticleById(articleId);
	}

	@Override
	public synchronized boolean  addArticle(Article article) {
		if(articleDAO.articleExists(article.getTitle(), article.getCategory()))
	{
			return false;
	}
	else {
		articleDAO.addArticle(article);
			return true;
		}
		
	}

	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
		
	}

	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
		
	}

}
