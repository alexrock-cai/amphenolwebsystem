<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form role="form" action="/pm/updateYearlyPM" class="form-horizontal" id="editYearlyPMModal-validation" method="post">
                        <div class="modal fade " id="editYearlyPMModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">新建季度PM计划</h4>
                                            <input type="hidden" name="id" value="${yearlyPM.id }"/>
                                        </div>
                                        <div class="modal-body">
                                                                         
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <input type="text" value="${yearlyPM.equipmentID }" name="equipmentID" readonly="" class="form-control" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">年度</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="please chosen" class="form-control " name="year" tabindex="2">
                                                	
														<option value="2014" <c:if test="${yearlyPM.year==2014 }">selected</c:if> >2014年</option>
														<option value="2015" <c:if test="${yearlyPM.year==2015 }">selected</c:if> >2015年</option>
														<option value="2016" <c:if test="${yearlyPM.year==2016 }">selected</c:if> >2016年</option>
														<option value="2017" <c:if test="${yearlyPM.year==2017 }">selected</c:if> >2017年</option>
														<option value="2018" <c:if test="${yearlyPM.year==2018 }">selected</c:if> >2018年</option>
														<option value="2019" <c:if test="${yearlyPM.year==2019 }">selected</c:if> >2019年</option>
														<option value="2020" <c:if test="${yearlyPM.year==2020 }">selected</c:if> >2020年</option>
														<option value="2021" <c:if test="${yearlyPM.year==2021 }">selected</c:if> >2021年</option>
													                                              	
                                                </select>
                                            </div>
                                        </div>
                                        

                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">年度季度计划日期</label>
                                            <div class="col-lg-6">
                                            	
                                                 	<input type="text" class="form-control pm-datepicker" value="01-01" name="yearPMDay" id="yearPM" />
                                                 
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
                     