package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PracticeFormPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Practice Form Tests using Page Object Model")
public class PracticeFormTest extends BaseTest {
    
    static WebDriverWait wait;
    static PracticeFormPage practiceFormPage;

    @BeforeAll
    static void initPage() {
        practiceFormPage = new PracticeFormPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void cleanupAfterEach() {
        // Close modal if it's open
        try {
            practiceFormPage.closeModal();
        } catch (Exception e) {
            // Modal might not be open, ignore
        }
    }

    @Test
    @Order(1)
    @DisplayName("Should successfully submit form with all required fields")
    void testSubmitFormSuccess() {
        practiceFormPage.navigate();
        
        // Fill form with valid data
        practiceFormPage.fillForm(
            "Nguyen",
            "Van A",
            "nguyenvana@example.com",
            "Male",
            "0123456789",
            "15 Jan 1990",
            "Maths",
            "Sports",
            "",
            "123 Le Loi Street, Ho Chi Minh City",
            "NCR",
            "Delhi"
        );
        
        // Submit form
        practiceFormPage.submitForm();
        
        // Verify modal is displayed
        assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed after form submission");
        
        // Verify modal title
        String modalTitle = practiceFormPage.getModalTitle();
        assertEquals("Thanks for submitting the form", modalTitle, "Modal title should match");
        
        // Verify form data in modal
        String modalContent = practiceFormPage.getModalTableText();
        assertTrue(modalContent.contains("Nguyen"), "Modal should contain first name");
        assertTrue(modalContent.contains("Van A"), "Modal should contain last name");
        assertTrue(modalContent.contains("nguyenvana@example.com"), "Modal should contain email");
        assertTrue(modalContent.contains("Male"), "Modal should contain gender");
        assertTrue(modalContent.contains("0123456789"), "Modal should contain mobile number");
        
        // Close modal
        practiceFormPage.closeModal();
    }

    @Test
    @Order(2)
    @DisplayName("Should submit form with female gender")
    void testSubmitFormWithFemaleGender() {
        practiceFormPage.navigate();
        
        practiceFormPage.fillForm(
            "Tran",
            "Thi B",
            "tranthib@example.com",
            "Female",
            "0987654321",
            "20 Feb 1995",
            "English",
            "Reading",
            "",
            "456 Nguyen Hue Street, Hanoi",
            "Uttar Pradesh",
            "Agra"
        );
        
        practiceFormPage.submitForm();
        
        assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed");
        String modalContent = practiceFormPage.getModalTableText();
        assertTrue(modalContent.contains("Female"), "Modal should contain Female gender");
        
        practiceFormPage.closeModal();
    }

    @Test
    @Order(3)
    @DisplayName("Should submit form with multiple hobbies")
    void testSubmitFormWithMultipleHobbies() {
        practiceFormPage.navigate();
        
        practiceFormPage.enterFirstName("Le");
        practiceFormPage.enterLastName("Van C");
        practiceFormPage.enterEmail("levanc@example.com");
        practiceFormPage.selectGender("Male");
        practiceFormPage.enterMobile("0555666777");
        practiceFormPage.enterDateOfBirth("10 Mar 1992");
        practiceFormPage.enterCurrentAddress("789 Tran Hung Dao Street, Da Nang");
        
        // Select multiple hobbies
        practiceFormPage.selectHobby("Sports");
        practiceFormPage.selectHobby("Music");
        
        practiceFormPage.selectState("Haryana");
        practiceFormPage.selectCity("Karnal");
        
        practiceFormPage.submitForm();
        
        assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed");
        
        practiceFormPage.closeModal();
    }

    @Test
    @Order(4)
    @DisplayName("Should submit form with minimum required fields")
    void testSubmitFormWithMinimumFields() {
        practiceFormPage.navigate();
        
        // Fill only required fields
        practiceFormPage.enterFirstName("Pham");
        practiceFormPage.enterLastName("Thi D");
        practiceFormPage.selectGender("Female");
        practiceFormPage.enterMobile("0111222333");
        practiceFormPage.enterDateOfBirth("05 Apr 1993");
        
        practiceFormPage.submitForm();
        
        assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed even with minimum fields");
        
        String modalContent = practiceFormPage.getModalTableText();
        assertTrue(modalContent.contains("Pham"), "Modal should contain first name");
        assertTrue(modalContent.contains("Thi D"), "Modal should contain last name");
        
        practiceFormPage.closeModal();
    }

    @Test
    @Order(5)
    @DisplayName("Should verify form fields are accessible")
    void testFormFieldsAccessibility() {
        practiceFormPage.navigate();
        
        // Test that all form fields are accessible
        assertDoesNotThrow(() -> {
            practiceFormPage.enterFirstName("Test");
            practiceFormPage.enterLastName("User");
            practiceFormPage.enterEmail("test@example.com");
            practiceFormPage.selectGender("Male");
            practiceFormPage.enterMobile("0123456789");
            practiceFormPage.enterDateOfBirth("01 Jan 2000");
            practiceFormPage.enterCurrentAddress("Test Address");
        }, "All form fields should be accessible");
    }

    @ParameterizedTest(name = "CSV Inline: {0} {1} - {10}")
    @Order(6)
    @CsvSource({
            "Nguyen,Van A,nguyenvana@example.com,Male,0123456789,15 Jan 1990,Maths,Sports,,123 Le Loi Street Ho Chi Minh City,NCR,Delhi,success",
            "Tran,Thi B,tranthib@example.com,Female,0987654321,20 Feb 1995,English,Reading,,456 Nguyen Hue Street Hanoi,Uttar Pradesh,Agra,success",
            "Le,Van C,levanc@example.com,Male,0555666777,10 Mar 1992,Physics,Music,,789 Tran Hung Dao Street Da Nang,Haryana,Karnal,success"
    })
    void testSubmitFormCsvInline(String firstName, String lastName, String email, String gender,
                                 String mobile, String dateOfBirth, String subjects, String hobby,
                                 String filePath, String address, String state, String city, String expected) {
        practiceFormPage.navigate();
        
        // Handle null values
        firstName = (firstName == null) ? "" : firstName.trim();
        lastName = (lastName == null) ? "" : lastName.trim();
        email = (email == null) ? "" : email.trim();
        mobile = (mobile == null) ? "" : mobile.trim();
        dateOfBirth = (dateOfBirth == null) ? "" : dateOfBirth.trim();
        address = (address == null) ? "" : address.trim();
        
        // Fill form
        practiceFormPage.fillForm(
            firstName, lastName, email, gender, mobile, dateOfBirth,
            subjects, hobby, filePath, address, state, city
        );
        
        // Submit form
        practiceFormPage.submitForm();
        
        // Verify based on expected result
        if (expected.equals("success")) {
            assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed after successful submission");
            String modalTitle = practiceFormPage.getModalTitle();
            assertEquals("Thanks for submitting the form", modalTitle, "Modal title should match");
            
            // Verify form data in modal
            String modalContent = practiceFormPage.getModalTableText();
            if (!firstName.isEmpty()) {
                assertTrue(modalContent.contains(firstName), "Modal should contain first name: " + firstName);
            }
            if (!lastName.isEmpty()) {
                assertTrue(modalContent.contains(lastName), "Modal should contain last name: " + lastName);
            }
            if (!email.isEmpty()) {
                assertTrue(modalContent.contains(email), "Modal should contain email: " + email);
            }
            
            practiceFormPage.closeModal();
        }
    }

    @ParameterizedTest(name = "CSV File: {0} {1} - {10}")
    @Order(7)
    @CsvFileSource(resources = "/practice-form-data.csv", numLinesToSkip = 1)
    void testSubmitFormFromCSV(String firstName, String lastName, String email, String gender,
                               String mobile, String dateOfBirth, String subjects, String hobby,
                               String address, String state, String city, String expected) {
        practiceFormPage.navigate();
        
        // Handle null values
        firstName = (firstName == null) ? "" : firstName.trim();
        lastName = (lastName == null) ? "" : lastName.trim();
        email = (email == null) ? "" : email.trim();
        mobile = (mobile == null) ? "" : mobile.trim();
        dateOfBirth = (dateOfBirth == null) ? "" : dateOfBirth.trim();
        address = (address == null) ? "" : address.trim();
        subjects = (subjects == null) ? "" : subjects.trim();
        hobby = (hobby == null) ? "" : hobby.trim();
        
        // Fill form
        practiceFormPage.fillForm(
            firstName, lastName, email, gender, mobile, dateOfBirth,
            subjects, hobby, "", address, state, city
        );
        
        // Submit form
        practiceFormPage.submitForm();
        
        // Verify based on expected result
        if (expected.equals("success")) {
            assertTrue(practiceFormPage.isModalDisplayed(), "Modal should be displayed after successful submission");
            String modalTitle = practiceFormPage.getModalTitle();
            assertEquals("Thanks for submitting the form", modalTitle, "Modal title should match");
            
            // Verify form data in modal
            String modalContent = practiceFormPage.getModalTableText();
            if (!firstName.isEmpty()) {
                assertTrue(modalContent.contains(firstName), "Modal should contain first name: " + firstName);
            }
            if (!lastName.isEmpty()) {
                assertTrue(modalContent.contains(lastName), "Modal should contain last name: " + lastName);
            }
            if (!email.isEmpty()) {
                assertTrue(modalContent.contains(email), "Modal should contain email: " + email);
            }
            if (gender != null && !gender.isEmpty()) {
                assertTrue(modalContent.contains(gender), "Modal should contain gender: " + gender);
            }
            if (!mobile.isEmpty()) {
                assertTrue(modalContent.contains(mobile), "Modal should contain mobile: " + mobile);
            }
            
            practiceFormPage.closeModal();
        }
    }
}

