/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.ClienteDAO;
import com.espe.distribuidas.catering.dao.EventoDAO;
import com.espe.distribuidas.catering.modelo.Cliente;
import com.espe.distribuidas.catering.modelo.Evento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author PabloA
 */
@LocalBean
@Stateless

public class EventoServicio {
    
    @EJB
    EventoDAO eventoDAO;
    
    @EJB
    ClienteDAO clienteDAO;

    public void crearEvento(Evento evento) {

        this.eventoDAO.insert(evento);

    }

    public Evento obtenerPorNumero(Integer numfactura) {
        return this.eventoDAO.findById(numfactura, false);
    }

    public List<Evento> obtenerEventos() {
        return this.eventoDAO.findAll();
    }

    
    public List<Cliente> obtenerClientes() {
        return this.clienteDAO.findAll();
    }
    public void actualizarEvento(Evento evento) {
        this.eventoDAO.update(evento);
    }

    public void eliminarEvento(Integer codigo) {
        Evento evento = this.eventoDAO.findById(codigo, false);
        this.eventoDAO.remove(evento);
    }
}
