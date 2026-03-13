package com.hackerrank.tradingplatform.controller;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.TraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/trading/traders")
public class TraderController {
    @Autowired
    private TraderService traderService;

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> registerTrader(@RequestBody Trader trader) {
        return traderService.registerTrader(trader);
    }

    //get by email
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TraderDTO> getTraderByEmail(@RequestParam("email") String email) {
        Trader traderByEmail = traderService.getTraderByEmail(email);
        if(traderByEmail == null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(new TraderDTO(traderByEmail), HttpStatus.OK);
    }

    //get all
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Trader> getAllTraders() {
        return traderService.getAllTraders();
    }

    //update by email
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateTrader(@RequestBody @Valid UpdateTraderDTO trader) {
       return traderService.updateTrader(trader);
    }

    //add money
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addMoney(@RequestBody @Valid AddMoneyTraderDTO trader) {
       return traderService.addMoney(trader);
    }
}
