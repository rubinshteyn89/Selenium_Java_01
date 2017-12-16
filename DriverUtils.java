
public class DriverUtils{

  public void typeById(String string, String id){
    driver.findElement(By.id(id)).sendKeys(string);
  }
  
  public void clickById(String id){
    driver.findElement(By.id(id)).click();
  }

}
