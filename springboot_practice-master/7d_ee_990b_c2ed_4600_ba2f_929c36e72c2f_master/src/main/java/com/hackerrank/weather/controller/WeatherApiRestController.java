package com.hackerrank.weather.controller;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {
    private final WeatherRepository weatherRepository;

    public WeatherApiRestController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @PostMapping
    public ResponseEntity<Weather> createWeatherRecord(@RequestBody Weather request){
        return new ResponseEntity<>(weatherRepository.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getAll(){
        return new ResponseEntity<>(weatherRepository.findAll(Sort.by(Sort.Direction.ASC,"id")),HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> findWeatherById(@PathVariable Integer id){
        Optional<Weather> isExistingRecord = weatherRepository.findById(id);
        return isExistingRecord.map(weather -> new ResponseEntity<>(weather, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWeatherById(@PathVariable Integer id){
        Optional<Weather> isExisting = weatherRepository.findById(id);
        if(isExisting.isPresent()){
            weatherRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
