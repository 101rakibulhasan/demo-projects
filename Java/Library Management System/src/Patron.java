/* PART DONE BY ISRAT JAHAN CHOITY */

public class Patron {
    String user;
    DevTools dev = new DevTools();
    Database db = new Database();
    Patron()
    {
        user = dev.LoginScreen(2);
    }
}
