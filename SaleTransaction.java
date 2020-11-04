/** 
 *  SaleTransaction class:
 *  This class is responsible for adding products to cart and removing products from cart.
 *  It also does the checkout and display the total cost for the checked out products from the cart.
 *  @author Sarma K.
 *  @version 8th Sept 2018
 */

import java.util.*;
public class SaleTransaction
{
    private int saleCode;
    private double totalCost;
    private Product items[];
    
    /**
     * Constructor for SaleTransaction class.
     * Instaniates the array items.
     * Sets default value of totalCost
     * @param items stores products
     * @param totalCost stores totalCost of the whole cart
     */
    public SaleTransaction()
    {
        items = new Product[3];
        totalCost = 0.0;
    }
    
    /**
     * Accessor method for saleCode.
     * return the saleCode value when invoked
     * @return saleCode
     */
    public int getSaleCode()
    {
        //return salecode
        return saleCode;
    }
    
    /**
     * Accessor method for totalCost.
     * return the totalCost value when invoked
     * @return totalCost
     */
    public double getTotalCost()
    {
        //return totalcost
        return totalCost;
    }
    
    /**
     * Mutator method for saleCode.
     * sets the saleCode value when invoked
     * @param saleCode temporary variable to store salecode
     */
    public void setSaleCode(int saleCode)
    {
        //sets salecode
        this.saleCode = saleCode;
    }
    
    /**
     * Mutator method for totalCost.
     * sets the totalCost value when invoked
     * @param totalCost is a temporary double data type variable to store totalcost
     */
    public void setTotalCost(double totalCost)
    {
        //sets totalcost
        this.totalCost = totalCost;
    }
    
    /**
     * A method to display products in cart.
     */
    public void displayProductDetails()
    {
       // display all products in items array
       for(int i = 0; i < items.length; i++ )
       {
           if(items[i] == null)
                break;
           System.out.println("Product: "+(i+1));
           items[i].display();
       }
    }
    
    /**
     * Method to receive a product and add it to cart.
     * Puts in cart if there is any empty space or else displays an error message
     * @param choice is a product that has been passed from the ProductList class to be added into cart.
     */
    public void addingToCart(Product choice)
    {
        //check if null then add else display error.
        for(int i = 0; i < items.length; i++)
        {
                if(items[i] == null)
                {
                    //if null add
                    if(choice.getQtyOnHand() >= choice.getMinOrderQty() )
                    {
                        items[i] = choice;          //add to cart
                        System.out.println("The following product has been added to the cart");
                        choice.display();
                        updateTotalCost(choice, 1); //update totalCost
                    }
                    else 
                        //if full display error.
                        System.out.println("Cannot add the product to the cart because the quantity if less than the minimum order quantity");
                    break;
                }
        }
    }
    
    /**
     * Updates the totalCost when a product is added to the cart or removed from the cart.
     * @param prod A temporary product that is either being removed or added
     * @param addOrRemove is an integer variable that says to increase or decrease the totalCost.
     */
    public void updateTotalCost(Product prod, int addOrRemove)
    {
        //check how many times is the product seen in cart, minimum once maximum items.length times
        int flag = 0;
        for(int i = 0; i < (items.length); i++)
        {
            if(items[i] == null)
                break;
            if(items[i].getName().equalsIgnoreCase(prod.getName()))    
            flag ++;
        }
        //addOrRemove = 1 then add to toalCost
        if(addOrRemove == 1)
        {
            if(flag > 0)
            if(prod.getQtyOnHand() >= ((flag)*prod.getMinOrderQty()))
                totalCost += prod.getPrice()*prod.getMinOrderQty();
        }
        //addOrRemove =2 then subtract from totalCost
        else if (addOrRemove == 2)
        {
            if(flag > 0)
            if(prod.getQtyOnHand() >= ((flag)*prod.getMinOrderQty()))
                totalCost -= prod.getPrice()*prod.getMinOrderQty();
        }
        System.out.println("You pay $"+totalCost+ " at checkout. ");
    }
    
    /**
     * Method that removes a product from the cart and updates the totalCost.
     */
    public void dropProduct()
    {
        //if no products in cart display error
        if(items[0] == null)
        {
            System.out.println("There are no products in the cart. Add some products to cart first.");
        }
        else
        {
            System.out.println("Select from the following list.");
            System.out.println();
            displayProductDetails();
            System.out.println();
            System.out.print("Select the product you want to drop or type 'exit' to return to main menu: ");
            Scanner sc = new Scanner(System.in);
            String ch = sc.next();
            System.out.println();
            while(true)
            {
                if(ch.equalsIgnoreCase("exit"))
                    break;
                    
                int choice = Integer.parseInt(ch);  //user choice
                Product[] temp = new Product[3];    //temporary cart
                int k=0;
                updateTotalCost(items[choice-1], 2);//update cost
                for(int i=0 ; i < items.length ;i++)
                {
                    if(items[i] == null)
                        break;
                    if(i == choice-1)
                        ;                           //ignore the chosen product
                    else
                        temp[k++] = items[i];       //add others to temporary cart
                }
                
                items = temp;                       //assign cart to temporary cart
                if(items[0] == null)
                {
                    System.out.println("Cart is empty");
                    break;
                }
                displayProductDetails();
                System.out.print("Select the product you want to drop or type 'exit' to return to main menu:");
                ch = sc.next();                     //users choice repeats until "exit"
                System.out.println();
                
            }
        }
    }
    
    /**
     * Method that is responsible for the checkout option (option 5)
     * It decreases the quantity of the product if its available for purchases.
     * Displays salecode and totalCost
     * Resets the whole cart to null and totalCost to 0.0 for next saleTransaction
     * @param salecode 
     */
    public void commitChanges1(int salecode)
    {
        Product prod = new Product();
        int i = 0;
        double cost = 0.0;
        int length = 0;
        while(i < items.length)
        {
            if(items[i] == null)
                break;
            i++;
        }
        length = i; //size of the cart
        i = 0;
        
        while(i < length)
        {
            if(items[i] == null)
                break;

            boolean flag = items[i].getQtyOnHand() < items[i].getMinOrderQty(); //check if product can be bought
            if (flag)
            {
                //if no display error
                System.out.println("Cannot proceed with checkout as the quantity is less for the product:" +items[i].getName());
                System.out.println();
            }
            else
            {
                //if yes then proceed with checkout
                cost = cost + (items[i].getMinOrderQty()*items[i].getPrice());
                items[i].setQuantity(items[i].getQtyOnHand()-items[i].getMinOrderQty());
            }   
            i++;
        }
        //if cart is null print error
        if(items[0] == null)
            System.out.println("You cannot proceed with checkout because you didnt add any product to cart");
        //else print the salecode and total cost
        else
        {
            setSaleCode(salecode);
            System.out.println();
            System.out.println();
            System.out.println(" Sale code No: "+ saleCode);
            System.out.println("The total cost you pay is $"+ totalCost);
            System.out.println("Thanks for the purchases.");
        }
        Product temp[] = new Product[3];
        items = temp;                       //reset the cart
        setTotalCost(0.0);                  //reset the totalCost
    }
    
    /**
     * Method that returns the size of the array 
     * @return i an integer that says how many products are there in the cart
     */
    public int getSize()
    {
        int i = 0;
        while(i < items.length)
        {
            if(items[i] == null)
                break;
            else
                i++;
        }
        return i;
    }
}