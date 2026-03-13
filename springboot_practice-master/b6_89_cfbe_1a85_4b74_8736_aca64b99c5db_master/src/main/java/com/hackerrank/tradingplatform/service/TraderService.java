package com.hackerrank.tradingplatform.service;

import com.hackerrank.tradingplatform.dto.AddMoneyTraderDTO;
import com.hackerrank.tradingplatform.dto.UpdateTraderDTO;
import com.hackerrank.tradingplatform.model.Trader;
import com.hackerrank.tradingplatform.repository.TraderRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TraderService {
    @Autowired
    private TraderRepository traderRepository;

    public ResponseEntity<Void> registerTrader(Trader trader) {
        if(traderRepository.findByEmail(trader.getEmail()).isPresent()){
            return ResponseEntity.status(400).build();
        }
        trader.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        trader.setUpdatedAt(trader.getCreatedAt());
        traderRepository.save(trader);
        return ResponseEntity.status(201).body(null);
    }

    public Trader getTraderById(Long id) {
        return traderRepository.findById(id).get();
    }

    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email).orElse(null);
    }

    public List<Trader> getAllTraders() {
        return traderRepository.findAll();
    }

    public ResponseEntity<Void> updateTrader(UpdateTraderDTO trader) {
        Optional<Trader> existingTrader = traderRepository.findByEmail(trader.getEmail());
        if(!existingTrader.isPresent())return ResponseEntity.status(404).build();
        Trader t = existingTrader.get();
        t.setName(trader.getName());
        t.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        traderRepository.save(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> addMoney(AddMoneyTraderDTO trader) {
        Optional<Trader> existingTrader = traderRepository.findByEmail(trader.getEmail());
        if(!existingTrader.isPresent())return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Trader t = existingTrader.get();
        t.setBalance(t.getBalance()+trader.getAmount());
        t.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        traderRepository.save(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
