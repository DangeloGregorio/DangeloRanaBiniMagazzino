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

    public Prodotto leggi(int id) {
        long posizione = gestioneKey.getPosizione(id);
        if (posizione == -1) {
            System.out.println("prodotto non trovato.");
            return null;
        }
        try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "r")) {
            raf.seek(posizione); // salta direttamente al record!

            int idLetto = raf.readInt();
            String nome = leggiStringaFissa(raf).trim();
            double prezzoA = raf.readDouble();
            double prezzoV = raf.readDouble();
            int scorta = raf.readInt();
            int scortaMin = raf.readInt();
            int proVenduti = raf.readInt();

            return new Prodotto(idLetto, nome, prezzoA, prezzoV, scorta, scortaMin, proVenduti);
        } catch (IOException e) {
            System.out.println("errore lettura: " + e.getMessage());
            return null;
        }
    }

    private void scriviStringaFissa(RandomAccessFile raf, String s) {
        try {
            char[] chars = new char[lunghezzaNome];
            java.util.Arrays.fill(chars, ' ');
            s.getChars(0, Math.min(s.length(), lunghezzaNome), chars, 0);
            for (char c : chars) {
                raf.writeChar(c);
            }
        } catch (IOException e) {
            System.out.println("errore scrittura stringa: " + e.getMessage());
        }
    }

    private String leggiStringaFissa(RandomAccessFile raf) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < lunghezzaNome; i++) {
                sb.append(raf.readChar());
            }
        } catch (IOException e) {
            System.out.println("errore lettura stringa: " + e.getMessage());
        }
        return sb.toString();
    }
}
