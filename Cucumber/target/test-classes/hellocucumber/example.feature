Feature:  A set of scenarios for testing the moodle file attachment

#  Scenario Outline: Student upload 3 files
#    Given teacher with user_name <TeacherUserName> and password <TeacherPassword>
#    And teacher Navigate to course <CourseName>
#    And  teacher edit Assignment <AssignmentName>
##    And teacher creates Assignment <AssignmentName>
#    And teacher set the maximal quantity of files to 3
#    When student with password <StudentPassword> and user name <StudentUserName>
#    And student navigates to course <CourseName>
#    And student is in the assignment <AssignmentName>
#    Then Student submit the 3 files of the assignment
#    Examples:
#      | TeacherUserName | TeacherPassword | CourseName | AssignmentName | StudentPassword | StudentUserName |
#      | "admin"         | "12345678sS#"   | "SQE"      | "Assignment 3" | "Student1234#"  | "student"       |
#
#
#  Scenario Outline: teacher set successfully the maximum quantity of files to 2
#    Given teacher with user_name <TeacherUserName> and password <TeacherPassword>
#    And teacher Navigate to course <CourseName>
##    When teacher creates Assignment <AssignmentName>
#    When teacher edit Assignment <AssignmentName>
#    Then teacher set the maximal quantity of files to 2
#    Examples:
#      | TeacherUserName | TeacherPassword | CourseName | AssignmentName |
#      | "admin"       | "12345678sS#"   | "SQE"      | "Assignment 3" |

#  Scenario Outline: Combine teacher set max quantity of file to 2 and student upload 3 files
#    Given teacher with user_name <TeacherUserName> and password <TeacherPassword>
#    And teacher Navigate to course <CourseName>
#    And  teacher edit Assignment <AssignmentName>
#    And teacher set the maximal quantity of files to 3
#    And student with password <StudentPassword> and user name <StudentUserName>
#    And student navigates to course <CourseName>
#    And student is in the assignment <AssignmentName>
#    When teacher set the maximal quantity of files to 2
#    Then Student submit the 3 files of the assignment
#    And exception thrown
#    Examples:
#      | TeacherUserName | TeacherPassword | CourseName | AssignmentName | StudentPassword | StudentUserName |
#      | "admin"         | "12345678sS#"   | "SQE"      | "Assignment 3" | "Student1234#"  | "student"       |

    Scenario: Teacher
        Given teacher with user_name and password
        And teacher log in
        Then uploadfile file


    Scenario: Student
        Given student with password and user name
        And student log in
        And student navigates to page


    Scenario: Teacher & student
    Given student with password and user name
    And student log in
    And student navigates to page
    When teacher with user_name and password
    And teacher log in
    And teacher navigate to page
    Then change appearance
    And terminate moodle

