
package com.heathadvisor.service;

import com.healthadvisor.entities.Article;
import java.io.InputStream;
import java.util.List;


public interface IGestionArticle {
    
    public void ajouterArticle(Article a);
    public void supprimerArticle(int ref);
    public void modifierArticle(int ref, String nom, String desc, String cont, String image);
    public List<Article> afficherArticle();
    
}
