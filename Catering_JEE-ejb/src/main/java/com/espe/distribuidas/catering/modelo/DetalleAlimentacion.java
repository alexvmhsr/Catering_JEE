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
@Table(name = "G4_DETALLE_ALIMENTACION")
public class DetalleAlimentacion implements Serializable {
  
    @EmbeddedId
    protected DetalleAlimentacionPK  detalleAlimentacionPK;
    
    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;
    
    @Column(name = "COD_ALIMENTACION", nullable = false)
    private int codigoAlimentacion;
    
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
    
    @Column(name = "VALOR_TOTAL", nullable = false, precision = 6, scale = 2)
    private BigDecimal valorTotal;
    
    @JoinColumn(name = "COD_ALIMENTACION", referencedColumnName = "COD_ALIMENTACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Alimentacion alimentacion;

    @JoinColumn(name = "COD_PAQUETE", referencedColumnName = "COD_PAQUETE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquete paquete;

    public DetalleAlimentacion() {
    }

    public DetalleAlimentacion(DetalleAlimentacionPK detalleAlimentacionPK) {
        this.detalleAlimentacionPK = detalleAlimentacionPK;
    }

    public DetalleAlimentacionPK getDetalleAlimentacionPK() {
        return detalleAlimentacionPK;
    }

    public void setDetalleAlimentacionPK(DetalleAlimentacionPK detalleAlimentacionPK) {
        this.detalleAlimentacionPK = detalleAlimentacionPK;
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

    public Alimentacion getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.detalleAlimentacionPK != null ? this.detalleAlimentacionPK.hashCode() : 0);
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
        final DetalleAlimentacion other = (DetalleAlimentacion) obj;
        if (this.detalleAlimentacionPK != other.detalleAlimentacionPK && (this.detalleAlimentacionPK == null || !this.detalleAlimentacionPK.equals(other.detalleAlimentacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleAlimentacion{" + "detalleAlimentacionPK=" + detalleAlimentacionPK + '}';
    }
    
    
}
