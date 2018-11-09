package com.abhishek.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.abhishek.app.entity.Article;

@Transactional
@Repository
public class ArticleDAO implements IArticleDAO {
	@PersistenceContext
	private EntityManager  entityManager;
	
 @Override
	public List<Article> getAllArticles() {
		String hql="From Article as article Order by article.articleId";
		
	
		return (List<Article>) entityManager.createQuery(hql).getResultList();
		
	}

	@Override
	public Article getArticleById(int articleId) {
		return entityManager.find( Article.class, articleId);
		
	}

	@Override
	public void addArticle(Article article) {
		entityManager.persist(article);
		
	}

	@Override
	public void updateArticle(Article article) {
		// TODO Auto-generated method stub
		Article  atcl=getArticleById(article.getArticleId());
		atcl.setTitle(article.getTitle());
		atcl.setCategory(article.getCategory());
		entityManager.flush();
		
		
	}

	@Override
	public void deleteArticle(int articleId) {
		entityManager.remove(getArticleById(articleId));
		
	}

	@Override
	public boolean articleExists(String title, String category) {
		
		String hql="FROM Article as atcl   WHERE atcl.title = ?1 and atcl.category = ?2";
		int count=  entityManager.createQuery(hql).setParameter(1, title).setParameter(2, category).getResultList().size();
		return count > 0 ?true:false;
	}

	

}
