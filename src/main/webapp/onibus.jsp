<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style1.css">
<title>Onibus</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	<div align="center" class="container">
		<form action="onibus" method="post">
			<p class="title">
				<b>Ônibus</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="id_input_data" type="text" id="placa" name="placa" placeholder="Placa"
						value='<c:out value="${onibus.placa }"></c:out>'>
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text"  id="marca" name="marca" placeholder="Marca"
						value='<c:out value="${onibus.marca }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="number" min="0"   id="ano" name="ano" placeholder="Ano"
						value='<c:out value="${onibus.ano }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text"  id="descricao" name="descricao" placeholder="Descrição"
						value='<c:out value="${onibus.descriscao }"></c:out>'>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<input type="submit" id="botao" name="botao" value="Cadastrar">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Alterar">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Excluir">
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Listar">
					</td>	
				</tr>
			</table>
		</form>
	</div>
	
	<div align="center">
		<c:if test="${not empty erro }">
			<h2><b> <c:out value="${erro }" /> </b></h2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty saida }">
			<h3><b> <c:out value="${saida }" /> </b></h3>	
		</c:if>
	</div>
	<br />
	<br />
	<br />
	<div align="center" >
		<c:if test="${not empty list_onibus }">
			<table class="table_round">
				<thead>
					<tr>
						<th class="lista">Placa</th>
						<th class="lista">Marca</th>
						<th class="lista">Ano</th>
						<th class="lista_ultimoelemento">Descrição</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${list_onibus }">
						<tr>
							<td class="lista"><c:out value="${o.placa } " /></td>
							<td class="lista"><c:out value="${o.marca } " /></td>
							<td class="lista"><c:out value="${o.ano } " /></td>
							<td class="lista_ultimoelemento"><c:out value="${o.descriscao } " /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>	
	
</body>
</html>