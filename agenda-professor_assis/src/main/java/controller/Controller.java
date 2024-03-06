package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/controller", "/insert", "/main", "/select", "/update", "/delete","/report" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listaContato(request, response);

		} else if (action.equals("/update")) {

			editarContato(request, response);

		} else if (action.equals("/delete")) {

			removerContato(request, response);

		}else if (action.equals("/report")) {

			gerarRelatorio(request, response);

		} else {

			response.sendRedirect("index.html");
		}

	}

	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("agenda.jsp");

		ArrayList<JavaBeans> lista = dao.listaContatos();
		// ** Mostra a iteração da lista **//
//		for (int i = 0; i <lista.size(); i++) {			
//			System.out.println(lista.get(i).getIdcon());
//			System.out.println(lista.get(i).getNome());			
//		}
//		

		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//System.out.println(request.getParameter("nome"));
//System.out.println(request.getParameter("fone"));
//System.out.println(request.getParameter("email"));

		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		dao.inserirContato(contato);

	}

	protected void listaContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recebendo o id do contato enviaado
		String idcon = request.getParameter("idcon");
		// seta a variavel jaavaBeans
		contato.setIdcon(idcon);

		// Executar o metodo selecionarContato
		dao.selecionarContato(contato);
		// teste de recebimento
//	System.out.println(contato.getIdcon());
//	System.out.println(contato.getNome());
//	System.out.println(contato.getFone());
//	System.out.println(contato.getEmail());
		// setar os atributos com o sonteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		RequestDispatcher rs = request.getRequestDispatcher("editar.jsp");
		rs.forward(request, response);

	}

//Meteodo Update
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//	System.out.println(request.getParameter("idcon"));
//	System.out.println(request.getParameter("nome"));
//	System.out.println(request.getParameter("fone"));
//	System.out.println(request.getParameter("email"));
		// Seta variaveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		dao.alterarContato(contato);
		// **redireconar para contatos
		response.sendRedirect("main");
	}

//Meteodo deletar
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//Recebimento do id
		String idcon = (request.getParameter("idcon"));
		//System.out.println(idcon);

//Seta variaveis JavaBeans
contato.setIdcon(idcon);

dao.deletarContato(contato);
//	//**redireconar para contatos
response.sendRedirect("main");
	}
	
	//Meteodo relatório
		protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

Document documento = new Document();

try {
	
	// tipo de conteudo
	response.setContentType("apllication/pdf");
	
	//Nome do Documento
	response.addHeader("content-Disposition", "inline; filename="+"contatos.pdf");
	
	// Criar documento	
	PdfWriter.getInstance(documento, response.getOutputStream());
	
	//Abrir documento - conteúdo	
	documento.open();
	documento.add(new Paragraph ("Lista de contatos:"));
	documento.add(new Paragraph(""));
	
	//Criar tabela
	PdfPTable tabela = new PdfPTable(3);
	PdfPCell col1 = new PdfPCell(new Paragraph("nome"));
	PdfPCell col2 = new PdfPCell(new Paragraph("fone"));
	PdfPCell col3 = new PdfPCell(new Paragraph("email"));
	tabela.addCell(col1);
	tabela.addCell(col2);
	tabela.addCell(col3);
	
	//Populara tabela com os contatos
	ArrayList<JavaBeans> lista = dao.listaContatos();
	
	for(int i =0; i<lista.size();i++) {
		tabela.addCell(lista.get(i).getNome());
		tabela.addCell(lista.get(i).getFone());
		tabela.addCell(lista.get(i).getEmail());
		
	}
	documento.add(tabela);
	documento.close();
	
} catch (Exception e) {
	System.out.println(e);
	documento.close();
}

		}

}
