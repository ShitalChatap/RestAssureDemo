package com.maveric.project.test;


import static org.hamcrest.CoreMatchers.equalToObject;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.project.pojo.EmployeeCsvPojo;
import com.maveric.project.pojo.EmployeePojo;
import com.opencsv.bean.CsvToBeanBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.support.FileReader;
import io.restassured.response.Response;
import junit.framework.Assert;

public class RestApiTestold 
{
	@BeforeClass
	public static void  setUpEnv()
	{
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";	
		
	}
	@Test(testName="Get All Employee Details")
	
	
	public void test1()
	{
		//normal response required Assert to test (old way)
		Response response =RestAssured.get("/employees");
		Assert.assertEquals(response.getStatusCode(),200);
		//validate response
		
		/*String responseBody =RestAssured.get("/employees")
		.then().assertThat()
		.statusCode(200)
		.and()
		.log()
		.body()
		.toString();

         System.out.println(responseBody);*/

     }
	@Test(testName="Get Specific employee details(using path parameter)")
	public void test2()
	{
		RestAssured.get("/employee/1")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("employee_name",equalToObject("Tiger Nixon"));
	}
	@Test(testName="Add new employee details")
	public void test4()
	{
		String payLoad ="{name:test,salary:123,age:23}";
		RestAssured.given()
		.contentType(ContentType.JSON)
		.body(payLoad)
		.post("/create")
		.then()
        .assertThat()	
        .statusCode(HttpStatus.SC_CREATED)
        .and()
        .body("message",equalToObject("Successfully!Record has been added."));
}
	@Test(testName="Add new employee deatials using hashmap")

	public void test5() 
	{
		HashMap <String,String> payload=new HashMap<>();
		payload.put("name", "shital");
		payload.put("salary", "2000");
		payload.put("age", "27");
		
		
		RestAssured.given().contentType(ContentType.JSON)
		.body(payload)
		.post("/create")
		.then().assertThat()
		.statusCode(HttpStatus.SC_CREATED)
		.and()
	//	.assertThat().body("message",equalToObject("Successfully! Record has been added."));
        .body(CoreMatchers.containsString("Successfully"));
	}
	
	@Ignore
	@Test(testName = "Add new employee details using employee.json file  ")
	public void test7() throws DatabindException, IOException
	{
 
		ObjectMapper mapper = new ObjectMapper();
		FileInputStream stream = new FileInputStream(new File("./TestData/EmployeeData.json"));
 
		EmployeePojo payLoad = mapper.readValue(stream, EmployeePojo.class);
 
		RestAssured.given().contentType(ContentType.JSON).body(payLoad).post("/create").then().assertThat()
				.body("message",equalToObject("Successfully! Record has been added."));
	}
 
	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Test(testName = "Add new employee details using employee csv file")
//	public void test8() throws DatabindException, IOException {
//		String uri = "/create";
// 
//		/*
//		 * FileReader reader = new FileReader("./TestData/EmployeeMockData.csv");
//		 * CsvToBeanBuilder<EmployeeCsvPojo> beanBuilder = new CsvToBeanBuilder(reader);
//		 * beanBuilder = beanBuilder.withType(EmployeeCsvPojo.class);
//		 * CsvToBean<EmployeeCsvPojo> toBean = beanBuilder.build();
//		 * List<EmployeeCsvPojo> beans = toBean.parse();
//		 */
// 
//
//		List<EmployeeCsvPojo> beans = new CsvToBeanBuilder(new FileReader("./Test/EmployeeMockData.csv")) // example of builder pattern
//				.withType(EmployeeCsvPojo.class).build().parse();
// 
//		  RestAssured.given() 
//		  .contentType(ContentType.JSON) .
//		  body(beans) .
//		  post(uri)
//		  .then() .
//		  assertThat().
//		  body("message",  equalToObject("Successfully! Record has been added."));
//	}
    @AfterClass
    public static void tearDownEnv() 
    {
     RestAssured.reset();
    }
    
}
	


