package br.edu.icomp.plaintext;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        TextView textBemVindo = findViewById(R.id.textBemVindo);

        Intent intent = getIntent();
        String login = intent.getStringExtra("login");

        textBemVindo.setText("Olá " + login + "!");
    }
}
