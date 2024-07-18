//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Home();
    }

    static void Home()
    {
        DevTools dev = new DevTools();
        dev.cls();

        System.out.println("-- Library Management System --");
        System.out.println("-- Made By Rakibul Hasan\n\n");


        System.out.println("-- Your Role : ");
        System.out.println("1. Librarian");
        System.out.println("2. Library Staff");
        System.out.println("3. Patron\n");
        System.out.println("** Type any other number for EXIT **");

        switch(dev.choice())
        {
            case 1:
                Librarian lib = new Librarian();
                break;

            case 2:
                LibraryStaff sup = new LibraryStaff();
                break;

            case 3:
                Patron adm = new Patron();
                break;

            default:
                dev.cls();
                System.out.println("Goodbye !");
                System.exit(0);
        }
    }

}