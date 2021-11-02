package apitestsuite;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

/*
 * Top 7 priority tests are automated
 * Note: the input data is passed inside each script; it can be moved to
 * test data file that can be read from external file in real time
 */
public class APIAutomatedTest {
	
	String validUrl = "https://world.openfoodfacts.org/api/v0/product/3017620429484";
	String invalidProductId = "https://world.openfoodfacts.org/api/v0/product/0301762042948498";
	String invalidUrl = "https://world.openfoodfacts.org/api/v0/productclass";
	
	@Test(priority =1)
	public void testResponseStatusCode() {
		get(validUrl).then().assertThat().statusCode(200);
	}	
	
	@Test(priority =2)
	public void testResponsebodyWithValidProductId() {
		get(validUrl).then().assertThat().body("code", equalTo("3017620429484"));
		get(validUrl).then().assertThat().body("status", equalTo(1));
		get(validUrl).then().assertThat().body("status_verbose",equalTo("product found"));
	}
	
	@Test(priority=3)
	public void testResponseContentType() {
		get(validUrl).then().assertThat().contentType("application/json");
	}
	
	@Test(priority=4)
	public void testResponseHeader() {
		get(validUrl).then().assertThat().headers("Server","nginx/1.10.3");	
		
	}	
	
	@Test(priority=5)
	public void testResonseTime() {
		get(validUrl).then().assertThat().time(lessThan(5L),TimeUnit.SECONDS);
	}
	
	@Test(priority=6)
	public void testResponsebodyWithInvalidProductId() {
		get(invalidProductId).then().assertThat().body("status_verbose",equalTo("product not found"));
		
	}
	
	@Test(priority=7)
	public void testResponsebodyWithInvalidUrl() {
		get(invalidUrl).then().assertThat().body("status_verbose",equalTo("no code or invalid code"));
		
	}
	
}
