package trainreservations;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Seat {
    private String noSeat;
    private int Gerbong;
    private String tipe;

    public Seat(String noSeat, int Gerbong, String tipe) {
        this.noSeat = noSeat;
        this.Gerbong = Gerbong;
        this.tipe = tipe;
    }

    public String getNoSeat() {
        return noSeat;
    }

    public int getGerbong() {
        return Gerbong;
    }

    public String getTipe() {
        return tipe;
    }

    @Override
    public String toString() {
        return "Seat{" + "noSeat=" + noSeat + ", Gerbong=" + Gerbong + ", tipe=" + tipe + '}';
    }
    
    
}
