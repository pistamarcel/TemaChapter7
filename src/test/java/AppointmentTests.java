import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.*;

public class AppointmentTests  extends BaseConfig {
    @Test(groups = "without_login")
    public void makeAppointmentWithoutLogin(){
        makeAppointmentButton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Nu am fost trimisi pe pagina de login desi am incarcat sa facem o programare fata a fi logati mai intai");
        assertFalse(driver.getCurrentUrl().contains("#appointment"), "Butonul Make Appointment ne-a redirectionat pe pagina de appointment desi nu suntem logati");
    }

    @Test(groups = "with_login")
 public void makeAppointmentFormCompletedCorrectly(){
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));

        WebElement selectDate = driver.findElement(By.id("txt_visit_date"));
        WebElement commentBox= driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));
        facility.selectByIndex(2);
        hospitalReadmission.isSelected();
        healthcareProgram.get(2).click();
        selectDate.sendKeys("01/04/2023");
        commentBox.sendKeys("Am auzit lucruri foarte bune despre dumneavoastra de la prieteni si m-am hotarat sa incerc serviciile eu insumi");
        bookAppointmentButton.click();
        assertTrue(driver.getCurrentUrl().contains("appointment.php#summary"),"Programarea nu sa realizat cu succes.");
        assertEquals(driver.findElement(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2")).getText(), "Appointment Confirmation",
                "Nu sa afisat corect mesajul de confirmare a programarii");

        WebElement confirmationMesage =   driver.findElement(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2"));
        System.out.println(confirmationMesage.getText());

    }


    @Test(groups = "with_login")
    public void makeAppointmentFormWithoutSelectingADate() {
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));
        WebElement commentBox = driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));

        facility.selectByIndex(2);
        hospitalReadmission.isSelected();
        healthcareProgram.get(0).click();
        commentBox.sendKeys("Am auzit lucruri foarte bune despre dumneavoastra de la prieteni si m-am hotarat sa incerc serviciile eu insumi");
        bookAppointmentButton.click();
        assertFalse(driver.getCurrentUrl().contains("appointment.php#summary"), "Programarea s-a realizat cu succes desi nu am ales o data.");
        String message = driver.findElement(By.id("txt_visit_date")).getAttribute("validationMessage");
        System.out.println(message);
        assertEquals(message, "Please fill out this field.", "Nu sa afisat mesajul de completare data");

    }

    @Test(groups = "with_login")
    public void makeAppointmentWithLettersInDateField() {
        Select facility = new Select(driver.findElement(By.id("combo_facility")));

        WebElement hospitalReadmission = driver.findElement(By.id("chk_hospotal_readmission"));
        List<WebElement> healthcareProgram = driver.findElements(By.cssSelector(".radio-inline"));

        WebElement selectDate = driver.findElement(By.id("txt_visit_date"));
        WebElement commentBox = driver.findElement(By.name("comment"));
        WebElement bookAppointmentButton = driver.findElement(By.id("btn-book-appointment"));
        facility.selectByIndex(1);
        hospitalReadmission.isSelected();
        healthcareProgram.get(1).click();
        selectDate.sendKeys("Test negativ esuat");
        commentBox.sendKeys("Test pentru a verifica daca putem introduce litere in datafield");
        bookAppointmentButton.click();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isElementPresent(By.cssSelector("div[class=\"col-xs-12 text-center\"]:first-child h2")), "DE VERIFICAT URGENT! Mesajul de confirmare apare desi am introdus date gresite in datefield." );
        softAssert.assertFalse(driver.getCurrentUrl().contains("appointment.php#summary"), "DE VERIFICAT URGENT! Am fost redirectionati pe pagina de confirmare a programarii desi am introdus data gresite in datefield");
        softAssert.assertTrue(driver.getCurrentUrl().contains("#appointment"), "Nu am ramas pe pagina de appointment desi am introdus data gresite in datefield");
        driver.quit();
        softAssert.assertAll();
    }

}


