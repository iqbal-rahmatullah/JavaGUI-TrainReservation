package trainreservations;


import java.util.LinkedList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Kereta {
    private String nama;
    private String asal;
    private String tujuan;
    private List<Seat> seatList;
    private String tipeKereta;

    public Kereta(String nama, String asal, String tujuan, String tipeKereta) {
        this.nama = nama;
        this.asal = asal;
        this.tujuan = tujuan;
        this.tipeKereta = tipeKereta;
        seatList = new LinkedList<Seat> ();
        
    }
    public boolean addSeat(Seat s){
        seatList.add(s);
        return true;
    }
    
    public String getNama() {
        return nama;
    }

    public String getAsal() {
        return asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public String getTipeKereta() {
        return tipeKereta;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }
    public void configureSeat() {
        Seat[] allSeat = new Seat[30];
        char code = 'A';
        int gerbong = 1;
        for(int i=0;i<allSeat.length;i++) {
            allSeat[i] = new Seat( String.valueOf(i + 1) + code, gerbong, "Ekonomi");
            
            //setiap kelipatan 10
            if(i % 10 == 0) {
                gerbong++;
            }
            //Setiap kelipatan 3
            if(i % 3 == 0 && i >= 3) {
                code++;
            }
            this.addSeat(allSeat[i]);
        }
    }
    
    
    
    
}
