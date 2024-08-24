package test.PracticeTests;

import net.datafaker.Faker;

public class FakeDataGeneration {

	public static void main(String[] args) {
		
		Faker faker =  new Faker();
		
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String mname = faker.name().malefirstName();
		String fname = faker.name().femaleFirstName();
		
		System.out.println("Name: "+ name);
		System.out.println("First Name: "+ firstName);
		System.out.println("Male First Name: "+ mname);
		System.out.println("Female First Name: "+ fname);

	}

}
