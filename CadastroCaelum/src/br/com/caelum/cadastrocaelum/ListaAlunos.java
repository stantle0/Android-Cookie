package br.com.caelum.cadastrocaelum;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastrocaelum.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.modelo.Aluno;


public class ListaAlunos extends ActionBarActivity {

    private ListView lista;
    private Aluno alunoSelecionado;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);
        
        lista = (ListView) findViewById(R.id.lista);
        
        /** Configurando a lista para usar o menu de contexto */
        registerForContextMenu(lista);
        
        /** Configurando o listener para editar o aluno quando houver clique em
         * item da ListView */
        lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				//TODO testar com alunoSelecionado para receber a item do adapter
				Aluno alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
				Intent irParaFormulario = new Intent(ListaAlunos.this,Formulario.class);
				irParaFormulario.putExtra("alunoSelecionado", alunoSelecionado);
				startActivity(irParaFormulario);
			}
        	
		});
        
        /** Configurando o listener para clique longo em item da ListView */
        lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				//Toast.makeText(ListaAlunos.this, "Clique longo em "+nomes[position], Toast.LENGTH_SHORT).show();
				//Toast.makeText(ListaAlunos.this, "Clique longo em "+alunoSelecionado, Toast.LENGTH_SHORT).show();				
				
				alunoSelecionado = (Aluno) adapter.getItemAtPosition(position);
				Toast.makeText(ListaAlunos.this, "Clique longo em aluno com id="+alunoSelecionado.getId(), Toast.LENGTH_SHORT).show();
				
				// TRUE consome o evento do clique longo, false não consome
				return false;
			}
        	
		});
        
    }

    @Override
    protected void onResume() {
    	super.onResume();
       	carregaLista();
    }

	private void carregaLista() {
		/** Valores para ser exibidos na lista*/
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.getLista();
        
        /** Configurando adapter*/
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);
        
		/** Configurando a listView para usar os valores do Adapter*/
        lista.setAdapter(adapter);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.lista_alunos, menu);
    	
    	
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
    	int itemClicado = item.getItemId();
    	
    	switch (itemClicado) {
    	case R.id.novo:
    		Intent irParaFormulario = new Intent(this,Formulario.class);
    		startActivity(irParaFormulario);
    		break;
    	
    	default:
    		break;
    		
    	}
    	
    	
    	
    	
    	return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
    		ContextMenuInfo menuInfo) {
    	
    	MenuItem ligar = menu.add("Ligar");
    	ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaTelaDeDiscagem = new Intent(Intent.ACTION_CALL);
				Uri discarPara = Uri.parse("tel:"+alunoSelecionado.getTelefone());
				irParaTelaDeDiscagem.setData(discarPara);
				startActivity(irParaTelaDeDiscagem);
				
				return false;
			}
		});
    	
    	menu.add("Enviar SMS");
    	MenuItem navegar = menu.add("Navegar no Site");
    	navegar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Intent irParaOSite = new Intent(Intent.ACTION_VIEW);
				Uri site = Uri.parse("http://"+alunoSelecionado.getSite());
				irParaOSite.setData(site);
				startActivity(irParaOSite);
				return false;
			}
		});
    	
    	MenuItem deletar = menu.add("Deletar");
    	deletar.setOnMenuItemClickListener (new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO alunoDAO = new AlunoDAO(ListaAlunos.this);
				alunoDAO.deletar(alunoSelecionado);
				alunoDAO.close();
				
				carregaLista();			
				return false;
			}
		});
    	
    	
    	menu.add("Ver no mapa");
    	menu.add("Enviar e-mail");
    	
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
}
