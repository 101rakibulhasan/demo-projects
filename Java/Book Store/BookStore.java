import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookStore {
    public static void main(String[] args)
    {
        ClearScreen cls = new ClearScreen();
        System.out.println("// BOOK STORE //");
        System.out.println("Made By Rakibul Hasan\n");
        System.out.println("Are you an Admin or User?");
        System.out.println("[A] Admin");
        System.out.println("[U/Any] User\n");
        System.out.println("Enter Choice >> ");
        Scanner s = new Scanner(System.in);

        List<Books> books = new ArrayList<Books>();

        String option = s.next();
        if(Objects.equals(option, "A") || Objects.equals(option, "a"))
        {
            Admin admin = new Admin(books);
        }else
        {
            Customer customer = new Customer(books);
        }
    }
}

class ClearScreen{
    ClearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class Books{
    String title;
    String author;
    String genre;
    int price;
    int quantity;

    Books()
    {
        ClearScreen cls = new ClearScreen();
        Scanner s = new Scanner(System.in);
        System.out.println("Enter title: ");
        title = s.nextLine();
        System.out.println("Enter author: ");
        author = s.nextLine();
        System.out.println("Enter genre: ");
        genre = s.nextLine();
        System.out.println("Enter price: ");
        price = s.nextInt();
        System.out.println("Enter quantity: ");
        quantity = s.nextInt();
    }
}

class User{
    String name;
    String email;
    String password;

    User()
    {
        ClearScreen cls = new ClearScreen();
        System.out.println("-- USER ACCOUNT --");
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Name: ");
        name = s.nextLine();
        System.out.println("Enter Email: ");
        email = s.nextLine();
        System.out.println("Enter Password: ");
        password = s.nextLine();

        UserDisplay();
    }

    void UserDisplay()
    {
        System.out.println("\n-- USER INFORMATION --");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("");
    }

}

class Cart{
    String title;
    int price;
}


class Customer extends User{
    String address;
    List<Cart> cart = new ArrayList<Cart>();
    Customer(List<Books> books)
    {
        if(books.size() == 0)
        {
            ClearScreen cls = new ClearScreen();
            System.out.println("No books available. Admin add a book first");
            System.exit(0);
        }else
        {
            System.out.println("-- CUSTOMER INFORMATION --");
            Scanner s = new Scanner(System.in);
            System.out.println("Enter Address: ");
            address = s.nextLine();
    
            System.out.println("");
            System.out.println("-- Welcome to the Book Store --");
            BuyBooks(books, cart);
        }

        
    }

    void BuyBooks(List<Books> books, List<Cart> cart)
    {
        while(true)
        {
            System.out.println("// AVAILABLE BOOKS");

            System.out.println("Books: " + books.size());
    
            for(int i=0; i < books.size(); i++)
            {
                System.out.println("["+i+"] "+ books.get(i).title);
            }
    
            System.out.println("");
    
    
            System.out.println("// ON CART");
            System.out.println("Cart: " + cart.size());
            for(int i=0; i < cart.size(); i++)
            {
                System.out.println("["+i+"] "+ cart.get(i).title);
            }  

            System.out.println("\n // Type -1 to confirm purchase or remove item from your cart ");
    
            System.out.println("Enter Index or -1: ");
            Scanner s = new Scanner(System.in);
            int ind = s.nextInt();

            if(ind == -1)
            {
                if(cart.size() == 0)
                {
                    ClearScreen cls = new ClearScreen();
                    System.out.println("No items in cart");
                    continue;
                }else
                {
                    ViewCart(cart);
                    break;
                }
            }else if(ind >= books.size())
            {
                ClearScreen cls = new ClearScreen();
                System.out.println("Invalid Index");
                continue;
            }else
            {
                Cart c = new Cart();
                c.title = books.get(ind).title;
                c.price = books.get(ind).price;
                cart.add(c);
                ClearScreen cls = new ClearScreen();
            }           
        }

    }

