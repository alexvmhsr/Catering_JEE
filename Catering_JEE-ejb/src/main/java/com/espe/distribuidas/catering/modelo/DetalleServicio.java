/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author Vane
 */
@Entity
@Table(name="G4_DETALLE_SERVICIO")
public class DetalleServicio implements Serializable {
    
    @EmbeddedId
    protected DetalleServicioPK  detalleServicioPK;
    
    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;
    
    @Column(name = "COD_SERVICio", nullable = false)
    private int codigoServicio;
    
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
    
    @Column(name = "VALOR_TOTAL", nullable = false, precision = 6, scale = 2)
    private BigDecimal valorTotal;

    public DetalleServicio() {
    }

    public DetalleServicio(DetalleServicioPK detalleServicioPK) {
        this.detalleServicioPK = detalleServicioPK;
    }

    public DetalleServicioPK getDetalleServicioPK() {
        return detalleServicioPK;
    }

    public void setDetalleServicioPK(DetalleServicioPK detalleServicioPK) {
        this.detalleServicioPK = detalleServicioPK;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.detalleServicioPK != null ? this.detalleServicioPK.hashCode() : 0);
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
        final DetalleServicio other = (DetalleServicio) obj;
        if (this.detalleServicioPK != other.detalleServicioPK && (this.detalleServicioPK == null || !this.detalleServicioPK.equals(other.detalleServicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleServicio{" + "detalleServicioPK=" + detalleServicioPK + ", codigoPaquete=" + codigoPaquete + ", codigoServicio=" + codigoServicio + ", cantidad=" + cantidad + ", valorTotal=" + valorTotal + '}';
    }
    
    
}
