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
import dominio.EntidadeDominio;

public class CnaeDAO extends AbstractJdbcDAO {
	

	public CnaeDAO(Connection cx){
		super(cx, "tb_cnaes", "cnae_id");
	}
	
	public CnaeDAO(){
		super("tb_cnaes", "cnae_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Cnae cnae = (Cnae) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_cnaes(cnae_codigo, cnae_dt_cadastro) ");
		sql.append(" VALUES (?, ?)");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, cnae.getCodigo());
            pst.setTimestamp(2, new Timestamp(cnae.getDtCadastro().getTime()));
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				cnae.setId(rs.getInt(1));
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
		Cnae cnae = (Cnae) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_cnaes SET cnae_codigo=? ");
		sql.append("WHERE cnae_id=?;");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, cnae.getCodigo());
            pst.setInt(2, cnae.getId());
			
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
		Cnae cnae = (Cnae) entidade;
		List<EntidadeDominio> listaCnaes = new ArrayList<EntidadeDominio>();
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
            pst.setInt(1, cnae.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaCnaes.add(new Cnae(
					rs.getInt("cnae_id"),
					rs.getString("cnae_codigo"),
					new Date(rs.getTimestamp("cnae_dt_cadastro").getTime())
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
		return listaCnaes;
	}

}

