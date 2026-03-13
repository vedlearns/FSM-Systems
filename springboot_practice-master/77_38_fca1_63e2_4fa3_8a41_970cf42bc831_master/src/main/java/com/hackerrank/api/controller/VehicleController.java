package com.hackerrank.api.controller;

import com.hackerrank.api.exception.BadRequestException;
import com.hackerrank.api.exception.ElementNotFoundException;
import com.hackerrank.api.model.Vehicle;
import com.hackerrank.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class VehicleController {
  private final VehicleService vehicleService;

  @Autowired
  public VehicleController(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
  }

  @GetMapping("/vehicle")
  public ResponseEntity<List<Vehicle>> getAllVehicles(){
      return new ResponseEntity<>(vehicleService.getAllVehicle(), HttpStatus.OK);
  }

  @PostMapping(value = "/vehicle")
  public ResponseEntity<Vehicle> createVehicle( @RequestBody Vehicle vehicle) {

      try{
          Vehicle newVehicle = vehicleService.createNewVehicle(vehicle);
          return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
      }catch (BadRequestException e){
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
  }

  @GetMapping(value = "/vehicle/{id}")
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
      if(id<=0 ) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      try{
          Vehicle vehicleById = vehicleService.getVehicleById(id);
          return new ResponseEntity<>(vehicleById, HttpStatus.OK);
      }catch (ElementNotFoundException e){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
}
