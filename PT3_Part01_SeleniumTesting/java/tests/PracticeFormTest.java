package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.PracticeFormPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Student Registration Form Tests")
public class PracticeFormTest extends BaseTest {
    static PracticeFormPage practiceFormPage;

    @BeforeAll
    static void initPage() {
        practiceFormPage = new PracticeFormPage(driver);
    }

    @BeforeEach
    void navigateToForm() {
        practiceFormPage.navigate();
    }

    @Test
    @Order(1)
    @DisplayName("Should submit form successfully with all required fields")
    void testSubmitFormWithRequiredFieldsOnly() {
        // Fill only required fields
        practiceFormPage.fillFirstName("John");
        practiceFormPage.fillLastName("Doe");
        practiceFormPage.selectGender("Male");
        practiceFormPage.fillMobileNumber("1234567890");

        // Submit form
        practiceFormPage.clickSubmit();

        // Verify success modal is displayed
        assertTrue(practiceFormPage.isModalDisplayed(),
                "Success modal should be displayed after form submission");
        assertEquals("Thanks for submitting the form", practiceFormPage.getModalTitle(),
                "Modal title should match expected text");

        // Verify submitted values
        assertEquals("John Doe", practiceFormPage.getSubmittedValue("Student Name"),
                "Student name should match submitted value");
        assertEquals("1234567890", practiceFormPage.getSubmittedValue("Mobile"),
                "Mobile number should match submitted value");

        // Close modal
        practiceFormPage.closeModal();
    }

    @Test
    @Order(2)
    @DisplayName("Should submit form successfully with all fields filled")
    void testSubmitFormWithAllFields() {
        // Fill all form fields
        practiceFormPage.fillFirstName("Jane");
        practiceFormPage.fillLastName("Smith");
        practiceFormPage.fillEmail("jane.smith@test.com");
        practiceFormPage.selectGender("Female");
        practiceFormPage.fillMobileNumber("9876543210");
        practiceFormPage.selectDateOfBirth("25", "March", "1995");
        practiceFormPage.fillSubjects("English");
        practiceFormPage.selectHobbies("Reading,Music");
        practiceFormPage.fillCurrentAddress("456 Oak Avenue, City, State");
        practiceFormPage.selectState("NCR");
        practiceFormPage.selectCity("Delhi");

        // Submit form
        practiceFormPage.clickSubmit();

        // Verify success
        assertTrue(practiceFormPage.isModalDisplayed(),
                "Success modal should be displayed");
        assertEquals("Thanks for submitting the form", practiceFormPage.getModalTitle());

        // Verify multiple submitted values
        assertEquals("Jane Smith", practiceFormPage.getSubmittedValue("Student Name"));
        assertEquals("jane.smith@test.com", practiceFormPage.getSubmittedValue("Student Email"));
        assertEquals("Female", practiceFormPage.getSubmittedValue("Gender"));
        assertEquals("9876543210", practiceFormPage.getSubmittedValue("Mobile"));
        assertEquals("25 March,1995", practiceFormPage.getSubmittedValue("Date of Birth"));
        assertTrue(practiceFormPage.getSubmittedValue("Hobbies").contains("Reading"));
        assertEquals("456 Oak Avenue, City, State",
                practiceFormPage.getSubmittedValue("Address"));
        assertEquals("NCR Delhi", practiceFormPage.getSubmittedValue("State and City"));

        practiceFormPage.closeModal();
    }

    @Test
    @Order(3)
    @DisplayName("Should fill form using fillForm helper method")
    void testFillFormHelperMethod() {
        // Use the helper method to fill entire form at once
        practiceFormPage.fillForm(
                "Alex",                          // firstName
                "Johnson",                       // lastName
                "alex.j@mail.com",              // email
                "Other",                         // gender
                "5555555555",                    // mobile
                "10",                            // day
                "December",                      // month
                "2000",                          // year
                "Computer Science",              // subjects
                "Sports",                        // hobbies
                "",                              // picturePath (empty for now)
                "789 Pine Road",                 // address
                "Haryana",                       // state
                "Karnal"                         // city
        );

        practiceFormPage.clickSubmit();

        assertTrue(practiceFormPage.isModalDisplayed());
        assertEquals("Alex Johnson", practiceFormPage.getSubmittedValue("Student Name"));
        assertEquals("Other", practiceFormPage.getSubmittedValue("Gender"));

        practiceFormPage.closeModal();
    }

