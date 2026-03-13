/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dangeloranabinimagazzino;

/**
 *
 * @author dangelo.gregorio
 */
public class Prodotto {
    private int id;
    private String nome;
    private double prezzoA;
    private double prezzoV;
    private int scorta;
    private int scortaMin;
    private int proVenduti;

    public Prodotto(int id, String nome, double prezzoA, double prezzoV, int scorta, int scortaMin, int proVenduti) {
        this.id = id;
        this.nome = nome;
        this.prezzoA = prezzoA;
        this.prezzoV = prezzoV;
        this.scorta = scorta;
        this.scortaMin = scortaMin;
        this.proVenduti = proVenduti;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzoA() {
        return prezzoA;
    }

    public void setPrezzoA(double prezzoA) {
        this.prezzoA = prezzoA;
    }

    public double getPrezzoV() {
        return prezzoV;
    }

    public void setPrezzoV(double prezzoV) {
        this.prezzoV = prezzoV;
    }

    public int getScorta() {
        return scorta;
    }

    public void setScorta(int scorta) {
        this.scorta = scorta;
    }

    public int getScortaMin() {
        return scortaMin;
    }

    public void setScortaMin(int scortaMin) {
        this.scortaMin = scortaMin;
    }

    public int getProVenduti() {
        return proVenduti;
    }

    public void setProVenduti(int proVenduti) {
        this.proVenduti = proVenduti;
    }

    
}