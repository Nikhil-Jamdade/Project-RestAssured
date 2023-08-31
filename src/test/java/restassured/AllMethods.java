package restassured;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AllMethods {

	Response response;
	String baseUri = "http://localhost:8088/employees";

	public static Response getAllEmployee() {

		Response response = RestAssured.given().baseUri("http://localhost:8088/employees").when().get();
		return response;

	}

	public static Response getSingleEmployee(int id) {

		Response response = RestAssured.given().baseUri("http://localhost:8088/employees/" + id + "/").when().get();
		return response;

	}

	public static Response createEmployee(String firstname, String lastname, int salary, String email) {

		HashMap<String, Object> requestBody = new HashMap<String, Object>();

		requestBody.put("firstName", firstname);
		requestBody.put("lastName", lastname);
		requestBody.put("salary", salary);
		requestBody.put("email", email);

		RestAssured.baseURI = "http://localhost:8088/employees";
		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody).post();
		return response;

	}

	public static Response update_employee(String firstname, int id, String lastname, int salary, String email) {

		HashMap<String, Object> requestBody = new HashMap<String, Object>();

		requestBody.put("firstName", firstname);
		requestBody.put("lastName", lastname);
		requestBody.put("salary", salary);
		requestBody.put("email", email);

		RestAssured.baseURI = "http://localhost:8088/employees/" + id + "/";
		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody).put();
		return response;

	}

	public static Response deleteEmployee(int id) {

		Response response = RestAssured.given().baseUri("http://localhost:8088/employees/" + id + "/").when().delete();
		return response;

	}
}
