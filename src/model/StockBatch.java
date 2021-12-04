package model;

import java.time.LocalDateTime;
import java.util.Set;

public class StockBatch {
    private Product product;
    private SupplyOrder supplyOrder;
    private int untrackableItemQuantity;
    private Set<TrackableItem> trackableItems;
    private LocalDateTime delivered;

    public StockBatch(Set<TrackableItem> trackableItems, Product product, SupplyOrder supplyOrder) {}
}
