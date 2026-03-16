/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dangeloranabinimagazzino;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author dangelo.gregorio
 */
public class GestioneKey {

    private String nomeFile;

    public GestioneKey() {
        this.nomeFile = "key.txt";
    }

    public GestioneKey(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public String getNomeFile() {
        return nomeFile;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public int posizione(int id) { 
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] parti = riga.split(";");
                if (Integer.parseInt(parti[0]) == id) {
                    return Integer.parseInt(parti[1]);  
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file della key non trovato.");
        } catch (IOException e) {
            System.out.println("errore lettura indice: " + e.getMessage());
        }
        return -1;
    }
    
    
    public void aggiungiRiga(int id, int posizione) { 
        try (PrintWriter pw = new PrintWriter(new FileWriter(nomeFile, true))) {
            pw.println(id + ";" + posizione);
        } catch (IOException e) {
            System.out.println("errore scrittura indice: " + e.getMessage());
        }
    }
}
