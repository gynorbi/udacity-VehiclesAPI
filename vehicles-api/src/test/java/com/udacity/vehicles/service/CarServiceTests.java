package com.udacity.vehicles.service;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTests {

    @Mock
    private CarRepository repository;

    @Mock
    private PriceClient priceClient;

    @Mock
    private MapsClient mapsClient;

    @InjectMocks
    private CarService carService;

    private final String ADDRESS = "New Street 1, NY";
    private final String PRICE = "USD 1000";

    @Before
    public void setup() {
        Car car = getCar();
        car.setId(1L);
        Location updatedLocation = new Location(car.getLocation().getLat(), car.getLocation().getLon());
        updatedLocation.setAddress(ADDRESS);
        given(priceClient.getPrice(any())).willReturn(PRICE);
        given(repository.findById(any())).willReturn(Optional.of(car));
        given(repository.save(any())).will(i -> i.getArguments()[0]);
        given(mapsClient.getAddress(any())).willReturn(updatedLocation);
    }

    @Test
    public void getCarWithPriceAndAddress(){
        Car car = carService.findById(1L);
        Assert.assertEquals("Car should have price of USD 1000",PRICE, car.getPrice());
        Assert.assertEquals("Car should have address", ADDRESS, car.getLocation().getAddress());
    }

    @Test
    public void updateCarCondition(){
        Car originalCar = getCar();
        originalCar.setId(1L);
        originalCar.setCondition(Condition.NEW);

        Car updatedCar = carService.save(originalCar);

        Assert.assertEquals("Updated car should have condition NEW.", Condition.NEW, updatedCar.getCondition());
    }

    /**
     * Creates an example Car object for use in testing.
     * @return an example Car object
     */
    private Car getCar() {
        Car car = new Car();
        car.setLocation(new Location(40.730610, -73.935242));
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(101, "Chevrolet");
        details.setManufacturer(manufacturer);
        details.setModel("Impala");
        details.setMileage(32280);
        details.setExternalColor("white");
        details.setBody("sedan");
        details.setEngine("3.6L V6");
        details.setFuelType("Gasoline");
        details.setModelYear(2018);
        details.setProductionYear(2018);
        details.setNumberOfDoors(4);
        car.setDetails(details);
        car.setCondition(Condition.USED);
        return car;
    }
}
