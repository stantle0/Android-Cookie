package com.cookie;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cookie.asynctask.IncrementadorAsyncTask;
import com.cookie.asynctask.ResultadoMessage;
import com.cookie.dao.IncrementadorDAO;
import com.cookie.incrementador.Incrementador;
import com.cookie.incrementador.IncrementadorAdapter;




public class MainActivity extends ActionBarActivity {


	private IncrementadorAsyncTask incrementadorAsyncTask;
	private Long caixaTotal = (long) 0;
	private Long cookiesPorSegundo = (long) 1;
	private Handler handler;
	private TextView textCookiesPorSegundo;
	private TextView textCaixa;
	private IncrementadorAdapter incrementadorAdapter;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        

        /** Inflamento da lista com os incrementadores*/
        IncrementadorDAO incrementadorDAO = new IncrementadorDAO();
        List<Incrementador> listaIncrementadores = incrementadorDAO.getLista();
        incrementadorAdapter = new IncrementadorAdapter(listaIncrementadores, this);
        ListView listViewIncrementadores = (ListView) findViewById(R.id.listaIncrementadores);
        listViewIncrementadores.setAdapter(incrementadorAdapter);
        
        /** Configurando a AsyncTask */
        preparaHandler();
        textCaixa = (TextView) findViewById(R.id.caixa);
        textCookiesPorSegundo = (TextView) findViewById(R.id.cookiespersec);
        incrementadorAsyncTask = new IncrementadorAsyncTask(handler);
        incrementadorAsyncTask.execute();
        
        /** Configurando o clique em cada incrementador para realizar compra */
        listViewIncrementadores.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public synchronized void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Incrementador incrementador = (Incrementador) adapter.getItemAtPosition(position);
				if (caixaTotal >= incrementador.getPreco()) {
					caixaTotal -= incrementador.getPreco();
					cookiesPorSegundo += incrementador.getValorIncremento();
					incrementador.compra();
				} else {
					Toast.makeText(MainActivity.this, "Cookies insuficientes", Toast.LENGTH_SHORT).show();
				}
				incrementadorAdapter.notifyDataSetChanged();
			}
        	
		});
        
    }

	private void preparaHandler() {
		handler = new Handler() {
			@Override
			public synchronized void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case ResultadoMessage.UM_SEGUNDO_PASSADO:
					caixaTotal = caixaTotal + cookiesPorSegundo;
					textCookiesPorSegundo.setText(String.valueOf(cookiesPorSegundo));
					textCaixa.setText(String.valueOf(caixaTotal));
					break;
				}
			}
		};
		
	}

}
