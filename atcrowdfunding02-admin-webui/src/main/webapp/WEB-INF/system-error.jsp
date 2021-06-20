<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/20
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>出错啦！</h1>

    <!-- 从请求域取出Exception对象，再进一步访问message属性就能够显示错误消息-->
    ${requestScope.exception.message}
</body>
</html>
