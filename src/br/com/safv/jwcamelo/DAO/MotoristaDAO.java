package br.com.safv.jwcamelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.safv.jwcamelo.DTO.MotoristaDTO;

public class MotoristaDAO {
	public Connection con;
	
	public MotoristaDAO(Connection con) {
		this.con = con;
	}

	public void cadastrar(MotoristaDTO motoristaDTO){
		String sql = "INSERT INTO MOTORISTA(NOME, EMAIL) VALUES (?, ?)";
		try(PreparedStatement pstm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, motoristaDTO.getNome());
			pstm.setString(2, motoristaDTO.getEmail());
			
			pstm.execute();
			
			try(ResultSet rst = pstm.getGeneratedKeys()){
				while(rst.next()) {
					motoristaDTO.setId(rst.getInt(1));
				}
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Motorista cadastrado: "+motoristaDTO);
	}
	
	public ArrayList<MotoristaDTO> listar() {
		ArrayList<MotoristaDTO> lista = new ArrayList<>();
		String sql = "SELECT * FROM MOTORISTA";
		
		try(PreparedStatement pstm = con.prepareStatement(sql)){
			pstm.execute();
			try(ResultSet rst = pstm.getResultSet()){
				while(rst.next()) {
					MotoristaDTO motoristaDTO = new MotoristaDTO(
							rst.getInt(1),rst.getString(2),rst.getString(3));
					
					lista.add(motoristaDTO);
				}
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return lista;
	}
	
}
