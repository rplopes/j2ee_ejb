package phasebook.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import phasebook.user.PhasebookUser;
import phasebook.user.PhasebookUserRemote;

/**
 * Servlet implementation class CreatePostForm
 */
public class CreatePostForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePostForm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = null;
		HttpSession session = request.getSession();
		try {
			ctx = new InitialContext();
			PhasebookUserRemote userBean;
			userBean = (PhasebookUserRemote) ctx.lookup("PhasebookUserBean/remote");
			
			PhasebookUser fromUser = userBean.getUserById(session.getAttribute("id"));
			PhasebookUser toUser = userBean.getUserById(request.getParameter("toUser"));

			String text = request.getParameter("post");
			String privacy = request.getParameter("privacy");

			String error = formValidation(text);
			if (error != null) {
				session.setAttribute("error", error);
				session.setAttribute("post", text);
				session.setAttribute("privacy", privacy);
				response.sendRedirect(Utils.url("user&id="+request.getParameter("toUser").toString()));
			} else {
				userBean.addPost(fromUser, toUser, text, privacy);
				response.sendRedirect(Utils.url("user&id="+request.getParameter("toUser").toString()));
			}
		} catch (NamingException e) {
			e.printStackTrace();
			session.setAttribute("error", "The submited data is incorrect");
			response.sendRedirect(Utils.url("user&id="+request.getParameter("toUser").toString()));
		}
	}
	
	private String formValidation(String text) {
		// Text can't be blank
		if (text == null || text.length() == 0) {
			return "Please write something to post";
		}

		return null;
	}

}
