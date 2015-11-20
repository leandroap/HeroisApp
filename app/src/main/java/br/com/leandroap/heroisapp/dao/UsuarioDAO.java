package br.com.leandroap.heroisapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.leandroap.heroisapp.model.Usuario;

/**
 * Created by leandro on 03/10/15.
 */
public class UsuarioDAO {

    private DBHelper dbHelper;
    private Context context;

    public UsuarioDAO(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(this.context);
    }

    public long salvar(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("nome", usuario.getNome());
        values.put("senha", usuario.getSenha());

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        long id = sqLiteDatabase.insert("usuario",null,values);
        sqLiteDatabase.close();
        return id;
    }

    public boolean validarLogin(String usuario, String senha){
        boolean isValido = false;
        String query = "SELECT * FROM USUARIO " +
                        "WHERE NOME = '" + usuario.trim() + "' AND SENHA = '" + senha.trim() +"';";
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);

        if (cursor.moveToFirst()){
            isValido = true;
        }

        return isValido;
    }
}
