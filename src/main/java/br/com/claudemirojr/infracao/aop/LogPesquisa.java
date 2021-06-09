package br.com.claudemirojr.infracao.aop;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LogPesquisa {

	private String servico;
	private String usuario;
	private String className;
	private String metodo;
	private String argumento;
	private String resultado;

	
	public LogPesquisa(String usuario, String className, String metodo, String argumento, String resultado) {
		super();
		
		String[] result = className.split("\\.");
		
		this.servico = "Serviço-Infração";
		this.usuario = usuario;
		this.className = result[result.length - 1];
		this.metodo = metodo;
		this.argumento = argumento;
		this.resultado = resultado;
	}

}
