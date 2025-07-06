package br.edu.icomp.plaintext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PasswordsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Acessa o RecyclerView do Layout (XML)
        recyclerView = findViewById(R.id.list_recycler);
        // Seta o Layout do RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Seta o adaptador
        adapter = new PasswordsAdapter(this);
        // Atualiza os dados da lista quando a activity for reiniciada
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }

    class PasswordsAdapter extends RecyclerView.Adapter<PasswordsViewHolder> {
        private Context context;
        private ArrayList<Password> passwords;
        PasswordDAO passwordDAO;

        //Activity, necessário para acessar o BD, Toasts, e abrir uma nova activity
        public PasswordsAdapter(Context context) {
            this.context = context;
            passwordDAO = new PasswordDAO(context);
            update();
        }

        // Pega a lista de senhas cadastradas
        public void update() { passwords = passwordDAO.getList(); }

        public PasswordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Chamado quando um item é criado pela 1a vez
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    // Infla o layout do item (list_item)
                    .inflate(R.layout.list_item, parent, false);
            // Cria e retorna um objeto da classe PasswordsViewHolder
            PasswordsViewHolder vh = new PasswordsViewHolder(v, context);
            return vh;
        }

        // Atualiza os textos de um item (holder) de acordo com sua posição na lista
        public void onBindViewHolder(PasswordsViewHolder holder, int position) {
            holder.name.setText(passwords.get(position).getName());
            holder.login.setText(passwords.get(position).getLogin());
            holder.id = passwords.get(position).getId();
        }

        public int getItemCount() { return passwords.size(); }
    }


    class PasswordsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView login, name;
        public int id;

        public PasswordsViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            // Acessa os dados da view (list_item.xml do item atual
            name = v.findViewById(R.id.itemName);
            login = v.findViewById(R.id.itemLogin);
            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            Intent intent = new Intent(context, EditActivity.class);
            intent.putExtra("passwordId", this.id);
            context.startActivity(intent);
        }

    }

    public void buttonAddClick(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }



}
