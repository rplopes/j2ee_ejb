<%@ page import="phasebook.controller.*"%>
<%@ page import="phasebook.user.*" %>
<%@ page import="java.util.List" %>

<%
	String id;
	try {
		id = "" + Utils.getUserBean().getUserById(request.getParameter("id")).getId();
	} catch (Exception e) {
		id = session.getAttribute("id").toString();
	}
	PhasebookUserRemote user = Utils.getUserBean();
	List<PhasebookUser> friends = user.getUserFriendships(id);
	if (friends.size() == 0) {
%>
		<p>This user has no friends.</p>
<%
	}
	else for (int i=0; i<friends.size(); i++) {
		PhasebookUser friend = friends.get(i);
%>
		<p>
			<b class="user"><%= Utils.a("user&id="+friend.getId(), Utils.text(friend.getName())) %></b><br />
			<i><%= Utils.text(friend.getEmail()) %></i>
		</p>
<% } %>