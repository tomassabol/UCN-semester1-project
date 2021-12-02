package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SupplyOfferContainer {
    private ArrayList<SupplyOffer> supplyOffers;

    /**
     * Constructor class SupplyOfferContainer
     */
    public SupplyOfferContainer() {
        supplyOffers = new ArrayList<>();
    }

    /**
     * @param supplyOffer to be added to an AraryList
     * @return true if successfully added
     */
    public boolean addSupplyOffer(SupplyOffer supplyOffer) {
        return supplyOffers.add(supplyOffer);
    }

    /**
     * @return a list of all supplyOffers
     */
    public List<SupplyOffer> getSupplyOffers() {
        return this.supplyOffers;
    }

    /**
     * @return true if successfully added
     */
    public boolean removeSupplyOffer(SupplyOffer supplyOffer) {
        return supplyOffers.remove(supplyOffer);
    }

    /**
     * @param id - find a supplyOffer with this specific id
     * @return supplyOffer with the specific ID if found
     */
    public SupplyOffer findSupplyOfferById(int id) {
        for (SupplyOffer supplyOffer : supplyOffers) {
            if (supplyOffer.ID == id) {
                return supplyOffer ;
            }
        }
        return null;
    }
    
}
