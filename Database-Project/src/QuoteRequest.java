public class QuoteRequest {
    protected String quotenote;
	 
    //constructors
    public QuoteRequest() {
    }
 
    public QuoteRequest(String quotenote) 
    {
        this.quotenote = quotenote;
    }
    
   //getter and setter methods
    public String getQuoteNote() {
        return quotenote;
    }
    public void setQuoteNote(String quotenote) {
        this.quotenote = quotenote;
    }
}
