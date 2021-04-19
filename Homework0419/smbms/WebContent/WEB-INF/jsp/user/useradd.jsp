<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%> <%--spring表单的标记标签库 prefix前缀，可以自己起名字--%>
<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户添加页面</span>
        </div>
        <div class="providerAdd">
            <fm:form method="post" id="userForm" action="${pageContext.request.contextPath }/user/addUserSave.do" modelAttribute="user">
<%--            <form id="userForm" name="userForm" method="post" action="${pageContext.request.contextPath }/user/addUserSave.do">--%>
<%--			<input type="hidden" name="method" value="add">--%>
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div>
                    <fm:label for="userCode" path="userCode">用户编码：</fm:label>
                    <fm:input path="userCode" id="userCode" />
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <fm:label for="userName" path="userName">用户名称：</fm:label>
                    <fm:input path="userName" id="userName" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="userPassword" for="userPassword">用户密码：</fm:label>
                    <fm:password path="userPassword" id="userPassword" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="ruserPassword" for="ruserPassword">确认密码：</fm:label>
                    <fm:password path="ruserPassword" id="ruserPassword" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="gender">用户性别：</fm:label>
                    <fm:radiobutton path="gender" id="gender" value="1" />男
                    <fm:radiobutton path="gender" id="gender" value="2" />女
<%--                    <fm:radiobutton path="gender" id="gender">--%>
<%--                        <fm:option value="1"  >男</fm:option>--%>
<%--                        <fm:option value="2">女</fm:option>--%>
                </div>
                <div>
                    <fm:label path="birthday" for="birthday">出生日期：</fm:label>
                    <fm:input path="birthday" id="birthday" cssClass="Wdate;" readonly="readonly" onclick="WdatePicker();" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="phone" for="phone">用户电话：</fm:label>
                    <fm:input path="phone" id="phone" valie="" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="address" for="address">用户地址：</fm:label>
                    <fm:input path="address" id="address" value="" />
                </div>
                <div>
                    <fm:label path="userRole">用户角色</fm:label>
                    <!-- 列出所有的角色分类 -->
                    <fm:select path="userRole" id="userRole"></fm:select>
	        		<font color="red"></font>
                </div>
                <div class="providerAddBtn">
                    <fm:button name="add" id="add">保存</fm:button>
                    <fm:button name="back" id="back">返回</fm:button>
                </div>
<%--            </form>--%>
            </fm:form>
        </div>
</div>
</section>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/useradd.js"></script>
