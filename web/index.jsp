<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Ask My Smartwatch</title>
</head>
<body>
<h1>Ask My Smartwatch from jsp</h1>

<form action="/searchResult" method="post" >
  please enter: <input type="text" name="searchInput" width="30">
  <input type="submit" value="Search">
</form>

<h2>${answer}</h2>

</body>
</html>

