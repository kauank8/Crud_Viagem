package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;

public class MotoristaDao implements ICrud<Motorista> {
	private GenericDao gDao;
	
	public MotoristaDao(GenericDao gDao) {
		super();
		this.gDao = gDao;
	}

	@Override
	public void inserir(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Insert Into Motorista Values(?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getCodigo());
		ps.setString(2, m.getNome());
		ps.setString(3, m.getNaturalidade());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Update Motorista SET nome=?, naturalidade=? Where codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(3, m.getCodigo());
		ps.setString(1, m.getNome());
		ps.setString(2, m.getNaturalidade());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Delete Motorista Where codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Motorista consultar(Motorista m) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Select codigo, nome, naturalidade from Motorista Where codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, m.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			m.setCodigo(rs.getInt("codigo"));
			m.setNome(rs.getString("nome"));
			m.setNaturalidade(rs.getString("naturalidade"));
		}
		rs.close();
		ps.close();
		c.close();
		return m;
	}

	@Override
	public List<Motorista> listar() throws SQLException, ClassNotFoundException {
		List<Motorista> motoristas = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "Select codigo, nome, naturalidade from Motorista ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("codigo"));
			m.setNome(rs.getString("nome"));
			m.setNaturalidade(rs.getString("naturalidade"));
			motoristas.add(m);
		}
		rs.close();
		ps.close();
		c.close();
		return motoristas;
	}


}
