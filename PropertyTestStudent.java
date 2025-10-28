import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertyTestStudent {
	
	private Property p1, p2, p3, p4; // MUST be Property
	private Plot plotA, plotB;

	@Before
	public void setUp() throws Exception {
		plotA = new Plot(1, 1, 2, 2); 
		plotB = new Plot(5, 5, 3, 3); 
		
		p1 = new Property("TestName1", "CityA", 1500.0, "OwnerA", plotA);
		
		p2 = new Property("TestName2", "CityB", 2500.0, "OwnerB", 5, 5, 3, 3);
	}

	@After
	public void tearDown() throws Exception {
		p1 = p2 = p3 = p4 = null;
		plotA = plotB = null;
	}
	
	@Test
	public void testNoArgConstructor() {
		p3 = new Property();
		assertEquals("", p3.getPropertyName());
		assertEquals(0.0, p3.getRentAmount(), 0.001);
		assertEquals(0, p3.getPlot().getX());
		assertEquals(1, p3.getPlot().getWidth());
	}
	
	@Test
	public void testCopyConstructor() {
		p4 = new Property(p1);
		assertEquals("TestName1", p4.getPropertyName());
		assertEquals(1500.0, p4.getRentAmount(), 0.001);
		
		Plot p4Plot = p4.getPlot();
		p4Plot.setX(99); 
		assertEquals(1, p1.getPlot().getX());
	}

	@Test
	public void testGetters() {
		assertEquals("TestName1", p1.getPropertyName());
		assertEquals("CityA", p1.getCity());
		assertEquals(1500.0, p1.getRentAmount(), 0.001);
		assertEquals("OwnerA", p1.getOwner());
		assertEquals(1, p1.getPlot().getX()); 
		assertEquals(2, p1.getPlot().getWidth());
	}

	@Test
	public void testSetters() {
		p1.setPropertyName("NewName");
		p1.setCity("NewCity");
		p1.setRentalAmount(3000.0);
		p1.setOwner("NewOwner");
		
		Plot newPlot = new Plot(10, 10, 5, 5);
		p1.setPlot(newPlot);
		
		assertEquals("NewName", p1.getPropertyName());
		assertEquals(3000.0, p1.getRentAmount(), 0.001);
		assertEquals(10, p1.getPlot().getX());
		assertEquals(5, p1.getPlot().getWidth());
		
		newPlot.setX(0); 
		assertEquals(10, p1.getPlot().getX());
	}

	@Test
	public void testToString() {
		assertEquals("TestName2,CityB,OwnerB,2500.0", p2.toString());
		
		p1.setRentalAmount(1234.567);
		assertEquals("TestName1,CityA,OwnerA,1234.567", p1.toString());
	}
}