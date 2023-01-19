/* @Provengo summon selenium */

/**
 go to log in
 */
defineEvent(SeleniumSession, "GoToLogin", function(session, e) {
    with (session) {
        session.click("//*[@id=\"usernavigation\"]/div/div/span/a")
        session.sleep(3000)
    }
})

/**
 enter log in info
 */
defineEvent(SeleniumSession, "EnterLoginInfo", function(session, e) {
    with (session) {
        writeText("//*[@id=\"username\"]", e.username)
        session.sleep(3000)
        writeText("//*[@id=\"password\"]", e.password)
        session.sleep(3000)
    }
})

/**
 navigate to page
 */
defineEvent(SeleniumSession, "NavigateToPage", function(session, e) {
    with (session) {
        session.click("//*[@id=\"moremenu-63c8970aee3db-navbar-nav\"]/li[3]/a")
        session.sleep(5000)
        session.click("//*[@id=\"course-info-container-2-3\"]/div/div/a/span[3]")
        session.sleep(5000)
        session.click("//*[@id=\"module-6\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/a")
        session.sleep(5000)
    }
})
defineEvent(SeleniumSession, "EnterLoginInfoT", function(session, e) {
    with (session) {
        writeText("//*[@id=\"username\"]", e.username)
        session.sleep(3000)
        writeText("//*[@id=\"password\"]", e.password)
        session.sleep(3000)
    }
})

defineEvent(SeleniumSession, "Delete page", function(session, e) {
    with (session) {

        session.sleep(5000)
        session.click("\"action-menu-toggle-2\"")
        session.sleep(5000)
        session.click(" //*[@id=\"actionmenuaction-7\"]")
        session.sleep(5000)
        session.click("//*[@class='btn btn-primary']")
        session.sleep(5000)
    }
})
