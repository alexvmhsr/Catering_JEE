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
import javax.persistence.Table;

/**
 *
 * @author Vane
 */
@Entity
@Table(name = "G4_PAQUETE")

public class Paquete implements Serializable {
    
    @Id
    @Column(name = "COD_PAQUETE", nullable = false)
    private Integer codigo;
    
    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;
    
    @Column(name = "ACTIVO", nullable = false)
    private Integer activo;
    
    @Column(name = "PRECIO_FINAL", nullable = false)
    private BigDecimal precio;
    
    @Column(name = "ITEMS", nullable = false)
    private Integer items;

    public Paquete() {
    }

    public Paquete(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Paquete other = (Paquete) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Paquete{" + "codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + ", precio=" + precio + ", items=" + items + '}';
    }
    
    
    
}
