<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style1.css">
<title>Viagem</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>
	<br />
	
	<div align="center" class="container">
		<form action="viagem" method="post">
			<p class="title">
				<b>Viagens</b>
			</p>
			<table>
				<tr>
					<td colspan="3">
						<input class="id_input_data" type="number" min="0" step="1" id="codigo" name="codigo" placeholder="Codigo"
						value='<c:out value="${viagem.codigo }"></c:out>'>
					</td>
					<td>
						<input type="submit" id="botao" name="botao" value="Buscar">
					</td>
				<tr>
					<td colspan="2">
						<input type="submit" id="botao" name="botao" value="Descricao Onibus">
					</td>
					<td colspan="2">
						<input type="submit" id="botao" name="botao" value="Descricao Viagem">
					</td>
				</tr>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text"  id="onibus" name="onibus" placeholder="Placa-Ônibus"
						value='<c:out value="${viagem.onibus.placa }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="number" min="0"  id="motorista" name="motorista" placeholder="Codigo-Motorista"
						value='<c:out value="${viagem.motorista.codigo }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="int" min="0" step="1"  id="hSaida" name="hSaida" placeholder="Hora Saida"
						value='<c:out value="${viagem.hora_saida }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="int" min="0" step="1"  id="hChegada" name="hChegada" placeholder="Hora Chegada"
						value='<c:out value="${viagem.hora_chegada }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text"   id="partida" name="partida" placeholder="Partida"
						value='<c:out value="${viagem.partida }"></c:out>'>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input class="input_data" type="text"   id="destino" name="destino" placeholder="Destino"
						value='<c:out value="${viagem.destino }"></c:out>'>
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
		<c:if test="${not empty viagens }">
			<table class="table_round">
				<thead>
					<tr>
						<th class="lista">Codigo</th>
						<th class="lista">Onibus</th>
						<th class="lista">Motorista</th>
						<th class="lista">Hora Saida</th>
						<th class="lista">Hora Chegada</th>
						<th class="lista">Partida</th>
						<th class="lista_ultimoelemento">Destino</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="v" items="${viagens }">
						<tr>
							<td class="lista"><c:out value="${v.codigo } " /></td>
							<td class="lista"><c:out value="${v.onibus.placa } " /></td>
							<td class="lista"><c:out value="${v.motorista.codigo } " /></td>
							<td class="lista"><c:out value="${v.hora_saida } " /></td>
							<td class="lista"><c:out value="${v.hora_chegada } " /></td>
							<td class="lista"><c:out value="${v.partida } " /></td>
							<td class="lista_ultimoelemento"><c:out value="${v.destino } " /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<div align="center" >
		<c:if test="${not empty desc_oni }">
			<table class="table_round">
				<thead>
					<tr>
						<th class="lista">Codigo</th>
						<th class="lista">Motorista</th>
						<th class="lista">Placa</th>
						<th class="lista">Marca</th>
						<th class="lista">Ano</th>
						<th class="lista_ultimoelemento">Descricao</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td class="lista"><c:out value="${desc_oni.codigo } " /></td>
							<td class="lista"><c:out value="${desc_oni.motorista.nome } " /></td>
							<td class="lista"><c:out value="${desc_oni.onibus.placa } " /></td>
							<td class="lista"><c:out value="${desc_oni.onibus.marca} " /></td>
							<td class="lista"><c:out value="${desc_oni.onibus.ano } " /></td>
							<td class="lista_ultimoelemento"><c:out value="${desc_oni.onibus.descriscao } " /></td>
						</tr>
				</tbody>
			</table>
		</c:if>
	</div>	
	
	<div align="center" >
		<c:if test="${not empty vt }">
			<table class="table_round">
				<thead>
					<tr>
						<th class="lista">Codigo</th>
						<th class="lista">Placa</th>
						<th class="lista">Hora Saida</th>
						<th class="lista">Hora Chegada</th>
						<th class="lista">Partida</th>
						<th class="lista_ultimoelemento">Destino</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<td class="lista"><c:out value="${vt[0] } " /></td>
							<td class="lista"><c:out value="${vt[1] } " /></td>
							<td class="lista"><c:out value="${vt[2] } " /></td>
							<td class="lista"><c:out value="${vt[3]} " /></td>
							<td class="lista"><c:out value="${vt[4] } " /></td>
							<td class="lista_ultimoelemento"><c:out value="${vt[5]} " /></td>
						</tr>
				</tbody>
			</table>
		</c:if>
	</div>			
</body>
</html>