package model;

public class Contractor {
    public final int ID;
    private String companyName;

    public Contractor(int id, String companyName) {
        this.ID = id;
        this.companyName = companyName;
    }

    // get/set companyName
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public int getID() {
    	return this.ID;
    }
}
