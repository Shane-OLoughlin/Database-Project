public class BillRequest {
    protected int billrequestid;
    protected String billnote;
    protected double billamount;
    protected String timegenerated;
    protected int orderofworkid;
    protected String email;
	 
    //constructors
    public BillRequest() {
    }
 
    public BillRequest(int billrequestid, String billnote, double billamount, String timegenerated, int orderofworkid, String email) 
    {
    	this.billrequestid = billrequestid;
    	this.billnote = billnote;
    	this.billamount = billamount;
    	this.timegenerated = timegenerated;
    	this.orderofworkid = orderofworkid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getBillRequestID() {
        return billrequestid;
    }
    public void setBillRequestID(int billrequestid) {
        this.billrequestid = billrequestid;
    }
    public String getBillNote() {
        return billnote;
    }
    public void setBillNote(String billnote) {
        this.billnote = billnote;
    }
    public double getBillAmount() {
        return billamount;
    }
    public void setBillAmount(double billamount) {
        this.billamount = billamount;
    }
    public String getTimeGenerated() {
        return timegenerated;
    }
    public void setTimeGenerated(String timegenerated) {
        this.timegenerated = timegenerated;
    }
    public int getOrderOfWorkID() {
        return orderofworkid;
    }
    public void setOrderOfWorkID(int orderofworkid) {
        this.orderofworkid = orderofworkid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}