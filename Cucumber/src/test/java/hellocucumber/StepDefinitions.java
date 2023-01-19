package hellocucumber;

import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {

//    private static MoodleStudentActuator;
    private String TEACHER_USERNAME = "admin";
    private String TEACHER_PASSWORD = "12345678sS#";
    private static MoodleTeacherActuator teacherActuator = null;
    private static MoodleStudentActuator studentActuator = null;
    public String webDriver = "webdriver.chrome.driver";


    public String path = "C:\\Users\\edenn\\Downloads\\Chrome Driver\\chromedriver_win32 (1)\\chromedriver.exe";
    public void TeacherMoodleInit() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        if(teacherActuator == null){
            teacherActuator = new MoodleTeacherActuator();
        }
        teacherActuator.initSession(webDriver, path);
    }

    public void studentMoodleInit(){
        if(studentActuator == null){
            studentActuator = new MoodleStudentActuator();
        }
        studentActuator.initSession(webDriver, path);
    }


    // Teacher //


    @Given("student with password and user name")
    public void studentWithPasswordAndUserName() {studentMoodleInit();}

    @And("student log in")
    public void studentLogIn() {studentActuator.goToLogin(); studentActuator.enterLoginInfo("student", "Student1234#");}

    @And("student navigates to page")
    public void studentNavigatesToPage() {studentActuator.navigateToPage();}

    @When("teacher with user_name and password")
    public void teacherWithUser_nameAndPassword() {TeacherMoodleInit();}

    @And("teacher log in")
    public void teacherLogIn() {
        teacherActuator.goToLogin();
        teacherActuator.enterLoginInfo("admin", "12345678sS#");
    }

    @And("teacher navigate to page")
    public void teacherNavigateToPage() {teacherActuator.teacherNavigatePage();}

    @Then("change appearance")
    public void changeAppearance() {
        teacherActuator.changeAppearance();
    }

    @And("terminate moodle")
    public void terminateMoodle() {
        studentActuator.terminateDriver();
        teacherActuator.terminateDriver();
    }

    @Then("uploadfile file")
    public void uploadfileFile() {/* uploaded a file manually */ }

}
