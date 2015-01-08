<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form role="form" action="/pm/updatePmInfo" class="form-horizontal" id="editPmInfoModal-validation">
                        <div class="modal fade" id="editPmInfoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">修改设备信息</h4>
                                            <input type="hidden" name="id" value="${pmInfo.id }"/>
                                        </div>
                                        <div class="modal-body">
                                                                         
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required] form-control" name="equipmentID" id="equipmentID-edit" value="${pmInfo.equipmentID }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备类别</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required] form-control" name="model" id="model-edit" value="${pmInfo.model }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备名称</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required] form-control" name="equipmentName" id="equipmentName-edit" value="${pmInfo.equipmentName }">
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">责任人</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required] form-control" name="owner" id="owner-edit" value="${pmInfo.owner }" >
                                            </div>
                                        </div>
                                       
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">责任人邮箱</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required,custom[email]]  form-control" name="ownerEmail" id="ownerEmail-edit" value="${pmInfo.ownerEmail }">
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">责任人上司</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required] form-control" name="supervisor" id="supervisor-edit" value="${pmInfo.supervisor }" >
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">责任人上司邮箱</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="validate[required,custom[email]]  form-control" name="supervisorEmail" id="supervisorEmail-edit" value="${pmInfo.supervisorEmail }" >
                                            </div>
                                        </div>                                                                
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">PM 计划 :</label>
                                            <div class="col-lg-6">
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox" name="isMonthlyPM" <c:if test="${pmInfo.isMonthlyPM }">checked</c:if> />月度PM
                                            	</label>
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox" name="isQuarterlyPM" <c:if test="${pmInfo.isQuarterlyPM }">checked</c:if> />季度PM
                                            	</label>
                                            	<label class="checkbox-inline">
                                                	<input type="checkbox" name="isYearlyPM" <c:if test="${pmInfo.isYearlyPM }">checked</c:if> />年度PM
                                            	</label>
                                            </div>
                                        </div>
                                                                                                                                                        
                                    
                                        </div>
                                        <div class="modal-footer">
                                            <button type="reset" class="btn btn-default" >取消</button>
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </form>