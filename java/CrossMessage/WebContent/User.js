/* CLASS USER */

function pageUser(id,username){
  makeMainPanel(id, username, undefined)
}


function isFollowed(id1, id2){
  // return whether 2 is followed by 1
  if(noConnection){
    if (follows[id1] == undefined) follows[id1] = new Set()
    return follows[id1].has(id2)
  }
  else {
    throw "function isFollowed() Not implemented yet"
  }
}

function follow(id1, id2){
  //add id2 in the follows set of id1

  if(noConnection){
    follows[id1].add(id2)
    s = "<button onclick='javascript:unfollow("+id1+","+id2 +")'>Unfollow</button>"
    $("#left .follow").html(s)
  }
  else {
    $.ajax({
	      type:"POST",
	      url:"AddFriend",
	      data:"key="+env.key+"friendId="+id2,
	      datatype:"json",
	      success:function(rep){ followSuccess(id1, id2);},
	      error:function(jqXHR,textStatus,errorThrown){ func_erreur(textStatus); }
  	})
  }
}

function followSuccess(id1, id2){
	s = "<button onclick='javascript:unfollow("+id1+","+id2 +")'>Unfollow</button>"
    $("#left .follow").html(s)
}


function unfollow(id1, id2){
  if(noConnection){
    follows[id1].delete(id2)
    s = "<button onclick='javascript:follow("+id1+","+id2 +")'>Follow</button>"
    $("#left .follow").html(s)
  }
  else {
    $.ajax({
	      type:"POST",
	      url:"RemoveFriend",
	      data:"key="+env.key+"friendId="+id2,
	      datatype:"json",
	      success:function(rep){ unfollowSuccess(id1, id2);},
	      error:function(jqXHR,textStatus,errorThrown){ func_erreur(textStatus); }
	})
  }
}

function unfollowSuccess(id1, id2){
	s = "<button onclick='javascript:unfollow("+id1+","+id2 +")'>Unfollow</button>"
    $("#left .follow").html(s)
}
