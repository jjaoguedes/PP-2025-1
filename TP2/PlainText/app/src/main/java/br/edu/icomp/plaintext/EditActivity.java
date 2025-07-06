package br.edu.icomp.plaintext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {
    private PasswordDAO passwordDAO;
    private int passwordId;
    private TextView editName, editLogin, editPassword, editNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Acessa os componentes da tela (e atribui para os atributos da classe)
        editName = findViewById(R.id.addName);
        editLogin = findViewById(R.id.addLogin);
        editPassword = findViewById(R.id.addPassword);
        editNotes = findViewById(R.id.addNotes);

        // DAO para gerenciar as senhas
        passwordDAO = new PasswordDAO(this);

        Intent intent = getIntent();
        // Tenta acessar o passwordId, enviado ao abrir a activity
        passwordId = intent.getIntExtra("passwordId", -1);

        // Verifica se uma senha foi passada como parâmetro
        if (passwordId != -1) {
            // Se o passwordId foi enviado, pega os dados da senha e preenche os campos de texto da tela
            Password password = passwordDAO.get(passwordId);
            editName.setText(password.getName());
            editLogin.setText(password.getLogin());
            editPassword.setText(password.getPassword());
            editNotes.setText(password.getNotes());
        }
    }



    public void salvarClicado(View view) {
        // Cria um novo Password (modelo)
        Password password = new Password(passwordId, editName.getText().toString(),
                editLogin.getText().toString(), editPassword.getText().toString(),
                editNotes.getText().toString());

        boolean result;

        // É uma nova senha, adiciona
        if (passwordId == -1) result = passwordDAO.add(password);
        //É uma senha já existente, atualiza
        else                  result = passwordDAO.update(password);

        //Se deu certo, volta para a activity anterior (lista)
        if (result) finish();
    }



}
