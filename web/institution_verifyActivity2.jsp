<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>审核活动2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_institution_verifyActivity2.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/judgeDate.js"></script>
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

            //判断指定时间是否大于当前系统时间
            var enrollEndTime = $("#enrollEndTime").text();
            if(date.isDuringDate(enrollEndTime, '2050-09-17 15:00')){
                $("#enrollStatus").text("报名状态：报名已结束");
            }
            else{
                $("#enrollStatus").text("报名状态：正在报名");
            }
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

<!--网站内容-->
<div class="content w clearfix">
    <div class="navBar">
        <p>导航栏</p>
        <a href="showClassmatesByPage"><button class="btn">党/团员管理</button></a>
        <a href="showPassActivityByPage"><button class="btn">活动管理</button></a>
        <a href="showVerifyingActivityByPage"><button class="btn">审核活动</button></a>
        <a href="institution_releaseActivity.jsp"><button class="btn">发布活动</button></a>
        <a href="institution_releaseNotice.jsp"><button class="btn">发布通知</button></a>
        <a href="showManNoticeByPage"><button class="btn">通知管理</button></a>
    </div>
    <div class="tip">
        <span>学院名称：
            <c:if test="${sessionScope.collegeName=='XX'}">
                信息学院
            </c:if>
            <c:if test="${sessionScope.collegeName=='HH'}">
                航海学院
            </c:if>
            <c:if test="${sessionScope.collegeName=='JT'}">
                交通学院
            </c:if>
            <c:if test="${sessionScope.collegeName=='WY'}">
                外语学院
            </c:if>
        </span>
        <span style="font-size: 20px;font-weight: bold ">审核活动</span>
    </div>
    <div class="detail">
        <span >所属组织：
            <c:if test="${activity.orgCategory==1}">
                <c:if test="${activity.tuanId._class=='rj1'}">软件1班</c:if>
                <c:if test="${activity.tuanId._class=='rj2'}">软件2班</c:if>
                <c:if test="${activity.tuanId._class=='jk1'}">计科1班</c:if>
                <c:if test="${activity.tuanId._class=='jk2'}">计科2班</c:if>
                <c:if test="${activity.tuanId._class=='lj1'}">轮机1班</c:if>
                <c:if test="${activity.tuanId._class=='lj2'}">轮机2班</c:if>
                <c:if test="${activity.tuanId._class=='hh1'}">航海1班</c:if>
                <c:if test="${activity.tuanId._class=='hh2'}">航海2班</c:if>
                <c:if test="${activity.tuanId._class=='jg1'}">交管1班</c:if>
                <c:if test="${activity.tuanId._class=='jg2'}">交管2班</c:if>
                <c:if test="${activity.tuanId._class=='wl1'}">物流1班</c:if>
                <c:if test="${activity.tuanId._class=='wl2'}">物流2班</c:if>
                <c:if test="${activity.tuanId._class=='yy1'}">英语1班</c:if>
                <c:if test="${activity.tuanId._class=='yy2'}">英语2班</c:if>
                <c:if test="${activity.tuanId._class=='ry1'}">日语1班</c:if>
                <c:if test="${activity.tuanId._class=='ry2'}">日语2班</c:if>
            </c:if>
            <c:if test="${activity.orgCategory==2}">
                <c:if test="${activity.managerId.collegeName=='XX'}">信息学院</c:if>
                <c:if test="${activity.managerId.collegeName=='HH'}">航海学院</c:if>
                <c:if test="${activity.managerId.collegeName=='JT'}">交通学院</c:if>
                <c:if test="${activity.managerId.collegeName=='WY'}">外语学院</c:if>
            </c:if>
        </span>
        <span style="overflow: auto;">活动标题：${activity.actTitle}</span>
        <span>活动时间：${activity.actBeginTime}—${activity.actEndTime}</span>
        <span>活动地点：${activity.actPlace}</span>
        <span>报名得分：${activity.getPoints}</span>
        <span id="enrollStatus"></span>
        <span id="enrollEndTime" style="display: none">${activity.enrollEndTime}</span>
        <span>报名名额上限：${activity.numLevel}</span>
        <span>报名截止时间：${activity.enrollEndTime}</span>
        <span>活动类型：
            <c:if test="${activity.category==4}">
                学院党员活动
            </c:if>
            <c:if test="${activity.category==3}">
                学院党团活动
            </c:if>
            <c:if test="${activity.category==2}">
                班级党员活动
            </c:if>
            <c:if test="${activity.category==1}">
                班级党团活动
            </c:if>
        </span>
        <div name="" readonly="readonly" disabled="disabled">活动详情</div>
        <a href="passActivity?actId=${activity.id}"><button class="btn btn-primary">审核成功</button></a>
        <a href="outActivity?actId=${activity.id}"><button class="btn btn-primary">审核失败</button></a>
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