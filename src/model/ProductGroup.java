package model;


/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */

public class ProductGroup {
    public final int ID;
    private String groupName;

    private ProductGroup(int Id, String groupName) {
        this.ID = Id;
        this.groupName = groupName;
    }
}
