package br.com.caelum.cadastrocaelum;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class Formulario extends Activity {
	
	private FormularioHelper helper;
	//private String caminhoArquivoFotoTemporario;
	private Aluno alunoParaSerAlterado;
	private Aluno alunoTemporario;
	private String caminhoArquivoFotoAntiga;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		/** Intenção foi editar ou criar novo aluno? */
		Intent intent = getIntent();
		alunoParaSerAlterado = (Aluno) intent.getSerializableExtra("alunoSelecionado");
		
		/** Recupera um aluno não salvo se foi alterada a orientação */
		if (savedInstanceState != null) {
			alunoTemporario = (Aluno) savedInstanceState.getSerializable("alunoTemporario");
		} else {
			/** Caso não tenha sido há duas possiblidades:
			 * Foi pedido para criar um novo aluno: é necessário instanciar um temporário
			 * Foi pedido para alterar um aluo: além do procedimento anterior, é necessário copiar os atributos
			 * */
			if (alunoParaSerAlterado == null)
				alunoTemporario = new Aluno();
			else
				//TODO construtor de copia necessario?
				alunoTemporario  = alunoParaSerAlterado;
		}
		
		/** Criação do helper */
		helper = new FormularioHelper(this);
		
		Toast.makeText(Formulario.this, "Aluno: "+alunoTemporario, Toast.LENGTH_SHORT).show();
		
		
		/** Grava os dados do aluno ao clicar no botão Gravar ou Alterar*/
		Button botao = (Button) findViewById(R.id.botao);
		if (alunoParaSerAlterado != null || savedInstanceState != null) {
			botao.setText("Alterar");
			helper.colocaAlunoNoFormulario(alunoTemporario);
		}
		
		botao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				AlunoDAO alunoDAO = new AlunoDAO(Formulario.this);
				if (alunoParaSerAlterado == null) {
					alunoDAO.salva(aluno);
				} else {
					aluno.setId(alunoParaSerAlterado.getId());
					alunoDAO.altera(aluno);
				}
				alunoDAO.close();
				
				finish();
				
			}
		});
		
		/** Acessa a camera do dispositivo ao clicar na imagem superior */
		ImageView foto = helper.getFoto();
		foto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				caminhoArquivoFotoAntiga = alunoTemporario.getFoto();
				helper.setCaminhoArquivoFoto(Environment.getExternalStorageDirectory().toString()+
						"/CadastroCaelum/"+System.currentTimeMillis()+"foto.png");
				File arquivoFoto = new File(helper.getCaminhoArquivoFoto());
				Uri localImagem = Uri.fromFile(arquivoFoto);
				irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
				
				startActivityForResult(irParaCamera, 123);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == 123) {
			if (resultCode == Activity.RESULT_OK) { //Clicou em Salvar foto 
				helper.carregaImagem(helper.getCaminhoArquivoFoto());
			} else { //Clicou em Descartar foto - retorna para foto anterior
				helper.setCaminhoArquivoFoto(caminhoArquivoFotoAntiga);
				//TODO Esse recarregamento é realmente necessário?
				helper.carregaImagem(caminhoArquivoFotoAntiga);
			}
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		//outState.putString("caminhoArquivoFotoTemporario", caminhoArquivoFotoTemporario);
		alunoTemporario = helper.pegaAlunoDoFormulario();
		outState.putSerializable("alunoTemporario", alunoTemporario);
	}

	public Aluno getAlunoTemporario() {
		return alunoTemporario;
	}

}
