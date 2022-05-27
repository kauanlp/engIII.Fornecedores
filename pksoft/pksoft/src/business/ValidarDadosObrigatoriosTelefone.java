package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarDadosObrigatoriosTelefone implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		StringBuilder sb = new StringBuilder();
		Fornecedor fornecedor = (Fornecedor)entidade;

        for (int i = 0; i<fornecedor.getTelefones().size(); i++) {

			if(fornecedor.getTelefones().get(i).getTipoTelefone() == null || 
			   fornecedor.getTelefones().get(i).getTipoTelefone() == ""){
			 	sb.append("Tipo Telefone invalido\n");
		 	}

			if(fornecedor.getTelefones().get(i).getDdi() == null || 
			   fornecedor.getTelefones().get(i).getDdi() == ""){
			 	sb.append("DDI Telefone invalido(s)\n");
		 	}

			if(fornecedor.getTelefones().get(i).getDdd() == null || 
			   fornecedor.getTelefones().get(i).getDdd() == ""){
			 	sb.append("DDD Telefone invalido(s)\n");
		 	}
			 
			if(fornecedor.getTelefones().get(i).getNumero() == null || 
			   fornecedor.getTelefones().get(i).getNumero() == ""){
			 	sb.append("Numero Telefone invalido(s)\n");
		 	}
        }
        
        if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }
		
	}

}
