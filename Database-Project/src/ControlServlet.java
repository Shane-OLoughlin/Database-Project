import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;




public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private String currentUser;
	    private HttpSession session=null;
	    
	    int userCounter = 12;
	    int quoteRequestCounter = 20;
	    int treeCounter = 20;
	    int quoteResponseCounter = 10;
	    int quoteRejectCounter = 10;
	    int orderOfWorkCounter = 10;
	    int billRequestCounter = 20;
	    int billRejectCounter = 10;
	    int paymentCounter = 10;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/davidsmith":
        		davidSmithPage(request,response, "");
        		break;
        	case "/client":
        		clientPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
         	case "/submitquoterequest":
        		submitquoterequest(request,response);
        		break;
         	case "/addtree":
        		treeSubmissionPage(request,response);
        		break;
         	case "/submittree":
        		submittree(request,response);
        		break;
         	case "/respond":
         		quoteResponseSubmissionPage(request, response);
         		break;
         	case "/submitquoteresponse":
         		submitquoteresponse(request, response);
        		break;
         	case "/reject":
         		quoteRejectSubmissionPage(request,response);
        		break;
            case "/submitquotereject":
            	submitquotereject(request, response);
            	break;
            case "/accept":
            	orderOfWorkSubmissionPage(request, response);
            	break;
            case "/submitorderofwork":
            	submitorderofwork(request, response);
            	break;
         	case "/sendbill":
         		billRequestSubmissionPage(request,response);
        		break;
            case "/submitbillrequest":
            	submitbillrequest(request, response);
            	break;
         	case "/rejectbill":
         		billRejectSubmissionPage(request,response);
        		break;
         	case "/submitbillreject":
         		submitbillreject(request,response);
        		break;
            case "/pay":
            	paymentSubmissionPage(request, response);
            	break;
         	case "/submitpayment":
         		submitpayment(request,response);
        		break;
        	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void davidSmithPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("David Smith view");
	    	request.setAttribute("listQuoteRequest", userDAO.listAllQuoteRequests());
	    	request.setAttribute("listTree", userDAO.listAllTrees());
	    	request.setAttribute("listQuoteResponse", userDAO.listAllQuoteResponses());
	    	request.setAttribute("listQuoteReject", userDAO.listAllQuoteRejects());
	    	request.setAttribute("listOrderOfWork", userDAO.listAllOrderOfWorks());
	    	request.setAttribute("listBillRequest", userDAO.listAllBillRequests());
	    	request.setAttribute("listReportOfRevenue", userDAO.listAllReportOfRevenues());
	    	request.setAttribute("listBillReject", userDAO.listAllBillRejects());
	    	request.getRequestDispatcher("davidSmithView.jsp").forward(request, response);
	    }
	    private void clientPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("Client view");
	    	request.setAttribute("listQuoteRequest", userDAO.listClientQuoteRequests(currentUser));
	    	request.setAttribute("listTree", userDAO.listClientTrees(currentUser));
	    	request.setAttribute("listQuoteResponse", userDAO.listClientQuoteResponses(currentUser));
	    	request.setAttribute("listQuoteReject", userDAO.listClientQuoteRejects(currentUser));
	    	request.setAttribute("listOrderOfWork", userDAO.listClientOrderOfWorks(currentUser));
	    	request.setAttribute("listBillRequest", userDAO.listClientBillRequests(currentUser));
	    	request.setAttribute("listReportOfRevenue", userDAO.listClientReportOfRevenues(currentUser));
	    	request.setAttribute("listBillReject", userDAO.listClientBillRejects(currentUser));
	    	request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }
	    private void treeSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Tree");
	    	String id = request.getParameter("id");
	    	QuoteRequest quoteRequest = userDAO.GetQuoteRequest(id);
	    	request.setAttribute("quoteRequest", quoteRequest);
   	 		request.getRequestDispatcher("treeSubmission.jsp").forward(request, response);
	    }
	    private void quoteResponseSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Quote Response");
	    	String id = request.getParameter("id");
	    	QuoteRequest quoteRequest = userDAO.GetQuoteRequest(id);
	    	request.setAttribute("quoteRequest", quoteRequest);
	    	request.getRequestDispatcher("quoteResponseSubmission.jsp").forward(request, response);
	    }
	    private void quoteRejectSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Quote Reject");
	    	String id = request.getParameter("id");
	    	QuoteRequest quoteRequest = userDAO.GetQuoteRequest(id);
	    	request.setAttribute("quoteRequest", quoteRequest);
   	 		request.getRequestDispatcher("quoteRejectSubmission.jsp").forward(request, response);
	    }
	    private void orderOfWorkSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Order Of Work");
	    	String id = request.getParameter("id");
	    	QuoteResponse quoteResponse = userDAO.GetQuoteResponse(id);
	    	request.setAttribute("quoteResponse", quoteResponse);
   	 		request.getRequestDispatcher("orderOfWorkSubmission.jsp").forward(request, response);
	    }
	    private void billRequestSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Bill Request");
	    	String id = request.getParameter("id");
	    	OrderOfWork orderOfWork = userDAO.GetOrderOfWork(id);
	    	request.setAttribute("orderOfWork", orderOfWork);
   	 		request.getRequestDispatcher("billRequestSubmission.jsp").forward(request, response);
	    }
	    private void billRejectSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Bill Reject");
	    	String id = request.getParameter("id");
	    	BillRequest billRequest = userDAO.GetBillRequest(id);
	    	request.setAttribute("billRequest", billRequest);
   	 		request.getRequestDispatcher("billRejectSubmission.jsp").forward(request, response);
	    }
	    private void paymentSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Payment");
	    	String id = request.getParameter("id");
	    	BillRequest billRequest = userDAO.GetBillRequest(id);
	    	request.setAttribute("billRequest", billRequest);
   	 		request.getRequestDispatcher("paymentSubmission.jsp").forward(request, response);
	    }
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if (email.equals("dsmith@gmail.com") && password.equals("david1234")) {
				 System.out.println("Login Successful! Redirecting to David Smith");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 davidSmithPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) { 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 session = request.getSession();
				 session.setAttribute("username", currentUser);
				 clientPage(request, response, "");			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String creditcardinfo = request.getParameter("creditcardinfo");
	   	 	String adress_street_num = request.getParameter("adress_street_num"); 
	   	 	String adress_street = request.getParameter("adress_street"); 
	   	 	String adress_city = request.getParameter("adress_city"); 
	   	 	String adress_state = request.getParameter("adress_state"); 
	   	 	String adress_zip_code = request.getParameter("adress_zip_code"); 
	   	 	String phonenumber = request.getParameter("phonenumber");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		   	 		userCounter++;
		            user users = new user(email,firstName, lastName, password, creditcardinfo, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, phonenumber ,userCounter);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }
	    private void submitquoterequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String quotenote = request.getParameter("quotenote");
   	 		System.out.println("Quote has been submittted");
   	 		quoteRequestCounter++;
   	 		QuoteRequest quoterequests = new QuoteRequest(quoteRequestCounter, quotenote, currentUser);
   	 		userDAO.insert(quoterequests);
   	 		clientPage(request, response, "");	
	    }
	    private void submittree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoterequestid = Integer.parseInt(request.getParameter("quoterequestid"));
	    	double size = Double.parseDouble(request.getParameter("size"));
	    	double height = Double.parseDouble(request.getParameter("height"));
	    	String location = request.getParameter("location");
	    	double proximitytohouse = Double.parseDouble(request.getParameter("proximitytohouse"));
	    	String picture1 = request.getParameter("picture1");
	    	String picture2 = request.getParameter("picture2");
	    	String picture3 = request.getParameter("picture3");
   	 		System.out.println("Tree has been submitted");
   	 		treeCounter++;
   	 		Tree trees = new Tree(treeCounter, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, currentUser);
   	 		userDAO.insert(trees);
   	 		clientPage(request, response, "");	
	    }
	    
	    private void submitquoteresponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int quoterequestid = Integer.parseInt(request.getParameter("quoterequestid"));
	    	String email = request.getParameter("email");
	    	double initialprice = Double.parseDouble(request.getParameter("initialprice"));
	    	String timewindow = request.getParameter("timewindow");
	    	System.out.println("Quote has been responded to");
   	 		quoteResponseCounter++;
   	 		QuoteResponse quoteresponses = new QuoteResponse(quoteResponseCounter, initialprice, timewindow, quoterequestid, email);
   	 		userDAO.insert(quoteresponses);
			davidSmithPage(request, response, "");
	    }
	    
	    private void submitquotereject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {   	 		
	    	int quoterequestid = Integer.parseInt(request.getParameter("quoterequestid"));
	    	String email = request.getParameter("email");
	    	String quoterejectnote = request.getParameter("quoterejectnote");
	    	System.out.println("Quote has been rejected");
	    	quoteRejectCounter++;
   	 		QuoteReject quoterejects = new QuoteReject(quoteRejectCounter, quoterejectnote, quoterequestid, email);
	    	userDAO.insert(quoterejects);
			davidSmithPage(request, response, "");
	    } 
	    private void submitorderofwork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {   	 		
	    	int quoteresponseid = Integer.parseInt(request.getParameter("quoteresponseid"));
	    	System.out.println("Order of Work has been created");
	    	String orderofworknote = request.getParameter("orderofworknote");
	    	orderOfWorkCounter++;
   	 		OrderOfWork orderofworks = new OrderOfWork(orderOfWorkCounter, orderofworknote, quoteresponseid, currentUser);
	    	userDAO.insert(orderofworks);
			clientPage(request, response, "");
	    } 
	    
	    private void submitbillrequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int orderofworkid = Integer.parseInt(request.getParameter("orderofworkid"));
	    	String email = request.getParameter("email");
	    	String billnote = request.getParameter("billnote");
	    	double billamount = Double.parseDouble(request.getParameter("billamount"));
   	 		System.out.println("Bill has been submitted");
	    	billRequestCounter++;
	    	BillRequest billrequests = new BillRequest(billRequestCounter, billnote, billamount, orderofworkid, email);
	    	userDAO.insert(billrequests);
			davidSmithPage(request, response, "");
	    }  
	    private void submitbillreject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int billrequestid = Integer.parseInt(request.getParameter("billrequestid"));
	    	String billrejectnote = request.getParameter("billrejectnote");
   	 		System.out.println("Bill has been rejected");
	    	billRejectCounter++;
	    	BillReject billrejects = new BillReject(billRejectCounter, billrejectnote, billrequestid, currentUser);
	    	userDAO.insert(billrejects);
			clientPage(request, response, "");
	    }  
	    private void submitpayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	int billrequestid = Integer.parseInt(request.getParameter("billrequestid"));
	    	double paymentamount = Double.parseDouble(request.getParameter("paymentamount"));
   	 		System.out.println("Payment has been submitted");
	    	paymentCounter++;
	    	ReportOfRevenue payments = new ReportOfRevenue(paymentCounter, paymentamount, billrequestid, currentUser);
	    	userDAO.insert(payments);
			clientPage(request, response, "");
	    }  
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        	response.sendRedirect("login.jsp");
        }    
}