    void ViewCart(List<Cart> cart)
    {
        ClearScreen cls = new ClearScreen();
        if(cart.size() == 0)
        {
            System.out.println("No items in cart");
            System.exit(0);
        }else
        {
            while(true)
            {
                ClearScreen cls3 = new ClearScreen();
                System.out.println("-- AVAILABLE CART --");
                for(int i=0; i < cart.size(); i++)
                {
                    System.out.println("["+i+"] "+ cart.get(i).title);
                }
                
                System.out.println("\n\n // Enter index to remove item from cart");
                System.out.println("// Enter -1 to go Purchase Section");
                System.out.println("Enter Index or -1: ");
                Scanner s = new Scanner(System.in);
                int ind = s.nextInt();

                if(ind == -1)
                {
                    if(cart.size() == 0)
                    {
                        System.out.println("No items in cart");
                        System.exit(0);
                    }else
                    {
                        ConfirmPurchase(cart);
                    } 
                }else if(ind < cart.size())
                {
                    cart.remove(ind);
                    
                }else
                {
                    continue;
                }   
            }
        }
        
        
    }

    void ConfirmPurchase(List<Cart> cart)
    {
        System.out.println("Confirm Purchase? [Y/Any]");
        Scanner s = new Scanner(System.in);
                        String yn = s.next();
                        yn = yn.toUpperCase();
                        if(Objects.equals(yn, "Y"))
                        {
                            ClearScreen cls2 = new ClearScreen();
                            System.out.println("Thanks for purchase, "+ name +" !");
                            System.exit(0);
                        }else
                        {
                            ViewCart(cart);
                        }
    }
        
}

class Admin{
    Admin(List<Books> books)
    {
        ClearScreen cls = new ClearScreen();
        System.out.println("-- ADMIN --");
        System.out.println("Total Books: " + books.size());

        for(int i=0; i < books.size(); i++)
        {
            System.out.println("["+i+"] Title: "+ books.get(i).title + " | Quantity: " + books.get(i).quantity+ " | Author: " + books.get(i).author + " | Genre: " + books.get(i).genre+ " | Price: " + books.get(i).price + "TK");
        }

        System.out.println("");

        System.out.println("[A] Add Book");
        System.out.println("[U] Update Book");
        System.out.println("[R] Remove Book");
        System.out.println("[Any] Go To User");
        System.out.println("Enter choice >> ");

        Scanner s = new Scanner(System.in);
        String option = s.next();
        option = option.toUpperCase();
        switch (option) {
            case "A" :
                AddBook(books);
                break;
            case "U" :
                if(books.size() > 0) 
                { 
                    UpdateBook(books);
                }
                else{
                    System.out.println("No books available");
                    System.out.println("[Any] Go Back");   
                    s.next();    
                    Admin a = new Admin(books);                   
                }
                break;
            case "R" : 
                if(books.size() > 0) 
                { 
                    RemoveBook(books);
                }
                else{
                    System.out.println("No books available");   
                    System.out.println("[Any] Go Back");   
                    s.next();  
                    Admin a = new Admin(books);
                }
                break;
                
            default : 
                Customer customer = new Customer(books);
            
        }
    }

    void AddBook(List<Books> books)
    {

        ClearScreen cls = new ClearScreen();
        Books b = new Books();
        books.add(b);

        Admin a = new Admin(books);
    }

    void UpdateBook(List<Books> books)
    {
        ClearScreen cls = new ClearScreen();
        System.out.println("-- BOOKS--");
        for(int i=0; i < books.size(); i++)
        {
            System.out.println("["+i+"] "+ books.get(i).title);
        }
        System.out.println("Enter position: ");
        Scanner s = new Scanner(System.in);
        int pos = s.nextInt();
        Books b = new Books();
        books.remove(pos);
        books.add(b);

        Admin a = new Admin(books);
    }

    void RemoveBook(List<Books> books)
    {
        System.out.println("Enter position: ");
        Scanner s = new Scanner(System.in);
        int pos = s.nextInt();
        books.remove(pos);
        Admin a = new Admin(books);
    }
}



