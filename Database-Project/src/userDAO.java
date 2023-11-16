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
import java.util.List;
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
	    	String email = resultSet.getString("email");
            quoteRequest = new QuoteRequest(quoterequestid, quotenote, email);
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
            int orderofworkid = resultSet.getInt("orderofworkid");
	    	String email = resultSet.getString("email");
            billRequest = new BillRequest(billrequestid, billnote, billamount, orderofworkid, email);
        }
        
        
        resultSet.close();
        disconnect();        
        return billRequest;
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
            String email = resultSet.getString("email");
             
            QuoteRequest quoterequests = new QuoteRequest(quoterequestid, quotenote, email);
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
	    	int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
             
            Tree trees = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, email);
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
            int orderofworkid = resultSet.getInt("orderofworkid");
            String email = resultSet.getString("email");
             
            BillRequest billrequests = new BillRequest(billrequestid, billnote, billamount, orderofworkid, email);
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
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            ReportOfRevenue reportofrevenues = new ReportOfRevenue(reportofrevenueid, paymentamount, billrequestid, email);
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
            String email = resultSet.getString("email");
             
            QuoteRequest quoterequests = new QuoteRequest(quoterequestid, quotenote, email);
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
	    	int quoterequestid = resultSet.getInt("quoterequestid");
	    	String email = resultSet.getString("email");
             
            Tree trees = new Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, email);
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
            int orderofworkid = resultSet.getInt("orderofworkid");
            String email = resultSet.getString("email");
             
            BillRequest billrequests = new BillRequest(billrequestid, billnote, billamount, orderofworkid, email);
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
            int billrequestid = resultSet.getInt("billrequestid");
            String email = resultSet.getString("email");
             
            ReportOfRevenue reportofrevenues = new ReportOfRevenue(reportofrevenueid, paymentamount, billrequestid, email);
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
		String sql = "insert into QuoteRequest(quoterequestid, quotenote, email) values (?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, quoterequests.getQuoteRequestID());
		preparedStatement.setString(2, quoterequests.getQuoteNote());	
		preparedStatement.setString(3, quoterequests.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insert(Tree trees) throws SQLException {      
    	connect_func();
		String sql = "insert into Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, email) values (?,?,?,?,?,?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, trees.getTreeID());
		preparedStatement.setDouble(2, trees.getSize());	
		preparedStatement.setDouble(3, trees.getHeight());
		preparedStatement.setString(4, trees.getLocation());
		preparedStatement.setDouble(5, trees.getproximitytohouse());
		preparedStatement.setString(6, trees.getPicture1());
		preparedStatement.setString(7, trees.getPicture2());
		preparedStatement.setString(8, trees.getPicture3());
		preparedStatement.setInt(9, trees.getQuoteRequestID());
		preparedStatement.setString(10, trees.getEmail());
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
		String sql = "insert into BillRequest(billrequestid, billnote, billamount, orderofworkid, email) values (?,?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, billrequests.getBillRequestID());
		preparedStatement.setString(2, billrequests.getBillNote());	
		preparedStatement.setDouble(3, billrequests.getBillAmount());
		preparedStatement.setInt(4, billrequests.getOrderOfWorkID());
		preparedStatement.setString(5, billrequests.getEmail());
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
		String sql = "insert into ReportOfRevenue(reportofrevenueid, paymentamount, billrequestid, email) values (?,?,?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setInt(1, payments.getReportOfRevenueID());
		preparedStatement.setDouble(2, payments.getPaymentAmount());	
		preparedStatement.setInt(3, payments.getBillRequestID());
		preparedStatement.setString(4, payments.getEmail());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?,creditcardinfo=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, phonenumber=?, clientid =? where email = ?";
        connect_func();
        
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
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
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
            user = new user(email, firstName, lastName, password, creditcardinfo, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,phonenumber,clientid);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
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
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
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
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
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
					            "orderofworkid INTEGER NOT NULL," +
					            "email VARCHAR(50) NOT NULL, " +
					            "PRIMARY KEY (billrequestid)" +
					        ");",
					        "DROP TABLE IF EXISTS ReportOfRevenue;",
					        "CREATE TABLE IF NOT EXISTS ReportOfRevenue ( " +
					            "reportofrevenueid INTEGER NOT NULL, " +
					            "paymentamount DOUBLE NOT NULL," +
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
		    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '1111111111111122', '0981', 'snoop street', 'kojik', 'HW', '87654','3334992992', '10'),"+
		    			"('dsmith@gmail.com', 'David ', 'Smith','david1234', '1111111111111133', '0941', 'snoop street', 'kojik', 'HW', '87644','2232823323', '12'),"+
		    			"('root', 'default', 'default','pass1234', '1111111111111144', '1726', 'Default', 'Default', '0', '00000','1232883883','11');")
		    			};
        
        String[] TUPLES2 = {("insert into QuoteRequest(quoterequestid, quotenote, email)"+
    			"values ('1', 'Make sure all branches are removed.', 'susie@gmail.com'),"+
		    		 	"('2', 'Move date to 9/23/2024.', 'don@gmail.com'),"+
		    	 	 	"('3', 'Make the price $149.', 'margarita@gmail.com'),"+
		    		 	"('4', 'Avoid dog. He bites.', 'jo@gmail.com'),"+
		    		 	"('5', 'Ring doorbell when you are here.', 'wallace@gmail.com'),"+
		    		 	"('6', 'Make the price $260.', 'amelia@gmail.com'),"+
		    			"('7', 'Move date to 2/27/2024.', 'sophie@gmail.com'),"+
		    			"('8', 'Knock when you are here.','angelo@gmail.com'),"+
		    			"('9', 'Make the price $181.', 'rudy@gmail.com'),"+
		    			"('10', 'Move date to 1/03/2025.', 'jeannette@gmail.com'),"+
		    			"('11', 'Ring doorbell when here.', 'susie@gmail.com'),"+
		    			"('12', 'Move date to 9/23/2024.', 'don@gmail.com'),"+
		    			"('13', 'Make price $200.', 'margarita@gmail.com'),"+
		    			"('14', 'Move date to 5/23/2024.', 'jo@gmail.com'),"+
		    			"('15', 'Make price $250.', 'wallace@gmail.com'),"+
		    			"('16', 'Move date to 3/21/2024.', 'amelia@gmail.com'),"+
		    			"('17', 'Make sure all branches are removed, please.', 'sophie@gmail.com'),"+
		    			"('18', 'Move date to 1/12/2024.', 'angelo@gmail.com'),"+
		    			"('19', 'Please knock when you arrive.', 'rudy@gmail.com'),"+
		    			"('20', 'Avoid my dog, she likes to bite strangers.', 'jeannette@gmail.com');")
		    			};
        
        String[] TUPLES3 = {("INSERT INTO Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid, email)"+
          		 "values ('1', '15.5', '6.2', 'Front Yard', '10.3', 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', '1', 'susie@gmail.com'),"+
          	        	"('2', '10.2', '4.8', 'Back Yard', '5.7', 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', '2', 'don@gmail.com'),"+
          	        	"('3', '8.7', '3.5', 'Side Yard', '8.1', 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', '3', 'margarita@gmail.com'),"+
          	        	"('4', '12.1', '5.9', 'Front Yard', '9.4', 'tree10.jpg', 'tree11.jpg', 'tree12.jpg', '4', 'jo@gmail.com'),"+
          	        	"('5', '17.3', '7.2', 'Back Yard', '12.8', 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', '5', 'wallace@gmail.com'),"+
          	        	"('6', '14.8', '6.0', 'Front Yard', '10.7', 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', '6', 'amelia@gmail.com'),"+
          	        	"('7', '9.5', '4.1', 'Side Yard', '7.3', 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', '7', 'sophie@gmail.com'),"+
          	        	"('8', '11.9', '5.6', 'Back Yard', '11.2', 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', '8', 'angelo@gmail.com'),"+
          	        	"('9', '13.2', '6.8', 'Front Yard', '10.9', 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', '9', 'rudy@gmail.com'),"+
          	        	"('10', '18.5', '7.7', 'Side Yard', '13.5', 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', '10', 'jeannette@gmail.com'),"+
          	        	"('11', '15.5', '6.2', 'Front Yard', '10.3', 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', '11', 'susie@gmail.com'),"+
          	        	"('12', '10.2', '4.8', 'Back Yard', '5.7', 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', '12', 'don@gmail.com'),"+
          	        	"('13', '8.7', '3.5', 'Side Yard', '8.1', 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', '13', 'margarita@gmail.com'),"+
          	        	"('14', '12.1', '5.9', 'Front Yard', '9.4', 'tree10.jpg', 'tree11.jpg', 'tree12.jpg', '14', 'jo@gmail.com'),"+
          	        	"('15', '17.3', '7.2', 'Back Yard', '12.8', 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', '15', 'wallace@gmail.com'),"+
          	        	"('16', '14.8', '6.0', 'Front Yard', '10.7', 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', '16', 'amelia@gmail.com'),"+
          	        	"('17', '9.5', '4.1', 'Side Yard', '7.3', 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', '17', 'sophie@gmail.com'),"+
          	        	"('18', '11.9', '5.6', 'Back Yard', '11.2', 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', '18', 'angelo@gmail.com'),"+
          	        	"('19', '13.2', '6.8', 'Front Yard', '10.9', 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', '19', 'rudy@gmail.com'),"+
          	        	"('20', '18.5', '7.7', 'Side Yard', '13.5', 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', '20', 'jeannette@gmail.com');")
          				};

        String[] TUPLES4 = {("INSERT INTO QuoteResponse(quoteresponseid, initialprice, timewindow, quoterequestid, email)"+
                "VALUES('1', '150.00', '2024-01-15 to 2024-02-05', '1', 'susie@gmail.com'),"+
                "('2', '170.00', '2024-02-20 to 2024-03-24', '2', 'don@gmail.com'),"+
                "('3', '140.00', '2024-03-10 to 2024-4-10', '3', 'margarita@gmail.com'),"+
                "('4', '160.00', '2024-04-05 to 2024-05-13', '4', 'jo@gmail.com'),"+
                "('5', '180.00', '2024-05-30 to 2024-06-17', '5', 'wallace@gmail.com'),"+
                "('6', '175.00', '2024-06-15 to 2024-07-13', '6', 'amelia@gmail.com'),"+
                "('7', '155.00', '2024-07-20 to 2024-08-13', '7', 'sophie@gmail.com'),"+
                "('8', '165.00', '2024-08-10 to 2024-09-13', '8', 'angelo@gmail.com'),"+
                "('9', '185.00', '2024-09-05 to 2024-10-24', '9', 'rudy@gmail.com'),"+
                "('10', '170.00', '2024-10-15 to 2024-11-13', '10', 'jeannette@gmail.com');")
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
                "('2', 'I like your quote request.', '2', 'don@gmail.com'),"+
                "('3', 'I approve your quote request!', '3', 'margarita@gmail.com'),"+
                "('4', 'Thank you for your quote request.', '4', 'jo@gmail.com'),"+
                "('5', 'This quote request feels great, like a three-pointer in basketball!', '5', 'wallace@gmail.com'),"+
                "('6', 'This quote request feels like a well-placed serve in volleyball: great!', '6', 'amelia@gmail.com'),"+
                "('7', 'I approve your quote request.', '7', 'sophie@gmail.com'),"+
                "('8', 'I like your quote request!', '8', 'angelo@gmail.com'),"+
                "('9', 'I agree with your quote request, let us do it!', '9', 'rudy@gmail.com'),"+
                "('10', 'Thank you for your quote request.', '10', 'jeannette@gmail.com');")
        				};
        
        String[] TUPLES7 = {("INSERT INTO BillRequest(billrequestid, billnote, billamount, orderofworkid, email)"+
        		"VALUES('1', 'The bill for tree trimming in the front yard is $53', '53.0', '1', 'susie@gmail.com'),"+
                "('2', 'The bill for tree removal in the back yard is $533.', '533.0', '2', 'don@gmail.com'),"+
                "('3', 'Bill for tree maintenance in the side yard is $232.', '232.0', '3', 'margarita@gmail.com'),"+
                "('4', 'Bill for tree pruning in the front yard is $232.', '232.0', '4', 'jo@gmail.com'),"+
                "('5', 'Bill for tree removal and cleanup in the back yard is $900.', '900.0', '5', 'wallace@gmail.com'),"+
                "('6', 'Bill for tree trimming in the front yard is $242.', '242.0', '6', 'amelia@gmail.com'),"+
                "('7', 'Bill for tree maintenance in the side yard is $535.', '535.0', '7', 'sophie@gmail.com'),"+
                "('8', 'Bill for tree removal and cleanup in the back yard is $444.', '444.0', '8', 'angelo@gmail.com'),"+
                "('9', 'Your concern is unfounded; bill for tree trimming in the front yard is $442.', '442.0', '9', 'rudy@gmail.com'),"+
                "('10', 'Discount for bill for tree maintenance in the side yard is $34; bill is $344.', '344.0', '10', 'jeannette@gmail.com');")
                };
        String[] TUPLES8 = {("INSERT INTO ReportOfRevenue(reportofrevenueid, paymentamount, billrequestid, email)"+
                "VALUES ('1', '53.0', '1', 'susie@gmail.com'),"+
                "('2', '533.0', '2', 'don@gmail.com'),"+
                "('3', '232.0', '3', 'margarita@gmail.com'),"+
                "('4', '232.0', '4', 'jo@gmail.com'),"+
                "('5', '900.0', '5', 'wallace@gmail.com');")
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