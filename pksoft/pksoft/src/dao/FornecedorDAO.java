package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dominio.Cnae;
import dominio.Contato;
import dominio.Endereco;
import dominio.EntidadeDominio;
import dominio.Fornecedor;
import dominio.Produto;
import dominio.Servico;
import dominio.Telefone;

public class FornecedorDAO extends AbstractJdbcDAO {

	IDAO cnaeDAO = new CnaeDAO();
	IDAO contatoDAO = new ContatoDAO();
	IDAO telefoneDAO = new TelefoneDAO();
	IDAO produtoDAO = new ProdutoDAO();
	IDAO servicoDAO = new ServicoDAO();
	IDAO forcnaeDAO = new FornecedorCnaeDAO();
	IDAO fortelDAO = new FornecedorTelefoneDAO();
	IDAO forcttDAO = new FornecedorContatoDAO();
	IDAO forpdtDAO = new FornecedorProdutoDAO();
	IDAO forsvcDAO = new FornecedorServicoDAO();

	public FornecedorDAO(Connection cx) {
		super(cx, "tb_fornecedores", "for_id");
	}
	
	public FornecedorDAO(){
		super("tb_fornecedores", "for_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor) entidade;
		
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_fornecedores(for_cnpj, for_rz_social, for_nm_fantasia, for_insc_municipal, for_insc_estadual, for_email, for_end_tipo, for_end_cep, for_end_tipo_logradouro, for_end_logradouro, for_end_numero, for_end_bairro, for_end_complemento, for_end_cidade, for_end_estado, for_end_pais, for_tp_fornecimento, for_dt_cadastro, for_status) ");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");	
		try {
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), 
			Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, fornecedor.getCnpj());
			pst.setString(2, fornecedor.getRzSocial());
			pst.setString(3, fornecedor.getNmFantasia());
			pst.setString(4, fornecedor.getInscricaoMunicipal());
			pst.setString(5, fornecedor.getInscricaoEstadual());
			pst.setString(6, fornecedor.getEmail());
			pst.setString(7, fornecedor.getEndereco().getTipoEndereco());
			pst.setString(8, fornecedor.getEndereco().getCep());
			pst.setString(9, fornecedor.getEndereco().getTipoLogradouro());
			pst.setString(10, fornecedor.getEndereco().getLogradouro());
			pst.setString(11, fornecedor.getEndereco().getNumero());
			pst.setString(12, fornecedor.getEndereco().getBairro());
			pst.setString(13, fornecedor.getEndereco().getComplemento());
			pst.setString(14, fornecedor.getEndereco().getCidade());
			pst.setString(15, fornecedor.getEndereco().getEstado());
			pst.setString(16, fornecedor.getEndereco().getPais());
			pst.setString(17, fornecedor.getTipoFornecimento());
            pst.setTimestamp(18, new Timestamp(fornecedor.getDtCadastro().getTime()));
			pst.setString(19, fornecedor.getStatus());
			
			pst.executeUpdate();	
			
