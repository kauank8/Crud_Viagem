package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Motorista;
import model.Onibus;
import model.Viagem;
import persistence.GenericDao;
import persistence.ViagemDao;

@WebServlet("/viagem")
public class ViagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViagemServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Entrada
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String onibus = request.getParameter("onibus");
		String motorista = request.getParameter("motorista");
		String hora_saida = request.getParameter("hSaida");
		String hora_chegada = request.getParameter("hChegada");
		String partida = request.getParameter("partida");
		String destino = request.getParameter("destino");
		
		// Retorno
		String saida = "";
		String erro = "";
		Viagem v = new Viagem();
		List<Viagem> viagens = new ArrayList<>();
		String vt[] = new String[10];
		Viagem v_desc_onibus = new Viagem();
		
		if (!cmd.contains("Listar")) {
			if(codigo != "") {
			v.setCodigo(Integer.parseInt(codigo));
			}
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			Onibus o = new Onibus();
			Motorista m = new Motorista();
			o.setPlaca(onibus);
			v.setOnibus(o);
			m.setCodigo(Integer.parseInt(motorista));
			v.setMotorista(m);
			v.setHora_chegada(Integer.parseInt(hora_chegada));
			v.setHora_saida(Integer.parseInt(hora_saida));
			v.setPartida(partida);
			v.setDestino(destino);
		}
		
		try {
			if (cmd.contains("Cadastrar")) {
				cadastrarViagem(v);
				saida="Viagem Cadastrado com sucesso";
				v = null;
				vt = null;
				v_desc_onibus = null;
			}
			if (cmd.contains("Alterar")) {
				alterarViagem(v);
				saida="Viagem Atualizado com sucesso";
				v = null;
				vt = null;
				v_desc_onibus = null;
			}
			if (cmd.contains("Excluir")) {
				excluirViagem(v);
				saida="Viagem Excluido com sucesso";
				v = null;
				vt = null;
				v_desc_onibus = null;
			}
			if (cmd.contains("Buscar")) {
				v = buscarViagem(v);
				vt = null;
				v_desc_onibus = null;
				if(codigo=="") {
				v=null;
				}
			}
			if (cmd.contains("Listar")) {
				viagens = listarViagem();
				vt = null;
				v = null;
				v_desc_onibus = null;
			}
			if (cmd.equals("Descricao Onibus")) {
				v_desc_onibus = descricao_onibus(v);
				if(v_desc_onibus.getOnibus() == null) {
					v_desc_onibus = null;
				}
				vt = null;
				v = null;
			}
			if (cmd.equals("Descricao Viagem")) {
				vt = descricao_viagem(v);
				if(codigo=="") {
					vt=null;
				}
				v = null;
				v_desc_onibus = null;
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("viagem", v);
			request.setAttribute("viagens", viagens);
			request.setAttribute("vt", vt);
			request.setAttribute("desc_oni", v_desc_onibus);

			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
		}

	}

	private void cadastrarViagem (Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.inserir(v);
	}

	private void alterarViagem (Viagem v) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.atualizar(v);
	}
	
	private void excluirViagem (Viagem v) throws SQLException, ClassNotFoundException  {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		vDao.excluir(v);
	}

	private Viagem buscarViagem (Viagem v)throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		v = vDao.consultar(v);
		return v;
		
	}

	private List<Viagem> listarViagem () throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		List<Viagem> viagens = vDao.listar();
		return viagens;
	}
	
	private Viagem descricao_onibus(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		v = vDao.descriscao_onibus(v);
		return v;	
	}
	
	private String[] descricao_viagem(Viagem v) throws ClassNotFoundException, SQLException {
		String text="";
		GenericDao gDao = new GenericDao();
		ViagemDao vDao = new ViagemDao(gDao);
		text = vDao.descriscao_Viagem(v);
		String vt[] = text.split("//");
		return vt;
	}
}
