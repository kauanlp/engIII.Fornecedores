package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarDadosObrigatoriosContato implements IStrategy {

	public String processar(EntidadeDominio entidade) {
	    StringBuilder sb = new StringBuilder();
		Fornecedor fornecedor = (Fornecedor)entidade;

        for (int i = 0; i<fornecedor.getContatos().size(); i++) {
            if(fornecedor.getContatos().get(i).getNome() == null || 
			   fornecedor.getContatos().get(i).getNome() == "") {
				sb.append("Nome de contato(s) invalido(s)\n");
			}

			if(fornecedor.getContatos().get(i).getEmail() == null || 
			   fornecedor.getContatos().get(i).getEmail() == "") {
				sb.append("Email de contato(s) invalido(s)\n");
		 	}

			if(fornecedor.getContatos().get(i).getDepartamento() == null || 
			   fornecedor.getContatos().get(i).getDepartamento() == "") {
				sb.append("Departamento de contato(s) invalido(s)\n");
			}

			if(fornecedor.getContatos().get(i).getTelefone().getTipoTelefone() == null || 
				fornecedor.getContatos().get(i).getTelefone().getTipoTelefone() == ""){
				sb.append("Tipo Telefone de contato(s) invalido(s)\n");
			}

			if(fornecedor.getContatos().get(i).getTelefone().getDdi() == null || 
				fornecedor.getContatos().get(i).getTelefone().getDdi() == ""){
				sb.append("DDI Telefone de contato(s) invalido(s)\n");
			}

			if(fornecedor.getContatos().get(i).getTelefone().getDdd() == null || 
				fornecedor.getContatos().get(i).getTelefone().getDdd() == ""){
				sb.append("DDD Telefone de contato(s) invalido(s)\n");
			}
					 
			if(fornecedor.getContatos().get(i).getTelefone().getNumero() == null || 
				fornecedor.getContatos().get(i).getTelefone().getNumero() == ""){
				sb.append("Numero Telefone de contato(s) invalido(s)\n");
			}

        }
        
		if (sb.length() > 0) {
            return sb.toString();
        } else {
            return null;
        }
		
	}

}
