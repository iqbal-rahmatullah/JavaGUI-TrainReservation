package trainreservations;

import java.time.LocalDate;
import java.time.Month;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ASUS
 */
public class Main {

    public static void main(String[] args) {
        Seat[] seats = new Seat[45];
        Customer cust1 = new Customer("Awal", "818181", "3122");
        Customer cust2 = new Customer("raya", "818223", "32022");
        Customer cust3 = new Customer("iqbl", "91911", "789122");

        Kereta kereta1 = new Kereta("Majapahit", "Surabaya", "Jakarta", "Lokal");
        Kereta kereta2 = new Kereta("Arjuna", "Surabaya", "Bandung", "Antarkota");

        Seat seat1 = new Seat("15A", 1, "bisnis");
        Seat seat2 = new Seat("30E", 2, "bisnis");
        Seat seat3 = new Seat("17B", 3, "eksekutif");
        Seat seat4 = new Seat("7B", 4, "eksekutif");
        Seat seat5 = new Seat("18C", 5, "eksekutif");

        kereta1.addSeat(seat1);
        kereta1.addSeat(seat2);
        kereta1.addSeat(seat3);
        kereta1.addSeat(seat4);
        kereta1.addSeat(seat5);

        Tiket tiket1 = new Tiket(cust1, kereta1, LocalDate.of(2023, Month.DECEMBER, 10), seat1);
        Tiket tiket2 = new Tiket(cust2, kereta1, LocalDate.of(2023, Month.DECEMBER, 10), seat2);

    }
;

}
