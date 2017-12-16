public class GoogleLandingPage extends WireFrame {

  private final String googleSearchBarId = "some id on the page";
  private final String googleSearchSubmitButtonId = "some id on the page"
  
  public void typeStringIntoSearchBar(String string){
    typeById(googleSearchBarId);
  }
  
  public void clickSearch(){
    clickById(googleSearchSubmitButtonId);
  }

}
