import javax.swing.*;
import java.awt.*;


public class TelaConsultar extends JFrame {
    private JTextField txtTombamento;
    private JTextArea infoArea;

    public TelaConsultar() {
        setTitle("Consultar Equipamento");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        txtTombamento = new JTextField(20);
        formPanel.add(new JLabel("Digite o Tombamento:"));
        formPanel.add(txtTombamento);

        JButton btnConsultar = new JButton("Consultar");
        formPanel.add(btnConsultar);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnVoltar = new JButton("Voltar ao Menu");
        buttonPanel.add(btnConsultar);
        buttonPanel.add(btnVoltar);

        infoArea = new JTextArea(10, 30);
        infoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(infoArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Ação do botão Consultar
        btnConsultar.addActionListener(e -> {
            String tombamento = txtTombamento.getText();
            Equipamento equipamento = EquipamentoDAO.consultarEquipamento(tombamento);
            if (equipamento != null) {
                infoArea.setText("Equipamento encontrado:\n");
                infoArea.append("Tombamento: " + equipamento.getTombamento() + "\n");
                infoArea.append("Tipo: " + equipamento.getTipo() + "\n");
                infoArea.append("Descrição: " + equipamento.getDescricao() + "\n");
                infoArea.append("Data de Aquisição: " + equipamento.getDataAquisicao() + "\n");
                infoArea.append("Responsável: " + equipamento.getResponsavel() + "\n");
                infoArea.append("Localização: " + equipamento.getLocalizacao() + "\n");
            } else {
                infoArea.setText("Equipamento não encontrado.\n");
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
        });
    }
}
