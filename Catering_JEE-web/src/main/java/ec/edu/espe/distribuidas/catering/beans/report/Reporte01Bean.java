package ec.edu.espe.distribuidas.catering.beans.report;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Henry Coral
 */
@ManagedBean
@ViewScoped
public class Reporte01Bean extends AbstractReportBean implements Serializable {

    private Date fecha;

    public void generateReport() {
        
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
