<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生日常党员活动管理系统主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page_index.css" type="text/css">
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
            //需要注意的是需要便利所有控件
            var enrollEndTime = $(".enrollEndTime");
            var enrollStatus = $(".enrollStatus");
            for (var k = 0; k < enrollEndTime.length; k++){
                if(date.isDuringDate(enrollEndTime[k].innerText, '2050-09-17 15:00')){
                    enrollStatus[k].innerText="报名状态：报名已结束";
                }
                else{
                    enrollStatus[k].innerText="报名状态：正在报名";
                }
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
<!--内容-->
<div class="content w clearfix">
    <!--<div class="longpicture"><img src="img/long.jpg" alt="主页长图" /></div>-->
<%--    <h4>活动列表</h4>--%>
    <div class="notice">
        <h3>近期通知</h3>
        <c:forEach items="${notices}" var="keyword" varStatus="id">
            <a href="showAllNoticeByPage"><button class="btn">${keyword.noticeTitle}</button></a>
        </c:forEach>
    </div>
    <div class="searchActivity clearfix">
        当前关键字：${searchString}&nbsp;&nbsp;&nbsp;${choose_collegeName}&nbsp;&nbsp;&nbsp;${choose_class}
        <div class="searchType clearfix">
            <form action="initIndex">
                <select name="collegeName">
                    <option value="XX">信息学院</option>
                    <option value="HH">航海学院</option>
                    <option value="JT">交通学院</option>
                    <option value="WY">外语学院</option>
                </select>
                <button class="btn btn-primary" onclick="this.form.submit()">筛选</button>
            </form>
            <form action="initIndex">
                <select name="_class">
                    <option value="rj1">软件1班</option>
                    <option value="rj2">软件2班</option>
                    <option value="jk1">计科1班</option>
                    <option value="jk2">计科2班</option>
                    <option value="lj1">轮机1班</option>
                    <option value="lj2">轮机2班</option>
                    <option value="hh1">航海1班</option>
                    <option value="hh2">航海2班</option>
                    <option value="jg1">交管1班</option>
                    <option value="jg2">交管2班</option>
                    <option value="wl1">物流1班</option>
                    <option value="wl2">物流2班</option>
                    <option value="yy1">英语1班</option>
                    <option value="yy2">英语2班</option>
                    <option value="ry1">日语1班</option>
                    <option value="ry2">日语2班</option>
                </select>
                <button class="btn btn-primary" onclick="this.form.submit()">筛选</button>
            </form>
            <form action="initIndex">
                <input type="text" name="collegeName" style="display: none" value="${choose_collegeName}">
                <input type="text" name="_class" style="display: none" value="${choose_class}">
                <input name="searchString" type="text" placeholder="关键字搜索"/>
                <button class="btn btn-primary" onclick="this.form.submit()">搜索</button>
            </form>
        </div>

        <c:forEach items="${actPageBean.list}" var="keyword" varStatus="id">
            <div class="activityState">
                <h3><a href="showDetailActivity?actId=${keyword.id}">${keyword.actTitle}</a></h3>
                <span class="time">发布方：
                    <c:if test="${keyword.orgCategory==1}">
                        班级
                    </c:if>
                    <c:if test="${keyword.orgCategory==2}">
                        学院
                    </c:if>
                </span>
                <span class="type">类型：
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
                </span>
                <span class="enrollEndTime" style="display: none">${keyword.enrollEndTime}</span>
                <span class="state enrollStatus"></span>
            </div>
        </c:forEach>

        <div class="fenye">
            <nav aria-label="Page navigation">
                <ul class="pagination pagination-lg">
                    <c:if test="${actPageBean.currentPage <=1 }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${actPageBean.currentPage >1 }">
                    <li>
                        </c:if>
                        <a href="initIndex?currentPage=${actPageBean.currentPage-1}&collegeName=${choose_collegeName}&_class=${choose_class}&searchString=${searchString}" aria-label="Previous">
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
                        <a href="initIndex?currentPage=${i}&collegeName=${choose_collegeName}&_class=${choose_class}&searchString=${searchString}">${i}</a></li>
                    </c:forEach>
                    <c:if test="${actPageBean.currentPage >=actPageBean.totalPage }">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${actPageBean.currentPage <actPageBean.totalPage }">
                    <li>
                        </c:if>
                        <a href="initIndex?currentPage=${actPageBean.currentPage+1}&totalPage=${actPageBean.totalPage}&collegeName=${choose_collegeName}&_class=${choose_class}&searchString=${searchString}" aria-label="Next">
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