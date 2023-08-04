package lk.ijse.thogakade.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private String item_id;
    private String item_name;
    private double price;
    private int qty;

    public ItemDTO() {
    }

    public ItemDTO(String item_id, String item_name, double price, int qty) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.price = price;
        this.qty = qty;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "item_id='" + item_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
