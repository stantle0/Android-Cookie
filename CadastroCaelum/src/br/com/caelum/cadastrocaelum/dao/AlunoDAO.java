package br.com.caelum.cadastrocaelum.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "CadastroCaelum";
	private static final int VERSAO = 5;
	

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("banco","vai criar banco");
		String ddl = "CREATE TABLE Alunos (" +
				"id  INTEGER PRIMARY KEY,"+
				"nome TEXT UNIQUE NOT NULL, "+
				"site TEXT, "+
				"endereco TEXT, "+
				"telefone TEXT, "+
				"foto TEXT, "+
				"nota REAL"+
				");";
		db.execSQL(ddl);
		Log.i("banco","criou banco");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i("banco", "vai apagar banco");
		String ddl = "DROP TABLE IF EXISTS Alunos";
		db.execSQL(ddl);
		
		this.onCreate(db);
	}
	
	public void salva(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("site", aluno.getSite());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		
		getWritableDatabase().insert("Alunos", null, values);
	}

	public List<Aluno> getLista() {
		String[] colunas = {"id", "nome", "site", "endereco", "telefone", "nota", "foto"};
		                   // 0     1        2        3           4          5       6
		Cursor cursor = getWritableDatabase().query("Alunos", colunas, null, null, null, null, null);
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		while (cursor.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setSite(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setTelefone(cursor.getString(4));
			aluno.setNota(cursor.getDouble(5));
			aluno.setFoto(cursor.getString(6));
			
			alunos.add(aluno);
		}
		
		return alunos;
	}

	public void deletar(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().delete("Alunos", "id=?", args);
	}

	public void altera(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("site", aluno.getSite());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());
		
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().update("Alunos", values, "id=?", args);
	}

	public boolean isAluno(String telefone) {
		String[] args = {telefone};
		Cursor cursor = getWritableDatabase().rawQuery("SELECT id FROM Alunos WHERE telefone=?", args );
		
		boolean existeUmPrimeiro = cursor.moveToFirst();
		return existeUmPrimeiro;
	}

}
