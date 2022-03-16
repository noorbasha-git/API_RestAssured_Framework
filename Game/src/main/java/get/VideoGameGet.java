package get;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

@SuppressWarnings("unused")
public class VideoGameGet {
	
	@Test(priority=1)	
	public void GET_getVideogmaes(){
		
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames")
			
		.then()
			.statusCode(200)
			.log().toString();
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void POST_createvidoegames() {
		
		HashMap payload=new HashMap();
		payload.put("id", 1003);
		payload.put("name","spider");
		payload.put("releaseDate","2022-03-16T09:18:16.821Z");
		payload.put("reviewScore",5);
		payload.put("category","adventure");
		payload.put("rating","Universal");
		
		
		
		
		Response res=
		given()
			.contentType("application/json")
			.body(payload)
			
		.when()
			.post("http://localhost:8080/app/videogames")
		
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonstring=res.asString();
		Assert.assertEquals(jsonstring.contains("Record Added Successfully"),true);	
		
		
	}
	
	@Test(priority=3)
	public void getVideoGamebyID() {
		
		given()
		
		.when()
			.get("http://localhost:8080/app/videogames/1003")
		
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id",equalTo("1003"))
			.body("videoGame.name",equalTo("spider"));
			
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority=4)
	public void PUT_changedata() {
		HashMap payload2=new HashMap();
		payload2.put("id", 1003);
		payload2.put("name","spider-man");
		payload2.put("releaseDate","2022-03-16T09:18:16.821Z");
		payload2.put("reviewScore",5);
		payload2.put("category","adventure");
		payload2.put("rating","Universal");
		
		given()
			.contentType("application/json")
			.body(payload2)
		
		.when()
			.put("http://localhost:8080/app/videogames/1003")
		
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id",equalTo("1003"))
			.body("videoGame.name",equalTo("spider-man"));
		
		
	
	}
	
	@Test(priority=5)
	public void deletegme() {
		
		Response res=
		given()
		
		.when().delete("http://localhost:8080/app/videogames/1003")
		
		.then().statusCode(200)
		.log().body()
		.extract().response();
		
			
		String response=res.asString();
		Assert.assertEquals(response.contains("Record Deleted Successfully"),true);
	}
	

}
