/* PART DONE BY ABIDUR RAHMAN */
public class LibraryStaff {
    String user;
    DevTools dev = new DevTools();
    Database db = new Database();
    LibraryStaff()
    {
        dev.cls();
        user = dev.LoginScreen(1);

    }
}
