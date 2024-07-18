import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DevTools {
    Database db = new Database();
    void cls()
    {
        try
        {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows"))
            {
                String[] cmd = {"cmd", "/c", "tree"};
                Runtime.getRuntime().exec(cmd);

            }else
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        }


        catch (final Exception e)
        {
            e.printStackTrace();
        }

    }

    int choice()
    {
        int x;
        try{
            System.out.print(">> ");
            Scanner ch = new Scanner(System.in);
            x = ch.nextInt();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            x = 100000;
        }
        return x;
    }

    String LoginScreen(int x)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("-- CREDENTIAL --");
        System.out.print("USERNAME >> ");
        String username = s.nextLine();
        System.out.print("PASSWORD >> ");
        String password = s.nextLine();

        List<Object[]> res = null;
        String role;

        if(x == 0)
        {
            role = "Librarian";

        }else if(x == 1)
        {
            role = "Library Staff";
        }else
        {
            role = "Patron";
        }

        String selectQuery = "SELECT * "
                + "FROM users "
                + "WHERE role = '" + role + "' "
                + "AND username = '" + username + "' "
                + "AND password = '" + password + "';";
        res = db.queryView(selectQuery);

        while(true)
        {
            if (res != null && !res.isEmpty()) break;
            System.out.println("-- ACCESS DENIED --");
            System.out.println("Try Again !");

            System.out.print("USERNAME >> ");
            username = s.nextLine();
            System.out.print("PASSWORD >> ");
            password = s.nextLine();

            selectQuery = "SELECT * "
                    + "FROM users "
                    + "WHERE role = '" + role + "' "
                    + "AND username = '" + username + "' "
                    + "AND password = '" + password + "';";
            res = db.queryView(selectQuery);
        }

        cls();
        return username;
    }

}
