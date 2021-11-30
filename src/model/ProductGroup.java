package model;
import java.util.ArrayList;
import java.util.List;

/**
 * @authors tomassabol, danielskenepe, tamastoth, attilabako
 * @version Nov 30, 2021
 */

public class ProductGroup {
    public final int ID;
    private String groupName;
    private ArrayList<ProductGroupLine> productGroupLines;

    /**
     * Constructor class ProductGroup 
     * @param Id - id of productgroup
     * @param groupName - name of the productgroup
     */
    private ProductGroup(int id, String groupName) {
        this.ID = id;
        this.groupName = groupName;
        productGroupLines = new ArrayList<>();
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

    /**
     * @param productGroupLine - productGroupLine to be added to an ArrayList
     * @return
     */
    public boolean addProductGroupLine(ProductGroupLine productGroupLine) {
        return productGroupLines.add(productGroupLine);
    }

    /**
     * @return list of productGroupLines
     */
    public List<ProductGroupLine> getProductGroupLines() {
        return productGroupLines;
    }

    /**
     * @param productGroupLine - productGroupLine to be removed from an ArrayList
     * @return true if successfully added
     */
    public boolean removeProductGroupLine(ProductGroupLine productGroupLine) {
        return productGroupLines.remove(productGroupLine);
    }
}
