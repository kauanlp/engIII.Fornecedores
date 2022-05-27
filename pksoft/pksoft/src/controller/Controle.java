package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import aplicacao.Resultado;
import controller.command.AlterarCommand;
import controller.command.ConsultarCommand;
import controller.command.ExcluirCommand;
import controller.command.ICommand;
import controller.command.SalvarCommand;
import controller.command.VisualizarCommand;
import controller.viewHelper.FornecedorVH;
import controller.viewHelper.IViewHelper;
import dominio.EntidadeDominio;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControleCliente
 */
public class Controle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;

	/**
	 * Default constructor.
	 */
	public Controle() {

		commands = new HashMap<String, ICommand>();

		ICommand salvarCommand = new SalvarCommand();
		ICommand alterarCommand = new AlterarCommand();
		ICommand consultarCommand = new ConsultarCommand();
		ICommand visualizarCommand = new VisualizarCommand();
		ICommand excluirCommand = new ExcluirCommand();

		commands.put("SALVAR", salvarCommand);
		commands.put("ALTERAR", alterarCommand);
		commands.put("CONSULTAR", consultarCommand);
        commands.put("VISUALIZAR", visualizarCommand);
		commands.put("EXCLUIR", excluirCommand);

		vhs = new HashMap<String, IViewHelper>();

		vhs.put("/pksoft/salvarFornecedor", new FornecedorVH());
		vhs.put("/pksoft/consultarFornecedores", new FornecedorVH());
		vhs.put("/pksoft/visualizarFornecedor", new FornecedorVH());
		vhs.put("/pksoft/alterarFornecedor", new FornecedorVH());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String operacao = request.getParameter("operacao");

		ICommand command = commands.get(operacao);

		String uri = request.getRequestURI();

		IViewHelper vh = vhs.get(uri);

		EntidadeDominio entidade = vh.getEntidade(request);

		Resultado resultado = command.executar(entidade);

		vh.setView(resultado, request, response);
	}

}
