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
import dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {
	

	public ProdutoDAO(Connection cx){
		super(cx, "tb_produtos", "pdt_id");
	}
	
	public ProdutoDAO(){
		super("tb_produtos", "pdt_id");			
	}
	
	public void salvar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_produtos(pdt_codigo, pdt_descricao, pdt_dt_cadastro) ");
		sql.append(" VALUES (?, ?, ?)");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, produto.getCodigo());
            pst.setString(2, produto.getDescricao());
            pst.setTimestamp(3, new Timestamp(produto.getDtCadastro().getTime()));
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()){
				produto.setId(rs.getInt(1));
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
		Produto produto = (Produto) entidade;
		openConnection();
		PreparedStatement pst=null;
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tb_produtos SET pdt_codigo=?, pdt_descricao=?, pdt_dt_cadastro=? ");
		sql.append("WHERE pdt_id=?");	
		try {
			connection.setAutoCommit(false);
								
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
            pst.setString(1, produto.getCodigo());
            pst.setString(2, produto.getDescricao());
            pst.setTimestamp(3, new Timestamp(produto.getDtCadastro().getTime()));
			pst.setInt(4, produto.getId());
			
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
		Produto produto = (Produto) entidade;
		List<EntidadeDominio> listaProdutos = new ArrayList<EntidadeDominio>();
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
            pst.setInt(1, produto.getId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				listaProdutos.add(new Produto(
					rs.getInt("pdt_id"),
					rs.getString("pdt_codigo"),
					rs.getString("pdt_descricao"),
					new Date(rs.getTimestamp("pdt_dt_cadastro").getTime())
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
		return listaProdutos;
	}

}