package CC12.Richard.Group1.Assign2;

public class Item {
    private String name;
    private int qty;
    private double price;
    private int qtyInBag = 0;

    private int category;

    private int code;

    public Item() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyInBag() {
        return qtyInBag;
    }

    public void setQtyInBag(int qtyInBag) {
        this.qtyInBag = qtyInBag;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
