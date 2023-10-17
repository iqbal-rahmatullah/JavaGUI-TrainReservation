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

    /**
     *
     * @return mengambil nomor seat / kursi
     */
    public String getNoSeat() {
        return noSeat;
    }

    /**
     *
     * @return mengambil/mendapatkan gerbong kereta
     */
    public int getGerbong() {
        return Gerbong;
    }

    /**
     *
     * @return mengambil tipe seat / kursi
     */
    public String getTipe() {
        return tipe;
    }

    /**
     *
     * @return menampilkan data seat dalam gerbong
     */
    @Override
    public String toString() {
        return "Seat{" + "noSeat=" + noSeat + ", Gerbong=" + Gerbong + ", tipe=" + tipe + '}';
    }
    
    
}
