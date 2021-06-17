<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/head.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>供应商管理页面 >> 供应商添加页面</span>
        </div>
        <div class="providerAdd">
           <fm:form id="providerForm" name="providerForm" method="post" action="${pageContext.request.contextPath }/provider/addProviderSave.do" modelAttribute="provider">
                <div class="">
                    <fm:label path="proCode" for="proCode">供应商编码：</fm:label>
                    <fm:input path="proCode" id="proCode" />
					<!-- 放置提示信息 -->
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="proName" for="proName">供应商名称：</fm:label>
                    <fm:input path="proName" id="proName" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="proContact" for="proContact">联系人：</fm:label>
                    <fm:input path="proContact" id="proContact" />
					<font color="red"></font>

                </div>
                <div>
                    <fm:label path="proPhone" for="proPhone">联系电话：</fm:label>
                    <fm:input path="proPhone" if="proPhone" />
					<font color="red"></font>
                </div>
                <div>
                    <fm:label path="proAddress" for="proAddress">联系地址：</fm:label>
                    <fm:input path="proAddress" id="proAddress" />
                </div>
                <div>
                    <fm:label path="proFax" for="proFax">传真：</fm:label>
                    <fm:input path="proFax" id="proFax" />
                </div>
                <div>
                    <fm:label path="proDesc" for="proDesc">描述：</fm:label>
                    <fm:input path="proDesc" id="proDesc" />
                </div>
                <div class="providerAddBtn">
                    <fm:button name="add" id="add">保存</fm:button>
                    <fm:button name="back" id="back">返回</fm:button>
                </div>
           </fm:form>
     </div>
</div>
</section>
<%@include file="../common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/provideradd.js"></script>
