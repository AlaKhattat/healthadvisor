
package com.heathadvisor.service;

import com.healthadvisor.entities.Article_Votes;
import java.util.List;


public interface IGestionArticlesVotes {
   
    public void ajouterArticleVotes(Article_Votes av);
    public void supprimerArticleVotes(Article_Votes av);
    public List<Article_Votes> afficherArticleVotes();
}
