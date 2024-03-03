package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Motorista;
import model.Onibus;
import model.Viagem;

public class ViagemDao implements ICrud<Viagem> {
private GenericDao gDao;
	
	public ViagemDao(GenericDao gDao) {
		super();
		this.gDao = gDao;
	}
	@Override
	public void inserir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Insert Into Viagem Values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.setString(2, v.getOnibus().getPlaca());
		ps.setInt(3, v.getMotorista().getCodigo());
		ps.setInt(4, v.getHora_saida());
		ps.setInt(5, v.getHora_chegada());
		ps.setString(6, v.getPartida());
		ps.setString(7, v.getDestino());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Update Viagem SET onibus = ?, motorista = ?, hora_saida = ? , hora_chegada = ?, partida = ?, destino = ? Where codigo = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, v.getOnibus().getPlaca());
		ps.setInt(2, v.getMotorista().getCodigo());
		ps.setInt(3, v.getHora_saida());
		ps.setInt(4, v.getHora_chegada());
		ps.setString(5, v.getPartida());
		ps.setString(6, v.getDestino());
		ps.setInt(7, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Delete Viagem Where codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Viagem consultar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "Select codigo, onibus, motorista, hora_chegada, hora_saida, partida, destino From Viagem Where codigo = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			v.setCodigo(rs.getInt("codigo"));
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("onibus"));
			v.setOnibus(o);
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("motorista"));
			v.setMotorista(m);
			v.setHora_chegada(rs.getInt("hora_chegada"));
			v.setHora_saida(rs.getInt("hora_saida"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
		}
		rs.close();
		ps.close();
		c.close();
		return v;
	}

	@Override
	public List<Viagem> listar() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "Select codigo, onibus, motorista, hora_chegada, hora_saida, partida, destino From Viagem ";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Viagem v = new Viagem();
			v.setCodigo(rs.getInt("codigo"));
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("onibus"));
			v.setOnibus(o);
			Motorista m = new Motorista();
			m.setCodigo(rs.getInt("motorista"));
			v.setMotorista(m);
			v.setHora_chegada(rs.getInt("hora_chegada"));
			v.setHora_saida(rs.getInt("hora_saida"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
			viagens.add(v);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
	
	public Viagem descriscao_onibus(Viagem v) throws ClassNotFoundException, SQLException {
		Connection c = gDao.getConnection();
		String sql = "Select * from v_descriscao_onibus Where codigo = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			v.setCodigo(rs.getInt("codigo"));
			Onibus o = new Onibus();
			o.setPlaca(rs.getString("placa"));
			o.setMarca(rs.getString("marca"));
			o.setPlaca(rs.getString("placa"));
			o.setDescriscao(rs.getString("descricao"));
			o.setAno(rs.getInt("ano"));
			v.setOnibus(o);
			Motorista m = new Motorista();
			m.setNome(rs.getString("nome"));
			v.setMotorista(m);
		}
		rs.close();
		ps.close();
		c.close();
		return v;
	}
	
	public String descriscao_Viagem(Viagem v) throws ClassNotFoundException, SQLException {
		StringBuffer buffer = new StringBuffer();
		Connection c = gDao.getConnection();
		String sql = "Select * from v_descriscao_viagem Where codigo = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			buffer.append(rs.getInt("codigo") + "//");
			buffer.append(rs.getString("placa")+ "//");
			buffer.append(rs.getString("hora_saida")+ "//");
			buffer.append(rs.getString("hora_chegada")+ "//");
			buffer.append(rs.getString("partida")+ "//");
			buffer.append(rs.getString("destino")+ "//");
		}
		rs.close();
		ps.close();
		c.close();
		return buffer.toString();
	}
}
