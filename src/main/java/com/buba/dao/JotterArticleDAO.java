package com.buba.dao;

import com.buba.pojo.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JotterArticleDAO extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
