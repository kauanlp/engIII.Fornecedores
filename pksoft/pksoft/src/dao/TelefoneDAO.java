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
import dominio.Telefone;

public class TelefoneDAO extends AbstractJdbcDAO {
	

	public TelefoneDAO(Connection cx){
		super(cx, "tb_telefones", "tel_id");
	}
	
	public TelefoneDAO(){
		super("tb_telefones", "tel_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_telefones(tel_tipo, tel_ddi, tel_ddd, tel_numero, tel_dt_cadastro) ");
		sql.append(" VALUES (?, ?, ?, ?, ?)");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, telefone.getTipoTelefone());
			pst.setString(2, telefone.getDdi());
			pst.setString(3, telefone.getDdd());
			pst.setString(4, telefone.getNumero());
            pst.setTimestamp(5, new Timestamp(telefone.getDtCadastro().getTime()));
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				telefone.setId(rs.getInt(1));
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
		Telefone telefone = (Telefone) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_telefones SET tel_tipo=?, tel_ddi=?, tel_ddd=?, tel_numero=?, tel_dt_cadastro=? ");
		sql.append("WHERE tel_id=?");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, telefone.getTipoTelefone());
			pst.setString(2, telefone.getDdi());
			pst.setString(3, telefone.getDdd());
			pst.setString(4, telefone.getNumero());
            pst.setTimestamp(5, new Timestamp(telefone.getDtCadastro().getTime()));
            pst.setInt(6, telefone.getId());
			
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

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Telefone telefone = (Telefone) entidade;
		List<EntidadeDominio> listaTelefones = new ArrayList<EntidadeDominio>();
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
            pst.setInt(1, telefone.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaTelefones.add(new Telefone(
					rs.getInt("tel_id"),
					rs.getString("tel_tipo"),
					rs.getString("tel_ddi"),
					rs.getString("tel_ddd"),
					rs.getString("tel_numero"),
					new Date(rs.getTimestamp("tel_dt_cadastro").getTime())
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
		return listaTelefones;
	}

}

