package phasebook.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx = null;
		HttpSession session = request.getSession();
		try {
			ctx = new InitialContext();
			PhasebookUserRemote user;
			user = (PhasebookUserRemote) ctx.lookup("PhasebookUserBean/remote");

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");

			String error = formValidation(name, email, password1, password2);
			if (error != null) {
				session.setAttribute("error", error);
				session.setAttribute("name", name);
				session.setAttribute("email", email);
				response.sendRedirect(Utils.url("register"));
			} else {
				password1 = byteArrayToHexString(computeHash(password1 + "salt"
						+ email));

				int id = user.create(name, email, password1);
				session.setAttribute("id", id);
				response.sendRedirect(Utils.url(""));
			}
		} catch (NamingException e) {
			e.printStackTrace();
			session.setAttribute("error", "The submited data is incorrect");
			response.sendRedirect(Utils.url("register"));
		}

	}

	private String formValidation(String name, String email, String password1,
			String password2) {
		// Name can't be blank
		if (name == null || name.length() == 0) {
			return "You must specify your name";
		}

		// Email can't be blank
		if (email == null || email.length() == 0) {
			return "You must specify your email";
		}

		// Email must be valid
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			return "You must specify a valid email";
		}

		// Password can't be blank
		if (password1 == null || password1.length() == 0) {
			return "You must have a password";
		}

		// Passwords must match
		if (password2 == null || password1.compareTo(password2) != 0) {
			return "The passwords do not match";
		}

		return null;
	}

	private String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	private byte[] computeHash(String x) {
		java.security.MessageDigest d = null;
		try {
			d = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			d = null;
		}
		d.reset();
		d.update(x.getBytes());
		return d.digest();
	}

}
