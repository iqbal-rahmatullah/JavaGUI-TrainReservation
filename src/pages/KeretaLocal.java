/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import trainreservations.*;
import javax.swing.*;

/**
 *
 * @author iqbalrahmatullah
 */
public class KeretaLocal extends javax.swing.JFrame {

    /**
     * Creates new form KeretaLocal
     */
    String asal, tujuan;
    Kereta[] kereta = new Kereta[10];
    public FormBooking formBook = new FormBooking();
    //Untuk penformatan tanggal
    SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM y");

    public KeretaLocal() {
        initComponents();

        kereta[0] = new Kereta("Majapahit", "Surabaya", "Sidoarjo", "Lokal");
        kereta[1] = new Kereta("Arjuna", "Surabaya", "Lamongan", "Lokal");
        kereta[2] = new Kereta("Argo Wilis", "Surabaya", "Bojonegoro", "Lokal");
        kereta[3] = new Kereta("Turangga", "Surabaya", "Ngawi", "Lokal");
        kereta[4] = new Kereta("Purwojaya", "Surabaya", "Nganjuk", "Lokal");
        kereta[5] = new Kereta("Taksaka", "Surabaya", "Madiun", "Lokal");
        kereta[6] = new Kereta("Mahahan", "Surabaya", "Ngawi", "Lokal");
        kereta[7] = new Kereta("Bima", "Surabaya", "Pasuruan", "Lokal");
        kereta[8] = new Kereta("Gajayana", "Surabaya", "Malang", "Lokal");
        kereta[9] = new Kereta("Brawijaya", "Surabaya", "Jombang", "Lokal");

        //Configure seat kereta
        for (int i = 0; i < kereta.length; i++) {
            kereta[i].configureSeat();
        }

        dateField.setMinSelectableDate(new Date());

        addInputComboBox();
//        kereta1.addSeat(seat1);
//        kereta1.addSeat(seat2);
//        kereta1.addSeat(seat3);
//        kereta1.addSeat(seat4);
//        kereta1.addSeat(seat5);
    }

    public void addInputComboBox() {
        for (int i = 0; i < kereta.length; i++) {
            if (kereta[i] != null) {
                // Cek apakah asal sudah ada dalam combo box
                boolean asalExists = false;
                for (int j = 0; j < inputAsal.getItemCount(); j++) {
                    if (inputAsal.getItemAt(j).equals(kereta[i].getAsal())) {
                        asalExists = true;
                        break;
                    }
                }
                if (!asalExists) {
                    inputAsal.addItem(kereta[i].getAsal());
                }

                // Cek apakah tujuan sudah ada dalam combo box
                boolean tujuanExists = false;
                for (int j = 0; j < inputTujuan.getItemCount(); j++) {
                    if (inputTujuan.getItemAt(j).equals(kereta[i].getTujuan())) {
                        tujuanExists = true;
                        break;
                    }
                }
                if (!tujuanExists) {
                    inputTujuan.addItem(kereta[i].getTujuan());
                }
            }
        }
    }

