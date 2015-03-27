package br.com.caelum.olamundo;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class Ouvinte implements OnClickListener {

	@Override
	public void onClick(View v) {
		Log.i("TAG","Mensagem de clique no botao");
		//Toast.makeText(OlaMundoActivity.this, "clicou no botao", Toast.LENGTH_LONG).show();
	}

}
