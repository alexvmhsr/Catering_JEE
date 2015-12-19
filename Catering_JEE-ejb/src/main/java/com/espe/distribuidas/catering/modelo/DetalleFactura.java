/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Vane
 */
@Entity
@Table(name = "G4_DETALLE_FACTURA")

public class DetalleFactura implements Serializable{
    
    @Id
    @Column(name = "COD_DETALLE_FACTURA", nullable = false)
    private Integer codigo;
    
    @Column(name = "COD_FACTURA", nullable = false)
    private Integer codigoFactura;
    
    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;
    
    @Column(name = "PRECIO_UNITARIO", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioUnitario;
   
    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;
    
    @Column(name = "PRECIO_TOTAL", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioTotal;

    @JoinColumn(name = "COD_FACTURA", referencedColumnName = "COD_FACTURA", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;
    
    public DetalleFactura() {
    }

    public DetalleFactura(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Integer codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final DetalleFactura other = (DetalleFactura) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Detalle_Factura{" + "codigo=" + codigo + ", codigoFactura=" + codigoFactura + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal + ", factura=" + factura + '}';
    }
    
    

    
       

    
    
    
}
