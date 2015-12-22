/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.espe.distribuidas.catering.servicio;


import com.espe.distribuidas.catering.dao.ClienteDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.Cliente;
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
public class ClienteService {

    @EJB
    ClienteDAO clienteDAO;
    
    public void crearCliente(Cliente cliente) {
        
        Cliente clienteTemp = new Cliente();
        clienteTemp.setCodigo(cliente.getCodigo());
        List<Cliente> clientes = this.clienteDAO.find(clienteTemp);
        if (clientes == null || clientes.isEmpty()) {
            this.clienteDAO.insert(cliente);
        } else {
            throw new ValidacionException("El número de cédula: " + cliente.getCodigo()+ " ya está asignado.");
        }
    }
    
    public Cliente obtenerClientePorCedula(String cedulaCliente) {
        return this.clienteDAO.findById(cedulaCliente, false);
    }
    public List<Cliente> obtenerClientes() {
        return this.clienteDAO.findAll();
    }
    
    public void actualizarCliente(Cliente cliente) {
        this.clienteDAO.update(cliente);        
    }
    
    
    public void eliminarCliente(String cedula) {
        Cliente cliente = this.clienteDAO.findById(cedula, false);
        this.clienteDAO.remove(cliente);
    }
}
