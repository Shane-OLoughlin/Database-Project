public class Statistics {
    private String email;
    private int clientid;
    private int totalTrees;
    private double totalDueAmount;
    private double totalPaidAmount;
    private String datesWorkDone;
    
   //constructors
    public Statistics() {
    }
    
    public Statistics(String email, int clientid, int totalTrees, double totalDueAmount, double totalPaidAmount, String datesWorkDone) {
        this.email = email;
        this.clientid = clientid;
        this.totalTrees = totalTrees;
        this.totalDueAmount = totalDueAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.datesWorkDone = datesWorkDone;
    }

    //getter and setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
	public int getClientID() {
		return clientid;
	}

	public void setClientID(int clientid) {
		this.clientid = clientid;
	}
    
    public int getTotalTrees() {
        return totalTrees;
    }

    public void setTotalTrees(int totalTrees) {
        this.totalTrees = totalTrees;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public String getDatesWorkDone() {
        return datesWorkDone;
    }

    public void setDatesWorkDone(String datesWorkDone) {
        this.datesWorkDone = datesWorkDone;
    }
}