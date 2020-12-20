<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>审核活动1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_institution_verifyActivity1.css" type="text/css">
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
    <div class="searchActivity clearfix">
        当前关键字：${searchString}&nbsp;&nbsp;&nbsp;${category}
        <div class="searchType clearfix">
            <form action="showVerifyingActivityByPage" method="get">
                <select name="category">
                    <option value="4">学院党员活动</option>
                    <option value="3">学院党团活动</option>
                    <option value="2">班级党员活动</option>
                    <option value="1">班级党团活动</option>
                </select>
                <button class="btn btn-primary" onclick="this.form.submit()">筛选</button>
            </form>
            <form action="showVerifyingActivityByPage" method="get">
                <input type="text" name="category" style="display: none" value="${category}">
                <input type="text" name="searchString"/>
                <button class="btn btn-primary" onclick="this.form.submit()">搜索</button>
            </form>
        </div>
        <c:forEach items="${actPageBean.list}" var="keyword" varStatus="id">
            <div class="activityState">
                <h3><a href="showDetailActivity?actId=${keyword.id}&to=verify">${keyword.actTitle}</a></h3>
                <label class="time">发布方：
                    <c:if test="${keyword.orgCategory==1}">
                        班级
                    </c:if>
                    <c:if test="${keyword.orgCategory==2}">
                        学院
                    </c:if>
                </label>
                <label class="type">类型：
                    <c:if test="${keyword.category==4}">
                        学院党员活动
                    </c:if>
                    <c:if test="${keyword.category==3}">
                        学院党团活动
                    </c:if>
                    <c:if test="${keyword.category==2}">
                        班级党员活动
                    </c:if>
                    <c:if test="${keyword.category==1}">
                        班级党团活动
                    </c:if>
                </label>
                <label class="state">
                    <c:if test="${keyword.verifyStatus==1}">
                        审核状态：待审核
                    </c:if>
                    <c:if test="${keyword.verifyStatus==2}">
                        审核状态：已通过
                    </c:if>
                </label>
            </div>
        </c:forEach>
        <div class="fenye clearfix">
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg">
                    <c:if test="${actPageBean.currentPage <=1 }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${actPageBean.currentPage >1 }">
                    <li>
                        </c:if>
                        <a href="showVerifyingActivityByPage?currentPage=${actPageBean.currentPage-1}&category=${category}&searchString=${searchString}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <c:forEach  var="i" begin="1" end="${actPageBean.totalPage}">
                        <c:if test="${actPageBean.currentPage == i }">
                            <li class="active">
                        </c:if>
                        <c:if test="${actPageBean.currentPage != i }">
                            <li>
                        </c:if>
                        <a href="showVerifyingActivityByPage?currentPage=${i}&category=${category}&searchString=${searchString}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${actPageBean.currentPage >=actPageBean.totalPage }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${actPageBean.currentPage <actPageBean.totalPage }">
                    <li>
                        </c:if>
                        <a href="showVerifyingActivityByPage?currentPage=${actPageBean.currentPage+1}&totalPage=${actPageBean.totalPage}&category=${category}&searchString=${searchString}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
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