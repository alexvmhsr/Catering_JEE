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
@Table(name = "G4_MOBILIARIO")


public class Mobiliario implements Serializable{
    
    @Id
    @Column(name = "COD_MOBILIARIO", nullable = false)
    private Integer codigo;
    
    @Column(name = "COD_TIPO_MOBILIARIO", nullable = false)
    private Integer codTipoMobiliario;
    
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;  
    
    @Column(name = "EXISTENCIA", nullable = false)
    private Integer existencia;  
    
    @Column(name = "VALOR", nullable = false)
    private BigDecimal valor; 
    
    @JoinColumn(name = "COD_TIPO_MOBILIARIO", referencedColumnName = "COD_TIPO_MOBILIARIO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoMobiliario tipoMobiliario;   

    public Mobiliario() {
    }
    
    public Mobiliario(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodTipoMobiliario() {
        return codTipoMobiliario;
    }

    public void setCodTipoMobiliario(Integer codTipoMobiliario) {
        this.codTipoMobiliario = codTipoMobiliario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMobiliario getTipoMobiliario() {
        return tipoMobiliario;
    }

    public void setTipoMobiliario(TipoMobiliario tipoMobiliario) {
        this.tipoMobiliario = tipoMobiliario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Mobiliario other = (Mobiliario) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mobiliario{" + "codigo=" + codigo + ", codTipoMobiliario=" + codTipoMobiliario + ", nombre=" + nombre + ", existencia=" + existencia + ", valor=" + valor + ", tipoMobiliario=" + tipoMobiliario + '}';
    }
        
}
