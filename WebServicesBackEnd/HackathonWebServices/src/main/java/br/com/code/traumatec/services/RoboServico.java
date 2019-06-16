package br.com.code.traumatec.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class RoboServico {

	public String robo(String cepAtual, String cepDestino) {
		System.setProperty("webdriver.chrome.driver", "C:\\newchrome\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.navigate().to("https://www.transvias.com.br/distancias");
		
		driver.findElement(By.xpath("/html/body/div[1]/section[2]/div[1]/div/label[1]/input")).sendKeys(cepAtual);
		driver.findElement(By.xpath("/html/body/div[1]/section[2]/div[1]/div/label[2]/input")).sendKeys(cepDestino);
		driver.findElement(By.xpath("/html/body/div[1]/section[2]/div[1]/div/div[2]/button")).click();
		try {
			Thread.sleep(2000);
		} catch (Exception e) {}
		String distancia = driver.findElement(By.xpath("/html/body/div[1]/section[2]/div[1]/div/div[3]/h5/span")).getText();
		driver.close();
		return distancia;
	}

}
