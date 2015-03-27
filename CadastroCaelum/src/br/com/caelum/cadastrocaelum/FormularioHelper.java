package br.com.caelum.cadastrocaelum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

public class FormularioHelper {

	private Formulario formulario;
	private EditText editNome;
	private EditText editSite;
	private EditText editEndereco;
	private EditText editTelefone;
	private RatingBar ratingNota;
	private ImageView foto;
	private String caminhoArquivoFoto;

	public FormularioHelper(Formulario formulario) {
		this.formulario = formulario;
		editNome = (EditText) formulario.findViewById(R.id.nome);
		editSite = (EditText) formulario.findViewById(R.id.site);
		editEndereco = (EditText) formulario.findViewById(R.id.endereco);
		editTelefone = (EditText) formulario.findViewById(R.id.telefone);
		ratingNota = (RatingBar) formulario.findViewById(R.id.nota);
		foto = (ImageView) formulario.findViewById(R.id.foto);
		
		
	}

	public Aluno pegaAlunoDoFormulario() {
		Aluno aluno = new Aluno();
		aluno.setNome(editNome.getText().toString());
		aluno.setSite(editSite.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setNota(Double.valueOf(ratingNota.getRating()));
		aluno.setFoto(caminhoArquivoFoto);
		return aluno;
	}

	public void colocaAlunoNoFormulario(Aluno alunoTemporario) {
		
		editNome.setText(alunoTemporario.getNome());
		editSite.setText(alunoTemporario.getSite());
		editEndereco.setText(alunoTemporario.getEndereco());
		editTelefone.setText(alunoTemporario.getTelefone());
		ratingNota.setRating(alunoTemporario.getNota().floatValue());
		
		caminhoArquivoFoto = alunoTemporario.getFoto();
		if (alunoTemporario.getFoto() != null) {
			carregaImagem(caminhoArquivoFoto);
		}
	}
	
	public ImageView getFoto() {
		return foto;
	}

	public void carregaImagem(String caminhoArquivoFoto) {
		Bitmap imagem  = BitmapFactory.decodeFile(caminhoArquivoFoto);
		Bitmap imagemReduzida = Bitmap.createScaledBitmap(imagem, 100, 100, true);
		foto.setImageBitmap(imagemReduzida);
	}

	public String getCaminhoArquivoFoto() {
		return caminhoArquivoFoto;
	}

	public void setCaminhoArquivoFoto(String caminhoArquivoFoto) {
		this.caminhoArquivoFoto = caminhoArquivoFoto;
	}

}
