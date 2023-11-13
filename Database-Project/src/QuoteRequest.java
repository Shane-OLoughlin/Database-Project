public class QuoteRequest {
    protected int quoterequestid;
    protected String quotenote;
    protected String email;
	 
    //constructors
    public QuoteRequest() {
    }
 
    public QuoteRequest(int quoterequestid, String quotenote, String email) 
    {
    	this.quoterequestid = quoterequestid;
        this.quotenote = quotenote;
        this.email = email;
    }
    
   //getter and setter methods
    public int getQuoteRequestID() {
        return quoterequestid;
    }
    public void setQuoteRequestID(int quoterequestid) {
        this.quoterequestid = quoterequestid;
    }
    public String getQuoteNote() {
        return quotenote;
    }
    public void setQuoteNote(String quotenote) {
        this.quotenote = quotenote;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}