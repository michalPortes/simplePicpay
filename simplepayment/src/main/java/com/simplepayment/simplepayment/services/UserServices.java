package com.simplepayment.simplepayment.services;

import com.simplepayment.simplepayment.domain.user.User;
import com.simplepayment.simplepayment.domain.user.UserType;
import com.simplepayment.simplepayment.dtos.UserDTO;
import com.simplepayment.simplepayment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{

        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario logista nao esta autorizado a realizar transacoes");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.SaveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
    public void SaveUser(User user){
        this.repository.save(user);
    }
}
