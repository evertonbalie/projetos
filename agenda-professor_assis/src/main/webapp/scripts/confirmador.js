/**
 * Confirmação de exclusão de um contato
 * @ =everton Rodrgues
 */

 
 
 function confirmar(idcon){
	 
	 let resposta = confirm("confirma a exclusão deste contato ?")
	 
 if (resposta===true){
	 alert(idcon)
	 window.location.href="delete?idcon=" +idcon
 }
 
 }