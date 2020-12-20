<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_student_register.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/reg.js"></script>
    <script>
        $(function (){
            //登录用户信息显示
            var currentUser = "<%=session.getAttribute("sid")%>";
            var currentManager = "<%=session.getAttribute("mid")%>";
            if (currentUser != "null") {
                $("#afterLogin1").removeAttr("hidden");
            }
            else{
                $("#afterLogin1").attr("hidden","hidden");
            }
            if (currentManager != "null") {
                $("#afterLogin2").removeAttr("hidden");
            }
            else{
                $("#afterLogin2").attr("hidden","hidden");
            }

            //验证码
            $("#checkCode").click(function (){
                var date = new Date().getTime();
                this.src = "checkCode?signal="+date;
            })
        })
    </script>
</head>

<body>
<!--头部-->
<div class="header w">
    <!--创建导航条-->
    <div class="nav">

        <div class="nav-left">
            <div>
                <a href="showAllNoticeByPage"><button class="btn">通知</button></a>
                <a href="student_login.jsp"><button class="btn">登录</button></a>
                <a href="student_register.jsp"	><button class="btn">注册</button></a>
                <a href="institution_login.jsp"><button class="btn">我是管理员</button></a>
            </div>
        </div>
        <div id="afterLogin1" class="nav-right">
            <span>当前用户 :<%=session.getAttribute("name")%>(<%=session.getAttribute("stuNum")%>)</span>
            <a href="showMyInfo"><button class="btn btn-primary">学生主页</button></a>
            <a href="stuExit"><button class="btn btn-warning">退出登录</button></a>
        </div>
        <div id="afterLogin2" class="nav-right">
            <span>当前管理员 :<%=session.getAttribute("username")%></span>
            <a href="showClassmatesByPage?_class=${sessionScope._class}"><button class="btn btn-primary">管理员主页</button></a>
            <a href="manExit"><button class="btn btn-warning">退出登录</button></a>
        </div>
    </div>
    <div class="logo">
        <a href="initIndex" title="活动管理网站">
            <img src="img/logo.jpg" alt="网站的logo">
        </a>
    </div>
</div>

<!--内容-->
<div class="content w clearfix">
    <span>学生注册</span>
    <div class="register">
        <form class="clearfix" action="studentRegister">
            <input id="name" class="textBox" type="text" placeholder="姓名" name="name"  required="required" onkeyup="checkName()"/>
            <input id="password" class="textBox" type="password" placeholder="密码 8-20位"  name="password" required="required" onkeyup="checkPassword()"/>
            <input id="checkpassword" class="textBox" type="password" placeholder="确认密码"  name="rePassword" required="required" onkeyup="equalPassword()"/>
            <select id="xueyuan" name="collegeName" onchange="checkxueyuan()" required="required">
                <option value="" style="display: none;">学院</option>
                <option value="XX" id="XX" >信息学院</option>
                <option value="HH" id="HH" >航海学院</option>
                <option value="JT" id="JT" >交通学院</option>
                <option value="WY" id="WY" >外语学院</option>
            </select>
            <select id="banji" name="_class"  required="required">
                <option value="" id="kong"style="display: none;">班级</option>
                <option value="rj1" id="rj1" style="display: none;">软件1班</option>
                <option value="rj2" id="rj2" style="display: none;">软件2班</option>
                <option value="jk1" id="jk1" style="display: none;">计科1班</option>
                <option value="jk2" id="jk2" style="display: none;">计科2班</option>
                <option value="lj1" id="lj1" style="display: none;">轮机1班</option>
                <option value="lj2" id="lj2" style="display: none;">轮机2班</option>
                <option value="hh1" id="hh1" style="display: none;">航海1班</option>
                <option value="hh2" id="hh2" style="display: none;">航海2班</option>
                <option value="jg1" id="jg1" style="display: none;">交管1班</option>
                <option value="jg2" id="jg2" style="display: none;">交管2班</option>
                <option value="wl1" id="wl1" style="display: none;">物流1班</option>
                <option value="wl2" id="wl2" style="display: none;">物流2班</option>
                <option value="yy1" id="yy1" style="display: none;">英语1班</option>
                <option value="yy2" id="yy2" style="display: none;">英语2班</option>
                <option value="ry1" id="ry1" style="display: none;">日语1班</option>
                <option value="ry2" id="ry2" style="display: none;">日语2班</option>
            </select>
            <select name="identity">
                <option value="1">团员</option>
                <option value="2">党员</option>
            </select>
            <lable class="zi">是否团支书? 是:</lable><input class="radio1" type="radio" name="isTuan" value="2" />
            <lable class="zi1">否:</lable><input class="radio1" type="radio" name="isTuan" value="1" checked/>
            <input id="submit" class="btn1 btn btn-primary" type="submit" value="注册" />
        </form>
        <span	id="tishi"></span>
    </div>
</div>
<!--内容结束-->
<!--网站底部-->
<div class="footer clearfix">
    <div class="w">
        <p>
            <a href="initIndex">网站主页</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="showAllNoticeByPage">网站通知</a>
        </p>
        <br/>

        <br/>
        <p>
            <a href="Mailto:yangfanjack1024@qq.com">如果您对该网站有任何意见，点击这里发送邮件给我们。</a>
        </p>
    </div>
</div>
</body>

</html>