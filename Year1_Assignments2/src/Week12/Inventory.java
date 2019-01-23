package Week12;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemInventory;
    private ArrayList<Shipment> shippedOrders;
    private ArrayList<Order> orders;
    private long itemID = 0;

    public Inventory() {
        this.itemInventory = new ArrayList<>();
        this.shippedOrders = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addItemToInventory(String itemName, double itemPrice, int itemQuantity) {
        boolean itemFound = false;
        int counter = 0;
        while (!itemFound && counter < itemInventory.size()) {
            Item item = itemInventory.get(counter);
            if (item.getItemName().equals(itemName) && item.getItemPrice() == itemPrice) {
                item.setItemQuantity(item.getItemQuantity() + 1);
                itemFound = true;
            }
            counter++;
        }
        if (itemFound) {
            Item item = new Item(++itemID, itemName, itemPrice, itemQuantity);
            itemInventory.add(item);
        }
    }

    public Item findItem(String itemName) {
        boolean itemFound = false;
        int counter = 0;
        Item item = null;

        while (!itemFound && counter < itemInventory.size()) {
            Item currItem = itemInventory.get(counter);
            if (item.getItemName().equals(itemName)) {
                itemFound = true;
                item = currItem;
            }
            counter++;
        }
        return item;
    }

    public void createShipment(Item item) {
        Shipment shipment = new Shipment(item);
        shippedOrders.add(shipment);
    }

    public int createOrder(Item item) {
        Order order = new Order(item);
        return order.getOrderID();
    }

    public boolean processOrder(int orderID) {
        boolean orderFound = false;
        Order order = null;
        while (!orderFound) {
            for (int index = 0; index < orders.size(); index++) {
                order = orders.get(index);
                if (order.getOrderID() == orderID) {
                    orderFound = true;
                }
            }
        }
        if (orderFound) {
            createShipment(order.getOrderedItem());

        }
        return orderFound;
    }

    public void printAllItemDetails() {
        for (Item item : itemInventory) {
            System.out.println(item.toString());
        }
    }
}
