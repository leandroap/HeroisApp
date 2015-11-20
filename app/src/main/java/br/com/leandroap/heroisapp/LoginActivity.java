package br.com.leandroap.heroisapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.leandroap.heroisapp.dao.UsuarioDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final String PREF_NAME_APP = "PREFHEROIS";
    private final String PREF_USUARIO = "PREFUSUARIO";
    private final String PREF_SENHA = "PREFSENHA";
    private final String PREF_MANTER_CONECTADO = "PREFMANTER_CONECTADO";

    private LinearLayout llContainer;
    private Button btConectar;
    private EditText etUsuario;
    private EditText etSenha;
    private CheckBox cbManterConectado;
    private TextView tvCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        llContainer = (LinearLayout)findViewById(R.id.llCotainer);
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etSenha = (EditText)findViewById(R.id.etSenha);
        cbManterConectado = (CheckBox)findViewById(R.id.cbManterConectado);

        btConectar = (Button)findViewById(R.id.btConectar);
        btConectar.setOnClickListener(this);
        tvCadastrar = (TextView)findViewById(R.id.tvCadastrar);
        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        NovoUsuarioActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences settings = getSharedPreferences(PREF_NAME_APP, MODE_PRIVATE);
        if (settings.getBoolean(PREF_MANTER_CONECTADO, false)){
            logar(settings.getString(PREF_USUARIO,""),
                    settings.getString(PREF_SENHA, ""));
        }
    }

    @Override
    public void onClick(View view) {
        logar(etUsuario.getText().toString().trim(),
                etSenha.getText().toString().trim());
    }

    private void logar(String usuario, String senha) {
        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        if(usuarioDAO.validarLogin(usuario, senha)){
            manterConectado();
            abrirMainActivity();

        } else {
            Animation animation = AnimationUtils.loadAnimation((LoginActivity.this),
                    R.anim.shake);
            llContainer.startAnimation(animation);
            Toast.makeText(this, R.string.toast_login_invalido, Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void manterConectado() {
        SharedPreferences settings = getSharedPreferences(PREF_NAME_APP, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(PREF_MANTER_CONECTADO, cbManterConectado.isChecked());
        editor.putString(PREF_USUARIO, etUsuario.getText().toString());
        editor.putString(PREF_SENHA, etSenha.getText().toString());
        editor.commit();
    }


}
