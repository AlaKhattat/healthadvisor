
package com.healthadvisor.entities;


public class Article_Votes {
    
    private int id_article;
    private String login_user;
    private double vote;

    public Article_Votes(Article a, Patient p, double vote) {
        this.id_article = a.getReference();
        this.login_user = p.getLogin();
        this.vote = vote;
    }

    public Article_Votes() {
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "La référence de l'article est :"+id_article+"\nLe login de l'utilisateur est :"+login_user+"\nLa note est"+vote;
    }
    
    
    
}
