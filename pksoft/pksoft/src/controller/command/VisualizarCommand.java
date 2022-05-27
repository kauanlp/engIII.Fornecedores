package controller.command;

import aplicacao.Resultado;
import dominio.EntidadeDominio;

public class VisualizarCommand extends AbstractCommand {

	public Resultado executar(EntidadeDominio entidade) {
		return fachada.consultar(entidade);
	}

}