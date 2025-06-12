import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaDeletar extends JFrame {
    private JTextField txtTombamento;
    private JTextArea infoArea;

    public TelaDeletar() {
        setTitle("Deletar Equipamento");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtTombamento = new JTextField(5);
        formPanel.add(new JLabel("Digite o Tombamento do Equipamento a ser Deletado:"));
        formPanel.add(txtTombamento);

        JButton btnDeletar = new JButton("Deletar");
        formPanel.add(btnDeletar);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnVoltar = new JButton("Voltar ao Menu");
        buttonPanel.add(btnDeletar);
        buttonPanel.add(btnVoltar);

        infoArea = new JTextArea(5, 30);
        infoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(infoArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Ação do botão Deletar
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tombamento = txtTombamento.getText();
                boolean deletado = EquipamentoDAO.deletarEquipamento(tombamento);
                if (deletado) {
                    infoArea.setText("Equipamento deletado com sucesso:\n");
                    infoArea.append("Tombamento: " + tombamento + "\n");
                } else {
                    infoArea.setText("Erro ao deletar equipamento ou equipamento não encontrado.\n");
                }
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> dispose()); // Fecha a janela atual
    }
}
