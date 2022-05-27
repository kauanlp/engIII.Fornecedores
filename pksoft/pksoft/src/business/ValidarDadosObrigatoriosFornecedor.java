package business;

import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class ValidarDadosObrigatoriosFornecedor implements IStrategy {

	public String processar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor)entidade;
		String cnpj = fornecedor.getCnpj();
		String rzSocial = fornecedor.getRzSocial();
		String nomeFantasia = fornecedor.getNmFantasia();
		String inscMun = fornecedor.getInscricaoMunicipal();
		String inscEst = fornecedor.getInscricaoEstadual();
		
		if(cnpj == null || rzSocial == null || nomeFantasia == null || inscMun == null || inscEst == null){
			return "Cnpj, Razao social, Nome Fantasia, Inscricao Municipal e Inscricao Estadual são de preenchimento obrigatório!\n";
		}
		
		if(cnpj.trim().equals("") || rzSocial.trim().equals("") || nomeFantasia.trim().equals("") || inscMun.trim().equals("") || inscEst.trim().equals("")){
			return "Cnpj, Razao social, Nome Fantasia, Inscricao Municipal e Inscricao Estadual são de preenchimento obrigatório!\n";
		}
		
		return null;
		
	}

}
