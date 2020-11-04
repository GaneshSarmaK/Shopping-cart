/** 
 *  RandomNumberGenerator class:
 *  This class is responsible for adding products to cart and removing products from cart.
 *  It also does the checkout and display the total cost for the checked out products from the cart.
 *  @author Sarma K.
 *  @version 8th Sept 2018
 */

public class RandomNumberGenerator
{
    private int minimumValue;
    private int maximumValue;
    
    /**
     * A Constructor for the RandomNumberGenerator class
     */
    public RandomNumberGenerator()
    {
        
    }
    
     /**
     * A  Parameterized Constructor for the RandomNumberGenerator class
     * @param min stores the minimum value
     * @param max stores the maximum value
     */
    public RandomNumberGenerator(int min,int max)
    {
        minimumValue = min;
        maximumValue = max;
    }
    
    /**
     * A method that generates a random number between the ranges given to it by using Math.random() method
     * @param min integer values that specifies the minimum value possible
     * @param max integer values that specifies the maximum value possible 
     * @return (int)(Math.random() * range) + minimumValue a random number between min and max values 
     */
    public int generate(int min, int max)
    {
        setMinimumValue( min );
        setMaximumValue( max );
        int range = ( maximumValue - minimumValue ) + 1;     
        return (int)(Math.random() * range) + minimumValue;
    }
    
    /**
     * A mutator method to set the Minimum value
     * @param minimumValue an integer that sets the minimum value of the random number 
     */
    public void setMinimumValue(int minimumValue)
    {
        this.minimumValue = minimumValue;
    }
    
    /**
     * A mutator method to set the Maximum value
     * @param maximumValue an integer that sets the maximum value of the random number
     */
    public void setMaximumValue(int maximumValue)
    {
        this.maximumValue = maximumValue;
    }
}