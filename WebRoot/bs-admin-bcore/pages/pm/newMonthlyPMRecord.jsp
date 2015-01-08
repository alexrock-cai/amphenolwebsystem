<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form role="form" action="/pm/createPMRecord" class="form-horizontal" id="newPMRecordModal-validation" method="post" enctype="multipart/form-data">
                        <div class="modal fade " id="newMonthlyPMRecordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">上传月度PM记录</h4>
                                            <input type="hidden" name="uploadBy" value="${user.getStr('username') }"/>
                                        </div>
                                        <div class="modal-body">
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">PM记录类型</label>
                                            <div class="col-lg-3">
                                                <input type="text" value="MonthlyPM" name="PMType" readonly="" class="form-control" />
                                            </div>
                                         </div> 
                                         <div class="form-group">
                                            <label class="control-label col-lg-4">年度</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="please chosen" class="form-control chzn-select" name="year" id="monthly-year" tabindex="2">
                                                	
                                                	<c:forEach items="${myears }" var="my">
														<option value="${my }">${my }</option>
													</c:forEach> 
													                                              	
                                                </select>
                                            </div>
                                        </div> 
                                         <div class="form-group">
                                            <label class="control-label col-lg-4">PM月份</label>
                                            
                      
                                            <div class="col-lg-3">
                                                <select data-placeholder="please chosen"  class="form-control " name="monthlyPM" id="monthlyPM" tabindex="2">
                                                	
														<option value="isJanPM">一月份</option>
														<option value="isFebPM">二月份</option>
														<option value="isMarPM">三月份</option>
														<option value="isAprPM">四月份</option>
														<option value="isMayPM">五月份</option>
														<option value="isJunPM">六月份</option>
														<option value="isJulPM">七月份</option>
														<option value="isAugPM">八月份</option>
														<option value="isSepPM">九月份</option>
														<option value="isOctPM">十月份</option>
													    <option value="isNovPM">十一月份</option>
													    <option value="isDecPM">十二月份</option>                                          	
                                                </select>
                                            </div>
   
                                        </div>                               
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="选择一个设备" class="form-control " name="equipmentID" id="mequipmentID" tabindex="2">
                                    
                                                	<c:forEach items="${meIDs }" var="me">
														<option value="${me }">${me }</option>
													</c:forEach>                                               	
                                                </select>
                                            </div>
                                        </div>
                                         
                                       
                                        
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">PM完成日期</label>
                                            <div class="col-lg-6">
                                            	
                                                 	<input type="text" class="form-control pm-datepicker" value="2014-01-01" name="PMTime" id="PMTime" />
                                                 
                                            </div>
                                        </div>
                                       
                                       <div class="form-group">
                                            <label class="control-label col-lg-4">PM执行人</label>
                                            <div class="col-lg-6">
                                                 <input type="text" class="form-control "  name="PMOperator" id="PMOperator" />
                                            </div>
                                        </div>
                                        
                                       
                                        <div class="form-group">
                        					<label class="control-label col-lg-4">PM记录</label>
                        					<div class="col-lg-8">
                            					<div class="fileupload fileupload-new" data-provides="fileupload">



                                					<div class="input-group">
                                    

                                    					<span class="btn btn-file btn-info">
                                        					<span class="fileupload-new">Select file</span>
                                        					<span class="fileupload-exists">Change</span>
                                        					<input type="file" name="record" />
                                    					</span> 
                                    					<a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload">Remove</a>
                                    
                                    					<br /> <br />
                                    					<div class="col-lg-9">
                                        					<i class="icon-file fileupload-exists"></i>
                                        					<span class="fileupload-preview"></span>
                                    					</div>
                            						</div>
                        						</div>
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