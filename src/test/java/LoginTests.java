import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTests extends BaseConfig {
     @Test(groups = "without_login")
    public void loginWithValidCredentials(){
         WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
         WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
         menuButton.click();
         login.click();
         loginSteps("John Doe", "ThisIsNotAPassword");
         assertTrue(driver.getCurrentUrl().contains("#appointment"),
                 "Nu suntem pe pagina de appointment dupa ce ne-am logat");

    }
    @Test(groups = "without_login")
    public void loginWithInvalidCredentials(){
        WebElement menuButton= driver.findElement(By.id("menu-toggle")) ;
        WebElement login= driver.findElement(By.cssSelector("a[href='profile.php#login"));
        menuButton.click();
        login.click();
        loginSteps("Pista Marcel", "AmGresitParola");
        assertFalse(driver.getCurrentUrl().contains("#appointment"),
                "Am fost redirectionat pe pagina de appointment desi am introdus date invalide la logare");
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Nu mai suntem pe pagina de login desi am introdus date invalide");
        assertTrue(isElementPresent(By.cssSelector(".lead.text-danger")), "Nu sa afisat mesajul de login failed dupa ce am introdus date invalide");

    }


}
