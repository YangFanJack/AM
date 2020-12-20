<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的活动2</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_student_myActivity2.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/tinymce/js/tinymce/tinymce.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/judgeDate.js"></script>
    <script>
        tinymce.init({
            selector: '#leave',
            language:'zh_CN',
            width:814,
            height: 400,
            resize: false
        });
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

            //回显提示
            if($("#msg").val() != ""){
                alert($("#msg").val());
            }
        })
    </script>
</head>

<body>
<!--头部-->
<div class="header w">
    <input id="msg" style="display: none" value="${msg}">
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
        <a href="showMyInfo"><button class="btn">我的信息</button></a>
        <a href="showMyAllEnrollByPage"><button class="btn">我的活动</button></a>
        <a href="showAllMessageByPage"><button class="btn">我的消息</button></a>
        <c:if test="${sessionScope.isTuan==2}">
            <a href="showMyClassmatesByPage"><button class="btn">我的组织</button></a>
            <a href="showMyActivityByPage"><button class="btn">我的申请</button></a>
            <a href="secretory_applyActivity.jsp"><button class="btn">申请活动</button></a>
        </c:if>
    </div>
    <div class="tip">
        <span>欢迎你：${sessionScope.name}</span>
        <span style="font-size: 20px;font-weight: bold ">我的活动</span>
    </div>
    <div class="detailActivity">
        <span>发布组织：
            <c:if test="${stu_act.actId.orgCategory==2}">
                <c:if test="${stu_act.actId.managerId.collegeName=='XX'}">信息学院</c:if>
                <c:if test="${stu_act.actId.managerId.collegeName=='HH'}">航海学院</c:if>
                <c:if test="${stu_act.actId.managerId.collegeName=='JT'}">交通学院</c:if>
                <c:if test="${stu_act.actId.managerId.collegeName=='WY'}">外语学院</c:if>
            </c:if>
            <c:if test="${stu_act.actId.orgCategory==1}">
                <c:if test="${stu_act.actId.tuanId._class=='rj1'}">软件1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='rj2'}">软件2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='jk1'}">计科1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='jk2'}">计科2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='lj1'}">轮机1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='lj2'}">轮机2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='hh1'}">航海1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='hh2'}">航海2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='jg1'}">交管1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='jg2'}">交管2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='wl1'}">物流1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='wl2'}">物流2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='yy1'}">英语1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='yy2'}">英语2班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='ry1'}">日语1班</c:if>
                <c:if test="${stu_act.actId.tuanId._class=='ry2'}">日语2班</c:if>
            </c:if>
        </span>
        <span>活动标题：${stu_act.actId.actTitle}</span>
        <span>活动时间：${stu_act.actId.actBeginTime}——${stu_act.actId.actEndTime}</span>
        <span>活动地点：${stu_act.actId.actPlace}</span>
        <span>报名得分：${stu_act.actId.getPoints}</span>
        <span id="enrollStatus"></span>
        <span id="enrollEndTime" style="display: none">${stu_act.actId.enrollEndTime}</span>
        <span>报名名额上限：${stu_act.actId.numLevel}</span>
        <span>报名截止时间：${stu_act.actId.enrollEndTime}</span>
        <span>活动类型：
            <c:if test="${stu_act.actId.category==4}">
                学院党员活动
            </c:if>
            <c:if test="${stu_act.actId.category==3}">
                学院党团活动
            </c:if>
            <c:if test="${stu_act.actId.category==2}">
                班级党员活动
            </c:if>
            <c:if test="${stu_act.actId.category==1}">
                班级党团活动
            </c:if>
        </span>
        <span>报名人数：${stu_act.actId.enrollNum}</span>
        <span>签到人数：${stu_act.actId.signNum}</span>
        <div style="overflow: auto" readonly="readonly" disabled="disabled">${stu_act.actId.actContent}</div>
    </div>
    <div class="signin">
        <span>签到情况</span>
        <a href="doSign?stu_actId=${stu_act.id}&actId=${stu_act.actId.id}"><button class="btn btn-primary">点击签到</button></a>
        <c:forEach items="${stuInAct}" var="keyword" varStatus="id">
            <div class="studentState">
                <span>${keyword.stuId.name}</span>
                <span>
                    <c:if test="${keyword.isSign==1}">
                        未签到
                    </c:if>
                    <c:if test="${keyword.isSign==2}">
                        已签到
                    </c:if>
                </span>
            </div>
        </c:forEach>
    </div>
    <div class="leavemessage">
        <span>留言区</span>
        <c:forEach items="${stuInAct}" var="keyword" varStatus="id">
            <c:if test="${keyword.wordsContent != null}">
                <div class="detailmessage clearfix">
                    <span>姓名：${keyword.stuId.name}</span>
                    <span>时间：${keyword.wordsTime}</span>
                    <span>内容：${keyword.wordsContent}</span>
                </div>
            </c:if>
        </c:forEach>
        <form action="doWords" method="get">
            <input type="text" value="${stu_act.id}" name="stu_actId" style="display: none">
            <input type="text" value="${stu_act.actId.id}" name="actId" style="display: none">
            <textarea id="leave" style="resize: none;" name="wordsContent" cols="70" rows="10" placeholder="留言内容"></textarea>
            <button class="btn btn-primary">提交留言</button>
        </form>
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