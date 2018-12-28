package com.ugarit.jsf;

import com.ugarit.interfaces.VehicleRemote;
import com.ugarit.persistence.entities.Vehicle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

    public void saveVehicle(){
        vehicleRemote.addVehicle(newVehicle);
        vehicles = vehicleRemote.getVehicles();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle getNewVehicle() {
        return newVehicle;
    }

}
