import javax.swing.*;
import java.awt.*;

public class TelaCadastrar extends JFrame {
    private JTextField txtTombamento, txtTipo, txtDescricao, txtData, txtResponsavel, txtLocal;
    private JTextArea infoArea;

    public TelaCadastrar() {
        setTitle("Cadastrar Equipamento");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));

        txtTombamento = new JTextField();
        txtTipo = new JTextField();
        txtDescricao = new JTextField();
        txtData = new JTextField();
        txtResponsavel = new JTextField();
        txtLocal = new JTextField();

        formPanel.add(new JLabel("Tombamento:"));
        formPanel.add(txtTombamento);
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(txtTipo);
        formPanel.add(new JLabel("Descrição:"));
        formPanel.add(txtDescricao);
        formPanel.add(new JLabel("Data de Aquisição:"));
        formPanel.add(txtData);
        formPanel.add(new JLabel("Responsável:"));
        formPanel.add(txtResponsavel);
        formPanel.add(new JLabel("Local:"));
        formPanel.add(txtLocal);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnVoltar = new JButton("Voltar ao Menu");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(btnConfirmar);
        buttonPanel.add(btnVoltar);

        infoArea = new JTextArea(6, 30);
        infoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(infoArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Ação do botão Confirmar
        btnConfirmar.addActionListener(e -> {
            String tombamento = txtTombamento.getText();
            String tipo = txtTipo.getText();
            String descricao = txtDescricao.getText();
            String data = txtData.getText();
            String responsavel = txtResponsavel.getText();
            String local = txtLocal.getText();

            boolean inserido = EquipamentoDAO.inserirEquipamento(tombamento, tipo, descricao, data, responsavel, local);
            if (inserido) {
                infoArea.setText("Equipamento cadastrado com sucesso:\n");
                infoArea.append("Tombamento: " + tombamento + "\n");
                infoArea.append("Tipo: " + tipo + "\n");
                infoArea.append("Descrição: " + descricao + "\n");
                infoArea.append("Data: " + data + "\n");
                infoArea.append("Responsável: " + responsavel + "\n");
                infoArea.append("Local: " + local + "\n");
            } else {
                infoArea.setText("Erro ao cadastrar equipamento.\n");
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a janela atual
        });
    }
}
