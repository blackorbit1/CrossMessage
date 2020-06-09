function connection(formulaire){
	noConnection = false;
	var username = formulaire.username.value;
	var password = formulaire.password.value;
	var ok = verif_formulaire_connection(username, password);
	if(ok){
		connect(username, password);
	}
}

function verif_formulaire_connection(username, password){
	if(username.length==0){
		func_erreur("Username obligatoire"); return false;
	}
	if(password.length==0){
		func_erreur("Password obligatoire"); return false;
	}
	return true;
}

function func_erreur(msg){
	var msg_box = "<div id = 'msg_err_connexion'>"+msg+"</div>";
	var old_msg = $("#msg_err_connexion");

	// alert("old message ="+old_msg+"  len = "+old_msg.length)

	if(old_msg.length==0){

		$("form").prepend(msg_box); //ajouter msg_box en 1er element du formulaire form
	}
	else{
		old_msg.replaceWith(msg_box);
	}
	$("#msg_err_connexion").css({"color":"red", "margin-top":"10px"})
}

function connect(username, password){
	// alert(username +" , "+ password);
	if(noConnection){
		throw "not implemented"
	} else {
		$.ajax({
	      type:"POST",
	      url:"Login",
	      data:"login="+username+"&pwd="+password,
	      datatype:"json",
	      success:function(rep){ connectSuccess(rep);},
	      error:function(jqXHR,textStatus,errorThrown){ func_erreur(textStatus); }
    	})
	}
}

function connectSuccess(rep){
		var tmp = JSON.parse(rep)
		env.key = tmp.key
		env.username = tmp.login
		env.id = tmp.id
	makeMainPanel(env.id, env.username, undefined)
}

function makeConnectionPanel(){
	var s = ""
	s+= "<header><h1> Log In </h1></header>"
	s+=	"<div id='connexion_main'>"
	s+=		"<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:connection(this)' >"
	s+=			"<div class='ids'> <input type='text' name='username' placeholder='Username'>"
	s+=			"</div>"
	s+=			"<div class='ids'> <input type='password' name='password' placeholder='Password'>"
	s+=			"</div>"
	s+=			"<div class='buttons'> <input type='submit' value='Connection'>"
	s+=			"</div>"
	s+=			"<div class='links'>"
	s+=				"<div id='link1' onclick = 'javascript:makeRegisterPanel()'>Create Account</div>"
	s+=			"</div>"
	s+=		"</form>"
	s+=	"</div>"
	$("body").html(s);
	$("head link").replaceWith("<link rel='stylesheet' type='text/css' href='login.css'>")
}

function logout(){
	$.ajax({
	      type:"POST",
	      url:"Logout",
	      data:"key="+env.key,
	      datatype:"json",
	      success:function(rep){ logoutSuccess(rep);},
	      error:function(jqXHR,textStatus,errorThrown){ func_erreur(textStatus); }
  	})
}

function logoutSuccess(){
	makeConnectionPanel()
}
