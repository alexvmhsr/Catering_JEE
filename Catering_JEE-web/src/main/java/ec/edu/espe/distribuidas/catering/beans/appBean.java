/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import ec.edu.espe.distribuidas.catering.util.MyUtil;

/**
 *
 * @author PabloA
 */
@ManagedBean
@ApplicationScoped
public class appBean {

    /**
     * Creates a new instance of appBean
     */
    public appBean() {
    }
    public String getBaseUrl(){
        return MyUtil.baseurl();
    }
}
