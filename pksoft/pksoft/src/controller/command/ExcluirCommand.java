package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public class ExcluirCommand extends AbstractCommand {

	public Resultado executar(EntidadeDominio entidade) {		
		return fachada.excluir(entidade);
	}

}
