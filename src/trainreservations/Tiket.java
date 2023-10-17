package trainreservations;


import java.time.LocalDate;
import java.util.Date;

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
    private Date date;
    private Seat seat;

    public Tiket(Customer customer, Kereta kereta, Date date, Seat seat) {
        this.customer = customer;
        this.kereta = kereta;
        this.date = date;
        this.seat = seat;
    }

    /**
     *
     * @return mengambil data customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @return mengambil data kereta
     */
    public Kereta getKereta() {
        return kereta;
    }

    /**
     *
     * @return mengambil data tanggal
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return mengambil data seat
     */
    public Seat getSeat() {
        return seat;
    }
    
    
    
}
