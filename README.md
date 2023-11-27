# Database-Project
---Part 1 beginning---
Hours individually:
Shane: 10
Rajeev: 10

Hours together:
7

We split 1 (Rajeev did E-R model and Shane did the relational model). We worked together on 2 (Rajeev: changed names of columns of working User table to reflect Project specifications, also typed Part 1 README) and worked together on 3, which Shane mostly implemented (Shane: redirect to David Smith page on login, created tables). We spent some time getting the GitHub page up too. Both recorded video for Part 1 on Rajeev's computer, and Rajeev made and then submitted final PDF for Part 1 from scratch. 

For installing, configuring and running project:

Install Eclipse, download zip of Database-Project from our GitHub, extract into eclipse-workspace folder, use File -> Import   -> Project from Folder or Archive in Eclipse, configure Java Compiler and Project Facets if needed, switch passwords in userDAO for user john if necessary, to run: right click on jsp page and Run As -> Run on Server, make further changes if desired to make it run differently.

--- Part 1 end ---

--- Part 2 beginning ---

Hours individually:
Shane: 25 
Rajeev: 18

Hours together: 23

Rajeev: did Part 2 README, implemented working clientID for User table, implemented working Quote Request ID for Quote Requests table for David Smith, implemented time-window initializations for the Quote Reponse table in UserDAO.java, made changes to David Smith page to implement reject and response links for each quote, got reject button to partially work and respond button to partially work. Once all the tables were implemented, cleaned up data for all necessary tables to make everything coherent.  

Shane: I updated the database tables, listed all the tables on the client and David Smith views, and implemented all the responding functionalities.

How to install, configure and run project at time of 11/16: Install Eclipse, download zip of Database-Project from our GitHub, extract into eclipse-workspace folder, use File -> Import   -> Project from Folder or Archive in Eclipse, configure Java Compiler and Project Facets if needed, switch passwords in userDAO for user john if necessary, to run: right click on jsp page and Run As -> Run on Server, make further changes if desired to make it run differently.

--- Part 2 end ----
---Part 3 beginning----

Hours individually:
Shane: 8 
Rajeev: 2

Hours together: 15

Rajeev's contributions: Drew up plan and document for submission, consulted with implementation of some problems, scripted first draft for recording, realized that we had it wrong, then directed final draft of recording.

Shane's contributions: physically wrote down the code for the functions for the lists for part 3 while receiving input from Rajeev for some problems and doing remaining problems by himself. 

How to install, configure and run project: Install Eclipse, download zip of Database-Project from our GitHub, extract into eclipse-workspace folder, use File -> Import   -> Project from Folder or Archive in Eclipse, configure Java Compiler and Project Facets if needed, switch passwords in userDAO for user john if necessary, to run: right click on jsp page and Run As -> Run on Server, make further changes if desired to make it run differently.

Problem 1:
No SQL statement for problem one
Problem 2:
"SELECT u.*, COUNT(DISTINCT t.treeid) AS tree_count " +
                "FROM User u " +
                "LEFT JOIN Tree t ON u.email = t.email " +
                "WHERE t.datecut != 'Has not been cut.' " +
                "GROUP BY u.clientid " +
                "HAVING tree_count = (SELECT MAX(tree_count) FROM (SELECT COUNT(DISTINCT treeid) AS tree_count FROM Tree WHERE datecut != 'Has not been cut.' GROUP BY email) AS temp)"
Problem 3:
"SELECT DISTINCT u.* " +
                     "FROM User u " +
                     "WHERE NOT EXISTS " +
                     "    (SELECT 1 " +
                     "     FROM QuoteRequest qr " +
                     "     WHERE qr.email = u.email AND qr.negotiations = 'yes')";
Problem 4:
"SELECT DISTINCT qr.* " +
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
Problem 5:
"SELECT u.* " +
                     "FROM User u " +
                     "JOIN QuoteRequest qr ON u.email = qr.email " +
                     "WHERE NOT EXISTS (SELECT 1 FROM OrderOfWork ow WHERE ow.email = u.email)";
Problem 6:
"SELECT * FROM Tree " +
                "WHERE datecut != 'Has not been cut.' AND height = (SELECT MAX(height) FROM Tree WHERE datecut != 'Has not been cut.')";
Problem 7:
"SELECT * FROM BillRequest br " +
                     "LEFT JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE rr.billrequestid IS NULL " +
                     "AND TIMESTAMPDIFF(WEEK, br.timegenerated, NOW()) > 1";
Problem 8:
"SELECT DISTINCT u.* " +
                     "FROM User u " +
                     "JOIN BillRequest br ON u.email = br.email " +
                     "LEFT JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE TIMESTAMPDIFF(WEEK, br.timegenerated, NOW()) > 1 " +
                     "      AND rr.billrequestid IS NULL";
Problem 9:
"SELECT u.email, u.firstName, u.lastName, u.password, u.creditcardinfo, " +
                     "u.adress_street_num, u.adress_street, u.adress_city, u.adress_state, " +
                     "u.adress_zip_code, u.phonenumber, u.clientid " +
                     "FROM User u " +
                     "JOIN BillRequest br ON u.email = br.email " +
                     "JOIN ReportOfRevenue rr ON br.billrequestid = rr.billrequestid " +
                     "WHERE TIMESTAMPDIFF(HOUR, br.timegenerated, rr.timepaid) <= 24";
Problem 10:
"SELECT u.email, u.clientid, " +
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






