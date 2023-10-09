package trainreservations;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ASUS
 */
public class Customer {
    private String name;
    private String noHP;
    private String noKTP;

    public Customer(String name, String noHP, String noKTP) {
        this.name = name;
        this.noHP = noHP;
        this.noKTP = noKTP;
    }

    public String getName() {
        return name;
    }

    public String getNoHP() {
        return noHP;
    }

    public String getNoKTP() {
        return noKTP;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", noHP=" + noHP + ", noKTP=" + noKTP + '}';
    }

    
    
}
