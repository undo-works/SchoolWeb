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
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String flag = request.getParameter("flag");
		//request.setAttribute("id", title);
		
		//flagの内容によって異なる処理
		RequestDispatcher rd = null;
		if(flag != null && flag.equals("01 注文管理")) {
			//注文管理画面へフォワード
			rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			
		}else if(flag != null && flag.equals("06")) {
			//注文/配達確認/顧客情報変更画面へフォワード
			//顧客番号の取り出し
			String custId = request.getParameter("custId");
			
			//顧客検索を行い、beanに格納
			CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
			Customer customer = new Customer();
			try {
				customer = dao.searchCustomerByCustId(Integer.parseInt(custId));
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("customer", customer);
			rd = request.getRequestDispatcher("/OrderInput.jsp");
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/TestFile.jsp");
		
		//以下、顧客情報検索処理
		String searchTel = request.getParameter("tel");
		String searchName = request.getParameter("name");
		CustomerSearchDBAccess dao = new CustomerSearchDBAccess();
		
		if(!"".equals(searchTel) && !"".equals(searchName) && searchTel != null && searchName != null) {
			try {
				ArrayList<Customer> list = dao.searchCustomer(searchTel,searchName);
				request.setAttribute("list", list);
				rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(!"".equals(searchTel) && searchTel != null) {
			try {
				ArrayList<Customer> telList = dao.searchCustomerByTel(searchTel);
				request.setAttribute("list", telList);
				rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(!"".equals(searchName) && searchName != null) {
			try {
				ArrayList<Customer> nameList = dao.searchCustomerByKana(searchName);
				request.setAttribute("list", nameList);
				rd = request.getRequestDispatcher("/CustomerSearch.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		
		
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
