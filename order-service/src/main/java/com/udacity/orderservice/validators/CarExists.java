package com.udacity.orderservice.validators;

import com.udacity.orderservice.client.vehicle.VehicleClient;
import com.udacity.orderservice.domain.order.CarOrder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateCarOrderValidator")
public class CarExists implements Validator {

    private VehicleClient vehicleClient;

    public CarExists(VehicleClient vehicleClient) {
        this.vehicleClient = vehicleClient;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CarOrder.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CarOrder carOrder = (CarOrder) o;
        if(carOrder.getVehicleId() == null){
            errors.rejectValue("vehicleId","vehicleId.null");
        }
        else {
            boolean vehicleExists = vehicleClient.doesVehicleExist(carOrder.getVehicleId());
            if(!vehicleExists){
                errors.rejectValue("vehicleId","vehicle.not.exists");
            }
        }
    }
}
