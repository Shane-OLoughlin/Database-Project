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
	    int quoteRequestCounter = 10;
	    int treeCounter = 10;
	    int quoteResponseCounter = 10;
	    int quoteRejectCounter = 10;	
	    
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
         	case "/submittree":
        		submittree(request,response);
        		break;
         	case "/respond":
         		request.getRequestDispatcher("quoteResponseSubmission.jsp").forward(request, response);
         		break;
         	case "/submitquoteresponse":
        		submitquoteresponse(request,response);
        		break;
         	case "/reject":
         		quoteRejectSubmissionPage(request,response);
        		break;
            case "/submitquotereject":
            	submitquotereject(request, response);
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
	    private void quoteRejectSubmissionPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("In Submit Quote Rejection");
	    	String id = request.getParameter("id");
	    	QuoteRequest quoteRequest = userDAO.GetQuoteRequest(id);
	    	request.setAttribute("quoteRequest", quoteRequest);
   	 		request.getRequestDispatcher("quoteRejectSubmission.jsp").forward(request, response);
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
				 request.getRequestDispatcher("clientView.jsp").forward(request, response);	 			 
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
   	 		System.out.println("Submission Successful! Added to database");
   	 		quoteRequestCounter++;
   	 		QuoteRequest quoterequests = new QuoteRequest(quoteRequestCounter, quotenote, currentUser);
   	 		userDAO.insert(quoterequests);
   	 		request.getRequestDispatcher("clientView.jsp").forward(request, response);
	    }
	    private void submittree(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	double size = Double.parseDouble(request.getParameter("size"));
	    	double height = Double.parseDouble(request.getParameter("height"));
	    	String location = request.getParameter("location");
	    	double proximitytohouse = Double.parseDouble(request.getParameter("proximitytohouse"));
	    	String picture1 = request.getParameter("picture1");
	    	String picture2 = request.getParameter("picture2");
	    	String picture3 = request.getParameter("picture3");
	    	int quoterequestid = Integer.parseInt(request.getParameter("quoterequestid"));
   	 		System.out.println("Submission Successful! Added to database");
   	 		treeCounter++;
   	 		Tree trees = new Tree(treeCounter, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, currentUser);
   	 		userDAO.insert(trees);
   	 		request.getRequestDispatcher("treeSubmission.jsp").forward(request, response);
	    }
	    
	    private void submitquoteresponse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In the quote response method");
	    	//int id = Integer.parseInt(request.getParameter("id"));
	    	double initialprice = Double.parseDouble(request.getParameter("initialprice"));
	    	String timewindow = request.getParameter("timewindow");
	    	String email = request.getParameter("email");
   	 		System.out.println("Submission Successful! Added to database");
   	 		quoteResponseCounter++;
   	 		QuoteResponse quoteresponses = new QuoteResponse(quoteResponseCounter, initialprice, timewindow, 5, email);
   	 		userDAO.insert(quoteresponses);
			davidSmithPage(request, response, "");
	    }
	    
	    private void submitquotereject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
   	 		System.out.println("Quote has been rejected");
	    	int id = Integer.parseInt(request.getParameter("id"));
	    	String quoterejectnote = request.getParameter("quoterejectnote");
	    	String email = request.getParameter("email");
	    	quoteRejectCounter++;
   	 		QuoteReject quoterejects = new QuoteReject(quoteRejectCounter, quoterejectnote, id, email);
	    	userDAO.insert(quoterejects);
			davidSmithPage(request, response, "");
	    }  
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    
	    
}
	        
	        
	    
	        
	        
	        
	    
