/** 
 *  Sale class:
 *  This class is the base class which interacts with different classes and using different methods register products,
 *  add them to cart or remove them from cart, display all available products and also checkout.
 *  It has a help menu that helps users understand its functionality.
 *  @author Sarma K.
 *  @version 8th Sept 2018
 */

import java.util.*;
public class Sale
{
    private ProductList prodList;
    private SaleTransaction transaction;
    private RandomNumberGenerator random;
    
    
    /**
     * Constructor for Sale class.
     * Instaniates the objects of ProductList, SaleTransaction and RandomNumberGenerator classes.
     */
    public Sale()
    {
        prodList = new ProductList();
        transaction = new SaleTransaction();
        random = new RandomNumberGenerator();
    }
    
    /**
     * A method to display the products in the ProductList class
     */
    public void displayProdList()
    {
        prodList.displayProductList();
    }
    
    /**
     * The main method that displays options to the user.
     * This method uses the methods of the SaleTransaction and ProductList classes and interacts with each other's methods
     * it has several options that the user can choose from.
     */
    public void start()
    {
        Scanner sc = new Scanner (System.in);
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        System.out.println("  Welcome to the Simple Inventory Management System");
        System.out.println("=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+");
        menu();
        int qty;
        int minqty;
        int choice;
        choice = Integer.parseInt(sc.next());
        while(true)
        {
            switch(choice)
            {
                case 1: //adding/registering a product
                        qty = random.generate(0,10);
                        minqty = random.generate(1,5);
                        check(qty, minqty);
                        break;
         
                case 2: //adding a product to cart
                        addToCart();
                        break;
         
                case 3: //removing a product from cart
                        transaction.dropProduct();
                        break;
         
                case 4: //displaying the available products
                        if(prodList.checkProductList(0))
                            System.out.println("The are no products availabe. Please try again later. ");
                        else
                            displayProdList();
                        break;
         
                case 5: //checking out the cart
                        int saleCode = random.generate(1000,9999);
                        transaction.commitChanges1(saleCode);
                        break;
         
                case 6: //help menu
                        help();
                        break;
         
                case 7: //exit case
                        System.out.println("Thanks for using the Simple Inventory Management System.");
                        System.exit(0);
                        break;
                    
                default: 
                        System.out.println("Please enter a valid option ");

            }
            menu();
            choice = Integer.parseInt(sc.next());
        }
    }
    
    /**
     * This method is the option 2 from the user i.e add products to cart in SaleTransaction class .
     * It checks if there are any products already and displays error is there is no space
     * If there is space it add the product and updates the total cost.
     */
    public void addToCart()
    {
        Scanner sc = new Scanner(System.in);
        boolean flag = prodList.checkProductList(0);
        //checking if there are any registered products in the inventory
        if (flag)   
            System.out.println(" There are no available products. Please add products to the ProductList");
        else
        {
            //if the cart is full display error
            if(transaction.getSize() == 3)
            {
                System.out.println("Cannot add any more products into the cart as the cart is full");
            }   
            else
            {
                System.out.println("Please select from the following products which are available: ");
                System.out.println();
                displayProdList();
                System.out.print("Enter your choice: ");
                int ch = sc.nextInt();
                System.out.println();
                //checking if the chosen product exists and if it is valid
                //if yes then add it to cart
                if(!prodList.checkProductList(ch-1))
                {
                    transaction.addingToCart(prodList.getProduct(ch));
                }
                else
                    System.out.println("Invalid choice");
            }
        }
    }
    
    /**
     * This method is the option 1 from the user i.e add products to inventory in ProductList class .
     * It checks if there is any space for products to be registered.
     * If there is space it add the product to the inventory.
     * It also performs all the validations on the name description and price values and generates randomnumbers 
     * It creates a product object from the Product class by using a non-default constructor of the Product class
     * It adds that product object to the inventory in the ProductList class
     * @param qty a random number between 0 and 10 which says the available quantity of the product
     * @param minqty a random number between 1 and 5 that says about the minimum quantity to be ordered when adding to cart and checking out 
     */
    public void check(int qty, int minqty)
    {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int check = prodList.check1();
        String name;
        String desc;
        double price;
        //checking for available space, if no space is availabale then display error
        if(check == 5) 
        {
            System.out.println("There is no space for more products. Please try again later");
        }
        else
        {
            name = nameValid(); //name check and validation, assign the right name to @param name
            System.out.print("Enter the product description: "); 
            //Description validation
            while(true)
            {
                desc = sc.nextLine();
                System.out.println();
                if(desc.length() >= 1 && desc.length() <= 50) 
                    break; 
                else
                    System.out.print("The product description should be between 1 and 50 characters. Try again: ");
            }
            System.out.print("Enter the price: ");
            //price validation
            while(true)
            {
                price = Double.parseDouble(sc.next());
                System.out.println();
                if(price <= 0)
                    System.out.print("The price cannot be less than 0. Please enter a valid price: ");
                else
                {
                    prodList.addProduct(name, desc, price,qty, minqty, check);  //adding product to the inventory
                    break;
                }
            }
        }  
    }   
    
    /**
     * A method that displays the menu when invoked to help user 
     */
    public void menu()
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Please Select from the following options: ");
        System.out.println("Press 1 to Register a Product for Sale ");
        System.out.println("Press 2 to Buy a Product to the Cart ");
        System.out.println("Press 3 to Remove a Product from the Cart ");
        System.out.println("Press 4 to View all the Available Products ");
        System.out.println("Press 5 to Check out ");
        System.out.println("Press 6 to Get Help");
        System.out.println("Press 7 to Exit");
        System.out.println();
        System.out.print("Please Enter your Choice: "); 
        
    }
    
    /**
     * A method that validates the name of the product that is entered after choosing option 1 i.e. adding to inventory
     * Checks if the name length is within limits
     * Checks if the name doesn't already exist.
     */
    public String nameValid()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the product: ");
        String name = sc.nextLine().trim();
        System.out.println();
        
        while(true)
        {
            //validation of length 
            if(name.length() < 4 || name.length() > 25)
            {
                System.out.print("The product name should be between 3 and 25: ");
                name = sc.nextLine().trim();
                System.out.println();
                continue;
            }
            //validation of the name if already exists
            if(prodList.nameCheck(name))
            {
                System.out.print("The product already exists please enter a new name: ");
                name = sc.nextLine().trim();
                System.out.println();
                continue;
            }
            else
                break;
        }
        return name;
    }
    
    public void help()
    {
        System.out.println(" This Inventory Management System allows you to Register, Buy and View all our available products. ");
        System.out.println(" Option 1 allows you to Register a product for Sale, So you can place a new product in the inventory ");
        System.out.println(" Option 2 allows you to Buy a product to the Cart. In this, you can buy the products that you have registered");
        System.out.print(" Option 3 allows you to Remove a product from the Cart. In case that you have switched your mind about buying a certain product, ");
        System.out.println(" or that you have just picked a wrong product in general, you can fix it here");
        System.out.println(" Option 4 allows you to to view all the available products in the inventory");
        System.out.println(" Option 5 allows you to 'Check Out' your cart, so you can be on your way with your purchased inventory");
        System.out.println(" Option 7 simply exits this program, hope that you have been satisfied with our service");
        
    }
}

