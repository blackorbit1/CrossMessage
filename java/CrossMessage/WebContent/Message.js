/* CLASS MESSAGE */

function Message(id, userId, author, text, date, comments){
  this.id       = id;       // message id
  this.userId = userId;     // author's id
  this.author   = author;   // author's username
  this.text     = text;     // actual content of the message
  this.date     = date;     // publication date
  if(comments == undefined)
    this.comments = []
  else
    this.comments = comments; // list of comments
}

Message.prototype.getHTML = function () {
  // function that returns the HTML code (a String) associated to the message
  var s = "";
  s+= "<div class='message' id='" + this.id + "' > ";
  s+= "<div class='info' >"
  s+= "<span class='user' onclick='javascript:pageUser(" + this.UserId +",\""+ this.author + "\")' >"
  s+= this.author.username + "</span>"
  s+= "<span class='date'>" + this.date + "</span>";
  s+= "</div>";
  s+= "<div class='text' >" + this.text + "</div>";
  s+= "<div class='action'> <span class='show_comments'"
  s+= "onclick='javascript:showComments("+this.id+")' >Show comments</span>"
  s+= "<span class='separator'> • </span>"
  s+= "<span class='reply' onclick='javascript:replyBox(" + this.id + ")'>"
  s+= "Reply</span>"
  s+= "</div> </div>"
  return s;

  /* a ajouter les commentaire */

};

function completeMessage(userId, friendsOfUserId){
  //
  if(!noConnection){
	  if(friendsOfUserId == undefined && userId == undefined){
		    $.ajax({
		        type:"GET",
		        url:"ListMessage",
		        datatype:"json",
		        success:function(rep){ completeMessageSuccess(rep);},
		        error:function(jqXHR,textStatus,errorThrown){ alert(textStatus); }
		      })
		  }
	  
	  else if(friendsOfUserId == undefined){
	    $.ajax({
	        type:"GET",
	        url:"ListMessage",
	        data:"userId="+userId,
	        datatype:"json",
	        success:function(rep){ completeMessageSuccess(rep);},
	        error:function(jqXHR,textStatus,errorThrown){ alert(textStatus); }
	      })
	  }
	  else if (userId == undefined){
		  $.ajax({
		        type:"GET",
		        url:"ListMessage",
		        data:"friendsOfUserId="+friendsOfUserId,
		        datatype:"json",
		        success:function(rep){ completeMessageSuccess(rep);},
		        error:function(jqXHR,textStatus,errorThrown){ alert(textStatus); }
		   })
	  }
  }
  else {
    var tab = getFromLocalDB(env.fromId, env.maxId, env.minId, 10)
    completeMessageSuccess(JSON.stringify(tab))
  }
}

function completeMessageSuccess(rep){
  var tab = JSON.parse(rep, revival1)
  var s = ""
  for( var i=0; i< tab.length; i++){
    var m = tab[i]
    s += m.getHTML()
    env.msgs[m.id] = m
    if( m.id > env.maxID){
      env.maxId = m.id
    }
    if (env.minId < 0 || m.id<env.minId){
      env.minId = m.id
    }
  }
  $("#messages").append(s)
}

function showComments(msgId){
  //display comments
  s = "<div class='comments'>"
  for(var i=0; i < env.msgs[msgId].comments.length; i++){
    s += env.msgs[msgId].comments[i].getHTML()
  }
  s+= "</div>"
  $("#" + msgId).append(s)

  //change action link
  m = "<span class='hide_comments' onclick='javascript:hideComments(" + msgId + ")'>Hide comments</span>"
  $("#" + msgId + " .action .show_comments").replaceWith(m)

}

function hideComments(msgId){
  //delete comments
  $("#" + msgId + " .comments").remove()
  // change action link
  m = "<span class='show_comments'onclick='javascript:showComments(" + msgId + ")' >Show comments</span>"
  $("#" + msgId + " .action .hide_comments").replaceWith(m)

}

function newMessage(text){
  if(noConnection){
    // console.log(text);
    // console.log(text.text);
    msg = new Message(localdb.length, getUserFromId(env.id), text.text.value, new Date() )
    // not sure what's best between those two solutions
    // msg = new Message(localdb.length, new User(env.id, env.username), text.text.value, new Date() )
    localdb[localdb.length] = msg
  }
  else{
    $.ajax({
      type:"POST",
      url:"AddMessage",
      data:"key="+env.key+"&content="+text.text.value,
      datatype:"json",
      success:function(rep){ newMessageSuccess(rep); },
      error:function(jqXHR,textStatus,errorThrown){ func_error_msg(textStatus); }
    })

  }
}

function newMessageSuccess(rep){
  makeMainPanel()
}


function replyBox(id){
  // develop the comment writing box
    if($(".message #"+id+" .new_comment").length >= 1 ) return //the box already exist, so do nothing
    s = ""
    s += "<div class='new_comment'>"
    s +="<form action='javascript:(function(){return})()' method='get' onsubmit='javascript:addComment("+id+",this)'>"
    s += "<textarea name='text' placeholder='Comment what comes to mind...'></textarea>"
    s += "<div class='buttons'><input type='submit' value='Comment!'>"
    s += "</div></form></div>"
  $("#"+id+" .action").after(s)
}

function addComment(id, text){
  //id = msg id
  c = new Comment(comments++, new User(env.id, env.username), text.text.value, new Date())
  // is it a good method ??
  env.msgs[id].comments.push(c) // we need to update messages we have on the client

  if(noConnection){
    localdb[id].comments.push(c)
    $("#"+id).replaceWith(localdb[id].getHTML())
    showComments(id)


  }
  else throw "not implemented yet"
}

function func_error_msg(text){
  var msg_box = "<div id = 'msg_err'>"+text+"</div>";
  var old_msg = $("#msg_err");

  if(old_msg.length==0){

    $("#main").prepend(msg_box); //ajouter msg_box en 1er element du formulaire form
  }
  else{
    old_msg.replaceWith(msg_box);
  }
  $("#msg_err").css({"color":"red", "margin-top":"10px"})
}
