public class QuoteReject {
    protected int quoterejectid;
    protected String quoterejectnote;
    protected int quoterequestid;
    protected String email;
	 
    //constructors
    public QuoteReject() {
    }
 
    public QuoteReject(int quoterejectid, String quoterejectnote, int quoterequestid, String email) 
    {
    	this.quoterejectid = quoterejectid;
        this.quoterejectnote = quoterejectnote;
        this.quoterequestid = quoterequestid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getQuoteRejectID() {
        return quoterejectid;
    }
    public void setQuoteRejectID(int quoterejectid) {
        this.quoterejectid = quoterejectid;
    }
    public String getQuoteRejectNote() {
        return quoterejectnote;
    }
    public void setQuoteRejectNote(String quoterejectnote) {
        this.quoterejectnote = quoterejectnote;
    }
    public int getQuoteRequestID() {
        return quoterequestid;
    }
    public void setQuoteRequestID(int quoterequestid) {
        this.quoterequestid = quoterequestid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}