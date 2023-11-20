public class QuoteRequest {
    protected int quoterequestid;
    protected String quotenote;
    protected String negotiations;
    protected String email;
	 
    //constructors
    public QuoteRequest() {
    }
 
    public QuoteRequest(int quoterequestid, String quotenote, String negotiations, String email) 
    {
    	this.quoterequestid = quoterequestid;
        this.quotenote = quotenote;
        this.negotiations = negotiations;
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
    public String getNegotiations() {
        return negotiations;
    }
    public void setNegotiations(String negotiations) {
        this.negotiations = negotiations;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}