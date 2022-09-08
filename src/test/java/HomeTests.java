import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeTests extends BaseConfig {




    @Test(groups = {"without_login"})
    public void HomeButtonTest(){

        loginbutton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#login"),
                "Nu suntem pe pagina de login desi am apasat pe butonul de login de sus");

        homeButoon();
        assertTrue(driver.getCurrentUrl().equals("https://katalon-demo-cura.herokuapp.com/"),
                "Nu suntem pe pagina de home dupa ce am apasat pe butonul de home de pe pagina login");

        loginbutton();
        loginSteps("John Doe", "ThisIsNotAPassword");

        assertTrue(driver.getCurrentUrl().contains("#appointment"),
                "Nu suntem pe pagina de appointment dupa ce ne-am logat");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Nu suntem pe pagina de home dupa ce am apasat pe butonul de home");

        historyButton();
        assertTrue(driver.getCurrentUrl().contains("history.php#history"),
                "Nu suntem pe pagina de history dupa ce am apasat butonul de history din meniu");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Nu suntem pe pagina de home dupa ce am apasat pe butonul de home");

        profileButton();
        assertTrue(driver.getCurrentUrl().contains("profile.php#profile"),
                "Nu suntem pe pagina de profile dupa ce am apasat butonul de profile din meniu");
        homeButoon();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Nu suntem pe pagina de home dupa ce am apasat pe butonul de home");

        logoutButton();
        assertEquals(driver.getCurrentUrl(), "https://katalon-demo-cura.herokuapp.com/",
                "Nu suntem pe pagina de home dupa ce ne-am apasat delogat");

    }


}
