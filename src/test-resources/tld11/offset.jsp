<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page" 
  xmlns:display="urn:jsptld:../../../src/tld/displaytag-12.tld">
  <jsp:text> <![CDATA[<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> ]]> </jsp:text>
  <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
      <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1" />
      <title>Displaytag unit test</title>
    </head>
    <body>
      <jsp:scriptlet> <![CDATA[
                Object testData = new org.displaytag.test.KnownNumberedList();
                request.setAttribute( "test", testData);
            ]]> </jsp:scriptlet>
      <display:table name="requestScope.test" id="table" offset="3" length="2">
        <display:column property="number"/>
      </display:table>
    </body>
  </html>
</jsp:root>