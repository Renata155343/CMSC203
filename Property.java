 /* Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: keeps property info
 *  * Due: 10/27/2025
 * Platform/compiler:Eclipse
 * I pledge that I have completed the programming 
assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Renata Podlesny
*/


public class Property 
{
	//vars
	private String propertyName, city, owner;
    private double rentalAmount;
    private Plot plot;
	
    //constructors
    public Property() { //no args
        this.propertyName = "";
        this.city = "";
        this.rentalAmount = 0.0;
        this.owner = "";
        this.plot = new Plot(); //runs the default plot constructor
    }
    
    public Property(String propertyName, String city, double rentalAmount, String owner, Plot plot) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentalAmount = rentalAmount;
        this.owner = owner;
        this.plot = new Plot(plot); //deep copy
    }
    
    public Property(String propertyName, String city, double rentalAmount, String owner, int x, int y, int width, int depth) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentalAmount = rentalAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }
    
    public Property(Property otherProperty) { //copy
        this.propertyName = otherProperty.propertyName;
        this.city = otherProperty.city;
        this.rentalAmount = otherProperty.rentalAmount;
        this.owner = otherProperty.owner;
        this.plot = new Plot(otherProperty.plot); //deep copy
    }
    
    public Property(String propertyName, String city, double rentalAmount, String owner) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentalAmount = rentalAmount;
        this.owner = owner;
        this.plot = new Plot();
    }
    
    //getters
    public String getPropertyName() {
        return propertyName;
    }
    public String getCity() {
        return city;
    }
    public double getRentAmount() {
        return rentalAmount;
    }
    public String getOwner() {
        return owner;
    }
    public Plot getPlot() {
        return new Plot(this.plot); 
    }
    
    //setters
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public void setPlot(Plot plot) {
        this.plot = new Plot(plot);
    }
    
    public String toString() {
        return propertyName + "," + city + "," + owner + "," + rentalAmount; //no spaces
    }
}
