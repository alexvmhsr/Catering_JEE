/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.dao;

import com.espe.distribuidas.catering.common.dao.DefaultGenericDAOImple;
import com.espe.distribuidas.catering.modelo.TipoMobiliario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author PabloA
 */
@LocalBean
@Stateless
public class TipoMobiliarioDAO extends DefaultGenericDAOImple<TipoMobiliario, Integer> {
    
    public TipoMobiliarioDAO() {
    super(TipoMobiliario.class);
    }
}
