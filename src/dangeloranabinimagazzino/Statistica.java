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
    
    public int prodottoPiuCostoso() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto costoso = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getPrezzoV() > costoso.getPrezzoV()) {
                costoso = p; 
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

        Prodotto economico = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getPrezzoV() < economico.getPrezzoV()) {
                economico = p; 
            }
        }
        return economico.getId();
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

        Prodotto massimo = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getScorta() > massimo.getScorta()) {
                massimo = p;
            }
        }
        return massimo.getId();
    }
    
   public int prodottoMenoScorta() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return 0;
        }

        Prodotto minimo = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getScorta() < minimo.getScorta()) {
                minimo = p;
            }
        }
        return minimo.getId();
    }
}
