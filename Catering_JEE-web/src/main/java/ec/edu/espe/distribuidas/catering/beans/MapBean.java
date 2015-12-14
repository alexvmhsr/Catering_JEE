/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polyline;


/**
 *
 * @author PabloA
 */
@ManagedBean
@RequestScoped
public class MapBean {
private final MapModel model;



public MapBean() {
model = new DefaultMapModel();
Polyline polyline = new Polyline();
polyline.getPaths().add(new LatLng(36.879466, 30.667648));
polyline.getPaths().add(new LatLng(36.883707, 30.689216));
polyline.getPaths().add(new LatLng(36.879703, 30.706707));
polyline.getPaths().add(new LatLng(36.885233, 37.702323));
model.addOverlay(polyline);

 }
 public MapModel getModel() { 
     model.addOverlay(null);
     return this.model; }


}
