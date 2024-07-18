/* PART DONE BY RAKIBUL HASAN */

import java.util.List;
import java.util.Scanner;

public class Librarian {
    String user;
    DevTools dev = new DevTools();
    Database db = new Database();

    List<Object[]> userList;
    List<Object[]> bookList;

    Librarian()
    {
        dev.cls();
        user = dev.LoginScreen(0);

        getUserList();
        getBookList();

        info();
    }

    void getUserList()
    {
        userList = db.queryView("SELECT * FROM users");
    }

    void getBookList()
    {
        bookList = db.queryView("SELECT * FROM books");
    }
    void info()
    {
        System.out.println("Hello, " + user);
        System.out.println();
        System.out.println("-- Enter Choice --");
        System.out.println("- BOOKS -");
        System.out.println("1. Add Book");
        System.out.println("2. Update Book");
        System.out.println("3. Remove Book");
        System.out.println("4. View Books\n");
        System.out.println("- USERS -");
        System.out.println("5. Add User");
        System.out.println("6. Update User");
        System.out.println("7. Remove User");
        System.out.println("8. View Users\n\n");
        System.out.println("** Type any other number for SIGNOUT **\n");

        switch (dev.choice())
        {
            case 1:
                AddBooks();
                break;

            case 2:
                UpdateBooks();
                break;

            case 3:
                RemoveBooks();
                break;

            case 4:
                ViewBooks();
                break;

            case 5:
                AddUsers();
                break;

            case 6:
                UpdateUsers();
                break;

            case 7:
                RemoveUsers();
                break;

            case 8:
                ViewUsers();
                break;

            default:
                Main.Home();

        }
    }

