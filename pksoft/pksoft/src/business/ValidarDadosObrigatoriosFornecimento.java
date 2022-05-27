package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarDadosObrigatoriosFornecimento implements IStrategy {

	public String processar(EntidadeDominio entidade) {
	    StringBuilder sb = new StringBuilder();
		Fornecedor fornecedor = (Fornecedor)entidade;
		
		if(fornecedor.getTipoFornecimento() == "PRODUTOS" || fornecedor.getTipoFornecimento() == "DUPLO") {

			for (int i = 0; i<fornecedor.getProdutosOfertados().size(); i++) {
	
				if(fornecedor.getProdutosOfertados().get(i).getCodigo() == null || 
				   fornecedor.getProdutosOfertados().get(i).getCodigo() == ""){
				 	sb.append("Codigo de Produto invalido\n");
			 	}
	
				if(fornecedor.getProdutosOfertados().get(i).getDescricao() == null || 
				   fornecedor.getProdutosOfertados().get(i).getDescricao() == ""){
				    sb.append("Descricao de Produto invalida\n");
			    }
	        }
		}

		if(fornecedor.getTipoFornecimento() == "PRODUTOS" || fornecedor.getTipoFornecimento() == "DUPLO") {

	        for (int i = 0; i<fornecedor.getServicosOfertados().size(); i++) {
	
				if(fornecedor.getServicosOfertados().get(i).getCodigo() == null || 
				   fornecedor.getServicosOfertados().get(i).getCodigo() == ""){
				 	sb.append("Codigo de Servico invalido\n");
			 	}
	
				if(fornecedor.getServicosOfertados().get(i).getDescricao() == null || 
				   fornecedor.getServicosOfertados().get(i).getDescricao() == ""){
				    sb.append("Descricao de Servico invalida\n");
			    }
	        }
		}

        if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }
		
	}

}
