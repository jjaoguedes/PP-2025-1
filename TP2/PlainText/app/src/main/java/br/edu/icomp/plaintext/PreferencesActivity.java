package br.edu.icomp.plaintext;

import android.os.Bundle;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new PreferencesFragment())
                .commit();
    }

    public static class PreferencesFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedState, String rootKey) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
            SharedPreferences.Editor editor = prefs.edit();

            // Checa e remove apenas se tipos antigos puderem causar erro
            try {
                // Se login não for string (por ex., foi um boolean antigo), remove
                Object loginValue = prefs.getAll().get("login");
                if (loginValue != null && !(loginValue instanceof String)) {
                    editor.remove("login");
                }

                Object senhaValue = prefs.getAll().get("senha");
                if (senhaValue != null && !(senhaValue instanceof String)) {
                    editor.remove("senha");
                }

                Object showLoginValue = prefs.getAll().get("showLogin");
                if (showLoginValue != null && !(showLoginValue instanceof Boolean)) {
                    editor.remove("showLogin");
                }

                editor.apply();

            } catch (Exception e) {
                // Fallback para não travar a tela de preferências
                editor.clear().apply();
            }

            // carrega as preferências da tela
            setPreferencesFromResource(R.xml.preferences, rootKey);
        }
    }


}
