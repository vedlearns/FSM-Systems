package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trades")
public class StockTradeRestController {
    private final StockTradeRepository repository;

    public StockTradeRestController(StockTradeRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<StockTrade> createStockTrade(@RequestBody StockTrade request){
        return new ResponseEntity<>(repository.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StockTrade>> getAllStocks(){
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<StockTrade> getStockTradeById(@PathVariable Integer id){
        return repository.findById(id).map((s)->new ResponseEntity<>(s,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockTrade(@PathVariable Integer id){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> putStockTrade(@PathVariable Integer id){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchStockTrade(@PathVariable Integer id){
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
