package br.edu.icomp.plaintext;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class PasswordDAO {

    private Context context; //Activity usando o DAO. Usado para toasts e BD.
    // Armazenamento das senhas
    //private static ArrayList<Password> passwordsList = new ArrayList<>();

    private SQLiteDatabase database;


    // Construtor, recebe a activity (contexto) como parâmetro
    // Acessa o SQLite usando a nossa classe auxiliar
    public PasswordDAO(Context context) {
        this.context = context;
        this.database = (new Database(context)).getWritableDatabase();
    }

    public ArrayList<Password> getList() {
        // Retorna um ArrayList, mas os dados virão do BD
        ArrayList<Password> result = new ArrayList<Password>();
        String sql = "SELECT * FROM passwords ORDER BY name";
        //Executa um comando SQL no SQLite do Android
        Cursor cursor = database.rawQuery(sql, null);

        // Para cada linha retornada da consulta anterior
        while (cursor.moveToNext()) {
            // Acessa a primeira coluna da linha como um inteiro
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String login = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            // Cria um objeto Password e insere na lista de retorno
            result.add(new Password(id, name, login, password, notes));
        }

        return result;
    }

    // Adiciona um Password no banco de dados
    public boolean add(Password password) {
        // Comando SQL para inserir os dados no BD
        String sql = "INSERT INTO passwords VALUES (NULL, "
                + "'" + password.getName() + "', "
                + "'" + password.getLogin() + "', "
                + "'" + password.getPassword() + "', "
                + "'" + password.getNotes() + "')";
        try {
            // Executa o comando SQL
            database.execSQL(sql);
            Toast.makeText(context, "Senha salva!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Atualiza um Password no banco de dados
    public boolean update(Password password) {
        // Comando SQL para atualizar os dados no BD
        String sql = "UPDATE passwords SET "
                + "name='" + password.getName() + "', "
                + "login='" + password.getLogin() + "', "
                + "password='" + password.getPassword() + "', "
                + "notes='" + password.getNotes() + "' "
                + "WHERE id=" + password.getId();
        try {
            database.execSQL(sql);
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show();
            return true;
        }
        catch (SQLException e) {
            Toast.makeText(context, "Erro! " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    // Dado um ID, retorna o Password, com este ID
    public Password get(int id) {
        String sql = "SELECT * FROM passwords WHERE id=" + id;
        // Executa um comando SQL no SQLite do Android
        Cursor cursor = database.rawQuery(sql, null);

        // Acessa a primeira linha retornada da consulta
        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            // Acessa a segunda coluna da linha como uma string
            String login = cursor.getString(2);
            String password = cursor.getString(3);
            String notes = cursor.getString(4);
            return new Password(id, name, login, password, notes);
        }
        //Se a senha com o ID passado não for encontrada, retorna nulo
        return null;

    }
}
