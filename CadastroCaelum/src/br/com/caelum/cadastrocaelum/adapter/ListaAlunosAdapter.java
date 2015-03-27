package br.com.caelum.cadastrocaelum.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

	private List<Aluno> alunos;
	private Activity activity;

	public ListaAlunosAdapter(List<Aluno> alunos, Activity activity) {
		this.alunos = alunos;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		/** Pega aluno para na posição desejada para inflar a linha*/
		Aluno aluno = alunos.get(position);
		
		/** Infla a linha */
		LayoutInflater inflater = activity.getLayoutInflater();
		View linha = inflater.inflate(R.layout.linha_listagem, null);
		
		/** Pega as views da linha para que sejam preenchidas com conteúdo do aluno*/
		TextView editNome = (TextView) linha.findViewById(R.id.nome);
		ImageView imgFoto = (ImageView) linha.findViewById(R.id.foto);
		
		/** Preenche os elemenots da linha */
		editNome.setText(aluno.getNome());
		if (aluno.getFoto() != null) { // se o aluno possui foto cadastrada
			Bitmap fotoAluno = BitmapFactory.decodeFile(aluno.getFoto());
			Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoAluno, 100, 100, true);
			imgFoto.setImageBitmap(fotoReduzida);
		} else {
			Drawable semFoto = activity.getResources().getDrawable(R.drawable.ic_action_user);
			imgFoto.setImageDrawable(semFoto);
		}
		
		return linha;
	}
	
	

}
