package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public class ConsultarCommand extends AbstractCommand {

	public Resultado executar(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}

}
