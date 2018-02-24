/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.heathadvisor.service;

import com.healthadvisor.entities.UserRegime;

/**
 *
 * @author asus
 */
public interface IGestionUserRegime 
{
    public UserRegime rechercherUserRegime(int id_user);
}
