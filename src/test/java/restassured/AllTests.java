package restassured;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AllTests {

	public static int id;
	Response response;
	List<String> all_names;
	List<Integer> all_ids;
	int Initial_Total_Employees;
	int Total_Employees_End;

	@Test
	public void TC01_getallEmployee() {
		response = AllMethods.getAllEmployee();

		// Validate response code is 200

		int actual_response = response.statusCode();

		Assert.assertEquals(actual_response, 200);

		// Validate that number of employees are 3

		JsonPath jobject = response.jsonPath();
		all_ids = jobject.get("id");
		all_names = jobject.get("names");
		Initial_Total_Employees = all_ids.size();

	}

	@Test
	public void TC02_postEmployee() {
		response = AllMethods.createEmployee("Nikhil", "Jamdade", 100000, "nik.jam@mail.com");
		System.out.println(response.asString());

		// Validate response code is 201

		int actual_response = response.statusCode();

		Assert.assertEquals(actual_response, 201);

		JsonPath jobject = response.jsonPath();

		// Validate Nikhil is in the response

		String empname = jobject.get("firstName");
		id = jobject.get("id");
		Assert.assertEquals(empname, "Nikhil");

		System.out.println("Employeename is" + empname + " " + "emplyee id is " + id);

	}

	@Test
	public void TC03_updateEmployee() {
		response = AllMethods.update_employee("Supra", id, "Toyota", 200000, "toy.sup@gmail.com");
		System.out.println(response.asString());
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);
		/*
		 * JsonPath jobject = response.jsonPath(); String empname =
		 * jobject.get("firstName"); Assert.assertEquals(empname, "Supra");
		 */
	}

	@Test
	public void TC04_getSingleEmployee() {
		response = AllMethods.getSingleEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);
		JsonPath jobject = response.jsonPath();
		String empname = jobject.get("firstName");

		Assert.assertEquals(empname, "Supra");

	}

	@Test
	public void TC05_deleteEmployee() {
		response = AllMethods.deleteEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 200);

	}

	@Test
	public void TC06_getSingleEmployee() {
		response = AllMethods.getSingleEmployee(id);
		int actual_response = response.statusCode();
		Assert.assertEquals(actual_response, 400);

	}

	@Test
	public void TC07_allgetEmployee() {
		response = AllMethods.getAllEmployee();
		System.out.println(response.asString());

		// Validate response code is 200

		int actual_response = response.statusCode();

		Assert.assertEquals(actual_response, 200);
		JsonPath jobject = response.jsonPath();
		all_ids = jobject.get("id");
		all_names = jobject.get("names");
		Total_Employees_End = all_ids.size();
		Assert.assertEquals(Total_Employees_End, Initial_Total_Employees);

	}
}
