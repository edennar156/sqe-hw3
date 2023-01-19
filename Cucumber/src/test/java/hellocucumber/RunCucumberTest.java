package hellocucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("hellocucumber")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
public class RunCucumberTest {
    private String TEACHER_USERNAME = "admin";
    private String TEACHER_PASSWORD = "12345678Ss#";
    private String COURSE_NAME = "SQE";
    private String STUDENT_USERNAME = "student";
    private String STUDENT_PASSWORD = "Student1234#";
    private int DEFAULT_LENGTH = 5;
    private MoodleTeacherActuator teacher;
    private MoodleStudentActuator student;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "C:\\Users\\edenn\\Downloads\\Chrome Driver\\chromedriver_win32 (1)\\chromedriver.exe";

    public void MoodleTeacherTest() {
        System.out.println("--------------- INITIALIZING MOODLE TEST - OPENING WEBPAGE ---------------");
        teacher = new MoodleTeacherActuator();
        teacher.initSession(webDriver, path);
//        student = new MoodleStudentActuator();
//        student.initSession(webDriver, path);

    }


    public void StudentUploadAssignment() {
        System.out.println("--------------- STARTING MOODLE Assignment TEST ---------------");
        System.out.println("---------------  TEACHER LOGIN ---------------");
        teacher.loginSequence(TEACHER_USERNAME, TEACHER_PASSWORD);


        System.out.println("--------------- NAVIGATING TO COURSE ---------------");
        teacher.myCoursesTab();
        teacher.goToCourse(COURSE_NAME);

//
        System.out.println("---------------  CREATE ASSIGNMENT WITH MAXIMUM QUANTITY OF 3---------------");
//        teacher.addAssignment("Assignment 3");
//        teacher.editAssignment();
//


        System.out.println("--------------- TEACHER LOGOUT ---------------");
        teacher.logoutAndCheck();

    }




}