    @ParameterizedTest(name = "Test {index}: {0} {1}")
    @Order(4)
    @DisplayName("Should submit form with data from CSV inline source")
    @CsvSource({
            "Mike,Wilson,mike.wilson@test.com,Male,1231231234,5,May,1992,Physics,Sports,,111 First St,NCR,Delhi",
            "Sarah,Connor,sarah.c@mail.com,Female,9879879876,20,August,1985,Chemistry,Reading,,222 Second Ave,Uttar Pradesh,Agra",
            "Pat,Taylor,pat.taylor@example.com,Other,5556667777,30,November,1998,Biology,Music,,333 Third Blvd,Haryana,Panipat"
    })
    void testSubmitFormFromCsvInline(String firstName, String lastName, String email,
                                     String gender, String mobile, String day, String month,
                                     String year, String subjects, String hobbies,
                                     String picturePath, String address, String state, String city) {
        // Fill form using parameters
        practiceFormPage.fillForm(firstName, lastName, email, gender, mobile,
                day, month, year, subjects, hobbies, picturePath,
                address, state, city);

        practiceFormPage.clickSubmit();

        // Verify submission
        assertTrue(practiceFormPage.isModalDisplayed(),
                "Modal should be displayed for " + firstName + " " + lastName);
        assertEquals(firstName + " " + lastName,
                practiceFormPage.getSubmittedValue("Student Name"));
        assertEquals(gender, practiceFormPage.getSubmittedValue("Gender"));
        assertEquals(mobile, practiceFormPage.getSubmittedValue("Mobile"));

        practiceFormPage.closeModal();
    }

    @ParameterizedTest(name = "CSV File Test: {0} {1}")
    @Order(5)
    @DisplayName("Should submit form with data from CSV file")
    @CsvFileSource(resources = "/practice-form-data.csv", numLinesToSkip = 1)
    void testSubmitFormFromCsvFile(String firstName, String lastName, String email,
                                   String gender, String mobile, String day, String month,
                                   String year, String subjects, String hobbies,
                                   String picturePath, String address, String state, String city) {
        // Fill form using CSV data
        practiceFormPage.fillForm(firstName, lastName, email, gender, mobile,
                day, month, year, subjects, hobbies, picturePath,
                address, state, city);

        practiceFormPage.clickSubmit();

        // Verify submission
        assertTrue(practiceFormPage.isModalDisplayed(),
                String.format("Modal should be displayed for test case: %s %s", firstName, lastName));

        // Verify key fields
        String expectedFullName = firstName + " " + lastName;
        assertEquals(expectedFullName, practiceFormPage.getSubmittedValue("Student Name"),
                "Student name should match");
        assertEquals(gender, practiceFormPage.getSubmittedValue("Gender"),
                "Gender should match");
        assertEquals(mobile, practiceFormPage.getSubmittedValue("Mobile"),
                "Mobile should match");

        if (email != null && !email.isEmpty()) {
            assertEquals(email, practiceFormPage.getSubmittedValue("Student Email"),
                    "Email should match");
        }

        practiceFormPage.closeModal();
    }

    @Test
    @Order(6)
    @DisplayName("Should handle multiple gender options")
    void testDifferentGenderOptions() {
        String[] genders = {"Male", "Female", "Other"};

        for (String gender : genders) {
            practiceFormPage.navigate();

            practiceFormPage.fillFirstName("Test");
            practiceFormPage.fillLastName("User");
            practiceFormPage.selectGender(gender);
            practiceFormPage.fillMobileNumber("1234567890");

            practiceFormPage.clickSubmit();

            assertTrue(practiceFormPage.isModalDisplayed(),
                    "Modal should be displayed for gender: " + gender);
            assertEquals(gender, practiceFormPage.getSubmittedValue("Gender"),
                    "Gender should be " + gender);

            practiceFormPage.closeModal();
        }
    }

