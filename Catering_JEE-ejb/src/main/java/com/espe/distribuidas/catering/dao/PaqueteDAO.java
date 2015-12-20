/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.dao;

import com.espe.distribuidas.catering.common.dao.DefaultGenericDAOImple;
import com.espe.distribuidas.catering.modelo.Paquete;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Vane
 */
@LocalBean
@Stateless
public class PaqueteDAO extends DefaultGenericDAOImple<Paquete, Integer>{
    
    public PaqueteDAO()
    {
        super(Paquete.class);
    }
}