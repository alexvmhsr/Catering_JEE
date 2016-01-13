/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vane
 */

@Entity
@Table(name = "G4_DETALLE_MOBILIARIO")

public class DetalleMobiliario implements Serializable {

    @EmbeddedId
    protected DetalleMobiliarioPK detalleMobiliarioPK;

    @Column(name = "COD_PAQUETE", nullable = false)
    private int codigoPaquete;

    @Column(name = "COD_MOBILIARIO", nullable = false)
    private int codigoMobiliario;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "VALOR_TOTAL", nullable = false, precision = 6, scale = 2)
    private BigDecimal valorTotal;

    @JoinColumn(name = "COD_MOBILIARIO", referencedColumnName = "COD_MOBILIARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mobiliario mobiliario;

    @JoinColumn(name = "COD_PAQUETE", referencedColumnName = "COD_PAQUETE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquete paquete;

    public DetalleMobiliario() {
    }

    public DetalleMobiliario(DetalleMobiliarioPK detalleMobiliarioPK) {
        this.detalleMobiliarioPK = detalleMobiliarioPK;
    }

    public DetalleMobiliarioPK getDetalleMobiliarioPK() {
        return detalleMobiliarioPK;
    }

    public void setDetalleMobiliarioPK(DetalleMobiliarioPK detalleMobiliarioPK) {
        this.detalleMobiliarioPK = detalleMobiliarioPK;
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

    public Mobiliario getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(Mobiliario mobiliario) {
        this.mobiliario = mobiliario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
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
        hash = 97 * hash + (this.detalleMobiliarioPK != null ? this.detalleMobiliarioPK.hashCode() : 0);
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
        final DetalleMobiliario other = (DetalleMobiliario) obj;
        if (this.detalleMobiliarioPK != other.detalleMobiliarioPK && (this.detalleMobiliarioPK == null || !this.detalleMobiliarioPK.equals(other.detalleMobiliarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleMobiliario{" + "detalleMobiliarioPK=" + detalleMobiliarioPK + ", codigoPaquete=" + codigoPaquete + ", codigoMobiliario=" + codigoMobiliario + ", cantidad=" + cantidad + ", valorTotal=" + valorTotal + ", mobiliario=" + mobiliario + ", paquete=" + paquete + '}';
    }

    
}
