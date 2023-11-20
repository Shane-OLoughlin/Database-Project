public class ReportOfRevenue {
    protected int reportofrevenueid;
    protected double paymentamount;
    protected String timepaid;
    protected int billrequestid;
    protected String email;
	 
    //constructors
    public ReportOfRevenue() {
    }
 
    public ReportOfRevenue(int reportofrevenueid, double paymentamount, String timepaid, int billrequestid, String email) 
    {
    	this.reportofrevenueid = reportofrevenueid;
        this.paymentamount = paymentamount;
        this.timepaid = timepaid;
        this.billrequestid = billrequestid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getReportOfRevenueID() {
        return reportofrevenueid;
    }
    public void setReportOfRevenueID(int reportofrevenueid) {
        this.reportofrevenueid = reportofrevenueid;
    }
    public double getPaymentAmount() {
        return paymentamount;
    }
    public void setPaymentAmount(double paymentamount) {
        this.paymentamount = paymentamount;
    }
    public String getTimePaid() {
        return timepaid;
    }
    public void setTimePaid(String timepaid) {
        this.timepaid = timepaid;
    }
    public int getBillRequestID() {
        return billrequestid;
    }
    public void setBillRequestID(int billrequestid) {
        this.billrequestid = billrequestid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}