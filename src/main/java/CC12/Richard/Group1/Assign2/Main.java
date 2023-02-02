package CC12.Richard.Group1.Assign2;

public class Main {

    public static ItemDao itemDao;
    public static UserDao userDao;
    public static DenomDao denomDao;
    public static HistoryDao historyDao;

    public static void main(String[] args) {

        itemDao = new ItemDao(null);
        userDao = new UserDao(null);
        denomDao = new DenomDao(null);
        historyDao = new HistoryDao(null);
        itemDao.createProductsTable();
        denomDao.createDenomTable();
        historyDao.createHistoryTable();
        historyDao.createTransactionTable();
        historyDao.createCancelledTransactionTable();
        userDao.createUsersTable();


        if (itemDao.count("Code", "Products") == 0) {
            itemDao.insertDefaultProductsValues();
        }

        if (denomDao.count("Denomination", "Denomination") == 0) {
            denomDao.insertDefaultDenomValues();
        }
        if (userDao.count("username", "Users") == 0) {
            userDao.insertDefaultUsersValues();
        }

        Controller controller = new Controller();
        new SaleGUI(controller);


    }
}
