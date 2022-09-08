package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverConfig {



        public static  ChromeDriver getChromeDriver(){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }

}


