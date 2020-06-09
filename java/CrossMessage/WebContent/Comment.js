/*CLASS COMMENT */

function Comment(id, author, text, date){
  this.id     = id;
  this.author = author;
  this.text   = text;
  this.date   = date;
}

Comment.prototype.getHTML = function () {
  var s = "";
  s+= "<div id='comment_" + this.id + "' class='comment'>"
  s+="<div class='info' >"
  s+= "<span class='user' onclick='javascript:pageUser(" + this.UserId +",\""+ this.author + "\")' >" + this.author.username
  s+= "</span> <span class='date' >"+ this.date + "</span></div>"
  s+= "<div class='text' >" + this.text + "</div>"
  s+= " </div>"
  return s
};
