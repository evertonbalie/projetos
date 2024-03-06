/**
 * 
 */

 function validar(){
	 
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value
	
	if(nome===""){
		alert('Preencha o campo nome')
		frmContato.nome.focuas()
		return false	
		
	}else if(fone===""){
		alert('Preecnha o campo fone')
		frmContato.fone.focus
		return false
	}else{
		
		dosument.forms["frmContato"].submit()
	}
	 
	 
 }