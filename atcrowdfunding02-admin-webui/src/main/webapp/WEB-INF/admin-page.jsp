<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2021/6/22
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">

<%@include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css"/>
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<script type="text/javascript">
    $(function(){
        // 调用后面声明的函数对页码导航条进行初始化操作
        initPagination();
    });
    // 生成页码导航条的函数
    function initPagination() {
        var totalRecord = ${requestScope.pageInfo.total}
        var properties = {
            // 边缘页
            num_edge_entries: 3,
            // 主体页数
            num_display_entries: 5,
            callback: pageSelectCallback,
            //每页显示多少条
            items_per_page: ${requestScope.pageInfo.pageSize},
            // 这个插件默认是从 0 开始的
            current_page: ${requestScope.pageInfo.pageNum - 1},
            prev_text: "上一页",
            next_text: "下一页"
        };
        // 生成页面导航条
        $("#Pagination").pagination(totalRecord, properties);
    }
    // 用户点击 1、2、3这样的页码调用这个
    // pageIndex 是 Pagination传给我们的那个 从0开始的页码
    function pageSelectCallback(pageIndex, jQuery) {
        var pageNum = pageIndex + 1;
        // 跳转页面
        window.location.href = "admin/get/page.html?pageNum=" + pageNum+"&keyword=${param.keyword}"
        return false;
    }
</script>
<body>

<%@include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@include file="/WEB-INF/include-sidebar.jsp" %>


            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                    </div>
                    <div class="panel-body">
                        <form action="admin/get/page.html" method="post" class="form-inline" role="form" style="float:left;">
                            <div class="form-group has-feedback">
                                <div class="input-group">
                                    <div class="input-group-addon">查询条件</div>
                                    <input name="keyword" class="form-control has-success" type="text" placeholder="请输入查询条件">
                                </div>
                            </div>
                            <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                        </form>
                        <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>

                        <%--<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>--%>
                        <a style="float: right;" href="admin/to/add/page.html" class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i> 新增</a>
                        <br>
                        <hr style="clear:both;">
                        <div class="table-responsive">
                            <table class="table  table-bordered">
                                <thead>
                                <tr>
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox"></th>
                                    <th>账号</th>
                                    <th>名称</th>
                                    <th>邮箱地址</th>
                                    <th width="100">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty requestScope.pageInfo.list}">
                                    <tr>
                                        <td colspan="6">抱歉！没有查询到您要的数据！</td>
                                    </tr>
                                </c:if>
                                <c:if test="${!empty requestScope.pageInfo.list}">
                                    <c:forEach items="${requestScope.pageInfo.list}" var="admin" varStatus="myStatus">
                                        <tr>
                                            <td>${myStatus.count}</td>
                                            <td><input type="checkbox"></td>
                                            <td>${admin.loginAcct}</td>
                                            <td>${admin.userName}</td>
                                            <td>${admin.email}</td>
                                            <td>
                                                <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                                <a href="admin/to/edit/page.html?adminId=${admin.id }&pageNum=${requestScope.pageInfo.pageNum }&keyword=${param.keyword }" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></a>
                                                <a href="admin/remove/${admin.id}/${requestScope.pageInfo.pageNum}/${param.keyword}.html" class="btn btn-danger btn-xs"><i class="glyphicon glyphicon-remove"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td colspan="6" align="center">
                                    <div id="Pagination" class="pagination"><%--这里显示分页--%></div>
                                </td>
                            </tr>

                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
     </div>
</div>

</body>
</html>