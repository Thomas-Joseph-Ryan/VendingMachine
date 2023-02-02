package CC12.Richard.Group1.Assign2;

public class Denom {
    private Double denom;
    private int quantity;
    private int quantityInUse = 0;

    public Denom() {
        
    }

    public Double getDenom() {
        return this.denom;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setDenom(Double denom) {
        this.denom = denom;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int incrementQtyInUse() {
        quantityInUse ++;
        return quantityInUse;
    }

    public int decrementQtyInUse() {
        quantityInUse --;
        return quantityInUse;
    }

    public int getQuantityInUse() {
        return quantityInUse;
    }
}
