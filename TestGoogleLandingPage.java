
public class TestGoogleLandingPage{
  
  @Test
  public void testSearchBar(){
    //call some method to nav to google.com
    GoogleLandingPage googleLandingPage = new GoogleLandingPage(driver);
    googleLandingPage.typeStringIntoSearchBar("testing search bar");
    googleLandingPage.clickSearch();
  }

}
