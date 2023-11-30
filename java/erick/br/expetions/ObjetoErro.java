package erick.br.expetions;

import org.springframework.http.HttpStatus;

public class ObjetoErro {

	private String menssagem;
	
	private int codigo;

	public ObjetoErro(String menssagem, int codigo) {
		super();
		this.menssagem = menssagem;
		this.codigo = codigo;
	}

	public ObjetoErro() {
		super();
	}

	public String getMenssagem() {
		return menssagem;
	}

	public void setMenssagem(String menssagem) {
		this.menssagem = menssagem;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	
	
 
}
