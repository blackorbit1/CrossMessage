function register(formulaire){
	var fname = formulaire.fname.value;
	var name = formulaire.name.value;
	var username = formulaire.username.value;
	var password = formulaire.password.value;
	var password2 = formulaire.confirm.value;
	if(checkparams(fname, name, username, password, password2)){
		//createUser(fname, name, username, mail, password); // for offline purpose only
		$.ajax({
			type:"POST",
			url:"CreateUser",
			data:"name="+name+"&fname="+fname+"&login="+username+"&pwd="+password,
			datatype:"json",
			success:function(rep){ registerResponse(rep); },
			error:function(jqXHR,textStatus,errorThrown){ func_erreur(textStatus); }
		})
	}
}

function registerResponse(rep){
	var tmp = JSON.parse(rep)
	if(tmp.status == "KO"){
		alert("There was a problem in User Creation");

	}
	else {
		makeConnectionPanel()
	}
}

function checkparams(fname, name, username, mail, password, password2){
	console.log("checking parameters");
	if(fname.length==0){
		func_erreur("frist name required"); return false;
	}
	if(name.length==0){
		func_erreur("last name required"); return false;
	}
	if(username.length==0){
		func_erreur("username required"); return false;
	}
	if(mail.length==0){
		func_erreur("mail required"); return false;
	}
	//  else {
	// 	var mailform = new RegExp("*@*\.*");

	// }
	if(password.length==0){
		func_erreur("password required"); return false;
	}
	if(password2.length==0){
		func_erreur("confirm password"); return false;
	}
	if(password != password2){
		func_erreur("password not matching"); return false;
	}
	return true;
}

function func_erreur(msg){
	var msg_box = "<div id = 'msg_err_conexion'>"+msg+"</div>";
	var old_msg = $("#msg_err_connexion");
	if(old_msg.length==0){
		$("form").prepend(msg_box); //ajouter msg_box en 1 element du formulaire form
	}
	else{
		old_msg.replaceWith(msg_box);
	}
	$("#msg_err_connexion").css(["color : red",
									"margin-top : 10px"]);
}


function makeRegisterPanel(){
	var s ="";

	s+=	"<header><h1> Register </h1></header>"
	s+=	"<div id='register_main'>"
	s+=	"<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:register(this)'>"
	s+=		"<div class='ids'> <span>First Name</span> <input type='text' name='fname' placeholder='John'>"
	s+=		"</div>"
	s+=		"<div class='ids'> <span>Last Name</span> <input type='text' name='name' placeholder='Doe'>"
	s+=		"</div>"
	s+=		"<div class='ids'> <span>Username</span> <input type='text' name='username' placeholder='djoe'>"
	s+=		"</div>"
	s+=		"<div class='ids'> <span>Password</span><input type='password' name='password' placeholder='Password'>"
	s+=		"</div>"
	s+=		"<div class='ids'> <span>Confirm Password</span> <input type='password' name='confirm' placeholder='Password'>"
	s+=		"</div>"
	s+=		"<div class='buttons'> <input type='submit' value='Register'>"
	s+=		"</div>"
	s+=		"<div class='links'>"
	s+=			"<div id='link1' onclick='javascript:makeConnectionPanel()'>Log In</div>"
	s+=		"</div>"
	s+=	"</form>"
	s+=	"</div>"

	$("body").html(s);
	$("head link").replaceWith("<link rel='stylesheet' type='text/css' href='register.css'>");

}
