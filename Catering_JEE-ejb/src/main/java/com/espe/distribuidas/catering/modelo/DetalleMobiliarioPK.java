/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Vane
 */
@Embeddable
public class DetalleMobiliarioPK implements Serializable{

    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;
    
    @Column(name = "COD_MOBILIARIO", nullable = false)
    private int codigoMobiliario;

    public DetalleMobiliarioPK() {
    }

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public int getCodigoMobiliario() {
        return codigoMobiliario;
    }

    public void setCodigoMobiliario(int codigoMobiliario) {
        this.codigoMobiliario = codigoMobiliario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.codigoPaquete;
        hash = 23 * hash + this.codigoMobiliario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleMobiliarioPK other = (DetalleMobiliarioPK) obj;
        if (this.codigoPaquete != other.codigoPaquete) {
            return false;
        }
        if (this.codigoMobiliario != other.codigoMobiliario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleMobiliarioPK{" + "codigoPaquete=" + codigoPaquete + ", codigoMobiliario=" + codigoMobiliario + '}';
    }

    
}
