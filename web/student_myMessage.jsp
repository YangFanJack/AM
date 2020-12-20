<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的消息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_student_myMessage.css" type="text/css">
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
        <span style="font-size: 20px;font-weight: bold ">我的消息</span>
    </div>
    <div class="searchMessage">
        当前关键字：${searchString}
        <form action="showAllMessageByPage" method="get">
            <input type="text" placeholder="搜索消息" name="searchString"/>
            <button class="btn btn-primary">搜索</button>
        </form>


        <c:forEach items="${mesPageBean.list}" var="keyword" varStatus="id">
            <div class="messageDetail clearfix">
                <div class="ms clearfix">
                    <span class="type">类型：
                        <c:if test="${keyword.mesCategory==4}">
                            活动申请提醒
                        </c:if>
                        <c:if test="${keyword.mesCategory==3}">
                            学院通知提醒
                        </c:if>
                        <c:if test="${keyword.mesCategory==2}">
                            暂无
                        </c:if>
                        <c:if test="${keyword.mesCategory==1}">
                            暂无
                        </c:if>
                    </span>
                    <span class="time">时间：${keyword.mesTime}</span>
                    <span class="state">状态：
                        <c:if test="${keyword.isRead==1}">
                            未读
                        </c:if>
                        <c:if test="${keyword.isRead==2}">
                            已读
                        </c:if>
                    </span>
                </div>
                <span class="messagecontent">消息内容：${keyword.mesContent}</span>
                <a href="doRead?messageId=${keyword.id}"><button class="btn btn-primary">已读</button></a>
                <a href="deleteMessage=${keyword.id}"><button class="btn btn-primary">删除</button></a>
            </div>
        </c:forEach>

        <div class="fenye clearfix">
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg">
                    <c:if test="${mesPageBean.currentPage <=1 }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${mesPageBean.currentPage >1 }">
                    <li>
                        </c:if>
                        <a href="showAllMessageByPage?currentPage=${mesPageBean.currentPage-1}&searchString=${searchString}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach  var="i" begin="1" end="${mesPageBean.totalPage}">
                        <c:if test="${mesPageBean.currentPage == i }">
                            <li class="active">
                        </c:if>
                        <c:if test="${mesPageBean.currentPage != i }">
                            <li>
                        </c:if>
                        <a href="showAllMessageByPage?currentPage=${i}&searchString=${searchString}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${mesPageBean.currentPage >=mesPageBean.totalPage }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${mesPageBean.currentPage <mesPageBean.totalPage }">
                    <li>
                        </c:if>
                        <a href="showAllMessageByPage?currentPage=${mesPageBean.currentPage+1}&totalPage=${mesPageBean.totalPage}&searchString=${searchString}" aria-label="Next">
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