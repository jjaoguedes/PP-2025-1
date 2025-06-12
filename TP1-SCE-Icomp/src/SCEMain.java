import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class SCEMain {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SCEMain window = new SCEMain();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SCEMain() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Sistema de Controle de Eventos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setLayout(new GridBagLayout());

        String[] labels = {"Cadastrar", "Editar", "Deletar", "Consultar", "Abrir Chamado"};
        Class<?>[] telas = {
            TelaCadastrar.class, TelaEditar.class, TelaDeletar.class, TelaConsultar.class, TelaAbrirChamado.class
        };

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(labels.length, 1, 10, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        for (int i = 0; i < labels.length; i++) {
            JButton btn = new JButton(labels[i]);
            btn.setFont(buttonFont);
            btn.setBackground(new Color(60, 120, 200));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(180, 45));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover effect
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(40, 100, 180));
                }

                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(60, 120, 200));
                }
            });

            final int index = i;
            btn.addActionListener(e -> abrirJanela(telas[index]));
            buttonPanel.add(btn);
        }

        frame.add(buttonPanel);
    }

    private void abrirJanela(Class<?> classeJanela) {
        try {
            // Verifica se a classe é uma subclasse de JFrame
            if (JFrame.class.isAssignableFrom(classeJanela)) {
                JFrame janela = (JFrame) classeJanela.getDeclaredConstructor().newInstance();
                janela.setVisible(true);
            } else {
                throw new Exception("Classe não é uma subclasse de JFrame.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
