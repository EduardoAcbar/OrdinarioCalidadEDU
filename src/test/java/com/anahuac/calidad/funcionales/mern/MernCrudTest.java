package com.anahuac.calidad.funcionales.mern;
//EAB

import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MernCrudTest {
	private WebDriver driver;
	private String baseUrl="https://mern-crud.herokuapp.com/";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	JavascriptExecutor js;

	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
	}

	
	@Test
	public void testCreateUser() throws Exception {

		//int numRandom = new Random().nextInt(); + String.valueOf(numRandom) +

		driver.get(baseUrl);
		driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Eduardo Acevedo");
		driver.findElement(By.name("email")).clear();

		//se puede agregar valor random al correo 
		driver.findElement(By.name("email")).sendKeys("l4lo420@gmail.com");

		driver.findElement(By.name("age")).clear();
		driver.findElement(By.name("age")).sendKeys("20");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();

		Thread.sleep(5000);

		String Nice = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
		assertThat("Nice one!", is(Nice));
	}




	@Test 
	public void testUpdateInfo() throws Exception{
		//driver.get("https://mern-crud.herokuapp.com/");
		driver.get(baseUrl);
		usuarioDefault(); 
		Thread.sleep(2000);
		//Actualizar area de nombre
		//Boton editar
		driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.name("name")).click();
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("Eduardo Barajas");
		//Actualizar area de correo
		driver.findElement(By.name("email")).click();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("correofake@gmail.com");
		//Actualizar area de edad
		driver.findElement(By.name("age")).click();
		driver.findElement(By.name("age")).clear();
		driver.findElement(By.name("age")).sendKeys("33");
		//Actualizar area de genero
		//Xpath del boton de genero: html/body/div[2]/div/div[2]/form/div[3]/div[2]/div
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
        Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[2]/div/i")).click();
		
		//verificar q si se haya actualizado al info ingersada
		String nametag = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
        assertThat("Eduardo Acevedo", is(nametag));
	}


	@Test 
	public void DeleteUser() throws Exception{
		driver.get(baseUrl);
		usuarioDefault(); 
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[5]/button[2]")).click(); 
		driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click(); 
		Thread.sleep(5000);
		String NametoDelete = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[1]")).getText();
		assertThat("Eduardo Acevedo", is(not(NametoDelete)));

	}

	
	
	@Test
	public void SearchInfo() throws Exception {

		driver.get(baseUrl);
		usuarioDefault();
		String NamePath = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();

		Thread.sleep(5000);

		assertThat("Eduardo Acevedo", is(NamePath));
	}

	


public  void usuarioDefault() {
	//driver.get(baseUrl); el metodo que llame a este, ya ejecuta el llamado al browser
	driver.findElement(By.xpath("/html/body/div/div/div[2]/button")).click();
	driver.findElement(By.name("name")).click();
	driver.findElement(By.name("name")).clear();
	driver.findElement(By.name("name")).sendKeys("Eduardo Acevedo");
	driver.findElement(By.name("email")).clear();
	driver.findElement(By.name("email")).sendKeys("l4lo420@gmail.com");
	driver.findElement(By.name("age")).sendKeys("20");
	driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
	driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::span[1]")).click();
	driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
	driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
	//cerrar
	driver.findElement(By.xpath("/html/body/div[2]/div/i")).click();
}


@After
public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
		fail(verificationErrorString);
	}
}

private boolean isElementPresent(By by) {
	try {
		driver.findElement(by);
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
}

private boolean isAlertPresent() {
	try {
		driver.switchTo().alert();
		return true;
	} catch (NoAlertPresentException e) {
		return false;
	}
}

private String closeAlertAndGetItsText() {
	try {
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		if (acceptNextAlert) {
			alert.accept();
		} else {
			alert.dismiss();
		}
		return alertText;
	} finally {
		acceptNextAlert = true;
	}
}
}
