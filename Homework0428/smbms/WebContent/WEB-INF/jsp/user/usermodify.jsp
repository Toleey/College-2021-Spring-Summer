<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%> <%--spring表单的标记标签库 prefix前缀，可以自己起名字--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>用户管理页面 >> 用户修改页面</span>
        </div>
        <div class="providerAdd">
            <fm:form id="userForm" modelAttribute="user" name="userForm" method="post" action="${pageContext.request.contextPath }/user/modifyUserSave.do" >
            <fm:hidden path="id" name="id"  value="${user.id}" />
			 <div>
                    <fm:errors path="userName"></fm:errors>
                    <fm:label path="userName" for="userName">用户名称：</fm:label>
                    <fm:input path="userName" id="userName" value="${user.userName }" />
					<font color="red"></font>
             </div>
			 <div>
                    <fm:label path="gender">用户性别：</fm:label>
                    <fm:select path="gender"  id="gender">
						<c:choose>
							<c:when test="${user.gender == 1 }">
                                <fm:option value="1" selected="selected">男</fm:option>
					    		<fm:option value="2">女</fm:option>
							</c:when>
							<c:otherwise>
								<fm:option value="1">男</fm:option>
					    		<fm:option value="2" selected="selected">女</fm:option>
							</c:otherwise>
						</c:choose>
					 </fm:select>
             </div>
			 <div>
                 <fm:errors path="birthday"></fm:errors>
                 <fm:label path="birthday" for="data">出生日期：</fm:label>
                    <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" type="date" var="formatBirthday" />
                    <fm:input path="birthday" type="text" Class="Wdate" id="birthday" value="${user.birthday }"
					readonly="readonly" onclick="WdatePicker();"/>
                    <font color="red"></font>
              </div>
			
		       <div>
                   <fm:errors path="phone"></fm:errors>
                   <fm:label path="phone" for="userphone">用户电话：</fm:label>
                    <fm:input path="phone" type="text" id="phone" value="${user.phone }" />
                    <font color="red"></font>
               </div>
                <div>
                    <fm:label path="address" for="userAddress">用户地址：</fm:label>
                    <fm:input path="address" type="text"  id="address" value="${user.address }"/>
                </div>
				<div>
                    <fm:label path="userRole" >用户角色：</fm:label>
                    <!-- 列出所有的角色分类 -->
					<fm:hidden path="userRole"  value="${user.userRole }" id="rid" />
					<fm:select path="userRole" id="userRole" />
        			<font color="red"></font>
                </div>
			 <div class="providerAddBtn">
                    <input type="button" name="save" id="save" value="保存" />
                    <input type="button" id="back" name="back" value="返回"/>
                </div>
            </fm:form>
        </div>
    </div>
</section>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/usermodify.js"></script>
