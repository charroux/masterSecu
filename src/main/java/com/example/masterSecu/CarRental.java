package com.example.masterSecu;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class CarRental {

    List<Car> cars = new ArrayList<Car>();

    public CarRental(){
        Car car = new Car("AA11BB", "Ferrari", 2000);
        cars.add(car);
        car = new Car("CC22DD", "Porsche", 3000);
        cars.add(car);
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return cars;
    }

    /**
     * Navigateur http://localhost:8080/cars/CC22DD
     * @param plateNumber
     * @return
     * @throws Exception
     */
    @GetMapping("/cars/{plateNumber}")
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception{
        Iterator<Car> iterator = cars.iterator();
        Car car = null;
        while(iterator.hasNext() && (car=iterator.next()).getPlateNumber().equals(plateNumber)==false){
        }
        if(car != null){
            return car;
        } else {
            return null;
        }
    }

    /**
     * Navigateur http://localhost:8080/cars/CC22DD?rent=true
     * @param plateNumber
     * @param rent
     * @param dates
     */
    @PutMapping(value = "/cars/{plateNumber}")
    public void rent(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value="rent", required = true)boolean rent,
            @RequestBody Dates dates) throws Exception {

        Car car = this.aCar(plateNumber);
        if(car != null){
            car.setRented(true);
        }

    }
}
