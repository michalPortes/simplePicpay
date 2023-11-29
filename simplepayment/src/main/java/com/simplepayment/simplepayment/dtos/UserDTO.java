package com.simplepayment.simplepayment.dtos;

import com.simplepayment.simplepayment.domain.user.User;
import com.simplepayment.simplepayment.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
