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
public class DetalleServicioPK implements Serializable {
    
    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;
    
    @Column(name = "COD_SERVICIO", nullable = false)
    private int codigoServicio;

    public DetalleServicioPK() {
    }

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.codigoPaquete;
        hash = 89 * hash + this.codigoServicio;
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
        final DetalleServicioPK other = (DetalleServicioPK) obj;
        if (this.codigoPaquete != other.codigoPaquete) {
            return false;
        }
        if (this.codigoServicio != other.codigoServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleServicioPK{" + "codigoPaquete=" + codigoPaquete + ", codigoServicio=" + codigoServicio + '}';
    }
    
    
    
}
