<%@ page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>
<%@ page import="phasebook.post.*" %>
<%@ page import="phasebook.friendship.*" %>
<%@ page import="phasebook.photo.*" %>
<%@ page import="java.util.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user;
	Object userId;
	Friendship fs;
	PhasebookUser me;
	me=userBean.getUserById(session.getAttribute("id"));
	int relationshipType = -1;
	if(request.getParameter("id") == null){
		userId =  session.getAttribute("id");
		user = userBean.getUserById(userId);
	}
	else{
		userId =  request.getParameter("id");
		try {
			Utils.getUserBean().getUserById(request.getParameter("id")).getName();
			user = userBean.getUserById(userId);
			relationshipType = Utils.getFriendshipBean().friendshipStatus(me,user);
		} catch (Exception e) {
			userId =  session.getAttribute("id");
			user = userBean.getUserById(session.getAttribute("id"));
		}
	}
	
	
%>
<form method="POST" action="CreateFriendshipForm">
	<input type="hidden" name="toUser" value="<%= userId.toString() %>"/>
	<input type="hidden" name="relationship" value="<%= relationshipType %>"/>
<%
	if(request.getParameter("id")!=null && request.getParameter("id") != session.getAttribute("id")){
   		if(relationshipType==0){%>
			<input type="submit" value="Add Friend" name="F0">
<%		}else if(relationshipType==1){%>
			<input type="submit" value="Friendship request sent" name="F1" disabled="disabled">
<%		}else if(relationshipType==2){ %>
			<input type="submit" value="Accept Friendship" name="F2">	
<%		}else if(relationshipType==3){}}%>

</form>

<h1><%= Utils.text(user.getName()) %></h1> 
<p class="tip"><%= Utils.text(user.getEmail()) %></p>

<%
	if (session.getAttribute("error") != null)
	{
%>
		<p style="color:red"><%= session.getAttribute("error") %></p>
<%
		session.removeAttribute("error");
	}
%>

<%
	String post = "";
	String privacy = "0";
	try {
		post = session.getAttribute("post").toString();
		session.removeAttribute("post");
		privacy = session.getAttribute("privacy").toString();
		session.removeAttribute("privacy");
	} catch (Exception e) {}
%>

<form enctype="multipart/form-data" method="POST" action="AddPhotoForm" style="padding: 0 50px 0 50px;">
	<p align="center">
		<textarea id="label" name="label"></textarea>
		<input type="hidden" name="user" value="<%= userId.toString() %>"/>
	</p>
	<p align="right">
		Search image file:
		<input type="file" name="file1">
	</p>
	<p align="right">
		<input type="checkbox" name="profile" value="1" /> Profile Photo
		<select name="privacy">
			<option value="0" <% if (privacy.compareTo("0")==0) { %>selected<% } %>>Public</option>
			<option value="1" <% if (privacy.compareTo("1")==0) { %>selected<% } %>>Private</option>
		</select>
		<input type="submit" value="Post" name="B1">
	</p>
</form>

<%
	List<Photo> photos = null;
	if (Utils.getFriendshipBean().searchFriendship(me, user) != null || me.equals(user) )
		 photos = userBean.getUserPhotos(userId);
	else
		photos = userBean.getUserPublicPhotos(userId);
	for (int i=photos.size()-1; i>=0; i--) {
%>
	<p>
		<% if (photos.get(i).isPrivate_()) { %><i>(private)</i><% } %>
		<% String photoURL = Utils.MAIN_PATH+userId.toString()+"/"+photos.get(i).getName();%>
		<br /> <%= Utils.aAbsolute(photoURL, Utils.img(photoURL)) %>
		<br /><%= Utils.text(photos.get(i).getLabel()) %>
	</p>
<%
	}
%>