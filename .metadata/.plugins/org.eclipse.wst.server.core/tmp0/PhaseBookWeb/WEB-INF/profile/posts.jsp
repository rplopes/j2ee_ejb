<%@ page import="phasebook.controller.*"%>
<%@ page import="phasebook.post.*" %>
<%@ page import="phasebook.user.*" %>
<%@ page import="java.util.*" %>

<%
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user;
	Object userId;
	PhasebookUser me=userBean.getUserById(session.getAttribute("id"));
	
	if(request.getParameter("id") == null){
		userId =  session.getAttribute("id");
		user = userBean.getUserById(userId);
	}
	else{
		userId =  request.getParameter("id");
		try {
			Utils.getUserBean().getUserById(request.getParameter("id")).getName();
			user = userBean.getUserById(userId);
		} catch (Exception e) {
			userId =  session.getAttribute("id");
			user = userBean.getUserById(session.getAttribute("id"));
		}
	}

	List<Post> posts = null;
	if (Utils.getFriendshipBean().searchFriendship(me, user) != null || me.equals(user) )
		 posts = userBean.getUserReceivedPosts(userId);
	else
		posts = userBean.getUserPublicPosts(userId);
	
	if (posts.size() == 0) {
%>
		<p>This user has no posts.</p>
<%
	}
	for (int i=posts.size()-1; i>=0; i--) {
		if(posts.get(i).getDeletedAt()==null){
%>
	<p>
		<form method="POST" action="RemovePostForm">
		<input type="hidden" name="postId" value="<%= posts.get(i).getId() %>"/>
		<input type="hidden" name="userId" value="<%= userId %>"/>
		<input type="submit" value="x" name="B0">
		</form>
		<b class="user"><%= Utils.a("user&id="+posts.get(i).getFromUser().getId(), Utils.text(posts.get(i).getFromUser().getName())) %></b>
		<% if (posts.get(i).isPrivate_()) { %><i>(private)</i><% } %>
		<% if (posts.get(i).getPhoto()!=null){ 
			String photoURL = Utils.MAIN_PATH+userId.toString()+"/"+posts.get(i).getPhoto().getName();
		%>
			<br /> <%= Utils.aAbsolute(photoURL, Utils.img(photoURL)) %>
		<%} %>
		<br /><%= Utils.text(posts.get(i).getText()) %>
	</p>
<%
	}}
%>