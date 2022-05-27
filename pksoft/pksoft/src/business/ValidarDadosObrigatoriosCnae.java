package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarDadosObrigatoriosCnae implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor)entidade;

        for (int i = 0; i<fornecedor.getCnaes().size(); i++) {
            if(fornecedor.getCnaes().get(i).getCodigo() == null || 
			   fornecedor.getCnaes().get(i).getCodigo() == ""){
				return "Cnae(s) invalido(s)\n";
			}
        }
		
		return null;
		
	}

}
