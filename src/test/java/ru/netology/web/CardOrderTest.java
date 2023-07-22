ackage ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest<list> {
    private WebDriver driver;

    @BeforeAll // хук, который запускает драйвер
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach // Выполняет действия до выполнения теста
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);}

    @AfterEach // выполняет действие после завершения теста
    void tearDown(){
        driver.quit(); // заканчивает работу
        driver = null; // обнуляет его
    }

    @Test
    void shouldTestSomething() throws InterruptedException {
        // загрузить страницу
        driver.get("http://localhost:9999/");

        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иванов Иван");
        elements.get(1).sendKeys("+79990000000");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());//trim удаляет лишние пробелы

        Thread.sleep(5000);
        // поиск элементов
        //взаимодействие с элементами

    }
}
