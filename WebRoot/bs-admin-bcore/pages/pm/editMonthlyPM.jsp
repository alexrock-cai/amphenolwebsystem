<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form role="form" action="/pm/updateMonthlyPM" class="form-horizontal" id="newMonthlyPMModal-validation">
                        <div class="modal fade" id="editMonthlyPMModal" tabindex="-1" role="dialog" aria-labelledby="monthlyPMModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">新建月度PM计划</h4>
                                            <input type="hidden" name="id" value="${monthlyPM.id }"/>
                                        </div>
                                        <div class="modal-body">
                                                                         
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <input type="text" value="${monthlyPM.equipmentID }" name="equipmentID" readonly="" class="form-control" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">年度</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="please chosen" class="form-control chzn-select" name="year" tabindex="2">
                                                	
														<option value="2014" <c:if test="${monthlyPM.year==2014 }">selected</c:if> >2014年</option>
														<option value="2015" <c:if test="${monthlyPM.year==2015 }">selected</c:if> >2015年</option>
														<option value="2016" <c:if test="${monthlyPM.year==2016 }">selected</c:if> >2016年</option>
														<option value="2017" <c:if test="${monthlyPM.year==2017 }">selected</c:if> >2017年</option>
														<option value="2018" <c:if test="${monthlyPM.year==2018 }">selected</c:if> >2018年</option>
														<option value="2019" <c:if test="${monthlyPM.year==2019 }">selected</c:if> >2019年</option>
														<option value="2020" <c:if test="${monthlyPM.year==2020 }">selected</c:if> >2020年</option>
														<option value="2021" <c:if test="${monthlyPM.year==2021 }">selected</c:if> >2021年</option>
													                                              	
                                                </select>
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">一月计划</label>
                                            <div class="col-lg-6">
                                            	
                                                 	<input type="text" class="form-control pm-datepicker" value="${monthlyPM.janPMDay }" name="janPMDay" id="janPM" />
                                                 
                                            </div>
                                        </div>
                                       
                                       <div class="form-group">
                                            <label class="control-label col-lg-4">二月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.febPMDay }"  name="febPMDay" id="febPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">三月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.marPMDay }"  name="marPMDay" id="marPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">四月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.aprPMDay }"  name="aprPMDay" id="aprPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">五月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.mayPMDay }"  name="mayPMDay" id="mayPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">六月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.junPMDay }" name="junPMDay" id="junPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">七月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.julPMDay }" name="julPMDay" id="julPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">八月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.augPMDay }" name="augPMDay" id="augPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">九月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.sepPMDay }" name="sepPMDay" id="sepPM" />
                                            </div>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">十月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.octPMDay }" name="octPMDay" id="octPM" />
                                            </div>
                                        </div>                                                                                                                
                                    	
                                    	<div class="form-group">
                                            <label class="control-label col-lg-4">十一月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.novPMDay }" name="novPMDay" id="novPM" />
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">十二月计划</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control pm-datepicker" value="${monthlyPM.decPMDay }" name="decPMDay"  id="decPM" />
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
                     