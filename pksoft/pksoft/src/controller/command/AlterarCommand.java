package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public class AlterarCommand extends AbstractCommand {

	public Resultado executar(EntidadeDominio entidade) {		
		return fachada.alterar(entidade);
	}

}
