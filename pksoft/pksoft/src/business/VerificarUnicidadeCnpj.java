package business;

import java.util.ArrayList;

import dao.FornecedorDAO;
import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class VerificarUnicidadeCnpj implements IStrategy {

    public String processar(EntidadeDominio entidade) {
        Fornecedor fornecedor = (Fornecedor) entidade;

        FornecedorDAO forDAO = new FornecedorDAO();
        ArrayList<Fornecedor> forn = null;
        try {
            forn = (ArrayList) forDAO.consultar(new Fornecedor());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Object fornecedor1 : forn) {
            if (fornecedor.getCnpj().equals(((Fornecedor) fornecedor1).getCnpj())) {
                return "CNPJ já cadastrado";
            }
        }

        return null;
    }

}
