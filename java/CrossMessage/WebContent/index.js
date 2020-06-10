function init(){
	noConnection = new Object();
	noConnection=false;
	env = new Object();
	//env.id = 1
	//env.username = 'joe'
	// env.key = 1
	env.minId = -1
	env.maxId = -1
	//setVirtualDB();
}

function setVirtualDB(){
	/*localdb = []
	follows = []
	users = []
	comments = 4
	var u1 = {"id":1,"username":"joe"}
	var u2 = {"id":2,"username":"fred"}
	var u3 = {"id":3,"username":"paul"}

	users[1] = u1
	users[2] = u2
	users[3] = u3

	follows[1] = new Set()
	follows[1].add(2)
	// follows[1].add(3)
	follows[2] = new Set()
	follows[2].add(1)
	follows[3] = new Set()
	follows[3].add(1)

	var c1 = new Comment(1,u2,"kikou sava ?", new Date(),0)
	var c2 = new Comment(2,u3,"trql emile", new Date(),0)
	var c3 = new Comment(3,u2,"gg wp",new Date(),0)

	localdb[1] = new Message(1,u1,"salut les meks", new Date(), [c1,c2])
	localdb[2] = new Message(2,u2,"quel match!!", new Date() )
	localdb[3] = new Message(3,u3, "enfin les vacances", new Date())
	localdb[4] = new Message(4,u1,"bac mention bien!!", new Date(), [c3])
	localdb[5] = new Message(5,u1,"Demain, dès l'aube, à l'heure où blanchit la campagne, Je partirai. Vois-tu, je sais que tu m'attends. J'irai par la forêt, j'irai par la montagne.Je ne puis demeurer loin de toi plus longtemps. Je marcherai les yeux fixés sur mes pensées, Sans rien voir au dehors, sans entendre aucun bruit, Seul, inconnu, le dos courbé, les mains croisées, Triste, et le jour pour moi sera comme la nuit.", new Date())*/

}

function getFromLocalDB(from, minId, maxId, nbMax){
	var msgs = []
	var nb = 0
	var f = new Set()

	if( from < 0){
		// home page, we need to display messages from users the current user follows
		f = follows[env.id]
		if( f == undefined){
			f = new Set()
		}
	}
	for(var i = localdb.length - 1; i >= 0; i--){
		// we start diplay the most recent messages
		if(nbMax >= 0 && nb >= nbMax){ break;} // max messages
		var m = localdb[i]
		if(m == undefined){ continue;} // message does not exist
		if(maxId < 0 || m.id < maxId && m.id > minId){
			// if(	f == undefined // user does not follow anyone, so it display the most recent messages
			// 		|| m.author.id == from // a certain user's messages
			// 	 	|| f.has(m.author.id)) // messages of people the user follows
			// {
			if(	(from < 0 && ( (f.has(m.author.id) || m.author.id == env.id || f.size == 0)) )||
					//on the news feed display messages fom the current user, the users followed or
					// if the current user does not follow anyone, the most recent messages
					m.author.id == from )
					// or display someone's page (from>=0) so display from's messages
			{
				msgs.push(m) // add the message to the list
				nb++ // increment the counter
			}
		}
	}
	return msgs
}

function revival1(key,value)
{
	if (value.comments!=undefined)
	{
		var c= new Message(value._id, value.userID, value.author, value.text, value.date, value.comments);
		return c;
	}
	else if (value.text!=undefined)
	{
		var c = new Comment(value.id, value.author, value.text, value.date, value.upvote);
		return c;
	}
	else if (key=="date")
	{
		var d=new Date(value);
		return d;
	}
	return value;
}

function getHubs(){

}

function create_hub(formulaire){
	noConnection = false;
	var hubName = formulaire.hubName.value;

		$.ajax({
			  type:"POST",
			  url:"CreateHub",
			  data:"hubName="+hubName,
			  datatype:"json",
			  success:function(rep){ alert("le hub a bien ete cree")},
			  error:function(jqXHR,textStatus,errorThrown){  alert("le hub N'A PAS ete cree") }
		  })

}

function add_room(formulaire){
	noConnection = false;
	var hubName = formulaire.hubName.value;
	var roomName = formulaire.roomName.value;
	var roomId = formulaire.roomId.value;
		$.ajax({
			  type:"POST",
			  url:"CreateRoom",
			  data:"hubName="+hubName+"&roomName="+roomName+"&id_discord_room="+roomId,
			  datatype:"json",
			  success:function(rep){  /*¯\_(ツ)_/¯*/},
			  error:function(jqXHR,textStatus,errorThrown){  /*¯\_(ツ)_/¯*/ }
		  })

}

function remove_room(formulaire){
	noConnection = false;
	var idDiscordRoom = formulaire.idDiscordRoom.value;
		$.ajax({
			  type:"POST",
			  url:"Logout",
			  data:"idDiscordRoom="+roomId,
			  datatype:"json",
			  success:function(rep){  /*¯\_(ツ)_/¯*/;},
			  error:function(jqXHR,textStatus,errorThrown){  /*¯\_(ツ)_/¯*/ }
		  })

}



