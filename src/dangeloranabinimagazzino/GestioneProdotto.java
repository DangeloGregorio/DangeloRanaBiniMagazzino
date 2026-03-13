/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dangeloranabinimagazzino;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author dangelo.gregorio
 */
public class GestioneProdotto {

    private static int lunghezzaNome = 30;
    private static int dimRecord = 4 + (lunghezzaNome * 2) + 8 + 8 + 4 + 4 + 4;

    private String nomeFile = "prodotto.dat";

    private GestioneKey gestioneKey;

    public GestioneProdotto() {
        this.gestioneKey = new GestioneKey("indice.txt");
        
    }
    
    public void scrivi(Prodotto p) {
    try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "rw")) {
        long posizione = raf.length(); // posizione in fondo al file
        raf.seek(posizione);

        raf.writeInt(p.getId());
        scriviStringaFissa(raf, p.getNome());
        raf.writeDouble(p.getPrezzoA());
        raf.writeDouble(p.getPrezzoV());
        raf.writeInt(p.getScorta());
        raf.writeInt(p.getScortaMin());
        raf.writeInt(p.getProVenduti());

        gestioneKey.aggiungiRiga(p.getId(), posizione);
    } catch (IOException e) {
        System.out.println("errore scrittura: " + e.getMessage());
    }
}
}
