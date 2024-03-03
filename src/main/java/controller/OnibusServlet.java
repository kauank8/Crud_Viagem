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
import model.Onibus;
import persistence.GenericDao;
import persistence.OnibusDao;


@WebServlet("/onibus")
public class OnibusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OnibusServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Entrada
		String cmd = request.getParameter("botao");
		String placa = request.getParameter("placa");
		String marca = request.getParameter("marca");
		String ano = request.getParameter("ano");
		String descricao = request.getParameter("descricao");

		// Retorno
		String saida = "";
		String erro = "";
		Onibus o = new Onibus();
		List<Onibus> onibus = new ArrayList<>();
		
		if (!cmd.contains("Listar")) {
			o.setPlaca(placa);
		}
		if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			o.setAno(Integer.parseInt(ano));
			o.setDescriscao(descricao);
			o.setMarca(marca);
		}

		try {
			if (cmd.contains("Cadastrar")) {
				cadastrarOnibus(o);
				saida="Onibus Cadastrado com sucesso";
				o = null;
			}
			if (cmd.contains("Alterar")) {
				alterarOnibus(o);
				saida="Onibus Atualizado com sucesso";
				o = null;
			}
			if (cmd.contains("Excluir")) {
				excluirOnibus(o);
				saida="Onibus Excluido com sucesso";
				o = null;
			}
			if (cmd.contains("Buscar")) {
				o = buscarOnibus(o);
			}
			if (cmd.contains("Listar")) {
				onibus = listarOnibus();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("onibus", o);
			request.setAttribute("list_onibus", onibus);

			RequestDispatcher rd = request.getRequestDispatcher("onibus.jsp");
			rd.forward(request, response);
		}

	}
	
	private void cadastrarOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		oDao.inserir(o);
	}

	private void alterarOnibus(Onibus o) throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		oDao.atualizar(o);
	}
	
	private void excluirOnibus(Onibus o) throws SQLException, ClassNotFoundException  {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		oDao.excluir(o);
	}

	private Onibus buscarOnibus(Onibus o)throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		o = oDao.consultar(o);
		return o;
		
	}

	private List<Onibus> listarOnibus() throws SQLException, ClassNotFoundException {
		GenericDao gDao = new GenericDao();
		OnibusDao oDao = new OnibusDao(gDao);
		List<Onibus> onibus = oDao.listar();
		return onibus;
	}

}