function makeMainPanel(fromId, fromUsername, query){
	env.msgs = []
	if(fromId == undefined) env.fromId = -1
	else env.fromId = fromId
	env.fromUsername = fromUsername
	env.query = query

	var hubs = getHubs();

	var s = `
	<div class='links'>
		<div id='link1' onclick = 'javascript:logout()'>Log Out</div>
	</div>
	
	<h1 class="mainT">CrossMessage</h1>
	
	<h2>Hub Selection</h2>
	
	<div class=hubname>
		<h1>Hub Name</h1>
		<div id="menu">
			<p>room: général</p>
		</div>
	</div>
	
	
	<div id="boxpp">
		<h1> Chat</h1>
		<div id="menu">
			<p>ID: Foo</p>
		</div>
		 
		<div id="chatbox">
			<!-- <p><i>$Name</i> $Texte</p>-->
			<p><i>Pog: </i>Chomp</p>
			<p><i>Hey: </i>Pelo</p>
			<p><i>Moi: </i>Pk l'html?</p>
			<p><i>Oui bjr?: </i>Les conventions de Genève sont des traités internationaux fondamentaux dans le domaine du droit international humanitaire. 
			Elles dictent les règles de conduite à adopter en période de conflits armés, et notamment la protection des civils, des membres de l'aide humanitaire, 
			des blessés ou encore, des prisonniers de guerre.
			La première convention de Genève date de 1864. Cependant, les textes en vigueur aujourd’hui ont été écrits après la Seconde Guerre mondiale. 
			Sept textes ont cours actuellement : les quatre conventions de Genève du 12 août 1949, les deux protocoles additionnels du 8 juin 1977 et le 
			troisième protocole additionnel de 2005. Les quatre conventions de Genève ont été mondialement ratifiées, 
			ce qui signifie que chacun des États du monde s’engage à les respecter.</p>
			<p><i>Pog: </i>Chomp.</p>
			<p><i>Hey: </i>Pelo?</p>		
			<p><i>Pog: </i>Chomp.</p>
			<p><i>Hey: </i>Pelo?</p>		
			<p><i>Pog: </i>Chomp.</p>
			<p><i>Hey: </i>Pelo?</p>		
		</div>
		 
		<form name="message" action="">
			<input name="usermsg" type="text" id="usermsg"/>
			<input name="submitmsg" type="submit"  id="submitmsg" value="Send" />
		</form>
	</div>
	
	<!-- Div admin -->
	<div class=admin>
		<h1>Admin</h1>
		<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:create_hub(this)' >
					<div class='ids'> <input type='text' name='hubName' placeholder='hubName'>
					</div>
					<div class='buttons'> <input type='submit' value='Créer Hub'>
					</div>
		</form>
			<p></p>
		<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:add_room(this)' >
					<div class='ids'> <input type='text' name='roomName' placeholder='roomName'>
					</div>
					<div class='ids'> <input type='text' name='hubName' placeholder='hubName'>
					</div>
					<div class='ids'> <input type='text' name='roomId' placeholder='DiscordRoomID'>
					</div>
					<div class='buttons'> <input type='submit' value='Ajouter Room'>
					</div>
		</form>
			<p></p>
			
			<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:remove_room(this)' >
					HUB1
					<div class='ids'> 
						<select name="id_room">
							<option value="ID DE LA ROOM">NOM DE LA ROOM</option>
							...
						</select>
					</div>

					

					<div class='buttons'> <input type='submit' value='Supprimer Room'>
					</div>
		</form>
			<p></p>
			
	</div>
	<!-- Div admin -->
	
	
	</body>
	</html>
	
  
	`;

	



	/*
		
	if(env.id == undefined) { // user not connected
		s+="<div class='header_action'><button id='login' onclick='makeConnectionPanel()'>Login</button</div></header>"
			
	} else{ //connected
		s+="<div class='header_action'><button id='profile' onclick='javascript:pageUser(" + env.id +",\""+ env.username + "\")'>Profile</button>"
		s+="<button id='logout' onclick='javascript:logout()'>Logout</button></div></header>"
	}
	
	s+= "<div id='left'>"
		
	if( env.fromId < 0 ) {
		// home page
		s += "<div id='title'>News Feed</div>"
			
	} else {
		// someone's page
		s += "<div id='title'>" + fromUsername + "</div>"
		s += "<div class='follow'>"
			
		if(env.id != fromId){
			
			if (isFollowed(env.id, fromId)) {
				s+= "<button onclick='javascript:unfollow("+env.id+","+fromId +")'>Unfollow</button>"
				
			} else {
				s+= "<button onclick='javascript:follow(" +env.id+","+fromId +")'>Follow</button>"
			}
		}
		s += "</div>"
	}

	s += "</div> <div id='main'>"
	if( fromId < 0 || fromId == env.id){
		s += "<div id='new_message'>"
		s +=	"<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:newMessage(this)'>"
		s += 		"<div class='textarea'>"
		s += 			"<textarea name='text' placeholder='Write what comes to mind...'></textarea>"
		s += 		"</div>"
		s += 		"<div class='buttons'> <input type='submit' value='Post it!'>"
		s += 	"</form> </div></div>"
	}
	
	s += "<div id='messages'></div> </div>"

	*/
	$("body").html(s)
	$("head link").replaceWith("<link rel='stylesheet' type='text/css' href='pageprincipale.css'>")
	
	//attention à la différence entre afficher les messages des amis de fromId  et afficher le profile de fromId, pour les deux fromId==env.id
	//ce que j'ai fait en dessous est peut etre faux
}
