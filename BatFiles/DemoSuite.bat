set projectLocation=C:\HCL\TESTAUTOMATION\ColesOnline_WCS
cd %projectLocation%
cmd /k mvn clean test -DsuiteXmlFile=DemoSuite.xml
pause