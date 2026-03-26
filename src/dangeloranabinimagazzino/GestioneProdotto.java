/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dangeloranabinimagazzino;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 *
 * @author dangelo.gregorio
 */
public class GestioneProdotto {

    private static int lunghezzaNome = 30;
    private static int dimRecord = 4 + (lunghezzaNome * 2) + 8 + 8 + 4 + 4 + 4;

    private String nomeFile = System.getProperty("user.dir") + "/prodotto.dat";

    private GestioneKey gestioneKey;

    public GestioneProdotto() {
        this.gestioneKey = new GestioneKey(System.getProperty("user.dir") + "/key.txt");

    }

    public void scrivi(Prodotto p) {
        try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "rw")) {
            int pos = (int) raf.length(); // posizione in fondo al file
            raf.seek(pos);

            raf.writeInt(p.getId());
            scriviStringaFissa(raf, p.getNome());
            raf.writeDouble(p.getPrezzoA());
            raf.writeDouble(p.getPrezzoV());
            raf.writeInt(p.getScorta());
            raf.writeInt(p.getScortaMin());
            raf.writeInt(p.getProVenduti());

            gestioneKey.aggiungiRiga(p.getId(), pos);
        } catch (IOException e) {
            System.out.println("errore scrittura: " + e.getMessage());
        }
    }

    public Prodotto leggi(int id) {
        int pos = gestioneKey.posizione(id);
        if (pos == -1) {
            System.out.println("prodotto non trovato.");
            return null;
        }
        try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "r")) {
            raf.seek(pos); // salta direttamente al record

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
            char[] carattere = new char[lunghezzaNome];
            java.util.Arrays.fill(carattere, ' ');
            s.getChars(0, Math.min(s.length(), lunghezzaNome), carattere, 0);
            for (char c : carattere) {
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

    public void rimuovi(int id) {
        // Legge tutti i prodotti, esclude quello con l'id da rimuovere,
        // riscrive il file e aggiorna la key
        List<Prodotto> lista = leggiTutti();

        try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "rw")) {
            raf.setLength(0); // svuota il file
            gestioneKey.rimuoviRiga(id); // rimuove dalla key

            for (Prodotto p : lista) {
                if (p.getId() == id) {
                    continue; // salta il prodotto da rimuovere
                }
                int pos = (int) raf.length();
                raf.seek(pos);

                raf.writeInt(p.getId());
                scriviStringaFissa(raf, p.getNome());
                raf.writeDouble(p.getPrezzoA());
                raf.writeDouble(p.getPrezzoV());
                raf.writeInt(p.getScorta());
                raf.writeInt(p.getScortaMin());
                raf.writeInt(p.getProVenduti());

                gestioneKey.aggiornaRiga(p.getId(), pos);
            }
        } catch (IOException e) {
            System.out.println("errore rimozione: " + e.getMessage());
        }
    }
    
    public void aggiorna(Prodotto p) {
    int pos = gestioneKey.posizione(p.getId());
    if (pos == -1) {
        System.out.println("prodotto non trovato per aggiornamento.");
        return;
    }
    try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "rw")) {
        raf.seek(pos);
        raf.writeInt(p.getId());
        scriviStringaFissa(raf, p.getNome());
        raf.writeDouble(p.getPrezzoA());
        raf.writeDouble(p.getPrezzoV());
        raf.writeInt(p.getScorta());
        raf.writeInt(p.getScortaMin());
        raf.writeInt(p.getProVenduti());
    } catch (IOException e) {
        System.out.println("errore aggiornamento: " + e.getMessage());
    }
}

    public List<Prodotto> leggiTutti() {
        List<Prodotto> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(nomeFile, "r")) {
            int numRecord = (int) raf.length() / dimRecord;
            for (int i = 0; i < numRecord; i++) {
                raf.seek(i * dimRecord);
                int id = raf.readInt();
                String nome = leggiStringaFissa(raf).trim();
                double prezzoA = raf.readDouble();
                double prezzoV = raf.readDouble();
                int scorta = raf.readInt();
                int scortaMin = raf.readInt();
                int proVenduti = raf.readInt();
                lista.add(new Prodotto(id, nome, prezzoA, prezzoV, scorta, scortaMin, proVenduti));
            }
        } catch (IOException e) {
            System.out.println("errore lettura tutti: " + e.getMessage());
        }
        return lista;
    }
}
