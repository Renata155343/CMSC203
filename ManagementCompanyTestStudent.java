import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ManagementCompanyTestStudent {
	
	ManagementCompany company;
	Property p1, p2, p3, p4, p5, p_outside, p_overlap;

	//consts
	private static final double MGMT_FEE = 6.0;
	private static final String COMPANY_NAME = "Railey";
	private static final String TAX_ID = "555555555";

	@Before
	public void setUp() throws Exception {
		company = new ManagementCompany(COMPANY_NAME, TAX_ID, MGMT_FEE);

		p1 = new Property("P1_Valid", "City1", 2000.0, "Owner1", 1, 1, 2, 2);
		p2 = new Property("P2_Valid", "City2", 3000.0, "Owner2", 4, 1, 2, 2);
		p3 = new Property("P3_Valid", "City3", 4500.0, "Owner3", 7, 1, 2, 2);
		
		p4 = new Property("P4_Valid", "City4", 1000.0, "Owner4", 1, 4, 2, 2);
		p5 = new Property("P5_Valid", "City5", 5000.0, "Owner5", 4, 4, 2, 2);

		p_outside = new Property("P_Outside", "OutsideCity", 100.0, "OwnerOut", 9, 9, 2, 2); 
		
		p_overlap = new Property("P_Overlap", "OverlapCity", 500.0, "OwnerOL", 2, 2, 2, 2);
	}

	@After
	public void tearDown() throws Exception {
		company = null;
		p1 = p2 = p3 = p4 = p5 = p_outside = p_overlap = null;
	}

	@Test
	public void testManagementCompanyConstructors() {
		ManagementCompany defaultCo = new ManagementCompany();
		assertEquals("", defaultCo.getName());
		assertEquals(0.0, defaultCo.getMgmFee(), 0.001);

		ManagementCompany intCo = new ManagementCompany("IntCo", "111", 5);
		assertEquals(5.0, intCo.getMgmFee(), 0.001);

		ManagementCompany copyCo = new ManagementCompany(company);
		assertEquals(COMPANY_NAME, copyCo.getName());
		assertEquals(MGMT_FEE, copyCo.getMgmFee(), 0.001);
	}
	
	@Test
	public void testGettersAndSetters() {
		company.setName("NewName");
		company.setTaxID("999");
		company.setMgmFee(7.5);
		
		assertEquals("NewName", company.getName());
		assertEquals("999", company.getTaxID());
		assertEquals(7.5, company.getMgmFee(), 0.001);

		Plot mgmtPlot = company.getPlot();
		assertEquals(10, mgmtPlot.getWidth()); 
		assertEquals(0, mgmtPlot.getX());     
	}

	@Test
	public void testAddProperty_SuccessAndCapacity() {

		assertEquals(0, company.addProperty(p1));
		assertEquals(1, company.getPropertiesCount());

		assertEquals(1, company.addProperty(p2));
		assertEquals(2, company.addProperty(p3));
		assertEquals(3, company.addProperty(p4));
		assertEquals(4, company.addProperty(p5)); 
		assertEquals(5, company.getPropertiesCount());

		Property p_tooMany = new Property("TooMany", "CityX", 100.0, "OwnerX", 8, 8, 1, 1);
		assertEquals(-1, company.addProperty(p_tooMany));
	}
	
	@Test
	public void testAddProperty_NullCheck() {
		assertEquals(0, company.addProperty(p1));

		assertEquals(-2, company.addProperty(null));
		assertEquals(1, company.getPropertiesCount()); 
	}
	
	@Test
	public void testAddProperty_EncompassCheck() {
		assertEquals(0, company.addProperty(p1));

		assertEquals(-3, company.addProperty(p_outside));
		assertEquals(1, company.getPropertiesCount()); 
	}
	
	@Test
	public void testAddProperty_OverlapCheck() {
		assertEquals(0, company.addProperty(p1));

		assertEquals(-4, company.addProperty(p_overlap));
		assertEquals(1, company.getPropertiesCount());
	}

	@Test
	public void testGetTotalRent() {
		company.addProperty(p1);
		company.addProperty(p2); 
		company.addProperty(p3); 
	
		assertEquals(9500.0, company.getTotalRent(), 0.001);
	}
	
	@Test
	public void testGetHighestRentProperty() {
		company.addProperty(p1); 
		company.addProperty(p2);
		company.addProperty(p3); 
		company.addProperty(p4);
		
		Property highest = company.getHighestRentProperty();
		assertEquals("P3_Valid", highest.getPropertyName());
		assertEquals(4500.0, highest.getRentAmount(), 0.001);

		highest.setRentalAmount(999.0);
		assertEquals(4500.0, company.getHighestRentProperty().getRentAmount(), 0.001);
	}
	
	@Test
	public void testRemoveLastProperty() {
		company.addProperty(p1); 
		company.addProperty(p2);
		company.addProperty(p3);
		assertEquals(3, company.getPropertiesCount());

		company.removeLastProperty();
		assertEquals(2, company.getPropertiesCount());

		assertEquals(3000.0, company.getHighestRentProperty().getRentAmount(), 0.001);

		company.removeLastProperty();
		assertEquals(1, company.getPropertiesCount());
	
		assertEquals(2000.0, company.getHighestRentProperty().getRentAmount(), 0.001);
	}

	@Test
	public void testIsMangementFeeValid() {
		// Valid fees
		assertTrue(company.isMangementFeeValid());
		company.setMgmFee(0.0);
		assertTrue(company.isMangementFeeValid()); 
		company.setMgmFee(100.0);
		assertTrue(company.isMangementFeeValid());

		company.setMgmFee(-0.1);
		assertFalse(company.isMangementFeeValid());
		company.setMgmFee(100.1);
		assertFalse(company.isMangementFeeValid());
	}
	
	@Test
	public void testToString() {
		company.addProperty(p1); 
		company.addProperty(p2); 
		
		double totalRent = 5000.0;
		double fee = totalRent * (6.0 / 100.0); 
		
		String expectedString = "List of the properties for " + COMPANY_NAME + ", taxID: " + TAX_ID + "\n"
				+ "______________________________________________________\n"
				+ p1.toString() + "\n"
				+ p2.toString() + "\n"
				+ "______________________________________________________\n"
				+ " total management Fee: " + fee;
		
		expectedString = "List of the properties for Railey, taxID: 555555555\n"
				+ "______________________________________________________\n"
				+ "P1_Valid,City1,Owner1,2000.0\n"
				+ "P2_Valid,City2,Owner2,3000.0\n"
				+ "______________________________________________________\n\n"
				+ " total management Fee: 300.0";

		assertEquals(expectedString, company.toString());
	}
}