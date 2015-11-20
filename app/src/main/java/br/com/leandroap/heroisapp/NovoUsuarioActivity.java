package br.com.leandroap.heroisapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.leandroap.heroisapp.dao.UsuarioDAO;
import br.com.leandroap.heroisapp.model.Usuario;

public class NovoUsuarioActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etSenha;
    private EditText etEmail;
    private Button btCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        etUsuario = (EditText)findViewById(R.id.etCadastrarUsuario);
        etSenha = (EditText)findViewById(R.id.etCadastrarSenha);
        etEmail= (EditText)findViewById(R.id.etCadastrarEmail);
        btCadastrar = (Button)findViewById(R.id.btCadastrarNovoUsuario);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setEmail(etEmail.getText().toString());
                usuario.setNome(etUsuario.getText().toString());
                usuario.setSenha(etSenha.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
                usuario.setId((int)usuarioDAO.salvar(usuario));

                if (usuario.getId() == -1) {
                    Toast.makeText(getApplicationContext(),
                            R.string.toast_erro_gravar_usuario, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.toast_sucesso_salvar_usuario, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }


}
