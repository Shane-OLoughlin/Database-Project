import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public QuoteRequest GetQuoteRequest(String id) throws SQLException {
        QuoteRequest quoteRequest = null;        
        String sql = "select * from QuoteRequest where quoterequestid = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoterequestid = resultSet.getInt("quoterequestid");
            String quotenote = resultSet.getString("quotenote");
            String negotiations = resultSet.getString("negotiations");
	    	String email = resultSet.getString("email");
            quoteRequest = new QuoteRequest(quoterequestid, quotenote, negotiations, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return quoteRequest;
    }
    
    public QuoteResponse GetQuoteResponse(String id) throws SQLException {
        QuoteResponse quoteResponse = null;        
        String sql = "select * from QuoteResponse where quoteresponseid = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoteresponseid = resultSet.getInt("quoteresponseid");
            double initialprice = resultSet.getDouble("initialprice");
            String timewindow = resultSet.getString("timewindow");
            int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
	    	quoteResponse = new QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return quoteResponse;
    }
       
    public OrderOfWork GetOrderOfWork(String id) throws SQLException {
        OrderOfWork orderOfWork = null;        
        String sql = "select * from OrderOfWork where orderofworkid = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int orderofworkid = resultSet.getInt("orderofworkid");
            String orderofworknote = resultSet.getString("orderofworknote");
            int quoteresponseid = resultSet.getInt("quoteresponseid");
	    	String email = resultSet.getString("email");
            orderOfWork = new OrderOfWork(orderofworkid, orderofworknote, quoteresponseid, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return orderOfWork;
    }
    
    public BillRequest GetBillRequest(String id) throws SQLException {
        BillRequest billRequest = null;        
        String sql = "select * from BillRequest where billrequestid = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int billrequestid = resultSet.getInt("billrequestid");
            String billnote = resultSet.getString("billnote");
            double billamount = resultSet.getDouble("billamount");
            String timegenerated = resultSet.getString("timegenerated");
            int orderofworkid = resultSet.getInt("orderofworkid");
	    	String email = resultSet.getString("email");
            billRequest = new BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return billRequest;
    }
    
    public Tree GetTree(String id) throws SQLException {
        Tree tree = null;        
        String sql = "select * from Tree where treeid = " + id;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int treeid = resultSet.getInt("treeid");
	    	double size = resultSet.getDouble("size");
	    	double height = resultSet.getDouble("height");
	    	String location = resultSet.getString("location");
	    	double proximitytohouse = resultSet.getDouble("proximitytohouse");
	    	String picture1 = resultSet.getString("picture1");
	    	String picture2 = resultSet.getString("picture2");
	    	String picture3 = resultSet.getString("picture3");
	    	String datecut = resultSet.getString("datecut");
	    	int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
            tree = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return tree;
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

             
            user users = new user(email,firstName, lastName, password, creditcardinfo, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, phonenumber,clientid);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<QuoteRequest> listAllQuoteRequests() throws SQLException {
        List<QuoteRequest> listQuoteRequest = new ArrayList<QuoteRequest>();        
        String sql = "SELECT * FROM QuoteRequest";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoterequestid = resultSet.getInt("quoterequestid");
            String quotenote = resultSet.getString("quotenote");
            String negotiations = resultSet.getString("negotiations");
            String email = resultSet.getString("email");
             
            QuoteRequest quoterequests = new QuoteRequest(quoterequestid, quotenote, negotiations, email);
            listQuoteRequest.add(quoterequests);
        }        
        resultSet.close();
        disconnect();        
        return listQuoteRequest;
    }
    public List<Tree> listAllTrees() throws SQLException {
        List<Tree> listTree = new ArrayList<Tree>();        
        String sql = "SELECT * FROM Tree";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int treeid = resultSet.getInt("treeid");
	    	double size = resultSet.getDouble("size");
	    	double height = resultSet.getDouble("height");
	    	String location = resultSet.getString("location");
	    	double proximitytohouse = resultSet.getDouble("proximitytohouse");
	    	String picture1 = resultSet.getString("picture1");
	    	String picture2 = resultSet.getString("picture2");
	    	String picture3 = resultSet.getString("picture3");
	    	String datecut = resultSet.getString("datecut");
	    	int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
             
            Tree trees = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email);
            listTree.add(trees);
        }        
        resultSet.close();
        disconnect();        
        return listTree;
    }
    
    public List<QuoteResponse> listAllQuoteResponses() throws SQLException {
        List<QuoteResponse> listQuoteResponses = new ArrayList<QuoteResponse>();        
        String sql = "SELECT * FROM QuoteResponse";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoteresponseid = resultSet.getInt("quoteresponseid");
            double initialprice = Double.parseDouble(resultSet.getString("initialprice"));
            String timewindow = resultSet.getString("timewindow");
            int quoterequestid = resultSet.getInt("quoterequestid");
            String email = resultSet.getString("email");
             
            QuoteResponse quoteresponses = new QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email);
            listQuoteResponses.add(quoteresponses);
        }        
        resultSet.close();
        disconnect();        
        return listQuoteResponses;
    }
    
    public List<QuoteReject> listAllQuoteRejects() throws SQLException {
        List<QuoteReject> listQuoteRejects = new ArrayList<QuoteReject>();        
        String sql = "SELECT * FROM QuoteReject";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int quoterejectid = resultSet.getInt("quoterejectid");
            String quoterejectnote = resultSet.getString("quoterejectnote");
            int quoterequestid = resultSet.getInt("quoterequestid");
            String email = resultSet.getString("email");
             
            QuoteReject quoterejects = new QuoteReject(quoterejectid, quoterejectnote, quoterequestid, email);
            listQuoteRejects.add(quoterejects);
        }        
        resultSet.close();
        disconnect();        
        return listQuoteRejects;
    }
    public List<OrderOfWork> listAllOrderOfWorks() throws SQLException {
        List<OrderOfWork> listOrderOfWorks = new ArrayList<OrderOfWork>();        
        String sql = "SELECT * FROM OrderOfWork";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int orderofworkid = resultSet.getInt("orderofworkid");
            String orderofworknote = resultSet.getString("orderofworknote");
            int quoteresponseid = resultSet.getInt("quoteresponseid");
            String email = resultSet.getString("email");
             
            OrderOfWork orderofworks = new OrderOfWork(orderofworkid, orderofworknote, quoteresponseid, email);
            listOrderOfWorks.add(orderofworks);
        }        
        resultSet.close();
        disconnect();        
        return listOrderOfWorks;
    }
    public List<BillRequest> listAllBillRequests() throws SQLException {
        List<BillRequest> listBillRequests = new ArrayList<BillRequest>();        
        String sql = "SELECT * FROM BillRequest";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int billrequestid = resultSet.getInt("billrequestid");
            String billnote = resultSet.getString("billnote");
            double billamount = resultSet.getDouble("billamount");
            String timegenerated = resultSet.getString("timegenerated");
            int orderofworkid = resultSet.getInt("orderofworkid");
            String email = resultSet.getString("email");
             
            BillRequest billrequests = new BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email);
            listBillRequests.add(billrequests);
        }        
        resultSet.close();
        disconnect();        
        return listBillRequests;
    }
    public List<ReportOfRevenue> listAllReportOfRevenues() throws SQLException {
        List<ReportOfRevenue> listReportOfRevenues = new ArrayList<ReportOfRevenue>();        
        String sql = "SELECT * FROM ReportOfRevenue";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int reportofrevenueid = resultSet.getInt("reportofrevenueid");
            double paymentamount = resultSet.getDouble("paymentamount");
            String timepaid = resultSet.getString("timepaid");
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            ReportOfRevenue reportofrevenues = new ReportOfRevenue(reportofrevenueid, paymentamount, timepaid, billrequestid, email);
            listReportOfRevenues.add(reportofrevenues);
        }        
        resultSet.close();
        disconnect();        
        return listReportOfRevenues;
    }
    public List<BillReject> listAllBillRejects() throws SQLException {
        List<BillReject> listBillRejects = new ArrayList<BillReject>();        
        String sql = "SELECT * FROM BillReject";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int billrejectid = resultSet.getInt("billrejectid");
            String billrejectnote = resultSet.getString("billrejectnote");
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            BillReject billrejects = new BillReject(billrejectid, billrejectnote, billrequestid, email);
            listBillRejects.add(billrejects);
        }        
        resultSet.close();
        disconnect();        
        return listBillRejects;
    }
    
    public List<QuoteRequest> listClientQuoteRequests(String currentUser) throws SQLException {
        List<QuoteRequest> listQuoteRequest = new ArrayList<QuoteRequest>();        
        String sql = "SELECT * FROM QuoteRequest WHERE email = ?";
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoterequestid = resultSet.getInt("quoterequestid");
            String quotenote = resultSet.getString("quotenote");
            String negotiations = resultSet.getString("negotiations");
            String email = resultSet.getString("email");
             
            QuoteRequest quoterequests = new QuoteRequest(quoterequestid, quotenote, negotiations, email);
            listQuoteRequest.add(quoterequests);
        }
        
        resultSet.close();
    }
    finally {
        disconnect();
    }

    return listQuoteRequest;
}
    public List<Tree> listClientTrees(String currentUser) throws SQLException {
        List<Tree> listTree = new ArrayList<Tree>();        
        String sql = "SELECT * FROM Tree WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int treeid = resultSet.getInt("treeid");
	    	double size = resultSet.getDouble("size");
	    	double height = resultSet.getDouble("height");
	    	String location = resultSet.getString("location");
	    	double proximitytohouse = resultSet.getDouble("proximitytohouse");
	    	String picture1 = resultSet.getString("picture1");
	    	String picture2 = resultSet.getString("picture2");
	    	String picture3 = resultSet.getString("picture3");
	    	String datecut = resultSet.getString("datecut");
	    	int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
             
            Tree trees = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email);
            listTree.add(trees);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
     
        return listTree;
    }
    
    public List<QuoteResponse> listClientQuoteResponses(String currentUser) throws SQLException {
        List<QuoteResponse> listQuoteResponses = new ArrayList<QuoteResponse>();        
        String sql = "SELECT * FROM QuoteResponse WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoteresponseid = resultSet.getInt("quoteresponseid");
            double initialprice = Double.parseDouble(resultSet.getString("initialprice"));
            String timewindow = resultSet.getString("timewindow");
            int quoterequestid = resultSet.getInt("quoterequestid");
            String email = resultSet.getString("email");
             
            QuoteResponse quoteresponses = new QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email);
            listQuoteResponses.add(quoteresponses);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
    
        return listQuoteResponses;
    }
    
    public List<QuoteReject> listClientQuoteRejects(String currentUser) throws SQLException {
        List<QuoteReject> listQuoteRejects = new ArrayList<QuoteReject>();        
        String sql = "SELECT * FROM QuoteReject WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int quoterejectid = resultSet.getInt("quoterejectid");
            String quoterejectnote = resultSet.getString("quoterejectnote");
            int quoterequestid = resultSet.getInt("quoterequestid");
            String email = resultSet.getString("email");
             
            QuoteReject quoterejects = new QuoteReject(quoterejectid, quoterejectnote, quoterequestid, email);
            listQuoteRejects.add(quoterejects);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
    
        return listQuoteRejects;
    }
    public List<OrderOfWork> listClientOrderOfWorks(String currentUser) throws SQLException {
        List<OrderOfWork> listOrderOfWorks = new ArrayList<OrderOfWork>();        
        String sql = "SELECT * FROM OrderOfWork WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int orderofworkid = resultSet.getInt("orderofworkid");
            String orderofworknote = resultSet.getString("orderofworknote");
            int quoteresponseid = resultSet.getInt("quoteresponseid");
            String email = resultSet.getString("email");
             
            OrderOfWork orderofworks = new OrderOfWork(orderofworkid, orderofworknote, quoteresponseid, email);
            listOrderOfWorks.add(orderofworks);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
     
        return listOrderOfWorks;
    }
    public List<BillRequest> listClientBillRequests(String currentUser) throws SQLException {
        List<BillRequest> listBillRequests = new ArrayList<BillRequest>();        
        String sql = "SELECT * FROM BillRequest WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
            
        while (resultSet.next()) {
            int billrequestid = resultSet.getInt("billrequestid");
            String billnote = resultSet.getString("billnote");
            double billamount = resultSet.getDouble("billamount");
            String timegenerated = resultSet.getString("timegenerated");
            int orderofworkid = resultSet.getInt("orderofworkid");
            String email = resultSet.getString("email");
             
            BillRequest billrequests = new BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email);
            listBillRequests.add(billrequests);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
    
        return listBillRequests;
    }
    public List<ReportOfRevenue> listClientReportOfRevenues(String currentUser) throws SQLException {
        List<ReportOfRevenue> listReportOfRevenues = new ArrayList<ReportOfRevenue>();        
        String sql = "SELECT * FROM ReportOfRevenue WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int reportofrevenueid = resultSet.getInt("reportofrevenueid");
            double paymentamount = resultSet.getDouble("paymentamount");
            String timepaid = resultSet.getString("timepaid");
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            ReportOfRevenue reportofrevenues = new ReportOfRevenue(reportofrevenueid, paymentamount, timepaid, billrequestid, email);
            listReportOfRevenues.add(reportofrevenues);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
    
        return listReportOfRevenues;
    }
    public List<BillReject> listClientBillRejects(String currentUser) throws SQLException {
        List<BillReject> listBillRejects = new ArrayList<BillReject>();        
        String sql = "SELECT * FROM BillReject WHERE email = ?";      
        
        connect_func();
        
        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, currentUser);

            ResultSet resultSet = preparedStatement.executeQuery();
         
        while (resultSet.next()) {
            int billrejectid = resultSet.getInt("billrejectid");
            String billrejectnote = resultSet.getString("billrejectnote");
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            BillReject billrejects = new BillReject(billrejectid, billrejectnote, billrequestid, email);
            listBillRejects.add(billrejects);
        }        
        
        resultSet.close();
    }
    finally {
        disconnect();
    }
        
        return listBillRejects;
    }
    
    public List<user> listBigClients() throws SQLException {
        List<user> listBigClients = new ArrayList<>();

        // Select users with the maximum tree count
        String sql = "SELECT u.*, COUNT(DISTINCT t.treeid) AS tree_count " +
                "FROM User u " +
                "LEFT JOIN Tree t ON u.email = t.email " +
                "WHERE t.datecut != 'Has not been cut.' " +
                "GROUP BY u.clientid " +
                "HAVING tree_count = (SELECT MAX(tree_count) FROM (SELECT COUNT(DISTINCT treeid) AS tree_count FROM Tree WHERE datecut != 'Has not been cut.' GROUP BY email) AS temp)";
        
        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num");
            String adress_street = resultSet.getString("adress_street");
            String adress_city = resultSet.getString("adress_city");
            String adress_state = resultSet.getString("adress_state");
            String adress_zip_code = resultSet.getString("adress_zip_code");
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

            user user = new user(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phonenumber, clientid);

            listBigClients.add(user);
        }

        resultSet.close();
        disconnect();
        return listBigClients;
    }
    public List<user> listEasyClients() throws SQLException {
        List<user> listEasyClients = new ArrayList<>();

        String sql = "SELECT DISTINCT u.* " +
                     "FROM User u " +
                     "WHERE NOT EXISTS " +
                     "    (SELECT 1 " +
                     "     FROM QuoteRequest qr " +
                     "     WHERE qr.email = u.email AND qr.negotiations = 'yes')";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num");
            String adress_street = resultSet.getString("adress_street");
            String adress_city = resultSet.getString("adress_city");
            String adress_state = resultSet.getString("adress_state");
            String adress_zip_code = resultSet.getString("adress_zip_code");
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

            user user = new user(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phonenumber, clientid);
            listEasyClients.add(user);
        }

        resultSet.close();
        disconnect();
        return listEasyClients;
    }
    public List<QuoteRequest> listOneTreeQuotes() throws SQLException {
        List<QuoteRequest> listOneTreeQuotes = new ArrayList<>();

        String sql = "SELECT DISTINCT qr.* " +
                     "FROM QuoteRequest qr " +
                     "JOIN Tree t ON qr.quoterequestid = t.quoterequestid " +
                     "WHERE qr.quoterequestid IN " +
                     "    (SELECT qr.quoterequestid " +
                     "     FROM QuoteRequest qr " +
                     "     JOIN Tree t ON qr.quoterequestid = t.quoterequestid " +
                     "     JOIN QuoteResponse qrs ON qr.quoterequestid = qrs.quoterequestid " +
                     "     JOIN OrderOfWork ow ON qrs.quoteresponseid = ow.quoteresponseid " +
                     "     GROUP BY qr.quoterequestid " +
                     "     HAVING COUNT(DISTINCT t.treeid) = 1)";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int quoterequestid = resultSet.getInt("quoterequestid");
            String quotenote = resultSet.getString("quotenote");
            String negotiations = resultSet.getString("negotiations");
            String email = resultSet.getString("email");

            QuoteRequest quoteRequest = new QuoteRequest(quoterequestid, quotenote, negotiations, email);
            listOneTreeQuotes.add(quoteRequest);
        }

        resultSet.close();
        disconnect();
        return listOneTreeQuotes;
    }
    public List<user> listProspectiveClients() throws SQLException {
        List<user> listProspectiveClients = new ArrayList<>();

        // Select users with email in QuoteRequest but not in OrderOfWork
        String sql = "SELECT u.* " +
                     "FROM User u " +
                     "JOIN QuoteRequest qr ON u.email = qr.email " +
                     "WHERE NOT EXISTS (SELECT 1 FROM OrderOfWork ow WHERE ow.email = u.email)";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num");
            String adress_street = resultSet.getString("adress_street");
            String adress_city = resultSet.getString("adress_city");
            String adress_state = resultSet.getString("adress_state");
            String adress_zip_code = resultSet.getString("adress_zip_code");
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

            user user = new user(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phonenumber, clientid);

            listProspectiveClients.add(user);
        }

        resultSet.close();
        disconnect();
        return listProspectiveClients;
    }
    
    public List<Tree> listHighestTrees() throws SQLException {
        List<Tree> listHighestTrees = new ArrayList<>();

     // Select trees with the largest height values and exclude those with datecut="Has not been cut."
        String sql = "SELECT * FROM Tree " +
                "WHERE datecut != 'Has not been cut.' AND height = (SELECT MAX(height) FROM Tree WHERE datecut != 'Has not been cut.')";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int treeid = resultSet.getInt("treeid");
            double size = resultSet.getDouble("size");
            double height = resultSet.getDouble("height");
            String location = resultSet.getString("location");
            double proximitytohouse = resultSet.getDouble("proximitytohouse");
            String picture1 = resultSet.getString("picture1");
            String picture2 = resultSet.getString("picture2");
            String picture3 = resultSet.getString("picture3");
            String datecut = resultSet.getString("datecut");
            int quoterequestid = resultSet.getInt("quoterequestid");
            String email = resultSet.getString("email");

            Tree trees = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email);
            listHighestTrees.add(trees);
        }

        resultSet.close();
        disconnect();
        return listHighestTrees;
    }
    public List<BillRequest> listOverdueBills() throws SQLException {
        List<BillRequest> overdueBills = new ArrayList<>();

        // Get the current timestamp
        LocalDateTime currentTimestamp = LocalDateTime.now();

        // Select overdue bills
        String sql = "SELECT * FROM BillRequest br " +
                     "LEFT JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE rr.billrequestid IS NULL " +
                     "AND TIMESTAMPDIFF(WEEK, br.timegenerated, NOW()) > 1";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int billrequestid = resultSet.getInt("billrequestid");
            String billnote = resultSet.getString("billnote");
            double billamount = resultSet.getDouble("billamount");
            String timegenerated = resultSet.getString("timegenerated");
            int orderofworkid = resultSet.getInt("orderofworkid");
            String email = resultSet.getString("email");

            BillRequest overdueBill = new BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email);
            overdueBills.add(overdueBill);
        }

        resultSet.close();
        disconnect();
        return overdueBills;
    }
    public List<user> listBadClients() throws SQLException {
        List<user> badClients = new ArrayList<>();

        // Get the current timestamp
        LocalDateTime currentTimestamp = LocalDateTime.now();

        // Select users with overdue BillRequests
        String sql = "SELECT DISTINCT u.* " +
                     "FROM User u " +
                     "JOIN BillRequest br ON u.email = br.email " +
                     "LEFT JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE TIMESTAMPDIFF(WEEK, br.timegenerated, NOW()) > 1 " +
                     "      AND rr.billrequestid IS NULL";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

            user badClient = new user(email,firstName, lastName, password, creditcardinfo, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, phonenumber,clientid);
            badClients.add(badClient);
        }

        resultSet.close();
        disconnect();
        return badClients;
    }
    public List<user> listGoodClients() throws SQLException {
        List<user> goodClients = new ArrayList<>();

        // Select users with corresponding ReportOfRevenues paid within 24 hours
        String sql = "SELECT u.email, u.firstName, u.lastName, u.password, u.creditcardinfo, " +
                     "u.adress_street_num, u.adress_street, u.adress_city, u.adress_state, " +
                     "u.adress_zip_code, u.phonenumber, u.clientid " +
                     "FROM User u " +
                     "JOIN BillRequest br ON u.email = br.email " +
                     "JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE TIMESTAMPDIFF(HOUR, br.timegenerated, rr.timepaid) <= 24";

        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String creditcardinfo = resultSet.getString("creditcardinfo");
            String adress_street_num = resultSet.getString("adress_street_num");
            String adress_street = resultSet.getString("adress_street");
            String adress_city = resultSet.getString("adress_city");
            String adress_state = resultSet.getString("adress_state");
            String adress_zip_code = resultSet.getString("adress_zip_code");
            String phonenumber = resultSet.getString("phonenumber");
            int clientid = resultSet.getInt("clientid");

            user goodClient = new user(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phonenumber, clientid);
            goodClients.add(goodClient);
        }

        resultSet.close();
        disconnect();
        return goodClients;
    }
    public List<Statistics> listStatistics() throws SQLException {
        List<Statistics> statistics = new ArrayList<>();

        // Select users with their statistics
     String sql = "SELECT u.email, u.clientid, " +
        	            "COUNT(DISTINCT CASE WHEN t.datecut != 'Has not been cut.' THEN t.treeid END) AS totalTrees, " +
        	            "IFNULL(totalDueAmount, 0) AS totalDueAmount, " +
        	            "IFNULL(totalPaidAmount, 0) AS totalPaidAmount, " +
        	            "GROUP_CONCAT(DISTINCT CASE WHEN t.datecut != 'Has not been cut.' THEN t.datecut END) AS datesWorkDone " +
        	            "FROM User u " +
        	            "LEFT JOIN Tree t ON u.email = t.email " +
        	            "LEFT JOIN (" +
        	            "    SELECT br.email, SUM(br.billamount) AS totalDueAmount " +
        	            "    FROM BillRequest br " +
        	            "    LEFT JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
        	            "    WHERE rr.billrequestid IS NULL " +
        	            "    GROUP BY br.email" +
        	            ") AS totalDue ON u.email = totalDue.email " +
        	            "LEFT JOIN (" +
        	            "    SELECT email, SUM(paymentamount) AS totalPaidAmount " +
        	            "    FROM ReportOfRevenue " +
        	            "    GROUP BY email" +
        	            ") AS totalPaid ON u.email = totalPaid.email " +
        	            "GROUP BY u.email, u.clientid " +
        	            "ORDER BY u.clientid";
        
        connect_func();
        statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String email = resultSet.getString("email");
            int clientid = resultSet.getInt("clientid");
            int totalTrees = resultSet.getInt("totalTrees");
            double totalDueAmount = resultSet.getDouble("totalDueAmount");
            double totalPaidAmount = resultSet.getDouble("totalPaidAmount");
            String datesWorkDone = resultSet.getString("datesWorkDone");

            Statistics userStatistics = new Statistics(email, clientid, totalTrees, totalDueAmount, totalPaidAmount, datesWorkDone);
            statistics.add(userStatistics);
        }

        resultSet.close();
        disconnect();
        return statistics;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,phonenumber,clientid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getcreditcardinfo());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setString(11, users.getphonenumber());		
			preparedStatement.setInt(12, users.getclientid());		

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(QuoteRequest quoterequests) throws SQLException {      
    	connect_func();
		String sql = "insert into QuoteRequest(quoterequestid, quotenote, negotiations, email) values (?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoterequests.getQuoteRequestID());
		preparedStatement.setString(2, quoterequests.getQuoteNote());
		preparedStatement.setString(3, quoterequests.getNegotiations());
		preparedStatement.setString(4, quoterequests.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(Tree trees) throws SQLException {      
    	connect_func();
		String sql = "insert into Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email) values (?,?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getTreeID());
		preparedStatement.setDouble(2, trees.getSize());	
		preparedStatement.setDouble(3, trees.getHeight());
		preparedStatement.setString(4, trees.getLocation());
		preparedStatement.setDouble(5, trees.getproximitytohouse());
		preparedStatement.setString(6, trees.getPicture1());
		preparedStatement.setString(7, trees.getPicture2());
		preparedStatement.setString(8, trees.getPicture3());
		preparedStatement.setString(9, trees.getDateCut());
		preparedStatement.setInt(10, trees.getQuoteRequestID());
		preparedStatement.setString(11, trees.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(QuoteResponse quoteresponses) throws SQLException {      
    	connect_func();
		String sql = "insert into QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email) values (?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoteresponses.getQuoteResponseID());
		preparedStatement.setDouble(2, quoteresponses.getInitialPrice());	
		preparedStatement.setString(3, quoteresponses.getTimeWindow());	
		preparedStatement.setInt(4, quoteresponses.getQuoteRequestID());	
		preparedStatement.setString(5, quoteresponses.getEmail());	
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(QuoteReject quoterejects) throws SQLException {      
    	connect_func();
		String sql = "insert into QuoteReject(quoterejectid, quoterejectnote, quoterequestid, email) values (?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoterejects.getQuoteRejectID());	
		preparedStatement.setString(2, quoterejects.getQuoteRejectNote());	
		preparedStatement.setInt(3, quoterejects.getQuoteRequestID());	
		preparedStatement.setString(4, quoterejects.getEmail());	
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(OrderOfWork orderofworks) throws SQLException {      
    	connect_func();
		String sql = "insert into OrderOfWork(orderofworkid, orderofworknote, quoteresponseid, email) values (?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, orderofworks.getOrderOfWorkID());	
		preparedStatement.setString(2, orderofworks.getOrderOfWorkNote());	
		preparedStatement.setInt(3, orderofworks.getQuoteResponseID());	
		preparedStatement.setString(4, orderofworks.getEmail());	
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(BillRequest billrequests) throws SQLException {      
    	connect_func();
		String sql = "insert into BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email) values (?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, billrequests.getBillRequestID());
		preparedStatement.setString(2, billrequests.getBillNote());	
		preparedStatement.setDouble(3, billrequests.getBillAmount());
		preparedStatement.setString(4, billrequests.getTimeGenerated());
		preparedStatement.setInt(5, billrequests.getOrderOfWorkID());
		preparedStatement.setString(6, billrequests.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(BillReject billrejects) throws SQLException {      
    	connect_func();
		String sql = "insert into BillReject(billrejectid, billrejectnote, billrequestid, email) values (?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, billrejects.getBillRejectID());
		preparedStatement.setString(2, billrejects.getBillRejectNote());	
		preparedStatement.setInt(3, billrejects.getBillRequestID());
		preparedStatement.setString(4, billrejects.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(ReportOfRevenue payments) throws SQLException {      
    	connect_func();
		String sql = "insert into ReportOfRevenue(reportofrevenueid, paymentamount, timepaid, billrequestid, email) values (?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, payments.getReportOfRevenueID());
		preparedStatement.setDouble(2, payments.getPaymentAmount());
		preparedStatement.setString(3, payments.getTimePaid());
		preparedStatement.setInt(4, payments.getBillRequestID());
		preparedStatement.setString(5, payments.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void updateDateCut(int treeid, String datecut) throws SQLException {
    	connect_func();
        String sql = "UPDATE Tree SET datecut = ? WHERE treeid = ?";
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, datecut);
        preparedStatement.setInt(2, treeid);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        disconnect();
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }  
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        "CREATE TABLE if not exists User( " +
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(20) NOT NULL, " +
					            "lastName VARCHAR(20) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "creditcardinfo CHAR(16) NOT NULL, " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "phonenumber CHAR(10),"+ 
					            "clientid INTEGER,"+
					            "PRIMARY KEY (clientid)" +
					        ");",
					        "DROP TABLE IF EXISTS QuoteRequest;",
					        "CREATE TABLE IF NOT EXISTS QuoteRequest ( " +
					            "quoterequestid INTEGER NOT NULL, " +
					            "quotenote VARCHAR(1000), " +
					            "negotiations VARCHAR(3), " +
					            "email VARCHAR(50)," +
					            "PRIMARY KEY (quoterequestid)" +
					        ");",
					        "DROP TABLE IF EXISTS Tree;",
					        "CREATE TABLE IF NOT EXISTS Tree ( " +
					            "treeid INTEGER NOT NULL, " +
					            "size DOUBLE, " +
					            "height DOUBLE, " +
					            "location VARCHAR(100), " +
					            "proximitytohouse DOUBLE, " +
					            "picture1 VARCHAR(100), " +
					            "picture2 VARCHAR(100), " +
					            "picture3 VARCHAR(100), " +
					            "datecut VARCHAR(100),"+
					            "quoterequestid INTEGER NOT NULL, " +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (treeid) " +
					        ");",
					        "DROP TABLE IF EXISTS QuoteResponse;",
					        "CREATE TABLE IF NOT EXISTS QuoteResponse ( " +
					            "quoteresponseid INTEGER NOT NULL, " +
					            "initialprice DOUBLE, " +
					            "timewindow VARCHAR(100), " +
					            "quoterequestid INTEGER NOT NULL, " +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (quoteresponseid)" +
					        ");",
					        "DROP TABLE IF EXISTS QuoteReject;",
					        "CREATE TABLE IF NOT EXISTS QuoteReject ( " +
					            "quoterejectid INTEGER NOT NULL, " +
					            "quoterejectnote VARCHAR(1000), " +
					            "quoterequestid INTEGER NOT NULL, " +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (quoterejectid)" +
					        ");",
					        "DROP TABLE IF EXISTS OrderOfWork;",
					        "CREATE TABLE IF NOT EXISTS OrderOfWork ( " +
					            "orderofworkid INTEGER NOT NULL, " +
					            "orderofworknote VARCHAR(1000), " +
					            "quoteresponseid INTEGER NOT NULL, " +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (orderofworkid)" +
					        ");",
					        "DROP TABLE IF EXISTS BillRequest;",
					        "CREATE TABLE IF NOT EXISTS BillRequest ( " +
					            "billrequestid INTEGER NOT NULL, " +
					            "billnote VARCHAR(1000), " +
					            "billamount DOUBLE NOT NULL," +
					            "timegenerated VARCHAR(50)," +
					            "orderofworkid INTEGER NOT NULL," +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (billrequestid)" +
					        ");",
					        "DROP TABLE IF EXISTS ReportOfRevenue;",
					        "CREATE TABLE IF NOT EXISTS ReportOfRevenue ( " +
					            "reportofrevenueid INTEGER NOT NULL, " +
					            "paymentamount DOUBLE NOT NULL," +
					            "timepaid VARCHAR(50)," +
					            "billrequestid INTEGER NOT NULL," +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (reportofrevenueid) " +
					        ");",
					        "DROP TABLE IF EXISTS BillReject;",
					        "CREATE TABLE IF NOT EXISTS BillReject ( " +
					            "billrejectid INTEGER NOT NULL, " +
					            "billrejectnote VARCHAR(1000), " +
					            "billrequestid INTEGER NOT NULL," +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (billrejectid)" +
					        ");",
					    };
        
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, creditcardinfo, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, phonenumber, clientid)"+
    			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '1111111111111111', '1234', 'whatever street', 'detroit', 'MI', '48202','7340172092', '1'),"+
		    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1111111111111112', '1000', 'hi street', 'mama', 'MO', '12345','1671911928', '2'),"+
		    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1111111111111113', '1234', 'ivan street', 'tata','CO','12561','1231111928', '3'),"+
		    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '1111111111111114', '3214','marko street', 'brat', 'DU', '54321','1899191901', '4'),"+
		    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1111111111111115', '4500', 'frey street', 'sestra', 'MI', '48202','9189222929', '5'),"+
		    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '1111111111111116', '1245', 'm8s street', 'baka', 'IL', '48000','8282922992', '6'),"+
		    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1111111111111117', '2468', 'yolos street', 'ides', 'CM', '24680','9392011992', '7'),"+
		    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '1111111111111118', '4680', 'egypt street', 'lolas', 'DT', '13579','2391911812', '8'),"+
		    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1111111111111119', '1234', 'sign street', 'samo ne tu','MH', '09876','0191911982', '9'),"+
		    			"('jeannette@gmail.com', 'Jeannette', 'Stone','jeannette1234', '1111111111111122', '0981', 'snoop street', 'kojik', 'HW', '87654','3334992992', '10'),"+
		    			"('prospective@gmail.com', 'Prospective', 'Steve','prospective1234', '1111111111111123', '9182', 'the street', 'the city', 'NJ', '91698','0710201092', '11');")
		    			};
        
        String[] TUPLES2 = {("insert into QuoteRequest(quoterequestid, quotenote, negotiations, email)"+
    			"values ('1', 'Make sure all branches are removed.', 'no', 'susie@gmail.com'),"+
		    		 	"('2', 'Move date to 9/23/2023.', 'no', 'don@gmail.com'),"+
		    	 	 	"('3', 'Make the price $149.', 'no', 'margarita@gmail.com'),"+
		    		 	"('4', 'Avoid dog. He bites.', 'no', 'jo@gmail.com'),"+
		    		 	"('5', 'Ring doorbell when you are here.', 'no', 'wallace@gmail.com'),"+
		    		 	"('6', 'Make the price $260.', 'no', 'amelia@gmail.com'),"+
		    			"('7', 'Move date to 2/27/2023.', 'no', 'sophie@gmail.com'),"+
		    			"('8', 'Knock when you are here.', 'no','angelo@gmail.com'),"+
		    			"('9', 'Make the price $181.', 'no', 'rudy@gmail.com'),"+
		    			"('10', 'Move date to 1/03/2025.', 'no', 'jeannette@gmail.com'),"+
		    			"('11', 'Ring doorbell when here.', 'no', 'susie@gmail.com'),"+
		    			"('12', 'Move date to 9/23/2023.', 'no', 'don@gmail.com'),"+
		    			"('13', 'Make price $200.', 'no', 'margarita@gmail.com'),"+
		    			"('14', 'Move date to 5/23/2023.', 'no', 'jo@gmail.com'),"+
		    			"('15', 'Make price $250.', 'no', 'wallace@gmail.com'),"+
		    			"('16', 'Move date to 3/21/2023.', 'no', 'amelia@gmail.com'),"+
		    			"('17', 'Make sure all branches are removed, please.', 'no', 'sophie@gmail.com'),"+
		    			"('18', 'Move date to 1/12/2023.', 'no', 'angelo@gmail.com'),"+
		    			"('19', 'Please knock when you arrive.', 'no', 'rudy@gmail.com'),"+
		    			"('20', 'Avoid my dog, she likes to bite strangers.', 'no', 'jeannette@gmail.com'),"+
		    			"('21', 'I am prospective.', 'no', 'prospective@gmail.com');")
		    			};
        
        String[] TUPLES3 = {("INSERT INTO Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, datecut, quoterequestid, email)"+
          		 "values ('1', '15.5', '6.2', 'Front Yard', '10.3', 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', '2023-02-05', '1', 'susie@gmail.com'),"+
          	        	"('2', '10.2', '4.8', 'Back Yard', '5.7', 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', '2023-03-24', '2', 'don@gmail.com'),"+
          	        	"('3', '8.7', '3.5', 'Side Yard', '8.1', 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', '2023-4-10', '3', 'margarita@gmail.com'),"+
          	        	"('4', '12.1', '5.9', 'Front Yard', '9.4', 'tree10.jpg', 'tree11.jpg', 'tree12.jpg','2023-05-13', '4', 'jo@gmail.com'),"+
          	        	"('5', '17.3', '7.2', 'Back Yard', '12.8', 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', '2023-06-17', '5', 'wallace@gmail.com'),"+
          	        	"('6', '14.8', '6.0', 'Front Yard', '10.7', 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', '2023-07-13', '6', 'amelia@gmail.com'),"+
          	        	"('7', '9.5', '4.1', 'Side Yard', '7.3', 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', '2023-08-13', '7', 'sophie@gmail.com'),"+
          	        	"('8', '11.9', '5.6', 'Back Yard', '11.2', 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', '2023-09-13', '8', 'angelo@gmail.com'),"+
          	        	"('9', '13.2', '6.8', 'Front Yard', '10.9', 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', '2023-10-24', '9', 'rudy@gmail.com'),"+
          	        	"('10', '18.5', '7.7', 'Side Yard', '13.5', 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', '2023-11-13', '10', 'jeannette@gmail.com'),"+
          	        	"('11', '15.5', '6.2', 'Front Yard', '10.3', 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', 'Has not been cut.', '11', 'susie@gmail.com'),"+
          	        	"('12', '10.2', '4.8', 'Back Yard', '5.7', 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', 'Has not been cut.', '12', 'don@gmail.com'),"+
          	        	"('13', '8.7', '3.5', 'Side Yard', '8.1', 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', 'Has not been cut.', '13', 'margarita@gmail.com'),"+
          	        	"('14', '12.1', '5.9', 'Front Yard', '9.4', 'tree10.jpg', 'tree11.jpg', 'tree12.jpg', 'Has not been cut.', '14', 'jo@gmail.com'),"+
          	        	"('15', '17.3', '7.2', 'Back Yard', '12.8', 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', 'Has not been cut.', '15', 'wallace@gmail.com'),"+
          	        	"('16', '14.8', '6.0', 'Front Yard', '10.7', 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', 'Has not been cut.', '16', 'amelia@gmail.com'),"+
          	        	"('17', '9.5', '4.1', 'Side Yard', '7.3', 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', 'Has not been cut.', '17', 'sophie@gmail.com'),"+
          	        	"('18', '11.9', '5.6', 'Back Yard', '11.2', 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', 'Has not been cut.', '18', 'angelo@gmail.com'),"+
          	        	"('19', '13.2', '6.8', 'Front Yard', '10.9', 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', 'Has not been cut.', '19', 'rudy@gmail.com'),"+
          	        	"('20', '18.5', '7.7', 'Side Yard', '13.5', 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', 'Has not been cut.', '20', 'jeannette@gmail.com'),"+
          	        	"('21', '13.5', '7.4', 'Side Yard', '12.5', 'tree31.jpg', 'tree32.jpg', 'tree33.jpg', 'Has not been cut.', '21', 'prospective@gmail.com');")
          				};

        String[] TUPLES4 = {("INSERT INTO QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email)"+
                "VALUES('1', '53.00', '2023-01-15 to 2023-02-05', '1', 'susie@gmail.com'),"+
                "('2', '533.00', '2023-02-20 to 2023-03-24', '2', 'don@gmail.com'),"+
                "('3', '232.0', '2023-03-10 to 2023-4-10', '3', 'margarita@gmail.com'),"+
                "('4', '232.0', '2023-04-05 to 2023-05-13', '4', 'jo@gmail.com'),"+
                "('5', '900.0', '2023-05-30 to 2023-06-17', '5', 'wallace@gmail.com'),"+
                "('6', '242.0', '2023-06-15 to 2023-07-13', '6', 'amelia@gmail.com'),"+
                "('7', '535.0', '2023-07-20 to 2023-08-13', '7', 'sophie@gmail.com'),"+
                "('8', '444.0', '2023-08-10 to 2023-09-13', '8', 'angelo@gmail.com'),"+
                "('9', '442.0', '2023-09-05 to 2023-10-24', '9', 'rudy@gmail.com'),"+
                "('10', '378.0', '2023-10-15 to 2023-11-19', '10', 'jeannette@gmail.com');")
        				};
        String[] TUPLES5 = {("INSERT INTO QuoteReject(quoterejectid, quoterejectnote, quoterequestid, email)"+
                "VALUES('1', 'I heard this customer was difficult to work with.', '11', 'susie@gmail.com'),"+
                "('2', 'Staggeringly high price for the job.', '12', 'don@gmail.com'),"+
                "('3', 'Waited outside front door for 25 minutes.', '13', 'margarita@gmail.com'),"+
                "('4', 'I heard you do not take this seriously', '14', 'jo@gmail.com'),"+
                "('5', 'Heard from other contractors to avoid you. Sorry!', '15', 'wallace@gmail.com'),"+
                "('6', 'Price was way too high.', '16', 'amelia@gmail.com'),"+
                "('7', 'I heard from my contractor friends that you were one to avoid', '17', 'sophie@gmail.com'),"+
                "('8', 'I waited outside your door at the scheduled time for 30 minutes!', '18', 'angelo@gmail.com'),"+
                "('9', 'Price was way too high!', '19', 'rudy@gmail.com'),"+
                "('10', 'Staggeringly high price for the job!', '20', 'jeannette@gmail.com');")
        				};
        
        String[] TUPLES6 = {("INSERT INTO OrderOfWork(orderofworkid, orderofworknote, quoteresponseid, email)"+
                "VALUES('1', 'Agreed! Let us do it!', '1', 'susie@gmail.com'),"+
                "('2', 'I like your quote response.', '2', 'don@gmail.com'),"+
                "('3', 'I approve your quote response!', '3', 'margarita@gmail.com'),"+
                "('4', 'Thank you for your quote response.', '4', 'jo@gmail.com'),"+
                "('5', 'This quote response feels great, like a three-pointer in basketball!', '5', 'wallace@gmail.com'),"+
                "('6', 'This quote response feels like a well-placed serve in volleyball: great!', '6', 'amelia@gmail.com'),"+
                "('7', 'I approve your quote response.', '7', 'sophie@gmail.com'),"+
                "('8', 'I like your quote response!', '8', 'angelo@gmail.com'),"+
                "('9', 'I agree with your quote response, let us do it!', '9', 'rudy@gmail.com'),"+
                "('10', 'Thank you for your quote response.', '10', 'jeannette@gmail.com');")
        				};
        
        String[] TUPLES7 = {("INSERT INTO BillRequest(billrequestid, billnote, billamount, timegenerated, orderofworkid, email)"+
        		"VALUES('1', 'The bill for tree trimming in the front yard is $53', '53.0', '2023-02-05 15:39:52', '1', 'susie@gmail.com'),"+
                "('2', 'The bill for tree removal in the back yard is $533.', '533.0', '2023-03-24 15:39:52', '2', 'don@gmail.com'),"+
                "('3', 'Bill for tree maintenance in the side yard is $232.', '232.0', '2023-4-10 15:39:52', '3', 'margarita@gmail.com'),"+
                "('4', 'Bill for tree pruning in the front yard is $232.', '232.0', '2023-05-13 15:39:52', '4', 'jo@gmail.com'),"+
                "('5', 'Bill for tree removal and cleanup in the back yard is $900.', '900.0', '2023-06-17 15:39:52', '5', 'wallace@gmail.com'),"+
                "('6', 'Bill for tree trimming in the front yard is $242.', '242.0', '2023-07-13 15:39:52', '6', 'amelia@gmail.com'),"+
                "('7', 'Bill for tree maintenance in the side yard is $535.', '535.0', '2023-08-13 15:39:52', '7', 'sophie@gmail.com'),"+
                "('8', 'Bill for tree removal and cleanup in the back yard is $444.', '444.0', '2023-09-13 15:39:52', '8', 'angelo@gmail.com'),"+
                "('9', 'Your concern is unfounded; bill for tree trimming in the front yard is $442.', '442.0', '2023-10-24 15:39:52', '9', 'rudy@gmail.com'),"+
                "('10', 'Discount for bill for tree maintenance in the side yard is $34; bill is $344.', '344.0', '2023-11-26 15:39:52', '10', 'jeannette@gmail.com');")
                };
        String[] TUPLES8 = {("INSERT INTO ReportOfRevenue(reportofrevenueid, paymentamount, timepaid, billrequestid, email)"+
                "VALUES ('1', '53.0', '2023-02-05 23:39:52', '1', 'susie@gmail.com'),"+
                "('2', '533.0', '2023-03-25 01:39:52', '2', 'don@gmail.com'),"+
                "('3', '232.0', '2023-4-14 15:39:52', '3', 'margarita@gmail.com'),"+
                "('4', '232.0', '2023-05-22 15:39:52', '4', 'jo@gmail.com'),"+
                "('5', '900.0', '2023-06-30 15:39:52', '5', 'wallace@gmail.com');")
    					};
        String[] TUPLES9 = {("INSERT INTO BillReject(billrejectid, billrejectnote, billrequestid, email)"+
                "VALUES ('1', 'Never mind, I found another contractor.', '6', 'amelia@gmail.com'),"+
                "('2', 'Price is too high, do not want to negotiate.', '7', 'sophie@gmail.com'),"+
                "('3', 'A friend said David Smith did a shoddy job.', '8', 'angelo@gmail.com'),"+
                "('4', 'A friend recommended another cheaper contractor, and I am taking it.', '9', 'rudy@gmail.com'),"+
                "('5', 'Price is too high, do not want to negotiate.', '10', 'jeannette@gmail.com');")
    					};
        		
        String[] FOREIGNKEYS = {
        	    "ALTER TABLE Tree " +
        	    "ADD CONSTRAINT fk_Tree_QuoteRequest " +
        	    "FOREIGN KEY (quoterequestid) " +
        	    "REFERENCES QuoteRequest (quoterequestid);",
        	    "ALTER TABLE QuoteResponse " +
        	    "ADD CONSTRAINT fk_QuoteResponse_QuoteRequest " +
        	    "FOREIGN KEY (quoterequestid) " +
        	    "REFERENCES QuoteRequest (quoterequestid);",
        	    "ALTER TABLE QuoteReject " +
        	    "ADD CONSTRAINT fk_QuoteReject_QuoteRequest " +
        	    "FOREIGN KEY (quoterequestid) " +
        	    "REFERENCES QuoteRequest (quoterequestid);",
        	    "ALTER TABLE OrderOfWork " +
        	    "ADD CONSTRAINT fk_OrderOfWork_QuoteResponse " +
        	    "FOREIGN KEY (quoteresponseid) " +
        	    "REFERENCES QuoteResponse (quoteresponseid);",
        	    "ALTER TABLE BillRequest " +
        	    "ADD CONSTRAINT fk_BillRequest_OrderOfWork " +
        	    "FOREIGN KEY (orderofworkid) " +
        	    "REFERENCES OrderOfWork (orderofworkid);",
        	    "ALTER TABLE ReportOfRevenue " +
        	    "ADD CONSTRAINT fk_ReportOfRevenue_BillRequest " +
        	    "FOREIGN KEY (billrequestid) " +
        	    "REFERENCES BillRequest (billrequestid);",
        	    "ALTER TABLE BillReject " +
        	    "ADD CONSTRAINT fk_BillReject_BillRequest " +
        	    "FOREIGN KEY (billrequestid) " +
        	    "REFERENCES BillRequest (billrequestid);"
        	};
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        for (int i = 0; i < TUPLES2.length; i++)	
        	statement.execute(TUPLES2[i]);
        for (int i = 0; i < TUPLES3.length; i++)	
        	statement.execute(TUPLES3[i]);
       for (int i = 0; i < TUPLES4.length; i++)	
        	statement.execute(TUPLES4[i]);
       for (int i = 0; i < TUPLES5.length; i++)	
        	statement.execute(TUPLES5[i]);
        for (int i = 0; i < TUPLES6.length; i++)	
        	statement.execute(TUPLES6[i]);
        for (int i = 0; i < TUPLES7.length; i++)	
        	statement.execute(TUPLES7[i]);
        for (int i = 0; i < TUPLES8.length; i++)	
        	statement.execute(TUPLES8[i]);
        for (int i = 0; i < TUPLES9.length; i++)	
        	statement.execute(TUPLES9[i]);
        for (int i = 0; i < FOREIGNKEYS.length; i++)	
        	statement.execute(FOREIGNKEYS[i]);
        disconnect();
    }
}