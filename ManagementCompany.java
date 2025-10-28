
/*
 * Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: main data manager that colelcts data in array, checks properties fit, and keeps track of rent
 * Due: 10/27/2025
 * Platform/compiler:Eclipse
 * I pledge that I have completed the programming 
assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Renata Podlesny
*/


public class ManagementCompany 
{
	//vars
	public static final int MAX_PROPERTY = 5, MGMT_WIDTH = 10, MGMT_DEPTH = 10;
	private String name, taxID;
    private double mgmFee; 
    private Property[] properties;
    private Plot plot;
    private int propertiesNum;
    
    //constructors
    public ManagementCompany() { //no args
        this.name = "";
        this.taxID = "";
        this.mgmFee = 0.0;
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH); 
        this.properties = new Property[MAX_PROPERTY];
        this.propertiesNum = 0;
    }
    
    public ManagementCompany(String name, String taxID, double mgmFee) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFee = mgmFee;
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
        this.properties = new Property[MAX_PROPERTY];
        this.propertiesNum = 0;
    }
    
    public ManagementCompany(String name, String taxID, int mgmFee) { //to handle ints
        this(name, taxID, (double) mgmFee); //converts to double and calls double constructor
    }
    
    public ManagementCompany(ManagementCompany otherCompany) { //copy
        this.name = otherCompany.name;
        this.taxID = otherCompany.taxID;
        this.mgmFee = otherCompany.mgmFee;
        this.plot = new Plot(otherCompany.plot); //copies plot
        this.properties = new Property[MAX_PROPERTY];
        this.propertiesNum = 0;
        for (int i = 0; i < otherCompany.propertiesNum; i++) { //so the property array is a deep copy
            this.properties[i] = new Property(otherCompany.properties[i]);
            this.propertiesNum++;
        }
    }
    
    //getters
    public String getName() {
        return name;
    }
    public String getTaxID() {
        return taxID;
    }
    public double getMgmFee() {
        return mgmFee;
    }
    public Plot getPlot() {
        return new Plot(this.plot);
    }
    
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }
    public void setMgmFee(double mgmFee) {
        this.mgmFee = mgmFee;
    }
    
    //methods
    public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) { //so u dont have to make an object
        Property newProperty = new Property(name, city, rent, owner, x, y, width, depth);
        return addProperty(newProperty);
    }
    
    public int addProperty(Property property) {  //overload to add existing object to property array
        if (isPropertiesFull()) 
            return -1; //error
      
        if (property == null) 
            return -2; //errror
        Plot propertyPlot = property.getPlot(); // Get a copy of the new property's plot

        if (!this.plot.encompasses(propertyPlot)) 
            return -3; //error

        for (int i = 0; i < propertiesNum; i++) 
        {
            // properties[i] is an existing Property
            Plot existingPlot = properties[i].getPlot(); 
            
            if (propertyPlot.overlaps(existingPlot)) 
                return -4; //error
        }

        properties[propertiesNum] = new Property(property); //if everything passes this runs and adds the property
        int indexAdded = propertiesNum;       
        propertiesNum++;
        return indexAdded;
    }
    
    public double getTotalRent() {
        double totalRent = 0.0;
        for (int i = 0; i < propertiesNum; i++) 
        {
            if (properties[i] != null) 
                totalRent += properties[i].getRentAmount();
        }
        return totalRent;
    }
    
    public Property getHighestRentProperty() {
        if (propertiesNum == 0) 
            return null;

        Property highestRent = properties[0];
        double maxRent = properties[0].getRentAmount();

        for (int i = 1; i < propertiesNum; i++) 
        {
            if (properties[i].getRentAmount() > maxRent) 
            {
                maxRent = properties[i].getRentAmount();
                highestRent = properties[i];
            }
        }
        return new Property(highestRent); 
    }
    
    public void removeLastProperty() {
        if (propertiesNum > 0) 
        {
            properties[propertiesNum - 1] = null; //uses the last property
            propertiesNum--;
        }
    }
    
    public boolean isPropertiesFull() { //just convenient to seperate it here for functionality and clarity
        return propertiesNum == MAX_PROPERTY;
    }
    
    public int getPropertiesCount() {
        return propertiesNum;
    }
    
    public boolean isMangementFeeValid() {
        return this.mgmFee >= 0.0 && this.mgmFee <= 100.0;
    }
    
    public String toString() {
        String out = "List of the properties for " + name + ", taxID: " + taxID + "\n";
        out += "______________________________________________________\n";

        for (int i = 0; i < propertiesNum; i++) //lists all properties
        { 
        	if (properties[i] != null) 
        	{
                out += properties[i].toString();
                if (i < propertiesNum - 1) //only adds newline if its not the last property, for formatting
                    out += "\n";
            }
        }

        out += "\n______________________________________________________";

        double totalRent = getTotalRent(); //figuring out fee
        double totalManagementFee = totalRent * (mgmFee / 100.0);
        out += "\n\n total management Fee: " + totalManagementFee;

        return out;
    }
    
}
