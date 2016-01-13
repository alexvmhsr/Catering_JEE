/*
 * ROCHE ECUADOR
 * Sistema: SWP 1.0 - ROCHE
 * Creado: 14-feb-2013 - 00:10:46
 * 
 * Los contenidos de este archivo son propiedad intelectual de ROCHE ECUADOR
 * y se encuentran protegidos por leyes de propiedad intelectual.
 * 
 * No se puede hacer uso de los mismos sin el expreso consentimiento de ROCHE.
 * 
 * Copyright 2012-2013 Roche Ecuador Todos los derechos reservados.
 */
package ec.edu.espe.distribuidas.catering.beans.report;

import com.espe.distribuidas.catering.modelo.Cliente;
import com.espe.distribuidas.catering.servicio.ClienteService;

import java.io.Serializable;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Henry Coral
 */
@ManagedBean
@ViewScoped
public class Reporte03Bean extends AbstractReportBean implements Serializable {

    private static final String QRY = "select zo.nombre, cl.razon_social, pr.descripcion, sum(pd.cantidad) suma "
            + "from swp_pedido pe, swp_pedido_detalle pd, swp_producto pr, swp_zona_geografica zo, swp_cliente cl "
            + "where pe.num_pedido = pd.num_pedido and pd.cod_producto=pr.cod_producto and zo.cod_zona=pe.cod_zona "
            + "and pe.estado_pedido='AUTH' and pr.cod_area='45800' and pe.estado_devolucion ='NO' "
            + "and pe.FECHA_REGISTRO between convert(datetime,";
    private static final String QRY1 = ") and convert(datetime,";
    private static final String QRY2 = ") and pe.cod_zona";
    private static final String QRY3 = " group by zo.nombre, cl.razon_social, pr.descripcion order by 1,4,3 desc";
    
    private Date fechaInicio;
    private Date fechaFin;
    private String zona;
    @EJB
    private ClienteService clienteServicio;
    private List<Cliente> listaCliente;

    @PostConstruct
    public void postConstructor() {
        this.listaCliente = this.clienteServicio.obtenerClientes();
    }

    public void generateReport() {
     
        

        
            
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public ClienteService getClienteServicio() {
        return clienteServicio;
    }

    public void setClienteServicio(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    
}
