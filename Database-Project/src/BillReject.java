public class BillReject {
    protected int billrejectid;
    protected String billrejectnote;
    protected int billrequestid;
    protected String email;
	 
    //constructors
    public BillReject() {
    }
 
    public BillReject(int billrejectid, String billrejectnote, int billrequestid, String email) 
    {
    	this.billrejectid = billrejectid;
        this.billrejectnote = billrejectnote;
        this.billrequestid = billrequestid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getBillRejectID() {
        return billrejectid;
    }
    public void setBillRejectID(int billrejectid) {
        this.billrejectid = billrejectid;
    }
    public String getBillRejectNote() {
        return billrejectnote;
    }
    public void setBillRejectNote(String billrejectnote) {
        this.billrejectnote = billrejectnote;
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