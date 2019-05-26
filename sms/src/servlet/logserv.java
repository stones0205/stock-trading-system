package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.account;
import account.individual_account;

/**
 * Servlet implementation class logserv
 */
@WebServlet("/logserv")
public class logserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        account acc=new individual_account();
        String info=acc.login(request);
        if(info!=null) {
        request.setAttribute("error", info);
        request.setAttribute("userID", request.getParameter("userID"));
        request.getRequestDispatcher("login").forward(request, response);
        }
        else {
        	HttpServletRequest req = (HttpServletRequest)request;
        	HttpSession session = req.getSession();
        	session.setAttribute("ID", acc.getID());
        	session.setAttribute("name", acc.getname());
        	response.sendRedirect("/sms/main");
        }
	}

}
