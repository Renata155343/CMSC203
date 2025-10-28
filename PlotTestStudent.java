import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlotTestStudent {
	
	private Plot p1, p2, p3, p4, p5;

	@Before
	public void setUp() throws Exception {
		p1 = new Plot(2, 2, 4, 4);		
		p2 = new Plot(3, 3, 1, 1);		
		p3 = new Plot(5, 5, 3, 3);		
		p4 = new Plot(6, 2, 2, 4); 		
		p5 = new Plot(7, 7, 1, 1); 
	}

	@After
	public void tearDown() throws Exception {
		p1 = p2 = p3 = p4 = p5 = null;
	}
	
	@Test
	public void testConstructors() {
		Plot defaultPlot = new Plot();
		assertEquals(0, defaultPlot.getX());
		assertEquals(1, defaultPlot.getWidth()); 
		
		Plot p_param = new Plot(10, 20, 5, 6);
		assertEquals(10, p_param.getX());
		assertEquals(6, p_param.getDepth());
		
		Plot p_copy = new Plot(p1);
		assertEquals(2, p_copy.getX());
		assertEquals(4, p_copy.getWidth());
		p_copy.setWidth(100);
		assertEquals(4, p1.getWidth());
	}
	
	@Test
	public void testGettersAndSetters() {
		p1.setX(5);
		p1.setY(10);
		p1.setWidth(7);
		p1.setDepth(8);
		
		assertEquals(5, p1.getX());
		assertEquals(10, p1.getY());
		assertEquals(7, p1.getWidth());
		assertEquals(8, p1.getDepth());
	}
	
	@Test
	public void testToString() {
		assertEquals("2,2,4,4", p1.toString()); 
		assertEquals("5,5,3,3", p3.toString());
	}
	
	@Test
	public void testOverlaps() {
		assertTrue(p1.overlaps(p3));
		assertTrue(p1.overlaps(p2));		
		assertFalse(p1.overlaps(p4));		
		assertFalse(p1.overlaps(p5));	
		assertFalse(p5.overlaps(p1));
	}
	
	@Test
	public void testEncompasses() {
		assertTrue(p1.encompasses(p2));		
		assertFalse(p1.encompasses(p3));		
		assertFalse(p1.encompasses(p4));		
		assertFalse(p1.encompasses(p5));		
		assertTrue(p1.encompasses(p1));
		
		Plot p_bound = new Plot(2, 2, 4, 4); 
		assertTrue(p1.encompasses(p_bound));
	}
}
