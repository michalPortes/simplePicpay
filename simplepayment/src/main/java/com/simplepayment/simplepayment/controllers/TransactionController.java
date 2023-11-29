package com.simplepayment.simplepayment.controllers;

import com.simplepayment.simplepayment.domain.transaction.Transaction;
import com.simplepayment.simplepayment.dtos.TransactionDTO;
import com.simplepayment.simplepayment.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionServices transactionServices;


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception{
            Transaction newTransaction = this.transactionServices.createTransaction(transaction);
            return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }

}
