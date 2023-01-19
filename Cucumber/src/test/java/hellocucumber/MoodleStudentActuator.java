package hellocucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Random;

public class MoodleStudentActuator {
    private WebDriver driver;
    private WebDriverWait wait;
//    public void initSession(String webDriver, String path){
//        // webDriver = "webdriver.chrome.driver"
//
//        System.setProperty(webDriver, path);
//
//        // new chrome driver object
//        this.driver = new ChromeDriver();
//
//        // new web driver wait -> waits until element are loaded (40 sec max)
//        long time = 40;
//
//
//        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//
//
//        // launch website -> localhost
//        driver.get("https://sandbox.moodledemo.net/");
//
//        // maximize the window - some web apps look different in different sizes
//        driver.manage().window().maximize();
//
//
//        /*
//            If we wanted to test the web application on different devices -
//                1. Open the web app
//                2. Right click -> click inspect
//                3. Click on the phone icon at the top left corner of the inspect window (the app changes preview format at this point)
//                4. Locate the dimensions drop-down list at the top of the web app and choose device
//                5. Copy dimensions size (on the right side of the drop-down list)
//                   -> driver.manage().window().setSize(new Dimension(width, height));
//         */
//
//        System.out.println("Driver setup finished for - " + driver.getTitle());
//    }

    public void initSession(String webDriver, String path){
        // webDriver = "webdriver.chrome.driver"
        // path = "C:\\Users\\eylon\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty(webDriver, path);

        // new chrome driver object
        this.driver = new ChromeDriver();

        // new web driver wait -> waits until element are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40).toSeconds());


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
        driver.findElement(By.linkText("Log in")).click();
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

    public void StudentWelcomeMessage(){
        // now to check if login process succeeded -> find the element which indicates it succeeded
        // $x("//*[contains(text(),'Welcome, Teacher!')]")
        driver.findElement(By.xpath("//*[contains(text(),'Welcome back, Sean!')]"));
    }

    public void loginSequence(String username, String password){
        // locate and click on web element -> login
        goToLogin();

        // enter username and password -> press login
        enterLoginInfo(username, password);

        // check for welcome message
        StudentWelcomeMessage();
    }

    public  void AssignmentSubmitFilesAndUpload(){
        //login
        loginSequence("student","Student1234#");
        //go to assignment 3
        goToAssignment("Assignment-3");
        //click on add submission
        driver.findElement(By.xpath("//*[@id=\"single_button63c7f490bf9e37\"]")).click();


        //add 3 files
        //*[@id="yui_3_17_2_1_1674049827353_67"]/div[1]/div[2]
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"yui_3_17_2_1_1674049827353_67\"]/div[1]/div[2]")));
        File f1 = new File("C:\\Users\\edenn\\OneDrive\\Desktop\\SQE-project\\Q1.txt");
        File f2 = new File("C:\\Users\\edenn\\OneDrive\\Desktop\\SQE-project\\Q2.txt");
        File f3 = new File("C:\\Users\\edenn\\OneDrive\\Desktop\\SQE-project\\Q3.txt");
        driver.findElement(By.xpath("//*[@id=\"yui_3_17_2_1_1674049827353_67\"]/div[1]/div[2]")).sendKeys(f1.getAbsolutePath());
        driver.findElement(By.xpath("//*[@id=\"yui_3_17_2_1_1674049827353_67\"]/div[1]/div[2]")).sendKeys(f2.getAbsolutePath());
        driver.findElement(By.xpath("//*[@id=\"yui_3_17_2_1_1674049827353_67\"]/div[1]/div[2]")).sendKeys(f3.getAbsolutePath());



    }

    public void SubmitMessage(){
        WebElement we = driver.findElement((By.xpath("//*[@id=\"yui_3_17_2_1_1674047318583_78\"]")));
        String massage= we.getText();
        if (massage.equals("Submitted for grading")){
            System.out.println("right massage");
        }
        else {
            System.out.println("the massage not good, the actual massage is: "+massage);
        }



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



    public void myCoursesTab(){
        // example of how we can save the element and then operate with it
        // find my courses -> click on it
        // $x("//*[contains(text(),'My courses') and @role='menuitem']")
        WebElement myCoursesTab = driver.findElement(By.xpath("//*[contains(text(),'My courses') and @role='menuitem']"));
        myCoursesTab.click();
    }

    public void goToCourse(String courseName){
        // find course -> click on it
        // $x("//*[@class='multiline' and contains(text(),'Demo course')]")[0].click()
        myCoursesTab();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='multiline' and contains(text(),'" + courseName + "')]"))).click();
    }
    public void goToAssignment(String assignmentName){
        // find course -> click on it
        // $x("//*[@class='multiline' and contains(text(),'Demo course')]")[0].click()
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"yui_3_17_2_1_1673974307492_66\"]" + assignmentName + "')]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"module-2\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/a"))).click();
    }


    public void submitAssigment(){

        Exception e = null;
        while(e == null){
            try{
                // select an answer and continue to next question until there are no more left
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"course-info-container-2-3\"]/div/div/a/span[3]"))).click();
                driver.findElement(By.xpath("//*[@id=\"yui_3_17_2_1_1673976931360_46\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"single_button63c6dd750aef29\"]")).click();
                driver.findElement(By.xpath("//*[@id=\"region-main\"]/div[2]")).click();
            }catch (Exception caught){
                e = caught;
            }
        }

    }

    public void navigateToPage()
    {
//        driver.get("http://localhost/mod/assign/view.php?id=2&action=editsubmission");
        driver.get("http://localhost/mod/page/view.php?id=9");
//        driver.findElement(By.xpath("//*[@id=\"moremenu-63c930a92a27f-navbar-nav\"]/li[3]/a")).click();
//        driver.findElement(By.xpath("//*[@id=\"course-info-container-2-4\"]/div/div/a/span[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"module-8\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/a/span")).click();
        //driver.findElement(By.xpath("//*[contains(text(),'page') and @class='instancename']")).click();
            }

//
//    // go to question bank from course page (more -> question bank)

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
        // close all the driver windows
        // another option - to close a browser window: driver.close();
        driver.quit();

    }

    public void submitFiles() {
        driver.findElement(By.xpath("//*[@id=\"yui_3_17_2_1_1674068556555_67\"]")).click();
    }
}


