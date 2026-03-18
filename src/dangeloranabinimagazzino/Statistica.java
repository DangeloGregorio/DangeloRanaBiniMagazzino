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
    
    public String prodottoPiuVenduto() {
        GestioneProdotto gp = new GestioneProdotto();
        List<Prodotto> lista = gp.leggiTutti();

        if (lista.isEmpty()) {
            return "Nessun prodotto trovato.";
        }

        Prodotto miglior = lista.get(0);
        for (Prodotto p : lista) {
            if (p.getProVenduti() > miglior.getProVenduti()) {
                miglior = p;
            }
        }

        return "Prodotto più venduto: " + miglior.getNome()
                + " (ID: " + miglior.getId()
                + ", venduti: " + miglior.getProVenduti() + ")";
    }
}
