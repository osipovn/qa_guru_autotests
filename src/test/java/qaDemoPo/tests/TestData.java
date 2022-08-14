package qaDemoPo.tests;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {
    static Faker faker = new Faker(new Locale("en"));
    public static String[] hobbies = new String[]{"Arts", "Maths", "Computer Science", "Commerce"};
    public static int markHobb = 1;
    static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userGender = "Male",
            userNumber = faker.phoneNumber().subscriberNumber(10) + "",
            birthDay = "15",
            birthMonth = "June",
            birthYear = "1991",
            uploadPath = "src/test/resources/uploadFile.png",
            currentAddress = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi";
}
