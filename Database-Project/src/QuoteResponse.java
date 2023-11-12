public class QuoteResponse {
    protected int quoteresponseid;
    protected double initialprice;
    protected String timewindow;
    protected int quoterequestid;
    protected String email;
	 
    //constructors
    public QuoteResponse() {
    }
 
    public QuoteResponse(int quoteresponseid, double initialprice, String timewindow, int quoterequestid, String email) 
    {
    	this.quoteresponseid = quoteresponseid;
        this.initialprice = initialprice;
        this.timewindow = timewindow;
        this.quoterequestid = quoterequestid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getQuoteResponseID() {
        return quoteresponseid;
    }
    public void setQuoteResponseID(int quoteresponseid) {
        this.quoteresponseid = quoteresponseid;
    }
    public double getInitialPrice() {
        return initialprice;
    }
    public void setInitialPrice(double initialprice) {
        this.initialprice = initialprice;
    }
    public String getTimeWindow() {
        return timewindow;
    }
    public void setTimeWindow(String timewindow) {
        this.timewindow = timewindow;
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