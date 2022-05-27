package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Cnae;
import dominio.EntidadeDominio;
import dominio.Fornecedor;

public class FornecedorCnaeDAO extends AbstractJdbcDAO {
	

	public FornecedorCnaeDAO(Connection cx){
		super(cx, "tb_fornecedor_cnae", "forcnae_id");
	}
	
	public FornecedorCnaeDAO(){
		super("tb_fornecedor_cnae", "forcnae_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor) entidade;
		openConnection();
		PreparedStatement pst=null;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql;
			
			for(int i = 0; i < fornecedor.getCnaes().size(); i++){
				sql = new StringBuilder();
				sql.append("INSERT INTO tb_fornecedor_cnae(forcnae_for_id, forcnae_cnae_id) ");
				sql.append(" VALUES (?, ?)");	
									
				pst = connection.prepareStatement(sql.toString(), 
						Statement.RETURN_GENERATED_KEYS);
				
				pst.setInt(1, fornecedor.getId());
				pst.setInt(2, fornecedor.getCnaes().get(i).getId());
				
				pst.executeUpdate();		
			}

			connection.commit();	
							
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	}

	public void alterar(EntidadeDominio entidade) {
		Fornecedor fornecedor = (Fornecedor) entidade;
		openConnection();
		PreparedStatement pst=null;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql;
			
			for(int i = 0; i < fornecedor.getCnaes().size(); i++){
				sql = new StringBuilder();
				sql.append("UPDATE tb_fornecedor_cnae SET forcnae_for_id=?, forcnae_cnae_id=? ");
				sql.append("WHERE for_id = forcnae_for_id AND cnae_id = forcnae_cnae_id");	
									
				pst = connection.prepareStatement(sql.toString(), 
						Statement.RETURN_GENERATED_KEYS);
				
				pst.setInt(1, fornecedor.getId());
				pst.setInt(2, fornecedor.getCnaes().get(i).getId());
				
				pst.executeUpdate();		
			}

			connection.commit();	
							
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		IDAO cnaeDAO = new CnaeDAO();
		Fornecedor fornecedor = (Fornecedor) entidade;
		List<EntidadeDominio> listaCnaes = new ArrayList<EntidadeDominio>();
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM ");
		sql.append(table);
		sql.append(" WHERE ");
		sql.append("forcnae_for_id");
		sql.append("=");
		sql.append("?");
		
		try {
			pst = connection.prepareStatement(sql.toString());
            pst.setInt(1, fornecedor.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaCnaes.add(
					(Cnae) cnaeDAO.consultar(
						new Cnae(
							rs.getInt("forcnae_cnae_id")
						)
					).get(0)
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
		return listaCnaes;
	}
	
	@Override
	public void excluir(EntidadeDominio entidade) {		
		openConnection();
		PreparedStatement pst=null;		
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(table);
		sb.append(" WHERE ");
		sb.append("forcnae_for_id");
		sb.append("=");
		sb.append("?");	
		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sb.toString());
			pst.setInt(1, entidade.getId());

			pst.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			
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

