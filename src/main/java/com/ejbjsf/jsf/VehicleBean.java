package com.ejbjsf.jsf;

import com.ejbjsf.interfaces.DuplicateLicenceException;
import com.ejbjsf.interfaces.VehicleRemote;
import com.ejbjsf.persistence.entities.Vehicle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("vehicle")
@RequestScoped
public class VehicleBean implements Serializable {

    @EJB
    VehicleRemote vehicleRemote;

    Vehicle newVehicle;

    private List<Vehicle> vehicles;

    @PostConstruct
    public void postConstruct() {
        vehicles = vehicleRemote.getVehicles();
        newVehicle = new Vehicle();
    }

    public void saveVehicle() {
        FacesContext currentInstance = FacesContext.getCurrentInstance();
        try {
            vehicleRemote.addVehicle(newVehicle);
            vehicles = vehicleRemote.getVehicles();
        } catch (DuplicateLicenceException e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            currentInstance.addMessage(null, message);
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle getNewVehicle() {
        return newVehicle;
    }

}
