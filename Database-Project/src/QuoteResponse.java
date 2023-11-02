public class QuoteResponse {
    protected int quoteresponseid;
    protected double initialprice;
    protected String timewindow;
	 
    //constructors
    public QuoteResponse() {
    }
 
    public QuoteResponse(int quoteresponseid, double initialprice, String timewindow) 
    {
    	this.quoteresponseid = quoteresponseid;
        this.initialprice = initialprice;
        this.timewindow = timewindow;
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
}