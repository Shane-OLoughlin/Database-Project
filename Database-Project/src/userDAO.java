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
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");

             
            user users = new user(email,firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code, cash_bal,PPS_bal);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code,cash_bal,PPS_bal) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAdress_street_num());		
			preparedStatement.setString(7, users.getAdress_street());		
			preparedStatement.setString(8, users.getAdress_city());		
			preparedStatement.setString(9, users.getAdress_state());		
			preparedStatement.setString(10, users.getAdress_zip_code());		
			preparedStatement.setInt(11, users.getCash_bal());		
			preparedStatement.setInt(12, users.getPPS_bal());		

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
        String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());		
		preparedStatement.setString(7, users.getAdress_street());		
		preparedStatement.setString(8, users.getAdress_city());		
		preparedStatement.setString(9, users.getAdress_state());		
		preparedStatement.setString(10, users.getAdress_zip_code());		
		preparedStatement.setInt(11, users.getCash_bal());		
		preparedStatement.setInt(12, users.getPPS_bal());
         
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
            String birthday = resultSet.getString("birthday");
            String adress_street_num = resultSet.getString("adress_street_num"); 
            String adress_street = resultSet.getString("adress_street"); 
            String adress_city = resultSet.getString("adress_city"); 
            String adress_state = resultSet.getString("adress_state"); 
            String adress_zip_code = resultSet.getString("adress_zip_code"); 
            int cash_bal = resultSet.getInt("cash_bal");
            int PPS_bal = resultSet.getInt("PPS_bal");
            user = new user(email, firstName, lastName, password, birthday, adress_street_num,  adress_street,  adress_city,  adress_state,  adress_zip_code,cash_bal,PPS_bal);
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
					            "birthday DATE NOT NULL, " +
					            "adress_street_num VARCHAR(4) , "+ 
					            "adress_street VARCHAR(30) , "+ 
					            "adress_city VARCHAR(20)," + 
					            "adress_state VARCHAR(2),"+ 
					            "adress_zip_code VARCHAR(5),"+ 
					            "cash_bal DECIMAL(13,2),"+ 
					            "PPS_bal DECIMAL(13,2),"+
					            "PRIMARY KEY (PPS_bal)" +
					        ");",
					        "DROP TABLE IF EXISTS QuoteRequest;",
					        "CREATE TABLE IF NOT EXISTS QuoteRequest ( " +
					            "quoterequestid INTEGER NOT NULL, " +
					            "quotenote VARCHAR(1000), " +
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
					            "PRIMARY KEY (treeid), " +
					            "FOREIGN KEY (quoterequestid) REFERENCES QuoteRequest (quoterequestid)" +
					        ");",
					        "DROP TABLE IF EXISTS DavidSmith;",
					        "CREATE TABLE IF NOT EXISTS DavidSmith ( " +
					            "davidsmithid INTEGER NOT NULL, " +
					            "PRIMARY KEY (davidsmithid)" +
					        ");",
					        "DROP TABLE IF EXISTS ReportOfRevenue;",
					        "CREATE TABLE IF NOT EXISTS ReportOfRevenue ( " +
					            "reportofrevenueid INTEGER NOT NULL, " +
					            "report VARCHAR(1000), " +
					            "davidsmithid INTEGER NOT NULL, " +
					            "PRIMARY KEY (reportofrevenueid), " +
					            "FOREIGN KEY (davidsmithid) REFERENCES DavidSmith (davidsmithid)" +
					        ");",
					        "DROP TABLE IF EXISTS QuoteResponse;",
					        "CREATE TABLE IF NOT EXISTS QuoteResponse ( " +
					            "quoteresponseid INTEGER NOT NULL, " +
					            "initialprice DOUBLE, " +
					            "timewindow VARCHAR(100), " +
					            "PRIMARY KEY (quoteresponseid)" +
					        ");",
					        "DROP TABLE IF EXISTS Respond;",
					        "CREATE TABLE IF NOT EXISTS Respond ( " +
					            "davidsmithid INTEGER NOT NULL, " +
					            "quotereponseid INTEGER NOT NULL, " +
					            "PRIMARY KEY (davidsmithid, quotereponseid) " +
					        ");",
					        "DROP TABLE IF EXISTS OrderOfWork;",
					        "CREATE TABLE IF NOT EXISTS OrderOfWork ( " +
					            "orderofworkid INTEGER NOT NULL, " +
					            "work VARCHAR(1000), " +
					            "PRIMARY KEY (orderofworkid)" +
					        ");",
					        "DROP TABLE IF EXISTS Accept;",
					        "CREATE TABLE IF NOT EXISTS Accept ( " +
					            "PPS_bal DECIMAL(13,2) NOT NULL, " +
					            "quoteresponseid INTEGER NOT NULL, " +
					            "orderofworkid INTEGER NOT NULL, " +
					            "PRIMARY KEY (PPS_bal, quoteresponseid, orderofworkid) " +
					        ");",
					        "DROP TABLE IF EXISTS BillRequest;",
					        "CREATE TABLE IF NOT EXISTS BillRequest ( " +
					            "billrequestid INTEGER NOT NULL, " +
					            "billnote VARCHAR(1000), " +
					            "orderofworkid INTEGER NOT NULL, " +
					            "PRIMARY KEY (billrequestid), " +
					            "UNIQUE (billrequestid)" +
					        ");",
					        "DROP TABLE IF EXISTS Send;",
					        "CREATE TABLE IF NOT EXISTS Send ( " +
					            "PPS_bal DECIMAL(13,2) NOT NULL, " +
					            "billrequestid INTEGER NOT NULL, " +
					            "PRIMARY KEY (PPS_bal, billrequestid) " +
					        ");",
					        "DROP TABLE IF EXISTS Pay;",
					        "CREATE TABLE IF NOT EXISTS Pay ( " +
					            "PPS_bal DECIMAL(13,2) NOT NULL, " +
					            "billrequestid INTEGER NOT NULL, " +
					            "PRIMARY KEY (PPS_bal, billrequestid) " +
					        ");",
					        "DROP TABLE IF EXISTS RejectBill;",
					        "CREATE TABLE IF NOT EXISTS RejectBill ( " +
					            "PPS_bal DECIMAL(13,2) NOT NULL, " +
					            "billrequestid INTEGER NOT NULL, " +
					            "clientnote VARCHAR(1000), " +
					            "PRIMARY KEY (PPS_bal, billrequestid) " +
					        ");"
					    };
        
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, birthday, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code, cash_bal, PPS_bal)"+
    			"values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', '1234', 'whatever street', 'detroit', 'MI', '48202','123', '1'),"+
		    		 	"('don@gmail.com', 'Don', 'Cummings','don123', '1969-03-20', '1000', 'hi street', 'mama', 'MO', '12345','124', '2'),"+
		    	 	 	"('margarita@gmail.com', 'Margarita', 'Lawson','margarita1234', '1980-02-02', '1234', 'ivan street', 'tata','CO','12561','234', '3'),"+
		    		 	"('jo@gmail.com', 'Jo', 'Brady','jo1234', '2002-02-02', '3214','marko street', 'brat', 'DU', '54321','567', '4'),"+
		    		 	"('wallace@gmail.com', 'Wallace', 'Moore','wallace1234', '1971-06-15', '4500', 'frey street', 'sestra', 'MI', '48202','899', '5'),"+
		    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '2000-03-14', '1245', 'm8s street', 'baka', 'IL', '48000','990', '6'),"+
		    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '1999-06-15', '2468', 'yolos street', 'ides', 'CM', '24680','453', '7'),"+
		    			"('angelo@gmail.com', 'Angelo', 'Francis','angelo1234', '2021-06-14', '4680', 'egypt street', 'lolas', 'DT', '13579','290', '8'),"+
		    			"('rudy@gmail.com', 'Rudy', 'Smith','rudy1234', '1706-06-05', '1234', 'sign street', 'samo ne tu','MH', '09876','900', '9'),"+
		    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '2001-04-24', '0981', 'snoop street', 'kojik', 'HW', '87654','944', '10'),"+
		    			"('dsmith@gmail.com', 'David ', 'Smith','david1234', '2001-05-24', '0941', 'snoop street', 'kojik', 'HW', '87644','934', '12'),"+
		    			"('root', 'default', 'default','pass1234', '1111-11-11', '0000', 'Default', 'Default', '0', '00000','983','11');")
		    			};
        
        String[] TUPLES2 = {("insert into QuoteRequest(quoterequestid, quotenote)"+
    			"values ('1', 'Make sure all branches are removed.'),"+
		    		 	"('2', 'Move date to 9/23/2024.'),"+
		    	 	 	"('3', 'Make the price $149.'),"+
		    		 	"('4', 'Avoid dog. He bites.'),"+
		    		 	"('5', 'Ring doorbell when you are here.'),"+
		    		 	"('6', 'Make the price $260.'),"+
		    			"('7', 'Move date to 2/27/2024.'),"+
		    			"('8', 'Knock when you are here.'),"+
		    			"('9', 'Make the price $181.'),"+
		    			"('10', 'Move date to 1/03/2025.');")
		    			};
        
        String[] TUPLES3 = {("INSERT INTO Tree(treeid, size, height, location, proximitytohouse, picture1, picture2, picture3, quoterequestid)"+
        		 "values ('1', '15.5', '6.2', 'Front Yard', '10.3', 'tree1.jpg', 'tree2.jpg', 'tree3.jpg', '1'),"+
        	        	"('2', '10.2', '4.8', 'Back Yard', '5.7', 'tree4.jpg', 'tree5.jpg', 'tree6.jpg', '2'),"+
        	        	"('3', '8.7', '3.5', 'Side Yard', '8.1', 'tree7.jpg', 'tree8.jpg', 'tree9.jpg', '3'),"+
        	        	"('4', '12.1', '5.9', 'Front Yard', '9.4', 'tree10.jpg', 'tree11.jpg', 'tree12.jpg', '4'),"+
        	        	"('5', '17.3', '7.2', 'Back Yard', '12.8', 'tree13.jpg', 'tree14.jpg', 'tree15.jpg', '5'),"+
        	        	"('6', '14.8', '6.0', 'Front Yard', '10.7', 'tree16.jpg', 'tree17.jpg', 'tree18.jpg', '6'),"+
        	        	"('7', '9.5', '4.1', 'Side Yard', '7.3', 'tree19.jpg', 'tree20.jpg', 'tree21.jpg', '7'),"+
        	        	"('8', '11.9', '5.6', 'Back Yard', '11.2', 'tree22.jpg', 'tree23.jpg', 'tree24.jpg', '8'),"+
        	        	"('9', '13.2', '6.8', 'Front Yard', '10.9', 'tree25.jpg', 'tree26.jpg', 'tree27.jpg', '9'),"+
        	        	"('10', '18.5', '7.7', 'Side Yard', '13.5', 'tree28.jpg', 'tree29.jpg', 'tree30.jpg', '10');")
        				};
        
        String[] TUPLES4 = {("INSERT INTO DavidSmith(davidsmithid)"+
                "VALUES ('1'),"+
                "('2'),"+
                "('3'),"+
                "('4'),"+
                "('5'),"+
                "('6'),"+
                "('7'),"+
                "('8'),"+
                "('9'),"+
                "('10');")
    					};
        String[] TUPLES5 = {("INSERT INTO ReportOfRevenue(reportofrevenueid, report, davidsmithid)"+
                "VALUES ('1', 'Generated $500', '1'),"+
                "('2', 'Generated $1500', '2'),"+
                "('3', 'Generated $3000', '3'),"+
                "('4', 'Generated $800', '4'),"+
                "('5', 'Generated $10000', '5'),"+
                "('6', 'Generated $1700', '6'),"+
                "('7', 'Generated $8000', '7'),"+
                "('8', 'Generated $6300', '8'),"+
                "('9', 'Generated $5500', '9'),"+
                "('10', 'Generated $5000', '10');")
    					};
        String[] TUPLES6 = {("INSERT INTO Respond(davidsmithid, quotereponseid)"+
                "VALUES ('1', '1'),"+
                "('2', '2'),"+
                "('3', '3'),"+
                "('4', '4'),"+
                "('5', '5'),"+
                "('6', '6'),"+
                "('7', '7'),"+
                "('8', '8'),"+
                "('9', '9'),"+
                "('10', '10');")
        				};
        String[] TUPLES7 = {("INSERT INTO QuoteResponse(quoteresponseid, initialprice, timewindow)"+
                "VALUES('1', '150.00', '2024-01-15'),"+
                "('2', '170.00', '2024-02-20'),"+
                "('3', '140.00', '2024-03-10'),"+
                "('4', '160.00', '2024-04-05'),"+
                "('5', '180.00', '2024-05-30'),"+
                "('6', '175.00', '2024-06-15'),"+
                "('7', '155.00', '2024-07-20'),"+
                "('8', '165.00', '2024-08-10'),"+
                "('9', '185.00', '2024-09-05'),"+
                "('10', '170.00', '2024-10-15');")
        				};
        
        String[] TUPLES8 = {("INSERT INTO OrderOfWork(orderofworkid, work)"+
                "VALUES('1', 'Trim and prune trees in the front yard.'),"+
                "('2', 'Remove dead branches and trim trees in the back yard.'),"+
                "('3', 'Tree maintenance for side yard trees.'),"+
                "('4', 'Tree trimming and shaping in the front yard.'),"+
                "('5', 'Tree removal in the back yard.'),"+
                "('6', 'Tree pruning in the front yard.'),"+
                "('7', 'Tree maintenance for side yard trees.'),"+
                "('8', 'Tree removal and cleanup in the back yard.'),"+
                "('9', 'Tree trimming and shaping in the front yard.'),"+
                "('10', 'Tree maintenance for side yard trees.');")
        				};
        
        String[] TUPLES9 = {("INSERT INTO Accept(PPS_bal, quoteresponseid, orderofworkid)"+
                "VALUES('1', '1', '1'),"+
                "('2', '2', '2'),"+
                "('3', '3', '3'),"+
                "('4', '4', '4'),"+
                "('5', '5', '5'),"+
                "('6', '6', '6'),"+
                "('7', '7', '7'),"+
                "('8', '8', '8'),"+
                "('9', '9', '9'),"+
                "('10', '10', '10');")
        				};
        
        String[] TUPLES10 = {("INSERT INTO BillRequest(billrequestid, billnote, orderofworkid)"+
        		"VALUES('1', 'The bill for tree trimming in the front yard is $53', '1'),"+
                "('2', 'The bill for tree removal in the back yard is $533', '2'),"+
                "('3', 'Bill for tree maintenance in the side yard is $232', '3'),"+
                "('4', 'Bill for tree pruning in the front yard is $232.', '4'),"+
                "('5', 'Bill for tree removal and cleanup in the back yard is $900.', '5'),"+
                "('6', 'Bill for tree trimming in the front yard is $242.', '6'),"+
                "('7', 'Bill for tree maintenance in the side yard is $535.', '7'),"+
                "('8', 'Bill for tree removal and cleanup in the back yard is $444.', '8'),"+
                "('9', 'Your concern is unfounded; bill for tree trimming in the front yard is $442.', '9'),"+
                "('10', 'Discount for bill for tree maintenance in the side yard is $34; bill is $344.', '10');")
                };
        		
        String[] TUPLES11 = {("INSERT INTO Send(PPS_bal, billrequestid)"+
                "VALUES('1', '1'),"+
                "('2', '2'),"+
                "('3', '3'),"+
                "('4', '4'),"+
                "('5', '5'),"+
                "('6', '6'),"+
                "('7', '7'),"+
                "('8', '8'),"+
                "('9', '9'),"+
                "('10', '10');")
        				};
        
        String[] TUPLES12 = {("INSERT INTO Pay(PPS_bal, billrequestid)"+
                "VALUES('1', '1'),"+
                "('2', '2'),"+
                "('3', '3'),"+
                "('4', '4'),"+
                "('5', '5'),"+
                "('6', '6'),"+
                "('7', '7'),"+
                "('8', '8'),"+
                "('9', '9'),"+
                "('10', '10');")
        				};

        String[] TUPLES13 = {("INSERT INTO RejectBill(PPS_bal, billrequestid, clientnote)"+
        		"VALUES('1', '1', 'Bill is incorrect.'),"+
                		"('2', '2', 'Service was not satisfactory.'),"+
                		"('3', '3', 'Overcharged for the work.'),"+
                		"('4', '4', 'Not what was expected.'),"+
                		"('5', '5', 'Did not complete the job.'),"+
                		"('6', '6', 'Bill does not match the quote.'),"+
                		"('7', '7', 'Not happy with the service.'),"+
                		"('8', '8', 'Quality of work is poor.'),"+
                		"('9', '9', 'Disputed charges on the bill.'),"+
        				"('10', '10', 'Work was incomplete.');")
        				};
        String[] FOREIGNKEYS = {"ALTER TABLE Accept " +
			            "ADD CONSTRAINT fk_Accept_User " +
			            "FOREIGN KEY (PPS_bal) " +
			            "REFERENCES User (PPS_bal);",
			            "ALTER TABLE Accept " +
			            "ADD CONSTRAINT fk_Accept_QuoteResponse " +
			            "FOREIGN KEY (quoteresponseid) " +
			            "REFERENCES QuoteResponse (quoteresponseid);",
			            "ALTER TABLE Accept " +
			            "ADD CONSTRAINT fk_Accept_OrderOfWork " +
			            "FOREIGN KEY (orderofworkid) " +
			            "REFERENCES OrderOfWork (orderofworkid);",
			            "ALTER TABLE Respond " +
        "ADD CONSTRAINT fk_Respond_DavidSmith " +
        "FOREIGN KEY (davidsmithid) " +
        "REFERENCES DavidSmith (davidsmithid);",
        "ALTER TABLE Respond " +
        "ADD CONSTRAINT fk_Respond_QuoteResponse " +
        "FOREIGN KEY (quotereponseid) " +
        "REFERENCES QuoteResponse (quoteresponseid);",
        "ALTER TABLE Send " +
        "ADD CONSTRAINT fk_Send_User " +
        "FOREIGN KEY (PPS_bal) " +
        "REFERENCES User (PPS_bal);",
        "ALTER TABLE Send " +
        "ADD CONSTRAINT fk_Send_BillRequest " +
        "FOREIGN KEY (billrequestid) " +
        "REFERENCES BillRequest (billrequestid);",
        "ALTER TABLE Pay " +
        "ADD CONSTRAINT fk_Pay_User " +
        "FOREIGN KEY (PPS_bal) " +
        "REFERENCES User (PPS_bal);",
        "ALTER TABLE Pay " +
        "ADD CONSTRAINT fk_Pay_BillRequest " +
        "FOREIGN KEY (billrequestid) " +
        "REFERENCES BillRequest (billrequestid);",
        "ALTER TABLE RejectBill " +
        "ADD CONSTRAINT fk_RejectBill_User " +
        "FOREIGN KEY (PPS_bal) " +
        "REFERENCES User (PPS_bal);",
        "ALTER TABLE RejectBill " +
        "ADD CONSTRAINT fk_RejectBill_BillRequest " +
        "FOREIGN KEY (billrequestid) " +
        "REFERENCES BillRequest (billrequestid);",
        "ALTER TABLE BillRequest " +
        "ADD CONSTRAINT fk_BillRequest_OrderOfWork " +
        "FOREIGN KEY (orderofworkid) " +
        "REFERENCES OrderOfWork (orderofworkid);"};
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
        for (int i = 0; i < TUPLES10.length; i++)	
        	statement.execute(TUPLES10[i]);
        for (int i = 0; i < TUPLES11.length; i++)	
        	statement.execute(TUPLES11[i]);
        for (int i = 0; i < TUPLES12.length; i++)	
        	statement.execute(TUPLES12[i]);
        for (int i = 0; i < TUPLES13.length; i++)	
        	statement.execute(TUPLES13[i]);
        for (int i = 0; i < FOREIGNKEYS.length; i++)	
        	statement.execute(FOREIGNKEYS[i]);
        disconnect()
    }
}