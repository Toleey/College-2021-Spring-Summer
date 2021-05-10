<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%> <%--spring表单的标记标签库 prefix前缀，可以自己起名字--%>

  <div class="right">
      <div class="location">
          <strong>你现在所在的位置是:</strong>
          <span>供应商管理页面 >> 供应商修改页</span>
      </div>
      <div class="providerAdd">
          <fm:form id="providerForm" modelAttribute="provider" name="providerForm" method="post" action="${pageContext.request.contextPath }/sys/provider/modifyProviderSave.do">
              <fm:hidden path="id" name="id"  value="${provider.id}" />
              <!--div的class 为error是验证错误，ok是验证成功-->
              <div class="">
                  <fm:label path="proCode">供应商编码：</fm:label>
                  <fm:input path="proCode" type="text" name="proCode" id="proCode" value="${provider.proCode }" readonly="readonly" />
              </div>
              <div>
                  <fm:label path="proName">供应商名称：</fm:label>
                 <fm:input path="proName" type="text" name="proName" id="proName" value="${provider.proName }" />
			<font color="red"></font>
              </div>
              
              <div>
                  <fm:label path="proContact">联系人：</fm:label>
                  <fm:input path="proContact" type="text" name="proContact" id="proContact" value="${provider.proContact }" />
			<font color="red"></font>
              </div>
              
              <div>
                  <fm:label path="proPhone">联系电话：</fm:label>
                  <fm:input path="proPhone" type="text" name="proPhone" id="proPhone" value="${provider.proPhone }" />
			<font color="red"></font>
              </div>
              
              <div>
                  <fm:label path="proAddress">联系地址：</fm:label>
                  <fm:input path="proAddress" type="text" name="proAddress" id="proAddress" value="${provider.proAddress }" />
              </div>
              
              <div>
                  <fm:label path="proFax">传真：</fm:label>
                  <fm:input path="proFax" type="text" name="proFax" id="proFax" value="${provider.proFax }" />
              </div>
              
              <div>
                  <fm:label path="proDesc">描述：</fm:label>
                  <fm:input path="proDesc" type="text" name="proDesc" id="proDesc" value="${provider.proDesc }"/>
              </div>
              <div class="providerAddBtn">
                  <input type="button" name="save" id="save" value="保存">
				  <input type="button" id="back" name="back" value="返回" >
              </div>
          </fm:form>
      </div>
  </div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/providermodify.js"></script>