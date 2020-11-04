
/** 
 *  Product class:
 *  This class is responsible for creation and manipulation of Product objects.
 *  It creates product objects and can change the values of parameters when required.
 *  @author Sarma K.
 *  @version 8th Sept 2018
 */

public class Product
{
    private String name;
    private String desc;
    private double price;
    private int qtyOnHand;
    private int minOrderQty;
    
    /**
     * Constructor for the class Product
     * Sets price to 0.0
     */
    public Product()
    {
       double price = 0.0;
       
    }
    
    /**
     * Constructor for the class Product
     * Sets the values of the product using the values passed to it when invoked
     * @param name stores name of the product
     * @param desc stores description of the product
     * @param price stores price of the product
     * @param qtyOnHand stores quantity available of the product
     * @param minOrderQty stores minimum order quantity of the product
     */
    public Product(String name, String desc, double price, int qtyOnHand, int minOrderQty)
    {
        
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.qtyOnHand = qtyOnHand;
        this.minOrderQty = minOrderQty;
    }
    
    /**
     * Accessor method for description.
     * return the description of the product value when invoked
     * @return desc
     */
    public String getDesc()
    {
        return desc;
    }
    
    /**
     * Accessor method for minOrderQty.
     * return the Minimum Order Qtantity of the product value when invoked
     * @return minOrderQty
     */
    public int getMinOrderQty()
    {
        return minOrderQty;
    }
    
    /**
     * Accessor method for name.
     * return the name of the product value when invoked
     * @return name
     */
    
    public String getName()
    {
        return name;
    }
    
    /**
     * Accessor method for price.
     * return the price value when invoked
     * @return price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Accessor method for qtyOnHand.
     * return the quantity available value when invoked
     * @return qtyOnHand
     */
    public int getQtyOnHand()
    {
        return qtyOnHand;
    }
    
    /**
     * Mutator method for saleCode.
     * sets the qtyOnHand value when invoked
     * @param qtyOnHand
     */
    public void setQuantity(int qtyOnHand)
    {
        this.qtyOnHand = qtyOnHand;
    }
    
    /**
     * Mutator method for name.
     * sets the name of the product when invoked
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Mutator method for desc.
     * sets the description of the produc when invoked
     * @param desc
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    
    }
    
    /**
     * Accessor method for price.
     * return the price value when invoked
     * @return price
     */
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    /**
     * Mutator method for minOrderQty.
     * sets the minimum order quantity value when invoked
     * @param minOrderQty
     */
    public void setMinOrderQty(int minOrderQty)
    {
        this.minOrderQty = minOrderQty;
    }
    
    /**
     * Method for displaying a product.
     * Used in ProductList class and SaleTracsaction class to display products
     */
    public void display()
    {
        System.out.println("Name: "+getName());
        System.out.println(" Description: "+getDesc());
        System.out.println(" Quantity: "+getQtyOnHand());
        System.out.println(" Price: $"+getPrice());
        System.out.println(" Min Order Quantity: "+getMinOrderQty()); 
        System.out.println();
    }
}
