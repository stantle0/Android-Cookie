package br.com.caelum.olamundo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class OlaMundoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        
        Button botao = (Button) findViewById(R.id.botao);
        
        /** COM INSTANCIA DE NOVA CLASSE **/
        //botao.setOnClickListener(new Ouvinte());
        
        /** COM CLASSE ANONIMA **/ 
        botao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("TAG", "mensagem ao clicar no botao");
				Log.i("TAG", "mensagem ao clicar no botao");
				Log.i("TAG", "mensagem ao clicar no botao");
				Toast.makeText(OlaMundoActivity.this, "clicou no botao", Toast.LENGTH_LONG).show();
				
			}
		});
    }

}
