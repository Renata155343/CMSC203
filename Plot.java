 /* Class: CMSC203 
 * Instructor: Huseyin Aygun
 * Description: defines property area
 * Due: 10/27/2025
 * Platform/compiler:Eclipse
 * I pledge that I have completed the programming 
assignment independently. 
 * I have not copied the code from a student or any source. 
 * I have not given my code to any student.
 * Print your Name here: Renata Podlesny
*/


public class Plot 
{
	//vars
	private int x, y, width, depth;

	//constructors
	public Plot() { //no args
        this.x = 0;
        this.y = 0;
        this.width = 1; //not set to 0 so it doesnt make problems later
        this.depth = 1;
    }
	
	public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }
	
	public Plot(Plot otherPlot) { //copy constructor
        this.x = otherPlot.x;
        this.y = otherPlot.y;
        this.width = otherPlot.width;
        this.depth = otherPlot.depth;
    }

	//getters
	public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getDepth() {
        return depth;
    }

    //setters
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setDepth(int depth) {
        this.depth = depth;
    }
	
	//methods
	public boolean overlaps(Plot plot) {
        //stores current plot boundries from object fields
        int currentX1 = this.x;
        int currentY1 = this.y;
        int currentX2 = this.x + this.width;
        int currentY2 = this.y + this.depth;

        //finds boundries of other plot
        int otherX1 = plot.x;
        int otherY1 = plot.y;
        int otherX2 = plot.x + plot.width;
        int otherY2 = plot.y + plot.depth;

        //returns true if current is completley to the left or right of other 
        boolean noXOverlap=(currentX2<=otherX1)||(otherX2<=currentX1);

        ///returns true if current is completley bellow or above other 
        boolean noYOverlap=(currentY2<=otherY1)||(otherY2<=currentY1);

        //returns false if they do not overlap
        return!(noXOverlap||noYOverlap);
    }
	
	public boolean encompasses(Plot plot) {
		//stores current plot boundries from object fields
        int currentX1 = this.x;
        int currentY1 = this.y;
        int currentX2 = this.x + this.width;
        int currentY2 = this.y + this.depth;

        //finds boundries of other plot
        int otherX1 = plot.x;
        int otherY1 = plot.y;
        int otherX2 = plot.x + plot.width;
        int otherY2 = plot.y + plot.depth;

        //if it fits inside x-wise
        boolean containedX = (otherX1 >= currentX1) && (otherX2 <= currentX2);

        //if it fits inside y-wise
        boolean containedY = (otherY1 >= currentY1) && (otherY2 <= currentY2);

        //both must be true for it to be fully contained
        return containedX && containedY;
    }
	
	public String toString() {
        return (this.x + "," + this.y + "," + this.width + "," + this.depth); //no spaces in between
    }
}
