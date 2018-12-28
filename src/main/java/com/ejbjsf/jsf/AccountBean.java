package com.ejbjsf.jsf;

import com.ejbjsf.interfaces.VehicleRemote;
import com.ejbjsf.persistence.entities.Vehicle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("account")
@RequestScoped
public class AccountBean implements Serializable {

    @EJB
    VehicleRemote vehicleRemote;

    private List<Vehicle> vehicles;

    @PostConstruct
    public void postConstruct() {
        vehicles = vehicleRemote.getVehicles();
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
