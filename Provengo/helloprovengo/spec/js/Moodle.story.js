/* @provengo summon selenium */

/**
 * Story 1
 */

story('read page', function () {
  let s = new SeleniumSession()//.start('http://localhosts')){
  s.goToLogin()
  s.enterLoginInfo({username: "student", password: "Student1234#"})
  s.navigateToPage()
})

/**
 * Story 2
 */

story('delete page', function () {
  // the "with" statement makes it redundant to write "s." before each call to a defined event (like the story above)
  let s = new SeleniumSession()//.start('http://localhost/mod/page/view.php?id=6')) {
  s.goToLogin()
  s.enterLoginInfoT({username: "admin", password: "12345678sS#"})
  s.navigateToPage()

})