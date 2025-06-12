import javax.swing.*;

public class TelaAbrirChamado extends JFrame {
    public TelaAbrirChamado() {
        setTitle("Abrir Chamado");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new JLabel("Hello World - Abrir Chamado", SwingConstants.CENTER));
    }
}
