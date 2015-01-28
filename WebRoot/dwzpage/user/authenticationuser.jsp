<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="pageContent">
	<form method="post" action="${CONTEXT_PATH}/user/saveAuthentica" class="pageForm required-validate"  onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div style=" float:left; display:block; margin:10px; overflow:auto; width:200px; height:200px; overflow:auto; border:solid 1px #CCC; line-height:21px; background:#FFF;">
			<p>工号：${u.username }</p>
			<p>姓名：${u.name }</p>
			<input name="id" value="${u.id }" type="hidden" />
			</div>
			<div style=" float:left; display:block; margin:10px; overflow:auto; width:200px; height:200px; overflow:auto; border:solid 1px #CCC; line-height:21px; background:#FFF;">
			<p>角色：</p>
			<ul class="tree treeFolder treeCheck expand" >
				<c:forEach items="${roles}" var="role">
					<c:set value='0' var='flag' />
						<c:forEach items="${roleids}" var="r">
							<c:if test='${role.id==r }'> 
								<c:set value='1' var='flag' />
								<li><a tname="role_ids" tvalue="${role.id }" checked="true" >${role.description}</a></li> 
							</c:if>
					
						</c:forEach>
						<c:if test='${flag==0 }'> <li><a tname="role_ids" tvalue="${role.id }"  >${role.description}</a></li> </c:if>
					
					
				</c:forEach>
			</ul>
			</div>
			
		
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

