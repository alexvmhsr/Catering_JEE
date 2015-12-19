/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

/**
 *
 * @author Vane
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetalleAlimentacionPK implements Serializable{
    
    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;
    
    @Column(name = "COD_ALIMENTACION", nullable = false)
    private int codigoAlimentacion;

    public DetalleAlimentacionPK() {
    }

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public int getCodigoAlimentacion() {
        return codigoAlimentacion;
    }

    public void setCodigoAlimentacion(int codigoAlimentacion) {
        this.codigoAlimentacion = codigoAlimentacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.codigoPaquete;
        hash = 97 * hash + this.codigoAlimentacion;
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
        final DetalleAlimentacionPK other = (DetalleAlimentacionPK) obj;
        if (this.codigoPaquete != other.codigoPaquete) {
            return false;
        }
        if (this.codigoAlimentacion != other.codigoAlimentacion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleAlimentacionPK{" + "codigoPaquete=" + codigoPaquete + ", codigoAlimentacion=" + codigoAlimentacion + '}';
    }
    
    
    
}
