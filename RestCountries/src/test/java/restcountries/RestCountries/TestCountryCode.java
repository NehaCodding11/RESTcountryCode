package restcountries.RestCountries;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Scanner;

import org.hamcrest.text.IsEmptyString;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//SampleApi = https://restcountries.eu/rest/v2/alpha/col
//name= https://restcountries.eu/rest/v2/name/{parameter}
//code= https://restcountries.eu/rest/v2/alpha/{code parameter}

public class TestCountryCode {
	@Test
	public void check_URI() {
		//Here we are testing that our given URI is working or not...
		String URI = "https://restcountries.eu/rest/v2/";
		RestAssured.baseURI = URI;
		Response res = RestAssured.given().contentType("application/json").get();
		//getting status coed
		System.out.println("Status code id : " +res.getStatusCode());
		//getting response as string
		System.out.println(res.asString());
		System.out.println("Response received");
		System.out.println("***********************************");
		
	}
	
	@Test(priority=1)
	public void param_countryCode() {
		Scanner sc = new Scanner(System.in);
		//getting user input for code
		
		System.out.println("Type the code: ");
		String code = sc.nextLine();// for example: in/New Delhi
		
		
			//This is for capital city
			System.out.println("Print the capital city:");
			String CapitalName = sc.nextLine();
			
			//getting response
			Response res1 = RestAssured.given().when().get("https://restcountries.eu/rest/v2/alpha/{countrycode}",code);
			System.out.println("Status code id : "+ res1.statusCode());
			System.out.println(res1.asString());
			String capital = res1.jsonPath().get("capital");
			System.out.println("Country Capital : "+capital);
			
			//valildating our code
			assertEquals(200, res1.getStatusCode());
			System.out.println("Test complete with country code *************");
			
			
	}
	
	@Test(priority=2)
	public void param_countryname() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the country name: ");
		String name = sc.nextLine();
		
		Response res2 = RestAssured.given().when().get("https://restcountries.eu/rest/v2/name/{countryname}",name);
		
		System.out.println("Status code :" +res2.statusCode());
		System.out.println(res2.asString());
		
		//Taking th eresponse in the List
		List<String> capitalList = res2.jsonPath().get("capital");
		for(String capital : capitalList) {
			System.out.println("capital name : "+capital);
			
		}
		
		assertEquals(200, res2.getStatusCode());
		System.out.println("Test complete with country name**************");
	}
	
	
	//For negative test scenario
	@Test(priority=3)
	public void wrong_URI() {
		System.out.println(" This is a negative scenario ");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the wrong URI ");
		String alpha2Code = sc.nextLine();
		
		Response res3 = RestAssured.given().when().get("https://restcountries.eu/rest/v2/alpha/{countrycode}",alpha2Code);
		System.out.println("Status code :" +res3.statusCode());
		System.out.println(res3.getStatusCode());
		
		assertEquals(400, res3.getStatusCode());
		System.out.println("Test complete for negative Scenario: *******************");
		
	}
	
	@Test(priority=4)
	public void Wrong_Method() {
		System.out.println("Using wrong method");
		String URI = "https://restcountries.eu/rest/v2/alpha123/th/";
		RestAssured.baseURI = URI;
		
		Response res4 = RestAssured.given().contentType("app;lication/json").post();
		System.out.println(res4.getStatusCode());
		
		///asserting our test
		assertEquals(400, res4.getStatusCode());
		System.out.println("Test for wrong method is done*************");
	}
	
	@Test(priority=5)
	public void invalid_param() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the invalid parameters ");
		String invparam = sc.nextLine();
		
		Response res5 = RestAssured.given().when().get("https://restcountries.eu/rest/v2/alpha/{countrycode}",invparam);
		
		System.out.println("Status code :" +res5.statusCode());
		System.out.println(res5.asString());
		
		assertEquals(400, res5.getStatusCode());
		
		System.out.println("Test for wrong method is done*************");
	}
	
	@Test(priority=6)
	public void empty_param() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the empty parameters ");
		String emptyparam = sc.nextLine();
		
		Response res6 = RestAssured.given().when().get("https://restcountries.eu/rest/v2/alpha/{countrycode}",emptyparam);
		
		System.out.println("Status code :" +res6.statusCode());
		System.out.println(res6.asString());
		
		assertEquals(400, res6.getStatusCode());
		
		System.out.println("Test for empty param is done*************");
	}
	
}	