public class Tree {
    protected int treeid;
    protected double size;
    protected double height;
    protected String location;
    protected double proximityToHouse;
    protected String picture1;
    protected String picture2;
    protected String picture3;
	 
    //constructors
    public Tree() {
    }
 
    public Tree(int treeid, double size, double height, String location, double proximityToHouse, String picture1, String picture2, String picture3) 
    {
    	this.treeid = treeid;
        this.size = size;
        this.height = height;
        this.location = location;
        this.proximityToHouse = proximityToHouse;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
    }
    
   //getter and setter methods
    public int getTreeID() {
        return treeid;
    }
    public void setTreeID(int treeid) {
        this.treeid = treeid;
    }
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public double getProximityToHouse() {
        return proximityToHouse;
    }
    public void setProximityToHouse(double proximityToHouse) {
        this.proximityToHouse = proximityToHouse;
    }
    public String getPicture1() {
        return picture1;
    }
    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }
    public String getPicture2() {
        return picture2;
    }
    public void setPicture2(String picture2) {
        this.picture1 = picture2;
    }
    public String getPicture3() {
        return picture3;
    }
    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }
}