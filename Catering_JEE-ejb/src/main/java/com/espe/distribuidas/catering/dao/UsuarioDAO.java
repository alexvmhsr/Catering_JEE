/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.dao;

import com.espe.distribuidas.catering.common.dao.DefaultGenericDAOImple;
import com.espe.distribuidas.catering.modelo.Usuario;

/**
 *
 * @author PabloA
 */
public class UsuarioDAO extends DefaultGenericDAOImple<Usuario, String> {

    public UsuarioDAO() {
    super(Usuario.class);
    }
    
}
