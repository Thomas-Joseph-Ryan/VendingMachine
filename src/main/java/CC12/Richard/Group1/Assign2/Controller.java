package CC12.Richard.Group1.Assign2;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private User user;
    private List<Item> itemsList = new ArrayList<>();
    private double amountPaid = 0;
    private List<Denom> denomList;

    private List<Denom> denomChangeGiven = new ArrayList<>();
    public Controller() {
        this.user = new User();

    }

    public User getUser() {
        return user;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDenomList(List<Denom> denomList) {
        this.denomList = denomList;
    }

    public List<Denom> getDenomList() {
        return denomList;
    }

    public List<Denom> getDenomChangeGiven() {
        return denomChangeGiven;
    }

    public void setDenomChangeGiven(List<Denom> denomChangeGiven) {
        this.denomChangeGiven = denomChangeGiven;
    }
}
