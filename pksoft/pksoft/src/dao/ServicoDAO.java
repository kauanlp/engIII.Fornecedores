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

import dominio.EntidadeDominio;
import dominio.Servico;

public class ServicoDAO extends AbstractJdbcDAO {
	

	public ServicoDAO(Connection cx){
		super(cx, "tb_servicos", "svc_id");
	}
	
	public ServicoDAO(){
		super("tb_servicos", "svc_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Servico servico = (Servico) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_servicos(svc_codigo, svc_descricao, svc_dt_cadastro) ");
		sql.append(" VALUES (?, ?, ?)");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, servico.getCodigo());
            pst.setString(2, servico.getDescricao());
            pst.setTimestamp(3, new Timestamp(servico.getDtCadastro().getTime()));
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				servico.setId(rs.getInt(1));
			}
			connection.commit();					
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		} finally {
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
	}

	public void alterar(EntidadeDominio entidade) {
		Servico servico = (Servico) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_servicos SET svc_codigo=?, svc_descricao=?, svc_dt_cadastro=? ");
		sql.append("WHERE svc_id=?;");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, servico.getCodigo());
            pst.setString(2, servico.getDescricao());
            pst.setTimestamp(3, new Timestamp(servico.getDtCadastro().getTime()));
            pst.setInt(4, servico.getId());
			
			pst.executeUpdate();		
		
			connection.commit();					
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		} finally {
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

	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Servico servico = (Servico) entidade;
		List<EntidadeDominio> listaServicos = new ArrayList<EntidadeDominio>();
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
            pst.setInt(1, servico.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaServicos.add(new Servico(
					rs.getInt("svc_id"),
					rs.getString("svc_codigo"),
					rs.getString("svc_descricao"),
					new Date(rs.getTimestamp("svc_dt_cadastro").getTime())
				));
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
		return listaServicos;
	}

}

