public class QuoteRequest {
    protected String quotenote;
    protected int quoterequestid;
	 
    //constructors
    public QuoteRequest() {
    }
 
    public QuoteRequest(int quoterequestid, String quotenote) 
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
}
