<%@ page import="phasebook.controller.*"%>
<%@ page import="javax.naming.*" %>
<%@ page import="phasebook.user.*" %>
<%@ page import="phasebook.post.*" %>
<%@ page import="phasebook.friendship.*" %>
<%@ page import="java.util.*" %>

<% 
	PhasebookUserRemote userBean = Utils.getUserBean();
	PhasebookUser user;
	Object userId;
	Friendship fs;
	PhasebookUser me = userBean.getUserById(session.getAttribute("id"));
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
	
	String post = "";
	String privacy = "0";
	try {
		post = session.getAttribute("post").toString();
		session.removeAttribute("post");
		privacy = session.getAttribute("privacy").toString();
		session.removeAttribute("privacy");
	} catch (Exception e) {}
	
	List<Post> posts = null;
	if (Utils.getFriendshipBean().searchFriendship(me, user) != null || me.equals(user) )
		 posts = userBean.getUserReceivedPosts(userId);
	else
		posts = userBean.getUserPublicPosts(userId);
%>
<p>
<%
	if (posts.size() == 0) {
%>
		This user has no photos.
<%
	}
%>
</p>
<div id="images">
<%
	for (int i=posts.size()-1; i>=0; i--) {
%>
	<% if (posts.get(i).getPhoto()!=null){ 
		String photoURL = Utils.MAIN_PATH+userId.toString()+"/"+posts.get(i).getPhoto().getName();
	%>
		<span style="margin: 15px"><%= Utils.aAbsolute(photoURL, Utils.img(photoURL)) %></span>
	<% } %>
<%
	}
%>
</div>