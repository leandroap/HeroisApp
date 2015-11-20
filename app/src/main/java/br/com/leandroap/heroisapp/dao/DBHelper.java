package br.com.leandroap.heroisapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leandro on 03/10/15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String NOME_DO_BANCO = "herois.db";
    public static final int VERSAO_DO_BANCO = 1;


    public DBHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String sql = "CREATE TABLE usuario (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT " +
                ",nome TEXT NOT NULL " +
                ",email TEXT NOT NULL " +
                ",senha TEXT NOT NULL" +
                ");";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
