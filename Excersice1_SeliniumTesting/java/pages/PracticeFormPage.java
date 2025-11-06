package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By genderMale = By.cssSelector("label[for='gender-radio-1']");
    private By genderFemale = By.cssSelector("label[for='gender-radio-2']");
    private By genderOther = By.cssSelector("label[for='gender-radio-3']");
    private By mobileField = By.id("userNumber");
    private By dateOfBirthField = By.id("dateOfBirthInput");
    private By subjectsField = By.id("subjectsInput");
    private By hobbiesSports = By.cssSelector("label[for='hobbies-checkbox-1']");
    private By hobbiesReading = By.cssSelector("label[for='hobbies-checkbox-2']");
    private By hobbiesMusic = By.cssSelector("label[for='hobbies-checkbox-3']");
    // Alternative locators for input checkboxes
    private By hobbiesSportsInput = By.id("hobbies-checkbox-1");
    private By hobbiesReadingInput = By.id("hobbies-checkbox-2");
    private By hobbiesMusicInput = By.id("hobbies-checkbox-3");
    private By uploadPicture = By.id("uploadPicture");
    private By currentAddressField = By.id("currentAddress");
    private By stateDropdown = By.id("state");
    private By cityDropdown = By.id("city");
    private By submitButton = By.id("submit");
    
    // Modal locators (after submission)
    private By modalTitle = By.id("example-modal-sizes-title-lg");
    private By modalTable = By.cssSelector(".table-responsive table");
    private By closeModalButton = By.id("closeLargeModal");

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                click(genderMale);
                break;
            case "female":
                click(genderFemale);
                break;
            case "other":
                click(genderOther);
                break;
        }
    }

    public void enterMobile(String mobile) {
        type(mobileField, mobile);
    }

    public void enterDateOfBirth(String date) {
        // Clear the field and enter date
        WebElement dateField = waitForVisibility(dateOfBirthField);
        dateField.click();
        dateField.sendKeys(Keys.CONTROL + "a");
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);
    }

    public void enterSubjects(String subjects) {
        WebElement subjectsElement = waitForVisibility(subjectsField);
        subjectsElement.click();
        subjectsElement.sendKeys(subjects);
        subjectsElement.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        By hobbyLabelLocator;
        By hobbyInputLocator;
        
        switch (hobby.toLowerCase()) {
            case "sports":
                hobbyLabelLocator = hobbiesSports;
                hobbyInputLocator = hobbiesSportsInput;
                break;
            case "reading":
                hobbyLabelLocator = hobbiesReading;
                hobbyInputLocator = hobbiesReadingInput;
                break;
            case "music":
                hobbyLabelLocator = hobbiesMusic;
                hobbyInputLocator = hobbiesMusicInput;
                break;
            default:
                return;
        }
        
        // Try to click the input checkbox directly first (more reliable)
        try {
            WebElement inputElement = waitForVisibility(hobbyInputLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", inputElement);
            
            // Use JavaScript click on input to avoid interception
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inputElement);
        } catch (Exception e) {
            // Fallback to label click with JavaScript
            WebElement labelElement = waitForVisibility(hobbyLabelLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", labelElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", labelElement);
        }
    }

    public void uploadPicture(String filePath) {
        WebElement uploadElement = waitForVisibility(uploadPicture);
        uploadElement.sendKeys(filePath);
    }

    public void enterCurrentAddress(String address) {
        type(currentAddressField, address);
    }

    public void selectState(String state) {
        // Scroll to state dropdown
        WebElement stateElement = waitForVisibility(stateDropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateElement);
        
        // Click to open dropdown
        stateElement.click();
        
        // Wait for dropdown options to appear and select the state
        By stateOptionLocator = By.xpath("//div[contains(@id, 'react-select-3-option') and text()='" + state + "']");
        WebElement stateOption = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(stateOptionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", stateOption);
        stateOption.click();
    }

    public void selectCity(String city) {
        // Wait for city dropdown to be clickable
        WebElement cityElement = waitForVisibility(cityDropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityElement);
        
        // Click to open dropdown
        cityElement.click();
        
        // Wait for dropdown options to appear
        By cityOptionLocator = By.xpath("//div[contains(@id, 'react-select-4-option') and text()='" + city + "']");
        WebElement cityOption = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(cityOptionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cityOption);
        cityOption.click();
    }

    public void submitForm() {
        WebElement submitBtn = waitForVisibility(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        submitBtn.click();
    }

    // Verification methods
    public boolean isModalDisplayed() {
        try {
            return waitForVisibility(modalTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public String getModalTableText() {
        return getText(modalTable);
    }

    public void closeModal() {
        try {
            if (isElementVisible(closeModalButton)) {
                WebElement closeButton = waitForVisibility(closeModalButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);

                // Wait for modal to disappear
                wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(modalTitle));
            }
        } catch (Exception e) {
            // Modal might not be visible, ignore
        }
    }

    // Helper method to fill entire form
    public void fillForm(String firstName, String lastName, String email, String gender,
                        String mobile, String dateOfBirth, String subjects, String hobby,
                        String filePath, String address, String state, String city) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        selectGender(gender);
        enterMobile(mobile);
        enterDateOfBirth(dateOfBirth);
        if (subjects != null && !subjects.isEmpty()) {
            enterSubjects(subjects);
        }
        if (hobby != null && !hobby.isEmpty()) {
            selectHobby(hobby);
        }
        if (filePath != null && !filePath.isEmpty()) {
            uploadPicture(filePath);
        }
        enterCurrentAddress(address);
        selectState(state);
        selectCity(city);
    }
}

