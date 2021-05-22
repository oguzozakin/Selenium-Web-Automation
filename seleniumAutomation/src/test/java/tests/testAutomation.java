package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class testAutomation {
    public WebDriver driver;

    /* Gerekli driver tanımlaması, path bildirimi ve siteye Url ile ilk erişim */
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\oguz\\IdeaProjects\\chromeDriver\\chromedriver.exe");
        this.driver = new ChromeDriver();
        String url = "https://www.gittigidiyor.com/";
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }
    /* Log In işleminin gerçekleştirilmesi */
    @Test
    public void logIn() {
        WebElement login = this.driver.findElement(By.id("SignIn"));
        login.click();
        this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        WebElement mailbox = this.driver.findElement(By.id("L-UserNameField"));
        mailbox.click();
        mailbox.sendKeys("oguzozakin@hotmail.com");
        WebElement password = this.driver.findElement(By.id("L-PasswordField"));
        password.click();
        password.sendKeys("xyz123");
        this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        this.driver.findElement(By.className("gg-d-24")).click();
    }
    /* 'bilgisayar' sözcüğünün aratılması ve 2.sayfaya geçiş */
    @Test
    public void search() {
        WebElement searchWord = this.driver.findElement(By.id("search_word"));
        searchWord.click();
        searchWord.sendKeys("bilgisayar");
        this.driver.findElement(By.id("header-search-find-link")).click();
        this.driver.findElement(By.xpath(".//*[@class='pager']/li[2]/a[1]")).click();
        /* rastgele bir ürün seçilmesi sepete ekleme işlemi */
        this.driver.findElement(By.xpath(".//*[@id='product_id_690451518']/a[1]")).click();
        WebElement price = this.driver.findElement(By.xpath(".//*[@class='lowPrice lastPrice']"));
        String priceText = price.getText();
        WebElement amountBox = this.driver.findElement(By.id("buyitnow_adet"));
        amountBox.click();
        amountBox.clear();
        amountBox.sendKeys("1");
        WebElement basketBtn = this.driver.findElement(By.id("add-to-basket"));
        basketBtn.click();
        this.driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
        this.driver.findElement(By.className("dIB")).click();
        /* ürünün fiyatı ile sepetteki fiyatın karşılaştırılması */
        WebElement priceBasket = this.driver.findElement(By.className("market-discounted-price-percentage"));
        String priceText2 = priceBasket.getText();
        if (priceText.compareTo(priceText2) > 0) {
            WebElement amountPlusBasket = this.driver.findElement(By.className("IncNumber gg-icon gg-icon-plus"));
            amountPlusBasket.click();
        }
    }
    /* sepetin boş hale getirilmesi */
    @After
    public void emptyTheBasket() {
        this.driver.findElement(By.className("btn-delete btn-update-item")).click();
    }
}

