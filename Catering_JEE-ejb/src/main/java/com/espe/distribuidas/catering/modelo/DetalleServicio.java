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
    
    @Column(name = "COD_SERVICIO", nullable = false)
    private int codigoServicio;
    
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
    
    @Column(name = "VALOR_TOTAL", nullable = false, precision = 6, scale = 2)
    private BigDecimal valorTotal;
    
    @JoinColumn(name = "COD_SERVICIO", referencedColumnName = "COD_SERVICIO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Servicio servicio;
    
    @JoinColumn(name = "COD_PAQUETE", referencedColumnName = "COD_PAQUETE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquete paquete;

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

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
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
        return "DetalleServicio{" + "detalleServicioPK=" + detalleServicioPK + ", cantidad=" + cantidad + ", valorTotal=" + valorTotal + ", servicio=" + servicio + ", paquete=" + paquete + '}';
    }


}
