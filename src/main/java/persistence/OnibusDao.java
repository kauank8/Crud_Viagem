package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Onibus;

public class OnibusDao implements ICrud<Onibus> {
	private GenericDao gDao;
	
	public OnibusDao(GenericDao gDao) {
		super();
		this.gDao = gDao;
	}

	@Override
	public void inserir(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Insert Into Onibus Values(?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, o.getPlaca());
		ps.setString(2, o.getMarca());
		ps.setInt(3, o.getAno());
		ps.setString(4, o.getDescriscao());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Update Onibus SET marca=?, ano=?, descriscao=? Where placa=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, o.getMarca());
		ps.setInt(2, o.getAno());
		ps.setString(3, o.getDescriscao());
		ps.setString(4, o.getPlaca());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Delete Onibus Where placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, o.getPlaca());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Onibus consultar(Onibus o) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Select placa, marca, ano, descricao from Onibus Where placa = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, o.getPlaca());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			o.setPlaca(rs.getString("placa"));
			o.setMarca(rs.getString("marca"));
			o.setAno(rs.getInt("ano"));
			o.setDescriscao(rs.getString("descricao"));
		}
		rs.close();
		ps.close();
		c.close();
		return o;
	}

	@Override
	public List<Onibus> listar() throws SQLException, ClassNotFoundException {
		List<Onibus> onibus = new ArrayList<>(); 
		Connection c = gDao.getConnection();
		String sql = "Select placa, marca, ano, descricao from Onibus ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("placa"));
			o.setMarca(rs.getString("marca"));
			o.setAno(rs.getInt("ano"));
			o.setDescriscao(rs.getString("descricao"));
			onibus.add(o);
		}
		rs.close();
		ps.close();
		c.close();
		return onibus;
	}
}

