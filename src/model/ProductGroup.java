package model;


/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */

public class ProductGroup {
    public final int ID;
    private String groupName;

    /**
     * Constructor class ProductGroup 
     * @param Id - id of productgroup
     * @param groupName - name of the productgroup
     */
    private ProductGroup(int id, String groupName) {
        this.ID = id;
        this.groupName = groupName;
    }

    // get method for productgroup ID
    public int getID() {
        return this.ID;
    }

    // set/get methods for groupname
    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
