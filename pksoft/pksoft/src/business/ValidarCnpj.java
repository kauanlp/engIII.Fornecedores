package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarCnpj implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor)entidade;
		if(fornecedor.getCnpj() == null || fornecedor.getCnpj().length()!=14){
			return "CPNJ INVALIDO";
		}
		return null;
		
	}

}
