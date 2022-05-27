package controller.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aplicacao.Resultado;
import business.IStrategy;
import business.ValidarCnpj;
import business.ValidarDadosObrigatoriosCnae;
import business.ValidarDadosObrigatoriosContato;
import business.ValidarDadosObrigatoriosFornecedor;
import business.ValidarDadosObrigatoriosFornecimento;
import business.ValidarDadosObrigatoriosTelefone;
import business.VerificarUnicidadeCnpj;
import dao.FornecedorDAO;
import dao.IDAO;
import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class Fachada implements IFachada {
	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;

    private Resultado resultado;

    public Fachada() {
        daos = new HashMap<String, IDAO>();
		daos.put(Fornecedor.class.getName(), new FornecedorDAO());
		
        rns = new HashMap<String, Map<String, List<IStrategy>>>();	
		Map<String, List<IStrategy>> rnsSALVAR = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsALTERAR = new HashMap<String, List<IStrategy>>();
		
        ValidarCnpj vCnpj = new ValidarCnpj();
        VerificarUnicidadeCnpj vUnicidadeCnpj = new VerificarUnicidadeCnpj();
        ValidarDadosObrigatoriosFornecedor vDadosFor = new ValidarDadosObrigatoriosFornecedor();
        ValidarDadosObrigatoriosCnae vDadosForCnae = new ValidarDadosObrigatoriosCnae();
        ValidarDadosObrigatoriosContato vDadosForCtt = new ValidarDadosObrigatoriosContato(); 
        ValidarDadosObrigatoriosTelefone vDadosForTel = new ValidarDadosObrigatoriosTelefone();
        ValidarDadosObrigatoriosFornecimento vDadosForFornecimento = new ValidarDadosObrigatoriosFornecimento();
 
        ArrayList<IStrategy> rnsFornecedorSalvar = new ArrayList<IStrategy>();
        rnsFornecedorSalvar.add(vCnpj);
        rnsFornecedorSalvar.add(vUnicidadeCnpj);
        rnsFornecedorSalvar.add(vDadosFor);
        rnsFornecedorSalvar.add(vDadosForCnae);
        rnsFornecedorSalvar.add(vDadosForCtt);
        rnsFornecedorSalvar.add(vDadosForTel);
        rnsFornecedorSalvar.add(vDadosForFornecimento);

        ArrayList<IStrategy> rnsFornecedorAlterar = new ArrayList<IStrategy>();
        rnsFornecedorAlterar.add(vCnpj);
        rnsFornecedorAlterar.add(vDadosFor);
        rnsFornecedorAlterar.add(vDadosForCnae);
        rnsFornecedorAlterar.add(vDadosForCtt);
        rnsFornecedorAlterar.add(vDadosForTel);
        rnsFornecedorAlterar.add(vDadosForFornecimento);

        rnsSALVAR.put(Fornecedor.class.getName(), rnsFornecedorSalvar);

        rnsALTERAR.put(Fornecedor.class.getName(), rnsFornecedorAlterar);

        rns.put("SALVAR", rnsSALVAR);
        rns.put("ALTERAR", rnsALTERAR);

    }

    @Override
    public Resultado salvar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "SALVAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.salvar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch(Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar o cadastro");
            }
        } else {
            resultado.setMsg(msg);
        }

        return resultado;
    }

    @Override
    public Resultado alterar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "ALTERAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.alterar(entidade);
                List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
                entidades.add(entidade);
                resultado.setEntidades(entidades);
            } catch(Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a alteração");
            }
        } else {
            resultado.setMsg(msg);
        }

        return resultado;
    }

    @Override
    public Resultado excluir(EntidadeDominio entidade) {

        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "EXCLUIR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                dao.excluir(entidade);
                resultado.setEntidades(dao.consultar(new Fornecedor()));
            } catch(Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a exclusão");
            }
        }

        return resultado;
    }

    @Override
    public Resultado consultar(EntidadeDominio entidade) {
        resultado = new Resultado();
        String nomeClasse = entidade.getClass().getName();

        String msg = executaRegras(entidade, "CONSULTAR");

        if(msg == null) {
            IDAO dao = daos.get(nomeClasse);
            try {
                resultado.setEntidades(dao.consultar(entidade));
            } catch(Exception e) {
                e.printStackTrace();
                resultado.setMsg("Não foi possível realizar a consulta");
            }
        }

        return resultado;
    }

    private String executaRegras(EntidadeDominio entidade, String operacao) {
        String nomeClasse = entidade.getClass().getName();
        StringBuilder msg = new StringBuilder();

        Map<String, List<IStrategy>> regrasOperacao = rns.get(operacao);

        if(regrasOperacao != null) {
            List<IStrategy> regras =regrasOperacao.get(nomeClasse);

            if(regras != null) {
                for(IStrategy regra : regras) {
                    String m = null;
                    try {
                        m = regra.processar(entidade);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }
        }

        if(msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
