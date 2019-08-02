<%--
  Created by IntelliJ IDEA.
  User: qinhe
  Date: 2019-07-29
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/assert/vendor/jquery/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/assert/js/common.js"></script>
</head>
<body>
<h2>这是index.jsp</h2><br>
pageContext.request.contextPath:${pageContext.request.contextPath}

<button onclick="logout()">登出</button>
<button onclick="showName()">显示名字</button>
</body>
<script>
    function logout() {
        $.ajax({
            url: getRootPath() + "/user/logout.htm",
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    alert("退出成功");
                    window.location.reload();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (errorThrown == "Internal Server Error") {
                    alert("服务器繁忙!!!");
                }
            }
        })
    }

    function showName() {
        $.ajax({
            url: getRootPath() + "/user/showName.htm",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    alert(data.data)
                } else {
                    alert(data.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (errorThrown == "Internal Server Error") {
                    alert("服务器繁忙!!!");
                }
            }
        })
    }
</script>
</html>