    public void AddButtonListSeat(Kereta kereta) {
        JButton[] listSeat = new JButton[kereta.getSeatList().size()];
        int buttonWidth = 70;
        int buttonHeight = 30;
        int horizontalSpacing = 10;
        int verticalSpacing = 10;
        int columns = seatPanel.getWidth() / (buttonWidth + horizontalSpacing);
        int x = 0;
        int y = 0;

        for (int i = 0; i < listSeat.length; i++) {
            listSeat[i] = new JButton(kereta.getSeatList().get(i).getNoSeat());
            listSeat[i].setBounds(x, y, buttonWidth, buttonHeight);
            seatPanel.add(listSeat[i]);
            if(kereta.getSeatList().get(i).getTipe().equalsIgnoreCase("Bisnis")) {
                listSeat[i].setBackground(Color.ORANGE);
            }else if(kereta.getSeatList().get(i).getTipe().equalsIgnoreCase("Eksekutif")) {
                listSeat[i].setBackground(Color.GREEN);
            }
            //Mengecek apakah ticket sudah laku
            for (int j = 0; j < FormBooking.allTicket.size(); j++) {
                if (kereta.getNama().equalsIgnoreCase(FormBooking.allTicket.get(j).getKereta().getNama())) {
                    if (kereta.getSeatList().get(i).getNoSeat().equals(FormBooking.allTicket.get(j).getSeat().getNoSeat())) {
                        //Mengecek tanggal ticket yang sudah sold
                        if (dateFormat.format(dateField.getCalendar().getTime()).equals(dateFormat.format(FormBooking.allTicket.get(j).getDate()))) {
                            listSeat[i].setEnabled(false);
                        } else {
                            listSeat[i].setEnabled(true);
                        }
                    }
                }
            }
            final int seatIndex = i;
            listSeat[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Mengambil Input dateField dan melakukan formatting
                    Date selectedDate = dateField.getCalendar().getTime();

                    //Mengirim data ke formBooking
                    formBook.keretaSelected = kereta;
                    formBook.seatSelected = kereta.getSeatList().get(seatIndex);
                    formBook.dateSelected = dateField.getCalendar().getTime();

                    formBook.keretaField.setText(kereta.getNama() + " ( " + kereta.getAsal() + " - " + kereta.getTujuan() + " )");
                    formBook.seatField.setText(kereta.getSeatList().get(seatIndex).getNoSeat());
                    formBook.tanggalField.setText(dateFormat.format(selectedDate));
                    formBook.setVisible(true);
                    setVisible(false);
                }
            }
            );

            x += buttonWidth + horizontalSpacing; // Pindah ke kolom berikutnya

