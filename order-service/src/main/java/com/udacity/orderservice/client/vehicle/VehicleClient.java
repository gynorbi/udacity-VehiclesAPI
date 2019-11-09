package com.udacity.orderservice.client.vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class VehicleClient {
    private static final Logger log = LoggerFactory.getLogger(VehicleClient.class);

    private final WebClient client;

    public VehicleClient(WebClient client) {
        this.client = client;
    }

    public boolean doesVehicleExist(Long vehicleId){
        try{
            log.info("Trying to validate vehicle: "+vehicleId);
            Mono<HttpStatus> monoStatus = client.get()
                    .uri("/cars")
                    .exchange()
                    .map(response -> response.statusCode());

            HttpStatus status = monoStatus.block();
            if(status == HttpStatus.OK){
                return true;
            }
            else if(status == HttpStatus.NOT_FOUND){
                return false;
            }
            else{
                log.warn("Vehicle API responded with status {} for vehicle id {}. Vehicle can't be validated",status.value(),vehicleId);
                return false;
            }
        }
        catch (Exception ex){
            log.error("Vehicle API is having problems.", ex);
            return false;
        }
    }
}
