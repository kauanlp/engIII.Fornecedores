package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public interface ICommand {
	
	public Resultado executar(EntidadeDominio entidade);

}