    @Test
    @Order(7)
    @DisplayName("Should handle multiple hobbies selection")
    void testMultipleHobbies() {
        practiceFormPage.fillFirstName("Hobby");
        practiceFormPage.fillLastName("Tester");
        practiceFormPage.selectGender("Male");
        practiceFormPage.fillMobileNumber("9999999999");

        // Select multiple hobbies
        practiceFormPage.selectHobbies("Sports,Reading,Music");

        practiceFormPage.clickSubmit();

        assertTrue(practiceFormPage.isModalDisplayed());
        String hobbies = practiceFormPage.getSubmittedValue("Hobbies");
        assertTrue(hobbies.contains("Sports"), "Hobbies should contain Sports");
        assertTrue(hobbies.contains("Reading"), "Hobbies should contain Reading");
        assertTrue(hobbies.contains("Music"), "Hobbies should contain Music");

        practiceFormPage.closeModal();
    }

    @Test
    @Order(8)
    @DisplayName("Should handle multiple subjects")
    void testMultipleSubjects() {
        practiceFormPage.fillFirstName("Subject");
        practiceFormPage.fillLastName("Tester");
        practiceFormPage.selectGender("Female");
        practiceFormPage.fillMobileNumber("8888888888");

        // Add multiple subjects
        practiceFormPage.fillSubjects("Maths,Physics,Chemistry");

        practiceFormPage.clickSubmit();

        assertTrue(practiceFormPage.isModalDisplayed());
        String subjects = practiceFormPage.getSubmittedValue("Subjects");
        assertTrue(subjects.contains("Maths"), "Subjects should contain Maths");
        assertTrue(subjects.contains("Physics"), "Subjects should contain Physics");
        assertTrue(subjects.contains("Chemistry"), "Subjects should contain Chemistry");

        practiceFormPage.closeModal();
    }

    @Test
    @Order(9)
    @DisplayName("Should handle different date selections")
    void testDifferentDates() {
        String[][] dates = {
                {"1", "January", "2000"},
                {"15", "June", "1995"},
                {"31", "December", "1990"}
        };

        for (String[] date : dates) {
            practiceFormPage.navigate();

            practiceFormPage.fillFirstName("Date");
            practiceFormPage.fillLastName("Test");
            practiceFormPage.selectGender("Male");
            practiceFormPage.fillMobileNumber("7777777777");
            practiceFormPage.selectDateOfBirth(date[0], date[1], date[2]);

            practiceFormPage.clickSubmit();

            assertTrue(practiceFormPage.isModalDisplayed());
            // Format day with leading zero if needed (form returns "01" not "1")
            String formattedDay = String.format("%02d", Integer.parseInt(date[0]));
            String expectedDate = formattedDay + " " + date[1] + "," + date[2];
            assertEquals(expectedDate, practiceFormPage.getSubmittedValue("Date of Birth"),
                    "Date should match " + expectedDate);

            practiceFormPage.closeModal();
        }
    }

    @Test
    @Order(10)
    @DisplayName("Should handle different state and city combinations")
    void testStateAndCityCombinations() {
        String[][] locations = {
                {"NCR", "Delhi"},
                {"Uttar Pradesh", "Agra"},
                {"Haryana", "Karnal"},
                {"Rajasthan", "Jaipur"}
        };

        for (String[] location : locations) {
            practiceFormPage.navigate();

            practiceFormPage.fillFirstName("Location");
            practiceFormPage.fillLastName("Test");
            practiceFormPage.selectGender("Female");
            practiceFormPage.fillMobileNumber("6666666666");
            practiceFormPage.selectState(location[0]);
            practiceFormPage.selectCity(location[1]);

            practiceFormPage.clickSubmit();

            assertTrue(practiceFormPage.isModalDisplayed());
            String expectedLocation = location[0] + " " + location[1];
            assertEquals(expectedLocation, practiceFormPage.getSubmittedValue("State and City"),
                    "State and City should match " + expectedLocation);

            practiceFormPage.closeModal();
        }
    }
}
