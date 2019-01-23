package Week12;

import java.util.Calendar;
import java.util.Date;

public class Shipment {
    private Date arrivalDate;
    private Item shippedItem;

    public Shipment(Item shippedItem) {
        this.shippedItem = shippedItem;
        arrivalDate = Calendar.getInstance().getTime();
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Item getShippedItem() {
        return shippedItem;
    }

    public void setShippedItem(Item shippedItem) {
        this.shippedItem = shippedItem;
    }
}
