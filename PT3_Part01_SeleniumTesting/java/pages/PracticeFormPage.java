package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By mobileNumberField = By.id("userNumber");
    private By dateOfBirthField = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By currentAddressField = By.id("currentAddress");
    private By submitButton = By.id("submit");
    private By modalTitle = By.id("example-modal-sizes-title-lg");
    private By closeModalButton = By.id("closeLargeModal");
    private By stateDropdown = By.id("state");
    private By stateInput = By.xpath("//div[@id='state']//input");
    private By cityDropdown = By.id("city");
    private By cityInput = By.xpath("//div[@id='city']//input");
    private By uploadPictureInput = By.id("uploadPicture");

    // Gender radio buttons
    private By maleGenderLabel = By.xpath("//label[@for='gender-radio-1']");
    private By femaleGenderLabel = By.xpath("//label[@for='gender-radio-2']");
    private By otherGenderLabel = By.xpath("//label[@for='gender-radio-3']");

    // Hobbies checkboxes
    private By sportsHobbyLabel = By.xpath("//label[@for='hobbies-checkbox-1']");
    private By readingHobbyLabel = By.xpath("//label[@for='hobbies-checkbox-2']");
    private By musicHobbyLabel = By.xpath("//label[@for='hobbies-checkbox-3']");

    // Month and Year dropdowns in date picker
    private By monthDropdown = By.className("react-datepicker__month-select");
    private By yearDropdown = By.className("react-datepicker__year-select");

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
        // Remove ads and footer that might block elements
        executeScript("document.querySelectorAll('ins.adsbygoogle').forEach(e => e.remove());");
        executeScript("document.querySelector('footer').remove();");
        executeScript("document.getElementById('fixedban').remove();");
    }

    public void fillFirstName(String firstName) {
        scrollToElement(firstNameField);
        type(firstNameField, firstName);
    }

    public void fillLastName(String lastName) {
        scrollToElement(lastNameField);
        type(lastNameField, lastName);
    }

    public void fillEmail(String email) {
        scrollToElement(emailField);
        type(emailField, email);
    }

    public void selectGender(String gender) {
        scrollToElement(maleGenderLabel);
        switch (gender.toLowerCase()) {
            case "male":
                clickWithJS(maleGenderLabel);
                break;
            case "female":
                clickWithJS(femaleGenderLabel);
                break;
            case "other":
                clickWithJS(otherGenderLabel);
                break;
        }
    }

    public void fillMobileNumber(String mobile) {
        scrollToElement(mobileNumberField);
        type(mobileNumberField, mobile);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        scrollToElement(dateOfBirthField);
        click(dateOfBirthField);

        // Select month
        selectFromDropdown(monthDropdown, month);

        // Select year
        selectFromDropdown(yearDropdown, year);

        // Select day
        By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') and " +
                "not(contains(@class,'outside-month')) and text()='" + day + "']");
        click(dayLocator);
    }

    public void fillSubjects(String subjects) {
        scrollToElement(subjectsInput);
        if (subjects != null && !subjects.isEmpty()) {
            String[] subjectArray = subjects.split(",");
            for (String subject : subjectArray) {
                type(subjectsInput, subject.trim());
                driver.findElement(subjectsInput).sendKeys(Keys.ENTER);
            }
        }
    }

    public void selectHobbies(String hobbies) {
        if (hobbies != null && !hobbies.isEmpty()) {
            String[] hobbyArray = hobbies.split(",");
            for (String hobby : hobbyArray) {
                scrollToElement(sportsHobbyLabel);
                switch (hobby.trim().toLowerCase()) {
                    case "sports":
                        clickWithJS(sportsHobbyLabel);
                        break;
                    case "reading":
                        clickWithJS(readingHobbyLabel);
                        break;
                    case "music":
                        clickWithJS(musicHobbyLabel);
                        break;
                }
            }
        }
    }

    public void uploadPicture(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.exists()) {
                driver.findElement(uploadPictureInput).sendKeys(file.getAbsolutePath());
            }
        }
    }

    public void fillCurrentAddress(String address) {
        scrollToElement(currentAddressField);
        type(currentAddressField, address);
    }

    public void selectState(String state) {
        if (state != null && !state.isEmpty()) {
            scrollToElement(stateDropdown);
            clickWithJS(stateDropdown);
            type(stateInput, state);
            driver.findElement(stateInput).sendKeys(Keys.ENTER);
        }
    }

    public void selectCity(String city) {
        if (city != null && !city.isEmpty()) {
            scrollToElement(cityDropdown);
            clickWithJS(cityDropdown);
            type(cityInput, city);
            driver.findElement(cityInput).sendKeys(Keys.ENTER);
        }
    }

    public void clickSubmit() {
        scrollToElement(submitButton);
        clickWithJS(submitButton);
    }

    public boolean isModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public String getSubmittedValue(String label) {
        By valueLocator = By.xpath("//td[text()='" + label + "']/following-sibling::td");
        return getText(valueLocator);
    }

    public void closeModal() {
        if (isModalDisplayed()) {
            click(closeModalButton);
        }
    }

    // Helper method to scroll to element
    @Override
    protected void scrollToElement(By locator) {
        WebElement element = waitForVisibility(locator);
        executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Helper method to click with JavaScript
    private void clickWithJS(By locator) {
        WebElement element = waitForVisibility(locator);
        executeScript("arguments[0].click();", element);
    }

    // Helper method to execute JavaScript
    private void executeScript(String script, Object... args) {
        ((JavascriptExecutor) driver).executeScript(script, args);
    }

    // Helper method to select from dropdown
    private void selectFromDropdown(By locator, String value) {
        WebElement dropdown = driver.findElement(locator);
        dropdown.sendKeys(value);
    }

    // Method to fill entire form at once
    public void fillForm(String firstName, String lastName, String email, String gender,
                         String mobile, String day, String month, String year,
                         String subjects, String hobbies, String picturePath,
                         String address, String state, String city) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillEmail(email);
        selectGender(gender);
        fillMobileNumber(mobile);
        selectDateOfBirth(day, month, year);
        fillSubjects(subjects);
        selectHobbies(hobbies);
        uploadPicture(picturePath);
        fillCurrentAddress(address);
        selectState(state);
        selectCity(city);
    }
}
