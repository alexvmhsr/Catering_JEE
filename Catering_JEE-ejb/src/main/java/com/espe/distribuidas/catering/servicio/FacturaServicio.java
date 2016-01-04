/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.ClienteDAO;
import com.espe.distribuidas.catering.dao.FacturaDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.Cliente;
import com.espe.distribuidas.catering.modelo.Factura;
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
public class FacturaServicio {

    @EJB
    FacturaDAO facturaDAO;
    
    @EJB
    ClienteDAO clienteDAO;

    public void crearFactura(Factura factura) {
        Factura facturaTemp = new Factura();
        facturaTemp.setCodigo(factura.getCodigo());
        List<Factura> facturas = this.facturaDAO.find(facturaTemp);
        if (facturas == null || facturas.isEmpty()) {
            this.facturaDAO.insert(factura);
        } else {
            throw new ValidacionException("El número de factura: " + factura.getCodigo() + " ya está asignado.");
        }
    }

    public Factura obtenerFacturaPorNumero(Integer numfactura) {
        return this.facturaDAO.findById(numfactura, false);
    }

    public List<Factura> obtenerTodas() {
        return this.facturaDAO.findAll();
    }
    public List<Cliente> obtenerClientes() {
        return this.clienteDAO.findAll();
    }

    public void actualizarFactura(Factura cliente) {
        this.facturaDAO.update(cliente);
    }

    public void eliminarFactura(Integer codigo) {
        Factura factura = this.facturaDAO.findById(codigo, false);
        this.facturaDAO.remove(factura);
    }
}
