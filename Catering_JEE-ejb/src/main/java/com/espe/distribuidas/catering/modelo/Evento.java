/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
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
@Table(name="G4_EVENTO")

public class Evento implements Serializable{
    
    @Id
    @Column(name="COD_EVENTO", nullable=false)
    private Integer codigo;
    
    @Column(name="COD_TIPO_EVENTO", nullable=false)
    private Integer codigoTipoEvento;
    
    @Column(name="COD_PAQUETE", nullable=false)
    private Integer codigoPaquete;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_EVENTO_INICIO", nullable= false)
    private Date fechaEventoInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_EVENTO_FINAL", nullable= false)
    private Date fechaEventoFinal;
    
    @Column(name="ASISTENTES", nullable= false)
    private Integer asistentes;
    
    @Column(name="DIRECCION", nullable= false)
    private String direccion;
    
    @Column(name="TELEFONO", nullable= false)
    private String telefono;
    
    @Column(name = "COD_CLIENTE", nullable = false)
    private String codigoCliente;
    
    @JoinColumn(name = "COD_CLIENTE", referencedColumnName = "COD_CLIENTE", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;
    
    @JoinColumn(name = "COD_TIPO_EVENTO", referencedColumnName = "COD_TIPO_EVENTO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoEvento tipoEvento;

    @JoinColumn(name = "COD_PAQUETE", referencedColumnName = "COD_PAQUETE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquete paquete;

    public Evento() {
    }

    public Evento(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigoTipoEvento() {
        return codigoTipoEvento;
    }

    public void setCodigoTipoEvento(Integer codigoTipoEvento) {
        this.codigoTipoEvento = codigoTipoEvento;
    }

    public Integer getCodigoPaquete() {
        return codigoPaquete;
    }

    public void setCodigoPaquete(Integer codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public Date getFechaEventoInicio() {
        return fechaEventoInicio;
    }

    public void setFechaEventoInicio(Date fechaEventoInicio) {
        this.fechaEventoInicio = fechaEventoInicio;
    }

    public Date getFechaEventoFinal() {
        return fechaEventoFinal;
    }

    public void setFechaEventoFinal(Date fechaEventoFinal) {
        this.fechaEventoFinal = fechaEventoFinal;
    }

    

    public Integer getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Integer asistentes) {
        this.asistentes = asistentes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Evento other = (Evento) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evento{" + "codigo=" + codigo + ", codigoTipoEvento=" + codigoTipoEvento + ", codigoPaquete=" + codigoPaquete + ", fechaEventoInicio=" + fechaEventoInicio + ", fechaEventoFinal=" + fechaEventoFinal + ", asistentes=" + asistentes + ", direccion=" + direccion + ", telefono=" + telefono + ", codigoCliente=" + codigoCliente + ", cliente=" + cliente + ", tipoEvento=" + tipoEvento + ", paquete=" + paquete + '}';
    }

    

    

    
}
