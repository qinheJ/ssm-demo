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
    <script src="${pageContext.request.contextPath}/assert/vendor/jquery/jquery.form.min.js"></script>
    <script src="${pageContext.request.contextPath}/assert/vendor/jquery/jquery.validate.min.js"></script>
    <script src="${pageContext.request.contextPath}/assert/js/common.js"></script>
</head>
<body>
<h2>这是login.jsp</h2><br>
<form id="loginForm" action="${pageContext.request.contextPath}/user/login.htm" method="post">
    <label>
        用户名：
        <input name="username"/>
    </label>
    <br/>
    <label>
        密码：
        <input name="password"/>
    </label>
    <br/>
    <button type="submit">登录</button>

</form>
<script>


    $(function () {
        $("#loginForm").submit(function () {
            console.log("submit");
            // return false;
        }).validate({
            errorPlacement: function (error, element) {
                // Append error within linked label
                // $(element)
                //     .closest("form")
                //     .find("label[for='" + element.attr("id") + "']")
                //     .append(error);
                error.css("color", "red");
                $(element).parent().append(error)
            },
            errorElement: "span",
            rules: {
                username: "required",
                password: "required",
            },
            messages: {
                username: "请输入用户名",
                password: "请输入密码",
            },
            submitHandler: function (form) {
                console.log("submitHandler");
                $(form).ajaxSubmit({
                    beforeSubmit: showRequest,
                    success: showResponse,  // post-submit callback
                    //url:       url         // override for form's 'action' attribute
                    //type:      type        // 'get' or 'post', override for form's 'method' attribute
                    dataType: "json",        // 'xml', 'script', or 'json' (expected server response type)
                    //clearForm: true        // clear all form fields after successful submit
                    //resetForm: true        // reset the form after successful submit

                    // $.ajax options can be used here too, for example:
                    //timeout:   3000

                })
            }
        })

    })

    // pre-submit callback
    function showRequest(formData, jqForm, options) {
        // formData is an array; here we use $.param to convert it to a string to display it
        // but the form plugin does this for you automatically when it submits the data
        var queryString = $.param(formData);

        // jqForm is a jQuery object encapsulating the form element.  To access the
        // DOM element for the form do this:
        // var formElement = jqForm[0];

        alert('确定要登录吗: \n\n' + queryString);

        // here we could return false to prevent the form from being submitted;
        // returning anything other than false will allow the form submit to continue
        return true;
    }

    // post-submit callback
    function showResponse(responseData, status, xhr, $form) {
        // for normal html responses, the first argument to the success callback
        // is the XMLHttpRequest object's responseText property

        // if the ajaxForm method was passed an Options Object with the dataType
        // property set to 'xml' then the first argument to the success callback
        // is the XMLHttpRequest object's responseXML property

        // if the ajaxForm method was passed an Options Object with the dataType
        // property set to 'json' then the first argument to the success callback
        // is the json data object returned by the server

        // alert('status: ' + statusText + '\n\nresponseText: \n' + responseText +
        //     '\n\nThe output div should have already been updated with the responseText.');

        if (responseData.success) {
            alert("登录成功");
            window.location = getRootPath() + "/index.htm";
        } else {
            alert("登录失败");
        }

        return "dsadsa";

    }

</script>

</body>
</html>
