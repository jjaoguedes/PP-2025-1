import java.sql.*;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/SCE_Icomp";
    private static final String USUARIO = "root";
    private static final String SENHA = "suaSenhaSegura";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
