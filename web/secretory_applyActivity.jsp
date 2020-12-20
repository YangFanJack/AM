<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>申请活动</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_secretory_applyActivity.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/tinymce/js/tinymce/tinymce.min.js"></script>

    <script>
        tinymce.init({
            selector: '#nactivityContent',
            language:'zh_CN',
            width:565,
            height: 400,
            resize: false
        });
        $(function(){
            var aa=window.sessionStorage.getItem("key");
            console.log(aa);
            if(aa=="kkk"){
                $("#tijiao").css("display","none")
                $("#genggai").css("display","inline")
            }
            else{
                $("#tijiao").css("display","inline")
                $("#genggai").css("display","none")
            }
            window.sessionStorage.clear()


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
        });
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
        <a href="showMyInfo"><button class="btn">我的信息</button></a>
        <a href="showMyAllEnrollByPage"><button class="btn">我的活动</button></a>
        <a href="showAllMessageByPage"><button class="btn">我的消息</button></a>
        <a href="showMyClassmatesByPage"><button class="btn">我的组织</button></a>
        <a href="showMyActivityByPage"><button class="btn">我的申请</button></a>
        <a href="secretory_applyActivity.jsp"><button class="btn">申请活动</button></a>
    </div>
    <div class="tip">
        <span>欢迎你：${sessionScope.name}</span>
        <span style="font-size: 20px;font-weight: bold ">申请活动</span>
    </div>
    <div class="applyActivity">
        <form action="
<c:if test="${msg!='change'}">applyActivity</c:if>
<c:if test="${msg=='change'}">changeActivity</c:if>
" method="get" class="clearfix">
            <input type="text" name="organization" value="活动发起者：
<c:if test="${sessionScope._class=='rj1'}">软件1班</c:if>
<c:if test="${sessionScope._class=='rj2'}">软件2班</c:if>
<c:if test="${sessionScope._class=='jk1'}">计科1班</c:if>
<c:if test="${sessionScope._class=='jk2'}">计科2班</c:if>
<c:if test="${sessionScope._class=='lj1'}">轮机1班</c:if>
<c:if test="${sessionScope._class=='lj2'}">轮机2班</c:if>
<c:if test="${sessionScope._class=='hh1'}">航海1班</c:if>
<c:if test="${sessionScope._class=='hh2'}">航海2班</c:if>
<c:if test="${sessionScope._class=='jg1'}">交管1班</c:if>
<c:if test="${sessionScope._class=='jg2'}">交管2班</c:if>
<c:if test="${sessionScope._class=='wl1'}">物流1班</c:if>
<c:if test="${sessionScope._class=='wl2'}">物流2班</c:if>
<c:if test="${sessionScope._class=='yy1'}">英语1班</c:if>
<c:if test="${sessionScope._class=='yy2'}">英语2班</c:if>
<c:if test="${sessionScope._class=='ry1'}">日语1班</c:if>
<c:if test="${sessionScope._class=='ry2'}">日语2班</c:if>
" disabled="disabled"  required="required"/>

            <select name="category"  required="required">
                <option value="" style="display: none;">活动类型</option>
                <c:if test="${activity.category==2}">
                    <option value="2" selected="selected">班级党员活动</option>
                    <option value="1">班级党团活动</option>
                </c:if>
                <c:if test="${activity.category==1}">
                    <option value="2">班级党员活动</option>
                    <option value="1" selected="selected">班级党团活动</option>
                </c:if>
                <c:if test="${activity.category!=2 && activity.category!=1}">
                    <option value="2">班级党员活动</option>
                    <option value="1">班级党团活动</option>
                </c:if>

            </select>
            <input name="actTitle" type="text" placeholder="活动标题" required="required" value="${activity.actTitle}"/>
            <select name="getPoints"  required="required">
                <option value="" style="display: none;">报名得分</option>
                <c:if test="${activity.getPoints==1}">
                    <option value="1" selected="selected">1分</option>
                    <option value="2">2分</option>
                    <option value="3">3分</option>
                </c:if>
                <c:if test="${activity.getPoints==2}">
                    <option value="1">1分</option>
                    <option value="2" selected="selected">2分</option>
                    <option value="3">3分</option>
                </c:if>
                <c:if test="${activity.getPoints==3}">
                    <option value="1">1分</option>
                    <option value="2">2分</option>
                    <option value="3" selected="selected">3分</option>
                </c:if>
                <c:if test="${activity.getPoints!=1 && activity.getPoints != 2 && activity.getPoints != 3}">
                    <option value="1">1分</option>
                    <option value="2">2分</option>
                    <option value="3">3分</option>
                </c:if>

            </select>
            <c:set value="${fn:split(activity.actBeginTime ,' ')}"  var="beginTime" />
            <c:set value="${fn:split(activity.actEndTime ,' ')}"  var="endTime" />
            <c:set value="${fn:split(activity.enrollEndTime ,' ')}"  var="enrollTime" />
            <input class="show_placeholder" type="date" name="actBeginD" placeholder="开始日期"  required="required" value="${beginTime[0]}"/>
            <input class="show_placeholder" type="time" name="actBeginT" placeholder="开始时间"  required="required" value="${beginTime[1]}"/>
            <input class="show_placeholder" type="date" name="actEndD" placeholder="结束日期" required="required" value="${endTime[0]}"/>
            <input class="show_placeholder" type="time" name="actEndT" placeholder="结束时间"  required="required" value="${endTime[1]}"/>

            <input name="actPlace" type="text" placeholder="活动地点"  required="required" value="${activity.actPlace}"/>
            <input name="numLevel" type="text" placeholder="报名名额上限"  required="required" value="${activity.numLevel}"/>
            <input class="show_placeholder" type="date" placeholder="报名截止日期" name="enrollEndD" required="required" value="${enrollTime[0]}"/>
            <input class="show_placeholder" type="time" placeholder="报名截止时间" name="enrollEndT" required="required" value="${enrollTime[1]}"/>
            <textarea id="nactivityContent" style="resize: none;" name="actContent" cols="70" rows="10" placeholder="活动内容">${activity.actContent}</textarea>
            <input id="tijiao" class="btn btn-primary btn1" type="submit" value="提交申请"/>
        </form>
        <lable style="color: red; font-size:150% ; margin-left: 290px;"><c:if test="${msg!='change'}">${msg}</c:if></lable>
    </div>
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