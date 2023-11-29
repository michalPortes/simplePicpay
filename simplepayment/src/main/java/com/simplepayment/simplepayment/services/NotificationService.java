package com.simplepayment.simplepayment.services;

import com.simplepayment.simplepayment.domain.user.User;
import com.simplepayment.simplepayment.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String mensage) throws Exception{

        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, mensage);


//        ResponseEntity<String> notificationResponse =  restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationRequest, String.class);
//
//        if(notificationResponse.getStatusCode() != HttpStatus.OK){
//            System.out.println("Erro ao enviar notificacao");
//            throw new Exception("Servico de notificacao fora do ar!");
//        }

        System.out.println("notificacao enviada");
    }
}
