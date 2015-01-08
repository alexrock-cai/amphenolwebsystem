<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form role="form" action="/pm/createPMRecord" class="form-horizontal" id="newPMRecordModal-validation" method="post" enctype="multipart/form-data">
                        <div class="modal fade " id="newQuarterlyPMRecordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="reset" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="H3">上传季度PM记录</h4>
                                            <input type="hidden" name="uploadBy" value="${user.getStr('username') }"/>
                                        </div>
                                        <div class="modal-body">
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">PM记录类型</label>
                                            <div class="col-lg-3">
                                                <input type="text" value="QuarterlyPM" name="PMType" readonly="" class="form-control" />
                                            </div>
                                         </div> 
                                         <div class="form-group">
                                            <label class="control-label col-lg-4">年度</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="please chosen" class="form-control chzn-select" name="year" id="quarterly-year" tabindex="2">
                                                	
                                                	<c:forEach items="${qyears }" var="qy">
														<option value="${qy }">${qy }</option>
													</c:forEach> 
													                                              	
                                                </select>
                                            </div>
                                        </div> 
                                         <div class="form-group">
                                            <label class="control-label col-lg-4">PM月份</label>
                                            
                      
                                            <div class="col-lg-3">
                                                <select data-placeholder="please chosen"  class="form-control " name="quarterlyPM" id="quarterlyPM" tabindex="2">
                                                	
														<option value="isQ1PM">Q1季度</option>
														<option value="isQ2PM">Q2季度</option>
														<option value="isQ3PM">Q3季度</option>
														<option value="isQ4PM">Q4季度</option>
														                                         	
                                                </select>
                                            </div>
   
                                        </div>                               
                                        <div class="form-group">
                                            <label class="control-label col-lg-4">设备编号</label>
                                            <div class="col-lg-6">
                                                <select data-placeholder="选择一个设备" class="form-control " name="equipmentID" id="qequipmentID" tabindex="2">
                                                	
                                                	<c:forEach items="${qeIDs }" var="qe">
														<option value="${qe }">${qe }</option>
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