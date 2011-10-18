package phasebook.controller;

import java.io.IOException;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;

import phasebook.user.PhasebookUserRemote;

/**
 * Servlet implementation class CreateUserForm
 */
public class CreateUserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateUserForm() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
			PhasebookUserRemote user;
			user = (PhasebookUserRemote) ctx.lookup("PhasebookUserBean/remote");
			user.create(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
			response.sendRedirect("?p=ola");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
