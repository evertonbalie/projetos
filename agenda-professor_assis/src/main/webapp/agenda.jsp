<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
  
    
    <%@page import ="model.JavaBeans" %>
    <%@ page import="java.util.ArrayList" %>
    <%    
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)request.getAttribute("contatos");
        
//     for(int i=0;i<lista.size();i++){
//     	out.println(lista.get(i).getIdcon());
//     	out.println(lista.get(i).getNome());
//     	out.println(lista.get(i).getFone());
//     	out.println(lista.get(i).getEmail());   	
//     }    
    %>
    
    
<!DOCTYPE html>
<html lang=""pt-br>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
<!-- <img alt="" src="imagens/agenda.png"> -->
<h1>Agenda de Contato</h1>

<a href="novo.html" class="Botao1">Novo Contato</a>
<table id="tabela">
<thead>
<tr>
<th>Id</th>
<th>Nome</th>
<th>Fone</th>
<th>E-mail</th>
<th>opções</th>
</tr>
</thead>
<tbody>
<%for (int i=0;i<lista.size();i++){ %>
<tr>
<td><%=lista.get(i).getIdcon() %></td>
<td><%=lista.get(i).getNome() %></td>
<td><%=lista.get(i).getFone() %></td>
<td><%=lista.get(i).getEmail() %></td>
<td><a href="select?idcon=<%=lista.get(i).getIdcon() %>" class="Botao1">Editar</a></td>
<td><a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="Botao2">Excluir</a></td>
<td><a href="report" class="Botao2">Relatório</a></td>
</tr>


</tr>
<%} %>

</tbody>


</table>

<script src="scripts/confirmador.js"></script>

</body>
</html>