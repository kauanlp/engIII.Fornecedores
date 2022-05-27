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

import dominio.Contato;
import dominio.EntidadeDominio;
import dominio.Telefone;

public class ContatoDAO extends AbstractJdbcDAO {
	

	public ContatoDAO(Connection cx) {
		super(cx, "tb_contatos", "ctt_id");
	}
	
	public ContatoDAO() {
		super("tb_contatos", "ctt_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Contato contato = (Contato) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_contatos(ctt_nome, ctt_dpto, ctt_email, ctt_telefone_id, ctt_dt_cadastro) ");
		sql.append(" VALUES (?, ?, ?, ?, ?)");
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getDepartamento());
            pst.setString(3, contato.getEmail());
            pst.setInt(4, contato.getTelefone().getId());
            pst.setTimestamp(5, new Timestamp(contato.getDtCadastro().getTime()));
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				contato.setId(rs.getInt(1));
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
		Contato contato = (Contato) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_contatos SET ctt_nome=?, ctt_dpto=?, ctt_email=?, ctt_telefone_id=?, ctt_dt_cadastro=? ");
		sql.append("WHERE ctt_id=?");

		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getDepartamento());
            pst.setString(3, contato.getEmail());
            pst.setInt(4, contato.getTelefone().getId());
            pst.setTimestamp(5, new Timestamp(contato.getDtCadastro().getTime()));
			pst.setInt(6, contato.getId());
			
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
		IDAO telDAO = new TelefoneDAO();
		
		Contato contato = (Contato) entidade;
		List<EntidadeDominio> listaContatos = new ArrayList<EntidadeDominio>();
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
            pst.setInt(1, contato.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaContatos.add(new Contato(
					rs.getInt("ctt_id"),
					rs.getString("ctt_nome"),
					rs.getString("ctt_dpto"),
					rs.getString("ctt_email"),
					(Telefone) telDAO.consultar(new Telefone(rs.getInt("ctt_telefone_id"))).get(0),
					new Date(rs.getTimestamp("ctt_dt_cadastro").getTime())
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
		return listaContatos;
	}

}

