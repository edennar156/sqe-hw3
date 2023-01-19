package hellocucumber;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import java.time.Duration;


public class MoodleTeacherActuator {
    private WebDriver driver;
    private WebDriverWait wait;


    public void initSession(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(40).toSeconds());


        // launch website -> localhost
        driver.get("http://localhost/");

        // maximize the window - some web apps look different in different sizes
        driver.manage().window().maximize();


        /*
            If we wanted to test the web application on different devices -
                1. Open the web app
                2. Right click -> click inspect
                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
                4. Locate the dimensions drop-down list at the top of the web app and choose device
                5. Copy dimensions size (on the right side of the drop-down list)
                   -> driver.manage().window().setSize(new Dimension(width, height));
         */

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void goToLogin(){
        // locate and click on web element -> login
        driver.get("http://localhost/login/index.php");
//        driver.findElement(By.linkText("Log in")).click();
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        // $x("//*[@id='username']")
        // driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);

        // locate the password input box and enter password
        // $x("//*[@name='password' and @type='password']")
        driver.findElement(By.xpath("//*[@name='password' and @type='password']")).sendKeys(password);

        // locate Log in button and press
        // $x("//*[@id='loginbtn']")
        driver.findElement(By.id("loginbtn")).click();
    }

    public void teacherWelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, Teacher!')]")
//        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, admin!')]"));
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, Admin!')]"));
    }

    public void loginSequence(String username, String password){
        // locate and click on web element -> login
        goToLogin();

        // enter username and password -> press login
        enterLoginInfo(username, password);

        // check for welcome message
        teacherWelcomeMessage();
    }

    public void logout(){
        // find user menu -> click on it
        driver.findElement(By.id("user-menu-toggle")).click();

        // find log out button in drop down menu -> click on it
        driver.findElement(By.linkText("Log out")).click();

    }

    public void logoutAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);
            logout();
        }
    }

    public void checkNotLoggedIn(){
        // to make sure we logged out -> find the login button again
        // $x("//*[contains(text(),'Log in')][1]")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Log in')][1]")));

    }

    public void checkNotLoggedInAll() throws InterruptedException {
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        System.out.println("num of windows" + driver.getWindowHandles().size());
        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            driver.wait(5);

            checkNotLoggedIn();
        }
    }

    public void logoutAndCheck(){
        logout();
        checkNotLoggedIn();
    }

    public void editModeOn(){
        // find edit mode switch -> click on it
        // $x("//*[@type='checkbox' and @name='setmode']")
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@type='checkbox' and @name='setmode']"))).click();
    }

    public void myCoursesTab(){
        // example of how we can save the element and then operate with it
        // find my courses -> click on it
        // $x("//*[contains(text(),'My courses') and @role='menuitem']")
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }

    public void goToCourse(String courseName){
        myCoursesTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }


    // Generates random strings according to desired length
    // Used when we want to initialize multiple objects and need unique names
    private String createRandomString(int stringlength){

        // case of empty string
        if(stringlength < 1)
            return "";
        Random rnd = new Random();
        StringBuilder newString = new StringBuilder(stringlength);

        // first letter is capitalized
        newString.append((char)('A' + rnd.nextInt(26)));

        // rest of letters are lower cased
        for(int i = 0; i < stringlength - 1; i++){
            char c = (char) ('a' + rnd.nextInt(26));
            newString.append(c);
        }
        return newString.toString();
    }

    private String createRandomSentence(int wordCount){
        if(wordCount < 1)
            return "";

        Random rnd = new Random();
        StringBuilder newString = new StringBuilder(wordCount);

        // rest of letters are lower cased
        for(int i = 0; i < wordCount; i++){
            String word = createRandomString(5);
            newString.append(word);
            newString.append(" ");
        }
        return newString.toString();
    }

    public void terminateDriver(){
        driver.quit();
    }

    public void navigateToAssignment()
    {
        driver.get("http://localhost/course/modedit.php?update=2&return=1");
    }

    public void editAssignmentField(){
        WebElement field = driver.findElement(By.xpath("//fieldset[3]/div[2]/div[3]/div[2]/select[1]"));
        field.sendKeys(Keys.UP);
        field.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"id_submitbutton2\"]")).click();
        //*[@id="id_submitbutton2"]
    }

    public void changeAppearance()
    {
        editModeOn();
        driver.findElement(By.id("action-menu-toggle-2")).click();
        driver.findElement(By.xpath(" //*[@id=\"actionmenuaction-7\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='btn btn-primary']"))).click();








//        driver.findElement(By.xpath("//h3[text()='common module settings']")).click();
//
////        driver.findElement(By.xpath("//*[@id='yui_3_17_2_1_1674072907081_1322']")).click();
//        WebElement x = driver.findElement(By.xpath("//*[@id=\"id_visible\"]"));
//        x.sendKeys(Keys.DOWN);
//        x.sendKeys(Keys.ENTER);
//        driver.findElement(By.xpath("//*[@id=\"id_submitbutton2\"]")).click();
    }
    public void teacherNavigatePage()
    {
        driver.get("http://localhost/course/view.php?id=2#section-0");
    }

}
