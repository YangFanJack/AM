<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的活动1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_student_myActivity1.css" type="text/css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" type="text/css">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
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
        <span style="font-size: 20px;font-weight: bold ">申请活动</span>
    </div>

    <div class="searchActivity">
        当前关键字：${searchString}
        <form action="showMyAllEnrollByPage" method="get">
            <input type="text" placeholder="按标题搜索" name="searchString"/>
            <button class="btn btn-primary">搜索</button>
        </form>

        <c:forEach items="${stu_actPageBean.list}" var="keyword" varStatus="id">
            <div class="activityDetail">
                <h3><a href="showMyDetailEnroll?stu_actId=${keyword.id}&actId=${keyword.actId.id}">${keyword.actId.actTitle}</a></h3>
                <span class="time">发布方：
                    <c:if test="${keyword.actId.orgCategory==1}">
                        班级
                    </c:if>
                    <c:if test="${keyword.actId.orgCategory==2}">
                        学院
                    </c:if>
                </span>
                <span class="type">类型：
                    <c:if test="${keyword.actId.category==4}">
                        学院党员活动
                    </c:if>
                    <c:if test="${keyword.actId.category==3}">
                        学院党团活动
                    </c:if>
                    <c:if test="${keyword.actId.category==2}">
                        班级党员活动
                    </c:if>
                    <c:if test="${keyword.actId.category==1}">
                        班级党团活动
                    </c:if>
                </span>
                <span class="state">参加状态：
                    <c:if test="${keyword.joinStatus==1}">
                        已报名
                    </c:if>
                    <c:if test="${keyword.joinStatus==2}">
                        已签到
                    </c:if>
                    <c:if test="${keyword.joinStatus==3}">
                        已逾期
                    </c:if>
                </span>
            </div>
        </c:forEach>

        <div class="fenye clearfix">
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg">
                    <c:if test="${stu_actPageBean.currentPage <=1 }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stu_actPageBean.currentPage >1 }">
                    <li>
                        </c:if>
                        <a href="showMyAllEnrollByPage?currentPage=${stu_actPageBean.currentPage-1}&searchString=${searchString}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach  var="i" begin="1" end="${stu_actPageBean.totalPage}">
                        <c:if test="${stu_actPageBean.currentPage == i }">
                            <li class="active">
                        </c:if>
                        <c:if test="${stu_actPageBean.currentPage != i }">
                            <li>
                        </c:if>
                        <a href="showMyAllEnrollByPage?currentPage=${i}&searchString=${searchString}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${stu_actPageBean.currentPage >=stu_actPageBean.totalPage }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${stu_actPageBean.currentPage <stu_actPageBean.totalPage }">
                    <li>
                        </c:if>
                        <a href="showMyAllEnrollByPage?currentPage=${stu_actPageBean.currentPage+1}&totalPage=${stu_actPageBean.totalPage}&searchString=${searchString}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
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