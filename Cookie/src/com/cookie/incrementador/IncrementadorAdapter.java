package com.cookie.incrementador;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cookie.R;



public class IncrementadorAdapter extends BaseAdapter {

	private List<Incrementador> incrementadores;
	private Activity activity;

	public IncrementadorAdapter(List<Incrementador> incrementadores,
			Activity activity) {
		this.incrementadores = incrementadores;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return incrementadores.size();
	}

	@Override
	public Object getItem(int position) {
		return incrementadores.get(position);
	}

	@Override
	public long getItemId(int position) {
		return incrementadores.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/** Pega incrementador para na posição desejada para inflar a linha */
		Incrementador incrementador = incrementadores.get(position);

		/** Infla a linha */
		LayoutInflater inflater = activity.getLayoutInflater();
		View linha = inflater.inflate(R.layout.linha_incrementador, null);
		
		/** Pega as views da linha para que sejam preenchidas com conteúdo do incrementador */
		TextView editNome = (TextView) linha.findViewById(R.id.nomeIncrementador);
		TextView editCookiesPorSeg = (TextView) linha.findViewById(R.id.cookiesPorSegIncrementador);
		TextView editPreco = (TextView) linha.findViewById(R.id.precoIncrementador);
		TextView editLevel = (TextView) linha.findViewById(R.id.levelIncrementador);
		
		/** Preenche os elemenots da linha */
		editNome.setText(incrementador.getNome());
		editCookiesPorSeg.setText("+"+incrementador.getValorIncremento().toString());
		editPreco.setText(incrementador.getPreco().toString());
		editLevel.setText(incrementador.getQuantidadeComprada().toString());
		
		return linha;

	}

}