    void AddBooks()
    {
        while(true)
        {
            dev.cls();
            System.out.println("-- ADD BOOK --");

            String title;
            String publisher;
            String category;
            boolean available;

            Scanner s = new Scanner(System.in);

            System.out.print("Title >> ");
            title = s.nextLine();
            System.out.flush();
            System.out.print("Publisher >> ");
            publisher = s.nextLine();
            System.out.flush();
            System.out.print("Category >> ");
            category = s.nextLine();
            System.out.flush();
            System.out.print("Available (y/n) >> ");
            String res = s.nextLine().toLowerCase();
            System.out.flush();
            while(!res.equals("y") && !res.equals("n"))
            {
                res = s.nextLine().toLowerCase();
            }
            available = res.equals("y");


            String insertQuery = "INSERT INTO books (title, publisher, category, available) " +
                    "VALUES ('" + title + "', '" + publisher + "', '" + category + "', " + available + ");";
            db.query(insertQuery);

            getBookList();

            System.out.println("Add another book? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }

        info();

    }

    void UpdateBooks()
    {
        while(true){
            dev.cls();

            showBooks();

            System.out.println("** Enter the book ID you want to modify **");
            System.out.println("(ID) ");
            int id = dev.choice();

            String title = null;
            String publisher = null;
            String category = null;

            List<Object[]> book = db.queryView("SELECT title, publisher, category\n" +
                    "FROM books\n" +
                    "WHERE id = " + id +";");
            Scanner s = new Scanner(System.in);

            System.out.println("** You can skip the input section that doesn't require modification, it will automatically set to previous value indicated in parenthesis **");


            for (Object[] row : book) {
                int i = 0;
                for (Object value : row) {
                    String x;
                    switch(i)
                    {
                        case 0:
                            title = value.toString();
                            System.out.print("Title: (" + title + ") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                title = x;
                            }

                            break;
                        case 1:
                            publisher = value.toString();
                            System.out.print("Publisher: (" + publisher+") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                publisher = x;
                            }
                            break;
                        case 2:
                            category = value.toString();
                            System.out.print("Category: ("+category+ ") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                category = x;
                            }
                            break;
                    }
                    i++;
                }
                System.out.println();
            }

            String updateQuery = "UPDATE books "
                    + "SET title = '" + title + "', "
                    + "publisher = '" + publisher + "', "
                    + "category = '" + category + "' "
                    + "WHERE id = " + id + ";";

            db.query(updateQuery);

            dev.cls();
            getBookList();
            showBooks();


            System.out.println("Update another book? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }

        info();

    }
    void RemoveBooks()
    {
        while(true){
            dev.cls();

            showBooks();

            System.out.println("** Enter the book ID you want to remove **");
            System.out.println("(ID) ");
            int id = dev.choice();

            db.query("DELETE FROM books\n" +
                    "WHERE id = " + id + ";");

            dev.cls();
            getBookList();
            showBooks();

            System.out.println("Remove another book? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }

        info();
    }

    void ViewBooks()
    {
        dev.cls();

        showBooks();

        System.out.println("-- Type any to return --");
        dev.choice();
        info();
    }

    void showBooks()
    {
        System.out.println("-- BOOKS --");
        for (Object[] row : bookList) {
            int i = 0;
            for (Object value : row) {
                switch(i)
                {
                    case 0:
                        System.out.print("ID: ");
                        break;
                    case 1:
                        System.out.print("Title: ");
                        break;
                    case 2:
                        System.out.print("Publisher: ");
                        break;
                    case 3:
                        System.out.print("Category: ");
                        break;
                    case 4:
                        System.out.print("Available: ");
                        break;
                    case 5:
                        System.out.println("Borrowed By: ");
                        break;
                }


                if(i == 4 )
                {
                    if (value instanceof Integer && (Integer) value == 1)
                    {
                        System.out.print("YES" + "   |   ");
                    }else
                    {
                        System.out.print("NO" + "   |   ");
                    }
                }else if(i == 5 )
                {
                    if (value == null)
                    {
                        System.out.print("NONE" + "   |   ");
                    }else
                    {
                        System.out.print(value + "   |   ");
                    }
                }else
                {
                    System.out.print(value + "   |   ");
                }
                i++;


            }
            System.out.println();

        }
    }

    void AddUsers()
    {
        while(true)
        {
            dev.cls();
            System.out.println("-- ADD USERS --");

            String role = "Patron";
            String name;
            String email;
            String username;
            String password;
            int fine = 0;

            Scanner s = new Scanner(System.in);

            System.out.println("What role you want to be? ");
            System.out.println("1. Librarian ");
            System.out.println("2. Library Staff ");
            System.out.println("3. Patron ");
            System.out.print("(Role) ");

            int x = dev.choice();

            while(x > 3 || x < 1)
            {
                System.out.print("-- WRONG CHOICE --");
                System.out.print("Try Again? ");
                System.out.print("(Role) ");
                x = dev.choice();
            }

            role = switch (x) {
                case 1 -> "Librarian";
                case 2 -> "Library Staff";
                case 3 -> "Patron";
                default -> role;
            };

            System.out.print("Full Name >> ");
            name = s.nextLine();
            System.out.flush();

            System.out.print("Email >> ");
            email = s.nextLine();
            System.out.flush();

            System.out.print("Username >> ");
            username = s.nextLine();
            System.out.flush();

            System.out.print("Password >> ");
            password = s.nextLine();
            System.out.flush();

            Database db = new Database();
            String insertQuery = "INSERT INTO users (role, name, email, username, password) " +
                    "VALUES ('" + role + "', '" + name + "', '" + email + "', '" + username + "', '" + password + "');";

            db.query(insertQuery);

            getUserList();

            System.out.println("Add another user? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }
        info();
    }

    void UpdateUsers()
    {
        while(true){
            dev.cls();

            showUsers();

            System.out.println("** Enter the user ID you want to modify **");
            System.out.println("(ID) ");
            int id = dev.choice();

            String role = null;
            String name = null;
            String email = null;
            String username = null;
            String password = null;

            List<Object[]> updateuser = db.queryView("SELECT role, name, email, username, password\n" +
                    "FROM users\n" +
                    "WHERE id = " + id +";");
            Scanner s = new Scanner(System.in);

            System.out.println("** You can skip the input section that doesn't require modification, it will automatically set to previous value indicated in parenthesis **");

            for (Object[] row : updateuser) {
                int i = 0;
                for (Object value : row) {
                    String x;
                    switch(i)
                    {
                        case 0:
                            role = value.toString();
                            System.out.println("Role: (" + role + ") ");
                            System.out.println("1. Librarian ");
                            System.out.println("2. Library Staff ");
                            System.out.println("3. Patron ");
                            System.out.print("(Role) ");

                            int p = dev.choice();

                            while(p > 3 || p < 1)
                            {
                                System.out.print("-- WRONG CHOICE --");
                                System.out.print("Try Again? ");
                                System.out.print("(Role) ");
                                p = dev.choice();
                            }

                            role = switch (p) {
                                case 1 -> "Librarian";
                                case 2 -> "Library Staff";
                                case 3 -> "Patron";
                                default -> role;
                            };

                            break;
                        case 1:
                            name = value.toString();
                            System.out.print("Full Name: (" + name+") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                name = x;
                            }
                            break;
                        case 2:
                            email = value.toString();
                            System.out.print("E-mail: ("+email+ ") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                email = x;
                            }
                            break;
                        case 3:
                            username = value.toString();
                            System.out.print("Username: ("+username+ ") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                username = x;
                            }
                            break;
                        case 4:
                            password = value.toString();
                            System.out.print("Password: ("+password+ ") >> ");
                            x = s.nextLine();
                            if(!x.isEmpty())
                            {
                                password = x;
                            }
                            break;

                    }
                    i++;
                }
                System.out.println();
            }

            String updateQuery = "UPDATE users "
                    + "SET role = '" + role + "', "
                    + "name = '" + name + "', "
                    + "email = '" + email + "', "
                    + "username = '" + username + "', "
                    + "password = '" + password + "' "
                    + "WHERE id = " + id + ";";

            db.query(updateQuery);

            dev.cls();
            getUserList();
            showUsers();


            System.out.println("Update another user? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }

        info();
    }
    void RemoveUsers()
    {
        while(true){
            dev.cls();

            showUsers();

            System.out.println("** Enter the user ID you want to remove **");
            System.out.println("(ID) ");
            int id = dev.choice();

            db.query("DELETE FROM users\n" +
                    "WHERE id = " + id + ";");

            dev.cls();
            getUserList();
            showUsers();

            System.out.println("Remove another user? --> ");
            System.out.println("1. Yes ");
            System.out.println("2. No");

            if(dev.choice() == 2)
            {
                break;
            }
        }

        info();
    }

    void showUsers()
    {
        System.out.println("-- USERS --");


        for (Object[] row : userList) {
            int i = 0;
            for (Object value : row) {

                switch(i)
                {
                    case 0:
                        System.out.print("ID: ");
                        break;
                    case 1:
                        System.out.print("Role: ");
                        break;
                    case 2:
                        System.out.print("Full Name: ");
                        break;
                    case 3:
                        System.out.print("Email: ");
                        break;
                    case 4:
                        System.out.print("Username: ");
                        break;
                }
                i++;
                if(i < 6)
                {
                    System.out.print(value + "   |   ");
                }

            }
            System.out.println();
        }
    }

    void ViewUsers()
    {
        dev.cls();

        showUsers();

        System.out.println("-- Type any to return --");
        dev.choice();
        info();
    }
}
