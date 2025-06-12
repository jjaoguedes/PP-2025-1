import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EquipamentoDAO {

    // Método para inserir um novo equipamento
    public static boolean inserirEquipamento(String tombamento, String tipo, String descricao,
                                             String data, String responsavel, String local) {
        String sql = "INSERT INTO Equipamentos (Tombamento, Tipo, Descricao, DataAquisicao, Responsavel, Localizacao) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tombamento);
            stmt.setString(2, tipo);
            stmt.setString(3, descricao);
            stmt.setString(4, data);
            stmt.setString(5, responsavel);
            stmt.setString(6, local);

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para consultar um equipamento pelo tombamento
    public static Equipamento consultarEquipamento(String tombamento) {
        String sql = "SELECT * FROM Equipamentos WHERE Tombamento = ?";
        Equipamento equipamento = null;

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tombamento);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                equipamento = new Equipamento(
                    rs.getString("Tombamento"),
                    rs.getString("Tipo"),
                    rs.getString("Descricao"),
                    rs.getString("DataAquisicao"),
                    rs.getString("Responsavel"),
                    rs.getString("Localizacao")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipamento;
    }

    // Método para editar um equipamento
    public static boolean editarEquipamento(String tombamento, String tipo, String descricao,
                                            String data, String responsavel, String local) {
        String sql = "UPDATE Equipamentos SET Tipo = ?, Descricao = ?, DataAquisicao = ?, Responsavel = ?, Localizacao = ? " +
                     "WHERE Tombamento = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            stmt.setString(2, descricao);
            stmt.setString(3, data);
            stmt.setString(4, responsavel);
            stmt.setString(5, local);
            stmt.setString(6, tombamento);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Retorna true se algum equipamento foi editado

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar um equipamento
    public static boolean deletarEquipamento(String tombamento) {
        String sql = "DELETE FROM Equipamentos WHERE Tombamento = ?";

        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tombamento);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // Retorna true se algum equipamento foi deletado

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
