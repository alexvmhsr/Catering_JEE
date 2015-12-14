/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author PabloA
 */
@ManagedBean
@RequestScoped
public class paginasBean {

    /**
     * Creates a new instance of paginasBean
     */
    public paginasBean() {
    }
    public String index(){
        System.out.println("index");
        return "/faces/index";
    }
    public String principal(){
        System.out.println("Principal");
        return "/faces/Principal";
    }
    public String administrar(){
        System.out.println("Administrar");
        return "/faces/Administrar";
    }
    public String activos(){
        System.out.println("Activos");
        return "/faces/Activos";
    }
    public String ubicacion(){
        System.out.println("Ubicacion");
        return "/faces/Ubicacion";
    }
    public String provedores(){
        System.out.println("Provedores");
        return "/faces/Provedores";
    }
}
