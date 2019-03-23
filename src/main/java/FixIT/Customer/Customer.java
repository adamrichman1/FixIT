package FixIT.Customer;

import FixIT.Core.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * This class represents a FixIT customer
 */
@JsonIgnoreProperties(ignoreUnknown =true)
public class Customer extends User {
    private String creditCard;
    private BigDecimal balance;

    public Customer() {}

    public Customer(String username) {
        super(username);
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
