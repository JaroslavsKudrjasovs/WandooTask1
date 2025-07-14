# Task:

1. Authorize user through public REST endpoint “/rest/public/login”
2. Fetch user balance through authorized endpoint “/rest/public/profile/account-entries”
3. Assert that user’s “openingBalance” == 0.00
4. If the test user has a broken state, register a new random one through a web page.
5. If you have experience in web test automation, additionally you can automate this same test with the web tests as
   well (not mandatory, completely up to you).

to run REST tests, right click
com.wandoo.resttests.SwaperLoginTest
and select Run 'SwaperLoginTest'
or in terminal type 'mvn clean test' and press 'Enter'

to run WEB tests, right click
com.wandoo.web.selenium.BalanceCheckTest
and select Run 'BalanceCheckTest'
or right click 'smokeTests.xml' and select Run 'smokeTests.xml'
