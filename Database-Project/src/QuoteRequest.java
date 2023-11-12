public class QuoteRequest {
    protected int quoterequestid;
    protected String quotenote;
    protected String emailquoterequest;
	 
    //constructors
    public QuoteRequest() {
    }
 
    public QuoteRequest(int quoterequestid, String quotenote, String emailquoterequest) 
    {
    	this.quoterequestid = quoterequestid;
        this.quotenote = quotenote;
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
    public String getemailquoterequest() {
        return emailquoterequest;
    }
    public void setemailquoterequest(String emailquoterequest) {
        this.emailquoterequest = emailquoterequest;
    }
}