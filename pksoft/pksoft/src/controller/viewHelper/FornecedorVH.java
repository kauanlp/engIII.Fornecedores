package controller.viewHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aplicacao.Resultado;
import dominio.Cnae;
import dominio.Contato;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Fornecedor;
import dominio.Produto;
import dominio.Servico;
import dominio.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FornecedorVH implements IViewHelper{
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		
		Fornecedor fornecedor = new Fornecedor();

			String txtStatus = request.getParameter("txtStatus");
			String txtCnpj = request.getParameter("txtCnpj");
			String txtRazaoSocial = request.getParameter("txtRazaoSocial");
			String txtNomeFantasia = request.getParameter("txtNomeFantasia");
			String txtInscricaoMunicipal = request.getParameter("txtInscricaoMunicipal");
			String txtInscricaoEstadual = request.getParameter("txtInscricaoEstadual");
			
			String[] listaCnaesString = request.getParameterValues("listaCnaesString");
			String[] listaCnaesId = request.getParameterValues("listaCnaesId");
			
			List<Cnae> listaCnaes = new ArrayList<Cnae>();
			if(listaCnaesString != null) {
		        for (int i = 0; i<listaCnaesString.length; i++) {

		        	if(listaCnaesId[i] == "") {
		        		listaCnaes.add(new Cnae(listaCnaesString[i]));
		        	} else {
		        		listaCnaes.add(new Cnae(
		        				Integer.parseInt(listaCnaesId[i]), 
		        				listaCnaesString[i], 
		        				new Date()
		        		));
		        	}
		        }
			}
			
			String txtEmail = request.getParameter("txtEmail");
	
			String[] tipoTelefone = request.getParameterValues("tipoTelefone");
			String[] ddiTelefone = request.getParameterValues("ddiTelefone");
			String[] dddTelefone = request.getParameterValues("dddTelefone");
			String[] numeroTelefone = request.getParameterValues("numeroTelefone");
			
			String[] listaTelefonesId = request.getParameterValues("listaTelefonesId");
	
	        List<Telefone> listaTelefones = new ArrayList<Telefone>();
			if(numeroTelefone != null) {
		        for (int i=0; i<numeroTelefone.length; i++) {
		        	if(listaTelefonesId[i].equals("")) {
			            listaTelefones.add(
			                new Telefone(
			                    tipoTelefone[i],
			                    ddiTelefone[i],
			                    dddTelefone[i],
			                    numeroTelefone[i]
			                )
			            );
		        	} else {
		        		listaTelefones.add(
		        				new Telefone(
		        						Integer.parseInt(listaTelefonesId[i]),
		        						tipoTelefone[i],
		 			                    ddiTelefone[i],
		 			                    dddTelefone[i],
		 			                    numeroTelefone[i],
		 			                    new Date()
		        				)
		        		 );
		        	}
		        }
			}
	
			String[] nomeContatos = request.getParameterValues("nomeContatos");
			String[] dptoContatos = request.getParameterValues("dptoContatos");
			String[] emailContatos = request.getParameterValues("emailContatos");
			String[] tipoTelefoneContatos = request.getParameterValues("tipoTelefoneContatos");
			String[] ddiTelefoneContatos = request.getParameterValues("ddiTelefoneContatos");
			String[] dddTelefoneContatos = request.getParameterValues("dddTelefoneContatos");
			String[] numeroTelefoneContatos = request.getParameterValues("numeroTelefoneContatos");
			
			String[] listaContatosId = request.getParameterValues("listaContatosId");
			String[] listaTelefonesContatosId = request.getParameterValues("listaTelefonesContatosId");
			
	        List<Contato> listaContatos = new ArrayList<Contato>();
	        if(nomeContatos != null) {
		        for (int i=0; i<nomeContatos.length; i++) {
		        	if(listaContatosId[i].equals("")) {
			        	listaContatos.add(
			                new Contato(
			                    nomeContatos[i],
			                    dptoContatos[i],
			                    emailContatos[i],
			                    new Telefone(
			                        tipoTelefoneContatos[i],
			                        ddiTelefoneContatos[i],
			                        dddTelefoneContatos[i],
			                        numeroTelefoneContatos[i]
			                    )
			                )
			        	);
		        	} else {
			        	listaContatos.add(
				                new Contato(
				                	Integer.parseInt(listaContatosId[i]),
				                    nomeContatos[i],
				                    dptoContatos[i],
				                    emailContatos[i],
				                    new Telefone(
						                Integer.parseInt(listaTelefonesContatosId[i]),
				                        tipoTelefoneContatos[i],
				                        ddiTelefoneContatos[i],
				                        dddTelefoneContatos[i],
				                        numeroTelefoneContatos[i],
				                        new Date()
				                    ),
				                    new Date()
				                )
				        	);
		        	}
		        }
	        }
	        
			String txtTipoEndereco = request.getParameter("txtTipoEndereco");
			String txtCep = request.getParameter("txtCep");
			String txtTipoLogradouro = request.getParameter("txtTipoLogradouro");
			String txtLogradouro = request.getParameter("txtLogradouro");
			String txtNumero = request.getParameter("txtNumero");
			String txtBairro = request.getParameter("txtBairro");
			String txtComplemento = request.getParameter("txtComplemento");
			String txtCidade = request.getParameter("txtCidade");
			String txtEstado = request.getParameter("txtEstado");
			String txtPais = request.getParameter("txtPais");
			
			Endereco endereco = new Endereco(txtTipoEndereco, txtCep, txtTipoLogradouro, txtLogradouro, txtNumero, txtBairro, txtComplemento, txtCidade, txtEstado, txtPais);
	
			String txtTipoFornecimento = request.getParameter("txtTipoFornecimento");
	
			String[] codigoProduto = request.getParameterValues("codigoProduto");
			String[] descricaoProduto = request.getParameterValues("descricaoProduto");
			
			String[] listaProdutosId = request.getParameterValues("listaProdutosId");
			
	        List<Produto> listaProdutos = new ArrayList<>();
	        if(codigoProduto != null) {
		        for (int i=0; i<codigoProduto.length; i++) {
		        	if(listaProdutosId[i] == "") {
		        		listaProdutos.add(new Produto(codigoProduto[i], descricaoProduto[i]));
		        	} else {
		        		listaProdutos.add(new Produto(
			        				Integer.parseInt(listaProdutosId[i]),
			        				codigoProduto[i], 
			        				descricaoProduto[i],
				                    new Date()
		        				)
		        		);
		        	}
		        } 
	        }
	
			String[] codigoServico = request.getParameterValues("codigoServico");
			String[] descricaoServico = request.getParameterValues("descricaoServico");
	        
			String[] listaServicosId = request.getParameterValues("listaServicosId");
			
	        List<Servico> listaServicos = new ArrayList<>();
	        if(codigoServico != null) {
		        for (int i=0; i<codigoServico.length; i++) {
		        	if(listaServicosId[i].equals("")) {
		        		listaServicos.add(new Servico(codigoServico[i], descricaoServico[i]));
		        	} else {
		        		listaServicos.add(new Servico(
		        					Integer.parseInt(listaServicosId[i]),
		        					codigoServico[i], 
		        					descricaoServico[i],
		        					new Date()
		        				)
		        			);
		        	}
		        }
	        }
	        
	        if(operacao.equals("SALVAR") || operacao.equals("CONSULTAR")){
	        	fornecedor = new Fornecedor(txtCnpj, txtRazaoSocial, txtNomeFantasia, txtInscricaoMunicipal, txtInscricaoEstadual, listaCnaes, txtEmail, listaTelefones, listaContatos, endereco, txtTipoFornecimento, listaProdutos, listaServicos, txtStatus);
	        }
	        
	        if(operacao.equals("VISUALIZAR") || operacao.equals("EXCLUIR")) {
				int txtId = (Integer.parseInt(request.getParameter("txtId")));
				fornecedor = new Fornecedor(txtId);
	        }
	        
	        if(operacao.equals("ALTERAR")) {
	        	int txtId = (Integer.parseInt(request.getParameter("txtId")));
	        	fornecedor = new Fornecedor(txtId, txtCnpj, txtRazaoSocial, txtNomeFantasia, txtInscricaoMunicipal, txtInscricaoEstadual, listaCnaes, txtEmail, listaTelefones, listaContatos, endereco, txtTipoFornecimento, listaProdutos, listaServicos, txtStatus, new Date());
	        }
		
		return fornecedor;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String operacao = request.getParameter("operacao");
		
		RequestDispatcher dispatcher = null;

		if (operacao.equals("SALVAR")) {
			if (resultado.getMsg() == null) {
				resultado.setMsg("Fornecedor salvo com sucesso.");
				request.setAttribute("resultado", resultado);
				dispatcher = request.getRequestDispatcher("/cadastroFornecedor.jsp");

			} else {
				request.setAttribute("resultado", resultado);
				request.getSession().setAttribute("fornecedorSessao", resultado);
				dispatcher = request.getRequestDispatcher("/cadastroFornecedor.jsp");
			}
		} else if (operacao.equals("CONSULTAR")) {
			
			request.getSession().setAttribute("fornecedorSessao", resultado);	
			dispatcher = request.getRequestDispatcher("/search.jsp");
			
		} else if (operacao.equals("VISUALIZAR")) {
			request.getSession().setAttribute("visualizarFornecedor", resultado.getEntidades().get(0));
			dispatcher = request.getRequestDispatcher("/visualizarFornecedor.jsp");

		} else if (operacao.equals("ALTERAR")) {
			if (resultado.getMsg() == null) {
				resultado.setMsg("Fornecedor alterado com sucesso.");
				request.setAttribute("resultado", resultado);
				dispatcher = request.getRequestDispatcher("/visualizarFornecedor.jsp");
			} else {
				request.setAttribute("resultado", resultado);
				request.getSession().setAttribute("fornecedorSessao", resultado);
				dispatcher = request.getRequestDispatcher("/visualizarFornecedor.jsp");
			}

		} else if (operacao.equals("EXCLUIR")) {

			request.setAttribute("fornecedorSessao", resultado);
			dispatcher = request.getRequestDispatcher("/search.jsp");
		}

		dispatcher.forward(request, response);
		
		
	}

}
