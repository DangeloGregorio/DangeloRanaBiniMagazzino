/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package dangeloranabinimagazzino;
import java.util.List;
/**
 *
 * @author dangelo.gregorio
 */
public class FrameMagazzino extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrameMagazzino.class.getName());

    private GestioneProdotto gp;
    private GestioneKey k;
    /**
     * Creates new form FrameMagazzino
     */
    public FrameMagazzino() {
        initComponents();
        gp = new GestioneProdotto();
        k = new GestioneKey();
        caricaProdotti();

        // Popola la combo delle statistiche
        cmbStatistiche.removeAllItems();
        cmbStatistiche.addItem("Prodotto più venduto");
        cmbStatistiche.addItem("Prodotto meno venduto");
        cmbStatistiche.addItem("Prodotto più costoso");
        cmbStatistiche.addItem("Prodotto più economico");
        cmbStatistiche.addItem("Prodotto con più scorta");
        cmbStatistiche.addItem("Prodotto con meno scorta");
    }

    private void caricaProdotti(){
        List <Prodotto> prodotti = gp.leggiTutti();
        cmbProdotti.removeAllItems();
        for(Prodotto p : prodotti){
            String voce = p.getId() + " - " + p.getNome();
            cmbProdotti.addItem(voce);
        }
    }
    
    private void mostraStatistica() {
        String selezionato = (String) cmbProdotti.getSelectedItem();
        if (selezionato == null) {
            return;
        }

        int id = Integer.parseInt(selezionato.split(" - ")[0]);
        Prodotto p = gp.leggi(id);
        if (p == null) {
            return;
        }

        String testo
                = "=== DATI PRODOTTO ===" + "\n"
                + "Nome: " + p.getNome() + "\n"
                + "Prezzo acquisto: " + p.getPrezzoA() + " €\n"
                + "Prezzo vendita: " + p.getPrezzoV() + " €\n"
                + "Scorta: " + p.getScorta() + "\n"
                + "Scorta minima: " + p.getScortaMin() + "\n"
                + "Prodotti venduti:" + p.getProVenduti() + "\n";

        txtArea.setText(testo);
    }

    private void rimuoviProdotto() {
        String selezionato = (String) cmbProdotti.getSelectedItem();
        if (selezionato == null) {
            return;
        }

        int id = Integer.parseInt(selezionato.split(" - ")[0]);

        int conferma = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Vuoi rimuovere il prodotto:\n" + selezionato + "?",
                "Conferma rimozione",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (conferma == javax.swing.JOptionPane.YES_OPTION) {
            gp.rimuovi(id);
            caricaProdotti();
            txtArea.setText("Prodotto rimosso con successo.");
            txtArea.setForeground(java.awt.Color.WHITE);
        }
    }
    
    private void aggiungiScorta(int n) {
        String selezionato = (String) cmbProdotti.getSelectedItem();
        if (selezionato == null) {
            return;
        }

        int id = Integer.parseInt(selezionato.split(" - ")[0]);
        Prodotto p = gp.leggi(id);
        if (p == null) {
            return;
        }

        p.setScorta(p.getScorta() + n);
        gp.aggiorna(p);
        txtArea.setText("Scorta aggiunta. "+"\n"
                + "Nuova scorta: " + p.getScorta());
    }

    private void rimuoviScorta(int n) {
        String selezionato = (String) cmbProdotti.getSelectedItem();
        if (selezionato == null) {
            return;
        }

        int id = Integer.parseInt(selezionato.split(" - ")[0]);
        Prodotto p = gp.leggi(id);
        if (p == null) {
            return;
        }
        
        if (p.getScorta() - n < p.getScortaMin()) {
            txtArea.setText("la scorta non può scendere"+"\n"
                    + "sotto la scorta minima (" + p.getScortaMin() + ").");
            return;
        }

        p.setScorta(p.getScorta() - n);
        p.setProVenduti(p.getProVenduti() + n);
        gp.aggiorna(p);                
        txtArea.setText("scorta rimossa. "
                + "Nuova scorta: " + p.getScorta());
    }
    
    private void cerca() {
        String filtro = (String) cmbStatistiche.getSelectedItem();
        if (filtro == null) {
            return;
        }

        Statistica s = new Statistica(0, "", 0, 0, 0, 0, 0);
        int id = 0;

        switch (filtro) {
            case "Prodotto più venduto":
                id = s.prodottoPiuVenduto();
                break;
            case "Prodotto meno venduto":
                id = s.prodottoMenoVenduto();
                break;
            case "Prodotto più costoso":
                id = s.prodottoPiuCostoso();
                break;
            case "Prodotto più economico":
                id = s.prodottoPiuEconomico();
                break;
            case "Prodotto con più scorta":
                id = s.prodottoPiuScorta();
                break;
            case "Prodotto con meno scorta":
                id = s.prodottoMenoScorta();
                break;
        }

        Prodotto p = gp.leggi(id);
        if (p == null) {
            txtArea.setText("Nessun prodotto trovato.");
            return;
        }

        txtArea.setText(
                "=== RISULTATO FILTRO ===" + "\n"
                + "Filtro: " + filtro + "\n"
                + "Nome: " + p.getNome() + "\n"
                + "Prezzo acquisto: " + p.getPrezzoA() + " €\n"
                + "Prezzo vendita: " + p.getPrezzoV() + " €\n"
                + "Scorta: " + p.getScorta() + "\n"
                + "Scorta minima: " + p.getScortaMin() + "\n"
                + "Prodotti venduti: " + p.getProVenduti() + "\n"
        );
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbStatistiche = new javax.swing.JComboBox<>();
        btnRimuoviScorta = new javax.swing.JButton();
        btnRimuovi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtScorta = new javax.swing.JTextField();
        btnStatistica1 = new javax.swing.JButton();
        btnAggiungiScorta = new javax.swing.JButton();
        cmbProdotti = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCerca = new javax.swing.JButton();
        sfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbStatistiche.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cmbStatistiche, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 412, 260, -1));

        btnRimuoviScorta.setText("Rimuovi");
        btnRimuoviScorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRimuoviScortaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRimuoviScorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 90, 30));

        btnRimuovi.setText("Rimuovi");
        btnRimuovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRimuoviActionPerformed(evt);
            }
        });
        getContentPane().add(btnRimuovi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 190, 30));

        txtArea.setBackground(new java.awt.Color(4, 8, 72));
        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Segoe UI", 0, 25)); // NOI18N
        txtArea.setForeground(new java.awt.Color(255, 255, 255));
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 460, 310));

        txtScorta.setForeground(new java.awt.Color(153, 153, 153));
        txtScorta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtScorta.setText("Scorta...");
        txtScorta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtScortaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtScortaFocusLost(evt);
            }
        });
        getContentPane().add(txtScorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 70, 30));

        btnStatistica1.setText("Statistica");
        btnStatistica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatistica1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnStatistica1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 190, 30));

        btnAggiungiScorta.setText("Aggiungi");
        btnAggiungiScorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAggiungiScortaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAggiungiScorta, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 90, 30));

        cmbProdotti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProdottiActionPerformed(evt);
            }
        });
        getContentPane().add(cmbProdotti, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 190, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FILTRI");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, 100, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PRODOTTO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 170, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("AGGIUNGI E VENDI SCORTE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 210, -1));

        btnCerca.setText("Cerca");
        btnCerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCercaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerca, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 450, 260, 30));

        sfondo.setForeground(new java.awt.Color(255, 255, 255));
        sfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dangeloranabinimagazzino/sfondo.jpg"))); // NOI18N
        sfondo.setAutoscrolls(true);
        sfondo.setFocusable(false);
        getContentPane().add(sfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtScortaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtScortaFocusGained
        if (txtScorta.getText().equals("Scorta...")) {
            txtScorta.setText("");
            txtScorta.setForeground(java.awt.Color.BLACK); // Colore testo normale
        }
    }//GEN-LAST:event_txtScortaFocusGained

    private void txtScortaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtScortaFocusLost
        if (txtScorta.getText().isEmpty()) {
            txtScorta.setForeground(java.awt.Color.GRAY); // Colore effetto suggerimento
            txtScorta.setText("Scorta...");
        }
    }//GEN-LAST:event_txtScortaFocusLost

    private void cmbProdottiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProdottiActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbProdottiActionPerformed

    private void btnStatistica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatistica1ActionPerformed
        // TODO add your handling code here:
        mostraStatistica();
    }//GEN-LAST:event_btnStatistica1ActionPerformed

    private void btnRimuoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRimuoviActionPerformed
        // TODO add your handling code here:
        rimuoviProdotto();
    }//GEN-LAST:event_btnRimuoviActionPerformed

    private void btnAggiungiScortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAggiungiScortaActionPerformed
        // TODO add your handling code here:
        String testo = txtScorta.getText();
        int n = Integer.parseInt(testo);
        aggiungiScorta(n);
    }//GEN-LAST:event_btnAggiungiScortaActionPerformed

    private void btnRimuoviScortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRimuoviScortaActionPerformed
        // TODO add your handling code here:
        String testo = txtScorta.getText();
        int n = Integer.parseInt(testo);
        rimuoviScorta(n);
    }//GEN-LAST:event_btnRimuoviScortaActionPerformed

    private void btnCercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCercaActionPerformed
        // TODO add your handling code here:
        cerca();
    }//GEN-LAST:event_btnCercaActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAggiungiScorta;
    private javax.swing.JButton btnCerca;
    private javax.swing.JButton btnRimuovi;
    private javax.swing.JButton btnRimuoviScorta;
    private javax.swing.JButton btnStatistica1;
    private javax.swing.JComboBox<String> cmbProdotti;
    private javax.swing.JComboBox<String> cmbStatistiche;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel sfondo;
    private javax.swing.JTextArea txtArea;
    private javax.swing.JTextField txtScorta;
    // End of variables declaration//GEN-END:variables
}
