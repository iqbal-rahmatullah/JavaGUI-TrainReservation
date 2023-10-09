package trainreservations;


import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Tiket {
    private Customer customer;
    private Kereta kereta;
    private LocalDate date;
    private Seat seat;

    public Tiket(Customer customer, Kereta kereta, LocalDate date, Seat seat) {
        this.customer = customer;
        this.kereta = kereta;
        this.date = date;
        this.seat = seat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Kereta getKereta() {
        return kereta;
    }

    public LocalDate getDate() {
        return date;
    }

    public Seat getSeat() {
        return seat;
    }
    
    
    
}
