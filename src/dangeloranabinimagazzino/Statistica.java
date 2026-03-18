/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package dangeloranabinimagazzino;
import java.util.*;
/**
 *
 * @author ranasgalla.niccolo
 */
public class Statistica extends Prodotto {

    public Statistica(int id, String nome, double prezzoA, double prezzoV, int scorta, int scortaMin, int proVenduti) {
        super(id, nome, prezzoA, prezzoV, scorta, scortaMin, proVenduti);
    }
    
    public int prodottoPiuVenduto() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto miglior = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getProVenduti() > miglior.getProVenduti()) {
                miglior = p;
            }
        }

        return miglior.getId();
    }
    
   public int prodottoPiuCostoso(){
       GestioneProdotto gp = new GestioneProdotto();
       List<Prodotto> lista = gp.leggiTutti();
       
       if (lista.isEmpty()) {
            return 0;
        }
       
        Prodotto costoso = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getPrezzoV() > costoso.getPrezzoV()) {
                costoso.setPrezzoV(p.getPrezzoV());
            }
        }
        
        return costoso.getId();
    }

    public int prodottoPiuEconomico() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto Economico = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getPrezzoV() < Economico.getPrezzoV()) {
                Economico.setPrezzoV(p.getPrezzoV());
            }
        }

        return Economico.getId();
    }

    public int prodottoMenoVenduto() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto peggiore = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getProVenduti() < peggiore.getProVenduti()) {
                peggiore = p;
            }
        }

        return peggiore.getId();
    }

    public int prodottoPiuScorta() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto scorta = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getScorta() > scorta.getScorta()) {
                scorta = p;
            }
        }

        return scorta.getId();
    }
    
    public int prodottoMenoScorta() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto scorta = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getScorta() < scorta.getScorta()) {
                scorta = p;
            }
        }

        return scorta.getId();
    }
}
