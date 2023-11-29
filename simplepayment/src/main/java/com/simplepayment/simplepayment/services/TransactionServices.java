package com.simplepayment.simplepayment.services;


import com.simplepayment.simplepayment.domain.transaction.Transaction;
import com.simplepayment.simplepayment.domain.user.User;
import com.simplepayment.simplepayment.dtos.TransactionDTO;
import com.simplepayment.simplepayment.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionServices {

    @Autowired
    private UserServices userServices;
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{

        User sender = this.userServices.findUserById(transaction.senderId());
        User receiver = this.userServices.findUserById(transaction.receiverId());

        boolean isAtuhorizad = this.authorizeTransaction(sender, transaction.value());

        if(!isAtuhorizad){
            throw new Exception("Transacao nao autorizada");
        }

        Transaction newtransaction = new Transaction();

        newtransaction.setAmount(transaction.value());
        newtransaction.setSender(sender);
        newtransaction.setReceiver(receiver);
        newtransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newtransaction);
        userServices.SaveUser(sender);
        userServices.SaveUser(receiver);

        this.notificationService.sendNotification(sender, "transacao enviada com sucesso");
        this.notificationService.sendNotification(receiver, "transacao recebida com sucesso");


        return newtransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("http://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else{
            return false;
        }
    }
}
