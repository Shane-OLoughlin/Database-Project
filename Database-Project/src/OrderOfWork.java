public class OrderOfWork {
    protected int orderofworkid;
    protected String orderofworknote;
    protected int quoteresponseid;
    protected String email;
	 
    //constructors
    public OrderOfWork() {
    }
 
    public OrderOfWork(int orderofworkid, String orderofworknote, int quoteresponseid, String email) 
    {
    	this.orderofworkid = orderofworkid;
    	this.orderofworknote = orderofworknote;
    	this.quoteresponseid = quoteresponseid;
        this.email = email;
    }
    
   //getter and setter methods
    public int getOrderOfWorkID() {
        return orderofworkid;
    }
    public void setOrderOfWorkID(int orderofworkid) {
        this.orderofworkid = orderofworkid;
    }
    public String getOrderOfWorkNote() {
        return orderofworknote;
    }
    public void setOrderOfWorkNote(String orderofworknote) {
        this.orderofworknote = orderofworknote;
    }
    public int getQuoteResponseID() {
        return quoteresponseid;
    }
    public void setQuoteResponseID(int quoteresponseid) {
        this.quoteresponseid = quoteresponseid;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}