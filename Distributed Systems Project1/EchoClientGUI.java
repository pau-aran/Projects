import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class EchoClientGUI extends JFrame implements ActionListener {

    private static final String END_MESSAGE = ".";
    private static final String HOST_NAME = "localhost";
    private static final String PORT_NUM = "13";

    private JButton yesBtn, noBtn, dontcareBtn;

    public EchoClientGUI() {
        super("Voting System");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create buttons
        yesBtn = new JButton("Yes");
        noBtn = new JButton("No");
        dontcareBtn = new JButton("Don't Care");

        // Set button colors and font
        Color buttonColor = new Color(59, 89, 182);
        Color buttonHoverColor = new Color(95, 135, 235);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        yesBtn.setBackground(buttonColor);
        yesBtn.setForeground(Color.WHITE);
        yesBtn.setFont(buttonFont);
        yesBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        yesBtn.setFocusPainted(false);
        yesBtn.addActionListener(this);
        yesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesBtn.setBackground(buttonHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesBtn.setBackground(buttonColor);
            }
        });

        noBtn.setBackground(buttonColor);
        noBtn.setForeground(Color.WHITE);
        noBtn.setFont(buttonFont);
        noBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        noBtn.setFocusPainted(false);
        noBtn.addActionListener(this);
        noBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noBtn.setBackground(buttonHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noBtn.setBackground(buttonColor);
            }
        });

        dontcareBtn.setBackground(buttonColor);
        dontcareBtn.setForeground(Color.WHITE);
        dontcareBtn.setFont(buttonFont);
        dontcareBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        dontcareBtn.setFocusPainted(false);
        dontcareBtn.addActionListener(this);
        dontcareBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dontcareBtn.setBackground(buttonHoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dontcareBtn.setBackground(buttonColor);
            }
        });

        // Add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.add(yesBtn);
        buttonPanel.add(noBtn);
        buttonPanel.add(dontcareBtn);

        // Add panel to content pane
        getContentPane().add(buttonPanel);

        // Set frame properties
        setSize(800, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String message = "";

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == yesBtn) {
            message = "yes";
        } else if (e.getSource() == noBtn) {
            message = "no";
        } else if (e.getSource() == dontcareBtn) {
            message = "dontcare";
        }

        try {
            EchoClientHelper2 helper = new EchoClientHelper2(HOST_NAME, PORT_NUM);
            helper.getEcho(message);
            helper.done();

            boolean done = false;
            while (!done) {
                // Popup window to ask if user wants to see results
                int reply = JOptionPane.showConfirmDialog(null, "Do you want to see the results?", "Results",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    // Send request to server to get results
                    EchoClientHelper2 helper2 = new EchoClientHelper2(HOST_NAME, PORT_NUM);
                    String response = helper2.getVotes("getCounts");
                    JOptionPane.showMessageDialog(null, response, "Results", JOptionPane.INFORMATION_MESSAGE);
                    done = true;
                } else {
                    done = true;
                }
            }

            System.exit(0);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EchoClientGUI();
    }
}