            // Jika sudah melewati panjang panel, geser ke baris berikutnya
            if (x + buttonWidth > seatPanel.getWidth()) {
                x = 0;
                y += buttonHeight + verticalSpacing; // Geser ke baris berikutnya
            }
        }

        // Set ukuran panel sesuai dengan jumlah tombol
        int panelHeight = ((listSeat.length - 1) / columns + 1) * (buttonHeight + verticalSpacing);
        seatPanel.setPreferredSize(new Dimension(seatPanel.getWidth(), panelHeight));
        seatPanel.revalidate(); // Revalidate panel untuk memastikan layout yang benar
    }

    public Kereta searchKereta(String asal, String tujuan) {
        Kereta temp = null;
        for (int i = 0; i < kereta.length; i++) {
            if (kereta[i].getAsal().equalsIgnoreCase(asal) && kereta[i].getTujuan().equalsIgnoreCase(tujuan)) {
                temp = kereta[i];
                break;
            }
        }
        return temp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listSeatFrame = new javax.swing.JFrame();
        rightPanel = new javax.swing.JPanel();
        Applogo = new javax.swing.JLabel();
        Train = new javax.swing.JLabel();
        Train1 = new javax.swing.JLabel();
        buttonHome = new javax.swing.JButton();
        bisnisTanda = new javax.swing.JPanel();
        BisnisLabel = new javax.swing.JLabel();
        EksekutifTanda = new javax.swing.JPanel();
        eksekutifLabel = new javax.swing.JLabel();
        inputAsal = new javax.swing.JComboBox<>();
        inputTujuan = new javax.swing.JComboBox<>();
        btn_submit = new javax.swing.JButton();
        seatPanel = new javax.swing.JPanel();
        dateField = new com.toedter.calendar.JDateChooser();

        javax.swing.GroupLayout listSeatFrameLayout = new javax.swing.GroupLayout(listSeatFrame.getContentPane());
        listSeatFrame.getContentPane().setLayout(listSeatFrameLayout);
        listSeatFrameLayout.setHorizontalGroup(
            listSeatFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        listSeatFrameLayout.setVerticalGroup(
            listSeatFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rightPanel.setBackground(new java.awt.Color(41, 128, 185));

        Applogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/applogo.png"))); // NOI18N

        Train.setFont(new java.awt.Font("PT Sans Narrow", 1, 36)); // NOI18N
        Train.setForeground(new java.awt.Color(255, 255, 255));
        Train.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Train.setText("RESERVATION");

        Train1.setFont(new java.awt.Font("PT Sans Narrow", 1, 36)); // NOI18N
        Train1.setForeground(new java.awt.Color(255, 255, 255));
        Train1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Train1.setText("KERETA LOCAL");

        buttonHome.setBackground(new java.awt.Color(255, 153, 0));
        buttonHome.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        buttonHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonHome.setText("Back To Home");
        buttonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonHomeActionPerformed(evt);
            }
        });

        bisnisTanda.setBackground(new java.awt.Color(255, 153, 51));

        javax.swing.GroupLayout bisnisTandaLayout = new javax.swing.GroupLayout(bisnisTanda);
        bisnisTanda.setLayout(bisnisTandaLayout);
        bisnisTandaLayout.setHorizontalGroup(
            bisnisTandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        bisnisTandaLayout.setVerticalGroup(
            bisnisTandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        BisnisLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        BisnisLabel.setForeground(new java.awt.Color(255, 255, 255));
        BisnisLabel.setText("Bisnis");

        EksekutifTanda.setBackground(new java.awt.Color(102, 204, 0));
        EksekutifTanda.setPreferredSize(new java.awt.Dimension(24, 22));

        javax.swing.GroupLayout EksekutifTandaLayout = new javax.swing.GroupLayout(EksekutifTanda);
        EksekutifTanda.setLayout(EksekutifTandaLayout);
        EksekutifTandaLayout.setHorizontalGroup(
            EksekutifTandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        EksekutifTandaLayout.setVerticalGroup(
            EksekutifTandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        eksekutifLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        eksekutifLabel.setForeground(new java.awt.Color(255, 255, 255));
        eksekutifLabel.setText("Eksekutif");

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                                .addComponent(Train)
                                .addGap(90, 90, 90))
                            .addGroup(rightPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()))
                        .addGroup(rightPanelLayout.createSequentialGroup()
                            .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Train1)
                                .addGroup(rightPanelLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(bisnisTanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(BisnisLabel)
                                    .addGap(18, 18, 18)
                                    .addComponent(EksekutifTanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eksekutifLabel)))
                            .addGap(80, 80, 80)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addComponent(Applogo)
                        .addGap(134, 134, 134))))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bisnisTanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BisnisLabel)
                    .addComponent(EksekutifTanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eksekutifLabel))
                .addGap(33, 33, 33)
                .addComponent(Applogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Train1)
                .addGap(2, 2, 2)
                .addComponent(Train)
                .addGap(18, 18, 18)
                .addComponent(buttonHome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        inputAsal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asal" }));

        inputTujuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tujuan" }));

        btn_submit.setBackground(new java.awt.Color(102, 153, 255));
        btn_submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit.setText("Search");
        btn_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seatPanelLayout = new javax.swing.GroupLayout(seatPanel);
        seatPanel.setLayout(seatPanelLayout);
        seatPanelLayout.setHorizontalGroup(
            seatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
        seatPanelLayout.setVerticalGroup(
            seatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 19, Short.MAX_VALUE)
                        .addComponent(seatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inputAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inputTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)))
                        .addComponent(btn_submit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(seatPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_submitActionPerformed
        asal = (String) inputAsal.getSelectedItem();
        tujuan = (String) inputTujuan.getSelectedItem();

        Kereta getKereta = searchKereta(asal, tujuan);
        //untuk memastikan bahwa layout komponen diperbarui sesuai dengan perubahan dan memaksa GUI menggambar ulang komponen sesuai dengan layout yang diperbarui.
        seatPanel.removeAll();
        seatPanel.revalidate();
        seatPanel.repaint();
        AddButtonListSeat(getKereta);
    }//GEN-LAST:event_btn_submitActionPerformed

    private void buttonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHomeActionPerformed
        Home homeFrame = new Home();
        homeFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonHomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KeretaLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeretaLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeretaLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeretaLocal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeretaLocal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Applogo;
    private javax.swing.JLabel BisnisLabel;
    private javax.swing.JPanel EksekutifTanda;
    private javax.swing.JLabel Train;
    private javax.swing.JLabel Train1;
    private javax.swing.JPanel bisnisTanda;
    private javax.swing.JButton btn_submit;
    private javax.swing.JButton buttonHome;
    private com.toedter.calendar.JDateChooser dateField;
    private javax.swing.JLabel eksekutifLabel;
    private javax.swing.JComboBox<String> inputAsal;
    private javax.swing.JComboBox<String> inputTujuan;
    private javax.swing.JFrame listSeatFrame;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel seatPanel;
    // End of variables declaration//GEN-END:variables
}
