
public class BaseDriver{
  
  protected String seleniumBrowser;
	protected String seleniumUrl;
	protected String whereToRun;
	protected String server;
	protected String pathToChromeDriver;
	protected String pathToIEDriver;
	protected boolean isRunningRemote;
  
  public void setup(ITestContext context) {
		

		if(context != null && context.getAttribute("driver") == null){
			this.context = context;
			seleniumBrowser = System.getProperty("BROWSER");//
			if (StringUtils.isEmpty(seleniumBrowser))
				seleniumBrowser = context.getCurrentXmlTest().getParameter("selenium.browser");

			pathToChromeDriver = env.get("CHROME_DRIVER_PATH");
			if (StringUtils.isEmpty(pathToChromeDriver)) {
				pathToChromeDriver = context.getCurrentXmlTest().getParameter("chrome.driver.path");
			}
			setUpDriver(context);
		}else{
			System.out.println("Using already created  ITestContext in setup(ITestContext context)");
			this.context = context;
			driver = (WebDriver)context.getAttribute("driver");
		}
	}
  
  private void setUpDriver(ITestContext context) {
		// StringUtils methods handle nulls so no need to check for nulls
		System.out.println("invoked setUpDriver() method on BaseTest class");
		if (StringUtils.containsIgnoreCase(seleniumBrowser, "firefox")) {
			capability = DesiredCapabilities.firefox();
			if (!StringUtils.containsIgnoreCase(whereToRun, "remote"))
				driver = new FirefoxDriver();
				System.out.println("Launching FireFox...");
		}
		else if (StringUtils.containsIgnoreCase(seleniumBrowser, "chrome")) {
			capability = DesiredCapabilities.chrome();
			if (!StringUtils.containsIgnoreCase(whereToRun, "remote")) {
				System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
				driver = new ChromeDriver();
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Launching Chrome...");
			}
		}
		else if (StringUtils.containsIgnoreCase(seleniumBrowser, "safari")) {
			capability = DesiredCapabilities.safari();
			if (!StringUtils.containsIgnoreCase(whereToRun, "remote"))
				driver = new SafariDriver();
				System.out.println("Launching Safari...");
		}
		else if (StringUtils.containsIgnoreCase(seleniumBrowser, "internet explorer")) {
			capability = DesiredCapabilities.internetExplorer();
			if (!StringUtils.containsIgnoreCase(whereToRun, "remote"))
				System.setProperty("webdriver.ie.driver","C:\\Selenium\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
			System.out.println("Launching IE...");
		}
		System.out.println("whereToRun value in setUpDriver call = " + whereToRun);
		if (StringUtils.containsIgnoreCase(whereToRun, "remote")) {
			try {
				driver = new RemoteWebDriver(new URL(seleniumUrl), capability);
				isRunningRemote = true;
			}
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("isRunningRemote value in setUpDriver call = " + isRunningRemote );
		if(context != null){
			context.setAttribute("driver",driver);
			context.setAttribute("isRunningRemote", isRunningRemote);
		}
	
		this.setDesktop();
		driver.manage().timeouts().implicitlyWait(Props.WAIT_SUPER, TimeUnit.SECONDS);
		try{
			driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			System.out.println("Managing Page Timeouts: "+ 120);
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			System.out.println("Current IP ="+Inet4Address.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
}
