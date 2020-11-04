/**
 *  ProductList class:
 *  This class is responsible for registering products and using these products for further transactions.
 *  @author Sarma K.
 *  @version 8th Sept 2018
 */

import java.util.*;
public class ProductList
{
    private Product listOfProducts[];
    
    /**
     * Constructor for the class ProductList
     * instantiates the array listOfProducts
     */
    public ProductList()
    {
        listOfProducts = new Product[5];
    }
    
    /**
     * A display method that displays all the products registered using option 1 from Sale class
     */
    public void displayProductList()
    {

       for(int i = 0; i< listOfProducts.length  ; i++ )
       {
        if(listOfProducts[i] == null) 
            break;
        System.out.println("Product: "+(i+1));
        listOfProducts[i].display();
       }
    }
    
    /**
     * A method to check if the listOfProducts is full or not.
     * It also finds the index value where we can add a new product
     * @return i an integer that sends the index value when listOfProducts is empty
     */
    public int check1()
    {
        int i=1;
        if(listOfProducts[0] == null)
            return 0;               //listOfProducts is empty
        else
        {
            while(i < listOfProducts.length)
            {
                if(listOfProducts[i] == null)
                {
                    break;
                }
                i++;
            }
            return i;
        }
    }
    
    /**
     * A method to check if the entered name for the product (name being case insensitive) already exists .
     * @return flag a boolean flag that tells us if the name already exists or not
     */
    public boolean nameCheck(String name)
    {
        boolean flag = false;
        for(int i = 0; i < listOfProducts.length ; i++ )
        {
            if(listOfProducts[i] == null )
                break;
            if( name.equalsIgnoreCase(listOfProducts[i].getName()))  
            {                
                flag = true;    //the product with same name exists
            }
        }
        return flag;
    }
    
    /**
     * A method to check if the chosen product exists .
     * @param i an integer index passed from Sale class
     * @return flag a boolean that says if the product with the index choice exists or not.
     */
    
    public boolean checkProductList(int i)  
    {
        boolean flag;
        if(listOfProducts[i] != null)
            flag =  false;
        else
            flag =  true;
        return flag;
    }
    
    /**
     * A method to get the chosen product to send it to SaleTransaction .
     * @param choice an integer choice for index 
     * @return listOfProducts[choice-1] a n object of Product class
     */
    public Product getProduct(int choice)
    {
         return listOfProducts[choice-1];
    }
    
    /**
     * A method to add a Product to the listOfProducts i.e. registering a product option 1
     * @param name name of the Product object
     * @param desc description of the Product object
     * @param price price of the Product object 
     * @param qty quantity available of the Product object
     * @param minqty minimum quantity of the Product object 
     * @param index an integer index to store the product in object of ProductList 
     */
    public void addProduct(String name, String desc, double price, int qty, int minqty, int index)
    {
        Product product = new Product(name, desc, price, qty, minqty);
        listOfProducts[index] = product;        //assign product object to listOfProducts[index]
    }
    
    
}