			connection.commit();	
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				fornecedor.setId(rs.getInt(1));
			}

            for (Cnae cnae : fornecedor.getCnaes()) {
                cnaeDAO.salvar(cnae);
            }
            
            for (Contato contato : fornecedor.getContatos()) {
                telefoneDAO.salvar(contato.getTelefone());
                contatoDAO.salvar(contato);
            }
            
            for (Telefone telefone : fornecedor.getTelefones()) {
                telefoneDAO.salvar(telefone);
            }
            
            for (Produto produto : fornecedor.getProdutosOfertados()) {
                produtoDAO.salvar(produto);
            }
            
            for (Servico servico : fornecedor.getServicosOfertados()) {
                servicoDAO.salvar(servico);
            }
            
            forcnaeDAO.salvar(fornecedor);
            fortelDAO.salvar(fornecedor);
            forcttDAO.salvar(fornecedor);

            if (fornecedor.getTipoFornecimento().equals("VENDA")||fornecedor.getTipoFornecimento().equals("DUPLO")) {
                forpdtDAO.salvar(fornecedor);
            }
            
            if (fornecedor.getTipoFornecimento().equals("SERVICO")||fornecedor.getTipoFornecimento().equals("DUPLO")) {
                forsvcDAO.salvar(fornecedor);
            }
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction) {
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void alterar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor) entidade;
		
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_fornecedores SET for_cnpj=?, for_rz_social=?, for_nm_fantasia=?, for_insc_municipal=?, for_insc_estadual=?, for_email=?, for_end_tipo=?, for_end_cep=?, for_end_tipo_logradouro=?, for_end_logradouro=?, for_end_numero=?, for_end_bairro=?, for_end_complemento=?, for_end_cidade=?, for_end_estado=?, for_end_pais=?, for_tp_fornecimento=?, for_dt_cadastro=?, for_status=? ");
		sql.append("WHERE for_id=?;");	
		try {
			connection.setAutoCommit(false);

			pst = connection.prepareStatement(sql.toString(), 
			Statement.RETURN_GENERATED_KEYS);
						
			pst.setString(1, fornecedor.getCnpj());
			pst.setString(2, fornecedor.getRzSocial());
			pst.setString(3, fornecedor.getNmFantasia());
			pst.setString(4, fornecedor.getInscricaoMunicipal());
			pst.setString(5, fornecedor.getInscricaoEstadual());
			pst.setString(6, fornecedor.getEmail());
			pst.setString(7, fornecedor.getEndereco().getTipoEndereco());
			pst.setString(8, fornecedor.getEndereco().getCep());
			pst.setString(9, fornecedor.getEndereco().getTipoLogradouro());
			pst.setString(10, fornecedor.getEndereco().getLogradouro());
			pst.setString(11, fornecedor.getEndereco().getNumero());
			pst.setString(12, fornecedor.getEndereco().getBairro());
			pst.setString(13, fornecedor.getEndereco().getComplemento());
			pst.setString(14, fornecedor.getEndereco().getCidade());
			pst.setString(15, fornecedor.getEndereco().getEstado());
			pst.setString(16, fornecedor.getEndereco().getPais());
			pst.setString(17, fornecedor.getTipoFornecimento());
            pst.setTimestamp(18, new Timestamp(fornecedor.getDtCadastro().getTime()));
			pst.setString(19, fornecedor.getStatus());
			pst.setInt(20, fornecedor.getId());

			pst.executeUpdate();	

			connection.commit();	
			

            for (Cnae cnae : fornecedor.getCnaes()) {
            	if(cnae.getId() == null) {
            		cnaeDAO.salvar(cnae);
            	} else {
                    cnaeDAO.alterar(cnae);
            	}

            }
            
            for (Telefone telefone : fornecedor.getTelefones()) {
            	if(telefone.getId() == null) {
            		telefoneDAO.salvar(telefone);
            	} else {
            		telefoneDAO.alterar(telefone);
            	}
            }
            
            for (Produto produto : fornecedor.getProdutosOfertados()) {
            	if(produto.getId() == null) {
                    produtoDAO.salvar(produto);
            	} else {
                    produtoDAO.alterar(produto);
            	}
            }
            
            for (Servico servico : fornecedor.getServicosOfertados()) {
            	if(servico.getId() == null) {
            		servicoDAO.salvar(servico);
            	} else {
            		servicoDAO.alterar(servico);
            	}
            }
            
            for (Contato contato : fornecedor.getContatos()) {
            	if(contato.getId() == null) {
                    telefoneDAO.salvar(contato.getTelefone());
                    contatoDAO.salvar(contato);
            	} else {
                    telefoneDAO.alterar(contato.getTelefone());
                    contatoDAO.alterar(contato);
            	}
            }
            
            forcnaeDAO.excluir(fornecedor);
            forcnaeDAO.salvar(fornecedor);
            
            fortelDAO.excluir(fornecedor);
            fortelDAO.salvar(fornecedor);
            
            forcttDAO.excluir(fornecedor);
            forcttDAO.salvar(fornecedor);

            if (fornecedor.getTipoFornecimento().equals("VENDA")||fornecedor.getTipoFornecimento().equals("DUPLO")) {
                forpdtDAO.excluir(fornecedor);
                forpdtDAO.salvar(fornecedor);
            }
            
            if (fornecedor.getTipoFornecimento().equals("SERVICO")||fornecedor.getTipoFornecimento().equals("DUPLO")) {
                forsvcDAO.excluir(fornecedor);
                forsvcDAO.salvar(fornecedor);
            }

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction) {
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		Fornecedor fornecedor = (Fornecedor) entidade;
		List<EntidadeDominio> listaFornecedores = new ArrayList<EntidadeDominio>();
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT ");
        sql.append("for_id ");

        sql.append("FROM ");
        sql.append("tb_fornecedores ");
        sql.append("LEFT JOIN tb_fornecedor_cnae ON tb_fornecedor_cnae.forcnae_for_id = tb_fornecedores.for_id ");
        sql.append("LEFT JOIN tb_cnaes ON tb_cnaes.cnae_id = tb_fornecedor_cnae.forcnae_cnae_id ");

        sql.append("LEFT JOIN tb_fornecedor_telefone ON tb_fornecedor_telefone.fortel_for_id = tb_fornecedores.for_id ");
        sql.append("LEFT JOIN tb_telefones fortel ON fortel.tel_id = tb_fornecedor_telefone.fortel_tel_id ");

        sql.append("LEFT JOIN tb_fornecedor_contato ON tb_fornecedor_contato.forctt_for_id = tb_fornecedores.for_id ");
        sql.append("LEFT JOIN tb_contatos ON tb_contatos.ctt_id = tb_fornecedor_contato.forctt_ctt_id ");
        sql.append("LEFT JOIN tb_telefones ctttel ON ctttel.tel_id = tb_contatos.ctt_telefone_id ");

        sql.append("LEFT JOIN tb_fornecedor_servico ON tb_fornecedor_servico.forsvc_for_id = tb_fornecedores.for_id ");
        sql.append("LEFT JOIN tb_servicos ON tb_servicos.svc_id = tb_fornecedor_servico.forsvc_svc_id ");
        
        sql.append("LEFT JOIN tb_fornecedor_produto ON tb_fornecedor_produto.forpdt_for_id = tb_fornecedores.for_id ");
        sql.append("LEFT JOIN tb_produtos ON tb_produtos.pdt_id = tb_fornecedor_produto.forpdt_pdt_id ");

        sql.append("WHERE ");
 
		if(fornecedor.getId()!= null && fornecedor.getId()> 0) sql.append("for_id = '" + fornecedor.getId() + "' AND ");
		if(fornecedor.getCnpj()!= null && fornecedor.getCnpj()!= "") sql.append("for_cnpj LIKE '%" + fornecedor.getCnpj() + "%' AND ");
		if(fornecedor.getRzSocial()!= null && fornecedor.getRzSocial()!= "") sql.append("for_rz_social LIKE '%" + fornecedor.getRzSocial() + "%' AND ");
		if(fornecedor.getNmFantasia()!= null && fornecedor.getNmFantasia()!= "") sql.append("for_nm_fantasia LIKE '%" + fornecedor.getNmFantasia() + "%' AND ");
		if(fornecedor.getInscricaoMunicipal()!= null && fornecedor.getInscricaoMunicipal()!= "") sql.append("for_insc_municipal LIKE '%" + fornecedor.getInscricaoMunicipal() + "%' AND ");
		if(fornecedor.getInscricaoEstadual()!= null && fornecedor.getInscricaoEstadual()!= "") sql.append("for_insc_estadual LIKE '%" + fornecedor.getInscricaoEstadual() + "%' AND ");
		
		if(fornecedor.getCnaes()!= null) {
			if(fornecedor.getCnaes().get(0).getCodigo()!= null && fornecedor.getCnaes().get(0).getCodigo()!= "") sql.append("cnae_codigo LIKE '%" + fornecedor.getCnaes().get(0).getCodigo() + "%' AND ");
		}
		
		if(fornecedor.getEmail()!= null && fornecedor.getEmail()!= "") sql.append("for_email LIKE '%" + fornecedor.getEmail() + "%' AND ");
		
		if(fornecedor.getTelefones()!= null) {
			if(fornecedor.getTelefones().get(0).getTipoTelefone()!= null && fornecedor.getTelefones().get(0).getTipoTelefone()!= "") sql.append("fortel.tel_tipo = '" + fornecedor.getTelefones().get(0).getTipoTelefone() + "' AND ");
			if(fornecedor.getTelefones().get(0).getDdi()!= null && fornecedor.getTelefones().get(0).getDdi()!= "") sql.append("fortel.tel_ddi = '" + fornecedor.getTelefones().get(0).getDdi() + "' AND ");
			if(fornecedor.getTelefones().get(0).getDdd()!= null && fornecedor.getTelefones().get(0).getDdd()!= "") sql.append("fortel.tel_ddd = '" + fornecedor.getTelefones().get(0).getDdd() + "' AND ");
			if(fornecedor.getTelefones().get(0).getNumero()!= null && fornecedor.getTelefones().get(0).getNumero()!= "") sql.append("fortel.tel_numero LIKE '%" + fornecedor.getTelefones().get(0).getNumero() + "%' AND ");
		}
		
		if(fornecedor.getContatos()!= null) {
			if(fornecedor.getContatos().get(0).getNome()!= null && fornecedor.getContatos().get(0).getNome()!= "") sql.append("ctt_nome LIKE '%" + fornecedor.getContatos().get(0).getNome() + "%' AND ");
			if(fornecedor.getContatos().get(0).getDepartamento()!= null && fornecedor.getContatos().get(0).getDepartamento()!= "") sql.append("ctt_dpto LIKE '%" + fornecedor.getContatos().get(0).getDepartamento() + "%' AND ");
			if(fornecedor.getContatos().get(0).getEmail()!= null && fornecedor.getContatos().get(0).getEmail()!= "") sql.append("ctt_email LIKE '%" + fornecedor.getContatos().get(0).getEmail() + "%' AND ");
			if(fornecedor.getContatos().get(0).getTelefone().getTipoTelefone()!= null && fornecedor.getContatos().get(0).getTelefone().getTipoTelefone()!= "") sql.append("ctttel.tel_tipo = '" + fornecedor.getContatos().get(0).getTelefone().getTipoTelefone() + "' AND ");
			if(fornecedor.getContatos().get(0).getTelefone().getDdi()!= null && fornecedor.getContatos().get(0).getTelefone().getDdi()!= "") sql.append("ctttel.tel_ddi = '" + fornecedor.getContatos().get(0).getTelefone().getDdi() + "' AND ");
			if(fornecedor.getContatos().get(0).getTelefone().getDdd()!= null && fornecedor.getContatos().get(0).getTelefone().getDdd()!= "") sql.append("ctttel.tel_ddd = '" + fornecedor.getContatos().get(0).getTelefone().getDdd() + "' AND ");
			if(fornecedor.getContatos().get(0).getTelefone().getNumero()!= null && fornecedor.getContatos().get(0).getTelefone().getNumero()!= "") sql.append("ctttel.tel_numero LIKE '%" + fornecedor.getContatos().get(0).getTelefone().getNumero() + "%' AND ");
		}
		
		if(fornecedor.getEndereco()!= null) {
			if(fornecedor.getEndereco().getTipoEndereco()!= null && fornecedor.getEndereco().getTipoEndereco()!= "") sql.append("for_end_tipo = '" + fornecedor.getEndereco().getTipoEndereco() + "' AND ");
			if(fornecedor.getEndereco().getCep()!= null && fornecedor.getEndereco().getCep()!= "") sql.append("for_end_cep LIKE '%" + fornecedor.getEndereco().getCep() + "%' AND ");
			if(fornecedor.getEndereco().getTipoLogradouro()!= null && fornecedor.getEndereco().getTipoLogradouro()!= "") sql.append("for_end_tipo_logradouro = '" + fornecedor.getEndereco().getTipoLogradouro() + "' AND ");
			if(fornecedor.getEndereco().getLogradouro()!= null && fornecedor.getEndereco().getLogradouro()!= "") sql.append("for_end_logradouro LIKE '%" + fornecedor.getEndereco().getLogradouro() + "%' AND ");
			if(fornecedor.getEndereco().getNumero()!= null && fornecedor.getEndereco().getNumero()!= "") sql.append("for_end_numero = '" + fornecedor.getEndereco().getNumero() + "' AND ");
			if(fornecedor.getEndereco().getBairro()!= null && fornecedor.getEndereco().getBairro()!= "") sql.append("for_end_bairro LIKE '%" + fornecedor.getEndereco().getBairro() + "%' AND ");
			if(fornecedor.getEndereco().getComplemento()!= null && fornecedor.getEndereco().getComplemento()!= "") sql.append("for_end_complemento LIKE '%" + fornecedor.getEndereco().getComplemento() + "%' AND ");
			if(fornecedor.getEndereco().getCidade()!= null && fornecedor.getEndereco().getCidade()!= "") sql.append("for_end_cidade LIKE '%" + fornecedor.getEndereco().getCidade() + "%' AND ");
			if(fornecedor.getEndereco().getEstado()!= null && fornecedor.getEndereco().getEstado()!= "") sql.append("for_end_estado LIKE '%" + fornecedor.getEndereco().getEstado() + "%' AND ");
			if(fornecedor.getEndereco().getPais()!= null && fornecedor.getEndereco().getPais()!= "") sql.append("for_end_pais LIKE '%" + fornecedor.getEndereco().getPais() + "%' AND ");
		}
		
		if(fornecedor.getTipoFornecimento()!= null && fornecedor.getTipoFornecimento()!= "") sql.append("for_tp_fornecimento = '" + fornecedor.getTipoFornecimento() + "' AND ");
		
		if(fornecedor.getProdutosOfertados()!= null) {
			if(fornecedor.getProdutosOfertados().get(0).getCodigo()!= null && fornecedor.getProdutosOfertados().get(0).getCodigo()!= "") sql.append("pdt_codigo = '" + fornecedor.getProdutosOfertados().get(0).getCodigo() + "' AND ");
			if(fornecedor.getProdutosOfertados().get(0).getDescricao()!= null && fornecedor.getProdutosOfertados().get(0).getDescricao()!= "") sql.append("pdt_descricao LIKE '%" + fornecedor.getProdutosOfertados().get(0).getDescricao() + "%' AND ");
		}
		
		if(fornecedor.getServicosOfertados()!= null) {
			if(fornecedor.getServicosOfertados().get(0).getCodigo()!= null && fornecedor.getServicosOfertados().get(0).getCodigo()!= "") sql.append("svc_codigo = '" + fornecedor.getServicosOfertados().get(0).getCodigo() + "' AND ");
			if(fornecedor.getServicosOfertados().get(0).getDescricao()!= null && fornecedor.getServicosOfertados().get(0).getDescricao()!= "") sql.append("svc_descricao LIKE '%" + fornecedor.getServicosOfertados().get(0).getDescricao() + "%' AND ");
		}
		
		if(fornecedor.getStatus()!= null && fornecedor.getStatus()!= "") sql.append("for_status = '" + fornecedor.getStatus() + "' AND ");
		
		sql.append("TRUE ");

        sql.append("GROUP BY ");
        sql.append("for_id;");

		try {				
			pst = connection.prepareStatement(sql.toString());

			// pst.setInt(1, fornecedor.getId());
			// pst.setString(2, fornecedor.getCnpj());
			// pst.setString(3, fornecedor.getRzSocial());
			// pst.setString(4, fornecedor.getNmFantasia());
			// pst.setString(5, fornecedor.getInscricaoMunicipal());
			// pst.setString(6, fornecedor.getInscricaoEstadual());
			// pst.setString(7, fornecedor.getCnaes().get(0).getCodigo());
			// pst.setString(8, fornecedor.getEmail());
			// pst.setString(9, fornecedor.getTelefones().get(0).getTipoTelefone());
			// pst.setString(10, fornecedor.getTelefones().get(0).getDdi());
			// pst.setString(11, fornecedor.getTelefones().get(0).getDdd());
			// pst.setString(12, fornecedor.getTelefones().get(0).getNumero());
			// pst.setString(13, fornecedor.getContatos().get(0).getNome());
			// pst.setString(14, fornecedor.getContatos().get(0).getDepartamento());
			// pst.setString(15, fornecedor.getContatos().get(0).getEmail());
			// pst.setString(16, fornecedor.getContatos().get(0).getTelefone().getTipoTelefone());
			// pst.setString(17, fornecedor.getContatos().get(0).getTelefone().getDdi());
			// pst.setString(18, fornecedor.getContatos().get(0).getTelefone().getDdd());
			// pst.setString(19, fornecedor.getContatos().get(0).getTelefone().getNumero());
			// pst.setString(20, fornecedor.getEndereco().getTipoEndereco());
			// pst.setString(21, fornecedor.getEndereco().getCep());
			// pst.setString(22, fornecedor.getEndereco().getTipoLogradouro());
			// pst.setString(23, fornecedor.getEndereco().getLogradouro());
			// pst.setString(24, fornecedor.getEndereco().getNumero());
			// pst.setString(25, fornecedor.getEndereco().getBairro());
			// pst.setString(26, fornecedor.getEndereco().getComplemento());
			// pst.setString(27, fornecedor.getEndereco().getCidade());
			// pst.setString(28, fornecedor.getEndereco().getEstado());
			// pst.setString(29, fornecedor.getEndereco().getPais());
			// pst.setString(30, fornecedor.getTipoFornecimento());
			// pst.setString(31, fornecedor.getProdutosOfertados().get(0).getCodigo());
			// pst.setString(32, fornecedor.getProdutosOfertados().get(0).getDescricao());
			// pst.setString(33, fornecedor.getServicosOfertados().get(0).getCodigo());
			// pst.setString(34, fornecedor.getServicosOfertados().get(0).getDescricao());
			// pst.setString(35, fornecedor.getStatus());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaFornecedores.add(consultarPorId(rs.getInt("for_id")));
			}					
		} catch (SQLException e) {
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return listaFornecedores;
	}

	@SuppressWarnings("unchecked")
	public Fornecedor consultarPorId(Integer id) {
		Fornecedor fornecedor = new Fornecedor(id);
		Fornecedor resultado = null;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ");
		sql.append(table);
		sql.append(" WHERE ");
		sql.append(idTable);
		sql.append("=");
		sql.append("?");
		
		try {				
			pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				resultado = new Fornecedor(
					rs.getInt("for_id"),
					rs.getString("for_cnpj"),
					rs.getString("for_rz_social"),
					rs.getString("for_nm_fantasia"),
					rs.getString("for_insc_municipal"),
					rs.getString("for_insc_estadual"),
					(List<Cnae>)(List<?>)forcnaeDAO.consultar(fornecedor),
					rs.getString("for_email"),
					(List<Telefone>)(List<?>)fortelDAO.consultar(fornecedor),
					(List<Contato>)(List<?>)forcttDAO.consultar(fornecedor),
					new Endereco(
						rs.getString("for_end_tipo"),
						rs.getString("for_end_cep"),
						rs.getString("for_end_tipo_logradouro"),
						rs.getString("for_end_logradouro"),
						rs.getString("for_end_numero"),
						rs.getString("for_end_bairro"),
						rs.getString("for_end_complemento"),
						rs.getString("for_end_cidade"),
						rs.getString("for_end_estado"),
						rs.getString("for_end_pais")
					),
					rs.getString("for_tp_fornecimento"),
					(List<Produto>)(List<?>)forpdtDAO.consultar(fornecedor),
					(List<Servico>)(List<?>)forsvcDAO.consultar(fornecedor),
					rs.getString("for_status"),
					new Date(rs.getTimestamp("for_dt_cadastro").getTime())
				);
			}					
		} catch (SQLException e) {
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}

	// public void ativar(EntidadeDominio entidade) {
	// 	Fornecedor fornecedor = (Fornecedor) entidade;
	// 	openConnection();
	// 	PreparedStatement pst=null;
	// 	StringBuilder sql = new StringBuilder();
		
	// 	sql.append("UPDATE ");
	// 	sql.append(table);
	// 	sql.append(" SET ");
	// 	sql.append("for_status");
	// 	sql.append("=");
	// 	sql.append("?");	
	// 	sql.append(" WHERE ");
	// 	sql.append(idTable);
	// 	sql.append("=");
	// 	sql.append("?");	
	// 	try {
	// 		connection.setAutoCommit(false);				
	// 		pst = connection.prepareStatement(sql.toString());
				
	// 		pst.setString(1, "ATIVO");
	// 		pst.setInt(2, fornecedor.getId());
		
	// 		pst.executeUpdate();		
	// 		connection.commit();					
	// 	} catch (SQLException e) {
	// 		try {
	// 			connection.rollback();
	// 		} catch (SQLException e1) {
	// 			e1.printStackTrace();
	// 		}
	// 		e.printStackTrace();	
	// 	}finally{
	// 		if(ctrlTransaction) {
	// 			try {
	// 				pst.close();
	// 				if(ctrlTransaction)
	// 					connection.close();
	// 			} catch (SQLException e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	}
	// }

	// public void inativar(EntidadeDominio entidade) {
	// 	Fornecedor fornecedor = (Fornecedor) entidade;
	// 	openConnection();
	// 	PreparedStatement pst=null;
	// 	StringBuilder sql = new StringBuilder();
		
	// 	sql.append("UPDATE ");
	// 	sql.append(table);
	// 	sql.append(" SET ");
	// 	sql.append("for_status");
	// 	sql.append("=");
	// 	sql.append("?");	
	// 	sql.append(" WHERE ");
	// 	sql.append(idTable);
	// 	sql.append("=");
	// 	sql.append("?");	
	// 	try {
	// 		connection.setAutoCommit(false);				
	// 		pst = connection.prepareStatement(sql.toString());
				
	// 		pst.setString(1, "INATIVO");
	// 		pst.setInt(2, fornecedor.getId());
		
	// 		pst.executeUpdate();		
	// 		connection.commit();					
	// 	} catch (SQLException e) {
	// 		try {
	// 			connection.rollback();
	// 		} catch (SQLException e1) {
	// 			e1.printStackTrace();
	// 		}
	// 		e.printStackTrace();	
	// 	}finally{
	// 		if(ctrlTransaction) {
	// 			try {
	// 				pst.close();
	// 				if(ctrlTransaction)
	// 					connection.close();
	// 			} catch (SQLException e) {
	// 				e.printStackTrace();
	// 			}
	// 		}
	// 	}
	// }
}

