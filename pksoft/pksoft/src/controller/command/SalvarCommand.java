package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public class SalvarCommand extends AbstractCommand {

	public Resultado executar(EntidadeDominio entidade) {
		return fachada.salvar(entidade);
	}

}
