
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Graphics extends JFrame {

    private String name;
    private boolean flag;
    private static Operations look_op;

    public Graphics() {
        this.name = "//localhost/MusicLibrary";
        this.flag = false;
        try {

            //ΕΔΩ ΕΧΟΥΜΕ ΤΙΣ ΠΟΛΙΤΙΚΕΣ ΑΣΦΑΛΕΙΑΣ ΟΙ ΟΠΟΙΕΣ ΔΕΝ ΜΑΣ ΕΠΙΤΡΕΠΟΥΝ ΤΗΝ ΕΚΚΙΝΗΣΗ ΤΗΣ ΕΦΑΡΜΟΓΗΣ
//            System.setProperty("java.security.policy", "file:./security.policy");
//
//            if (System.getSecurityManager() == null) {
//                System.setSecurityManager(new SecurityManager());
//            }
            // Ο ΠΕΛΑΤΗΣ ΣΥΝΔΕΕΤΑΙ ΜΕΣΩ ΑΥΤΗΣ ΤΗΣ ΠΟΡΤΑΣ ΚΑΙ ΤΗΣ ΙΡ ΣΤΟΝ SERVER ΤΗΣ ΤΡΑΠΕΖΑΣ
            Registry regi = LocateRegistry.getRegistry("127.0.0.1", 1234);
            // ΑΝΤΙΚΕΙΜΕΝΟ ΔΙΕΠΑΦΗΣ ΓΙΑ ΤΗΝ ΕΠΙΚΟΙΝΩΝΙΑ
            look_op = (Operations) regi.lookup("//localhost/MusicLibrary");
            System.out.println("OK CLIENT");

        } catch (NotBoundException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
        }

        setTitle("ALBUM LIBRARY");
        JPanel panel1;
        JPanel panel2;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        GridLayout grid = new GridLayout(1, 2);

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel2 = new JPanel();
        //Τα κουμπια για τις 6 λειτουργιες
        JButton display = new JButton("ΠΡΟΒΟΛΗ ALBUM");
        JButton add = new JButton("ΠΡΟΣΘΗΚΗ ALBUM");
        JButton update = new JButton("ΕΝΗΜΕΡΩΣΗ ALBUM");
        JButton delete = new JButton("ΔΙΑΓΡΑΦΗ ALBUM");
        JButton logout = new JButton("ΕΞΟΔΟΣ");
        ImageIcon image = new ImageIcon("mLib.jpg");
        JLabel fwto = new JLabel(image);
        JLabel sxolia = new JLabel("<HTML><B>ΜΕΝΟΥ ΕΠΙΛΟΓΩΝ \n</B></HTML>");
        sxolia.setFont(new Font("serif", Font.PLAIN, 18));

        logout.setBounds(10, 370, 100, 25);
        sxolia.setBounds(150, 60, 400, 30);
        display.setBounds(125, 100, 230, 30);
        add.setBounds(125, 150, 230, 30);
        update.setBounds(125, 200, 230, 30);
        delete.setBounds(125, 250, 230, 30);

        panel1.add(sxolia);
        panel1.add(display);
        panel1.add(add);
        panel1.add(update);
        panel1.add(delete);
        panel1.add(logout);
        panel2.add(fwto);
        setLayout(grid);
        add(panel1);
        add(panel2);
        setSize(1000, 450);
        setLocationRelativeTo(null);

        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    //ΕΠΙΚΟΙΝΩΝΙΑ ΜΕ ΤΡΑΠΕΖΑ ΜΕΣΩ ΔΙΕΠΑΦΗΣ Operations
                    //καλώντας την λειτουργια display()(για επικυρωση)
                    ArrayList<String> result = look_op.display();
                    
                    JOptionPane.showMessageDialog(null, result, "ΠΡΟΒΟΛΗ", JOptionPane.INFORMATION_MESSAGE);

                } catch (RemoteException ex) {
                    Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag = true;
                JFrame frame2 = new JFrame("ΠΡΟΣΘΗΚΗ");
                frame2.setVisible(true);
                setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                panel.setLayout(null);
                JLabel descLabel = new JLabel("ΕΙΣΑΓΕΤΕ ΠΕΡΙΓΡΑΦΗ");
                JTextField descField = new JTextField();
                descLabel.setBounds(70, 30, 150, 50);
                descField.setBounds(200, 40, 150, 30);
                panel.add(descLabel);
                panel.add(descField);
                JLabel genLabel = new JLabel("ΕΙΣΑΓΕΤΕ ΕΙΔΟΣ");
                JTextField genField = new JTextField();
                genLabel.setBounds(70, 70, 150, 50);
                genField.setBounds(200, 80, 150, 30);
                panel.add(genLabel);
                panel.add(genField);
                JLabel yearLabel = new JLabel("ΕΙΣΑΓΕΤΕ ΕΤΟΣ");
                JTextField yearField = new JTextField();
                yearLabel.setBounds(70, 110, 150, 50);
                yearField.setBounds(200, 120, 150, 30);
                panel.add(yearLabel);
                panel.add(yearField);
                JLabel songLabel = new JLabel("ΕΙΣΑΓΕΤΕ ΤΡΑΓΟΥΔΙΑ");
                JTextField songField = new JTextField();
                songLabel.setBounds(70, 150, 150, 50);
                songField.setBounds(200, 160, 150, 30);
                panel.add(songLabel);
                panel.add(songField);

                JButton codeButton = new JButton("OK");
                codeButton.setBounds(195, 200, 100, 30);
                panel.add(codeButton);
                frame2.add(panel);
                frame2.setSize(500, 300);
                frame2.setLocationRelativeTo(null);
                codeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame2.dispose();
                        // ποσο καταθεσης
                        String desc = descField.getText();
                        String gen = genField.getText();
                        String year = yearField.getText();
                        String song = songField.getText();

                        try {
                            //ΕΠΙΚΟΙΝΩΝΙΑ ΜΕ ΤΡΑΠΕΖΑ ΜΕΣΩ ΔΙΕΠΑΦΗΣ Operations 
                            //καλώντας την λειτουργια AddAlbum()(για καταθεση)
                            String result = look_op.AddAlbum(desc, gen, year, song);
                            if (result.equalsIgnoreCase("ok")) {
                                JOptionPane.showMessageDialog(null, "ΠΡΟΣΘΗΚΗ ANΕΠΙΤΥΧΗΣ", "NEW ALBUM", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, result, "NEW ALBUM", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag = true;
                JFrame frame2 = new JFrame("ΕΝΗΜΕΡΩΣΗ");
                frame2.setVisible(true);
                setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                panel.setLayout(null);
                JLabel upLabel = new JLabel("Εισάγετε ΠΕΡΙΓΡΑΦΗ");
                JTextField upField = new JTextField();
                upLabel.setBounds(170, 50, 200, 60);
                upField.setBounds(170, 110, 150, 30);
                panel.add(upLabel);
                panel.add(upField);

                JButton codeButton = new JButton("OK");
                codeButton.setBounds(190, 200, 100, 30);
                panel.add(codeButton);
                frame2.add(panel);
                frame2.setSize(500, 300);
                frame2.setLocationRelativeTo(null);
                codeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame2.dispose();
                        // ποσο για αναληψη
                        String des = upField.getText();

                        try {
                            //ΕΠΙΚΟΙΝΩΝΙΑ ΜΕ ΤΡΑΠΕΖΑ ΜΕΣΩ ΔΙΕΠΑΦΗΣ Operations 
                            //καλώντας την λειτουργια update()(για αναληψη)
                            String result = look_op.update(des);
                            if (result.equalsIgnoreCase("ok")) {
                                JOptionPane.showMessageDialog(null, "ΕΝΗΜΕΡΩΣΗ ΕΠΙΤΥΧΗΣ", "ΕΝΗΜΕΡΩΣΗ", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ΛΑΘΟΣ ΤΙΤΛΟΣ", "ΕΝΗΜΕΡΩΣΗ", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag = true;
                JFrame frame2 = new JFrame("ΔΙΑΓΡΑΦΗ");
                frame2.setVisible(true);
                setLayout(new FlowLayout());
                JPanel panel = new JPanel();
                panel.setLayout(null);
                JLabel delLabel = new JLabel("ΔΩΣΤΕ ΠΕΡΙΓΡΑΦΗ ΓΙΑ ΔΙΑΓΡΑΦΗ");
                JTextField delField = new JTextField();
                delLabel.setBounds(140, 50, 400, 100);
                delField.setBounds(170, 110, 150, 30);
                panel.add(delLabel);
                panel.add(delField);

                JButton codeButton = new JButton("ΔΙΑΓΡΑΦΗ");
                codeButton.setBounds(165, 170, 150, 30);
                panel.add(codeButton);
                frame2.add(panel);
                frame2.setSize(500, 300);
                frame2.setLocationRelativeTo(null);
                codeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame2.dispose();
                        //ΕΠΙΚΟΙΝΩΝΙΑ ΜΕ ΤΡΑΠΕΖΑ ΜΕΣΩ ΔΙΕΠΑΦΗΣ Operations 
                        //καλώντας την λειτουργια delete()(για ενημερωση)
                        String des = delField.getText();

                        try {

                            int result = look_op.delete(des);
                            if (result != 0) {
                                JOptionPane.showMessageDialog(null, "ΔΙΑΓΡΑΦΗ ΕΠΙΤΥΧΗΣ ", "ΔΙΑΓΡΑΦΗ", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "ΛΑΘΟΣ ΤΙΤΛΟΣ", "ΔΙΑΓΡΑΦΗ", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (RemoteException ex) {
                            Logger.getLogger(Graphics.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            }
        });
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();

                //κλεισιμο συνδεσης ΑΤΜ
            }
        });

    }
}
