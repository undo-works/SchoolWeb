package packages.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packages.bean.Customer;
import packages.dao.CustomerSearchDBAccess;

/**
 * Servlet implementation class DBserv_Two
 */
@WebServlet("/DBserv_Two")
public class DBserv_Two extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBserv_Two() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
			
			ArrayList<Customer> list = dao.searchCustomerByTel("09012345678");
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			
			rd.forward(request, response);
			
		}catch(Exception e) {
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
