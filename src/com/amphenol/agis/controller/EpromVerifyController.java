package com.amphenol.agis.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.amphenol.agis.pojo.PNInfo;
import com.amphenol.agis.util.ExcelReader;
import com.amphenol.agis.util.FileScanner;
import com.amphenol.agis.util.FileUtil;
import com.amphenol.agis.util.TextLogReader;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class EpromVerifyController extends Controller 
{
	public void index()
	{
		render("/pages/apps/eprom/epromList.html");
	}
	
	public void showSetPath()
	{
		render("/pages/apps/eprom/dialogEpromSetPath.html");
	}
	
	public void showEdit()
	{
		render("/pages/apps/eprom/dialogEpromEditPath.html");
	}
	
	
	public void delete()
	{
		System.out.println(getParaValuesToInt("rowId"));
		Integer[] ids=getParaValuesToInt("rowId");
		for(int i=0;i<ids.length;i++)
		{
			System.out.println(ids[i]);
			Db.deleteById("EpromFilePathSet", ids[i]);
		}
		renderJson("setPathStatus","haha");
	}
	
	public void updatePath()
	{
		Record re=Db.findById("EpromFilePathSet", getParaToInt("ID"));
		re.set("RootPath", getPara("RootPath").trim());
		re.set("LogFileType",getPara("LogFileType").trim());
		re.set("WorkStationName", getPara("WorkStationName").trim());
		Db.update("EpromFilePathSet", re);
		renderJson("setPathStatus","ok");
	}
	/**
	 * 设置相关文件的路径。
	 */
	public void setPath()
	{
		Record re=new Record();
		if(Db.findFirst("select * from EpromFilePathSet where RootPath = ?", getPara("RootPath"))!=null)
		{
			setAttr("setPathStatus","路径已经设置过了，不能重复设置");
			renderJson();
		}
		else
		{
			re.set("RootPath", getPara("RootPath").trim());
			re.set("LogFileType", getPara("LogFileType").trim());
			re.set("WorkStationName",getPara("WorkStationName").trim());
			if(Db.save("EpromFilePathSet", re))
			{
				setAttr("setPathStatus","ok");
				renderJson();
			}
			else
			{
				setAttr("setPathStatus","error");
				renderJson();
			}
		}

		
	}
	public void getPathList()
	{
		List<Record> list=Db.find("select * from EpromFilePathSet");
		setAttr("identifier", "id");
		setAttr("items",list);
		renderJson(new String[]{"items","identifier"});
		
	}
	/**
	 * 此程序功能实现对EPROOM Program 烧录程序的log文件进行解析抓取客户SN Number，并将此SN的烧录状态保存到数据库中。
	 */
	public void epromProgCheck()
	{
		Record re=new Record();
		FileScanner fileScanner=new FileScanner();
		TextLogReader logReader=new TextLogReader();
		re=Db.findFirst("select * from EpromFilePathSet where WorkStationName = 'Program'");
		if(re==null)
		{
			setAttr("epromProgCheckStatus","没有设置路径，请设置Log文件路径");
			Db.save("EpromCheckLog", new Record().set("event","warning").set("content", "Program log file path not set!"));
			renderJson();
		}
		else if(re.getStr("LogFileType").equals("A"))
		{
			//此处是类型为A的log文件处理过程
			String file=re.getStr("RootPath");
			List<File> files=fileScanner.getFileList(new File(file.trim()));
			File f;
			Record re2=new Record();
			//如果扫描到的文件数量与原先不符，说明有新的文件添加进去了，开始执行文件名处理
			if(files.size()!=re.getInt("totalFiles"))
			{
				for(int i=0;i<files.size();i++)
				{
					 f=files.get(i);
					//将获取到的文件名到数据库中查询，如果有记录说明此文件已经读取过可以跳过处理
					if(!Db.find("select * from EpromFileList where filename = ?",f.getName()).isEmpty())
					{
						continue;
					}
					//如果没有查询到此文件记录，则对该文件进行解析。
					else
					{
						try 
						{
							Db.save("EpromFileList",new Record().set("WorkStationName","Program").set("filename",f.getName()));
						    re2=Db.findFirst("select * from EpromFilePathSet where RootPath = ?",f.getParent());
						    Db.update("EpromFilePathSet",re2.set("totalFiles",re2.getInt("totalFiles")+1));
							List<PNInfo> pninfos=logReader.typeAProgramLogReader(f);
							PNInfo info1=new PNInfo();
							PNInfo info2=new PNInfo();
							for(int j=0;j<pninfos.size()-1;j++)
							{
								int k=j+1;
								
								info1=pninfos.get(j);
								info2=pninfos.get(k);
								if(info1.getCustomerSN().equals(info2.getCustomerSN()))
								{
									re2=Db.findFirst("select * from PnInfoFromDTS where customerSN = ?", info1.getCustomerSN());
									if(re2==null)
									{
										Db.save("EpromCheckLog", new Record().set("event","EProgramWarning").set("customerSN",info1.getCustomerSN()).set("content", info1.getCustomerSN()+":this Program customerSN is not found in DTS."));
										continue;
									}
									String left,right;
									if(info1.getLeftProgStatus()!=null)
									{
										left=info1.getLeftProgStatus();
									}
									else 
									{
										left=info2.getLeftProgStatus();
									}
									
									if(info1.getRightProgStatus()!=null)
									{
										right=info1.getRightProgStatus();
									}
									else 
									{
										right=info2.getRightProgStatus();
									}
									if(re2.getInt("programNum")!=0)
									{
										Db.save("EpromCheckLog",new Record().set("event", "Reburn").set("customerSN", re2.getStr("customerSN")).set("content", re2.getStr("customerSN")+":has burn "+re2.getInt("programNum")+" times before status is:"+re2.getStr("epromProg")));
									}
									
									if("Passed".equalsIgnoreCase(left) && "Passed".equalsIgnoreCase(right) )
									{
										int n=re2.getInt("programNum");
										re2.set("epromProg", "passed");
										re2.set("programNum", ++n);
									}
									else
									{
										int n=re2.getInt("programNum");
										re2.set("epromProg", "Failed");
										re2.set("programNum", ++n);
									}
									Db.update("PnInfoFromDTS",re2);
									j=k;
								}
								
							}
							
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		}
		else if(re.getStr("LogFileType").equals("B"))
		{
			//此处是类型为B的log文件处理过程
		}
		else if(re.getStr("LogFileType").equals("C"))
		{
			//此处是类型为C的log文件处理过程
		}
		renderJson();
	}
	
	public void epromVerifyCheck()
	{
		Record re=new Record();
		FileScanner fileScanner=new FileScanner();
		TextLogReader logReader=new TextLogReader();
		PNInfo pninfo;
		re=Db.findFirst("select * from EpromFilePathSet where WorkStationName = 'Verify'");
		if(re==null)
		{
			setAttr("epromVerifyCheckStatus","没有设置路径，请设置Log文件路径");
			renderJson();
		}
		else if(re.getStr("LogFileType").equals("A"))
		{
			List<File> files=fileScanner.getFileList(new File(re.getStr("RootPath")));
			File f;
			Record re2=new Record();
			if(re.getInt("totalFiles")!=files.size())
			{
				for(int i=0;i<files.size();i++)
				{
					f=files.get(i);
					//将获取到的文件名到数据库中查询，如果有记录说明此文件已经读取过可以跳过处理
					if(!Db.find("select * from EpromFileList where filename = ?",f.getName()).isEmpty())
					{
						continue;
					}
					else
					{
						try 
						{
							Db.save("EpromFileList",new Record().set("WorkStationName","Verify").set("filename",f.getName()));
						    re2=Db.findFirst("select * from EpromFilePathSet where RootPath = ?",f.getParent());
						    Db.update("EpromFilePathSet",re2.set("totalFiles",re2.getInt("totalFiles")+1));
							pninfo=logReader.typeAVerifyLogReader(f);
							re2=Db.findFirst("select * from PnInfoFromDTS where customerSN = ?", pninfo.getCustomerSN());
							if(re2==null)
							{
								Db.save("EpromCheckLog", new Record().set("event","EVerifyWarning").set("customerSN",pninfo.getCustomerSN()).set("content", pninfo.getCustomerSN()+":this Verify customerSN is not found in DTS."));
								continue;
							}
							else
							{
								if(re2.getInt("verifyNum")!=0)
								{
									Db.save("EpromCheckLog",new Record().set("event", "Reverify").set("customerSN", re2.getStr("customerSN")).set("content", re2.getStr("customerSN")+":has verfiy "+re2.getInt("verifyNum")+" times before status is:"+re2.getStr("epromVer")));
								}
								int n=re2.getInt("verifyNum");
								re2.set("epromVer", pninfo.getVerStatus());
								re2.set("verifyNum", ++n);
								Db.update("PnInfoFromDTS", re2);
							}
							
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		}
		else if(re.getStr("LogFileType").equals("B"))
		{
			
		}
		else if(re.getStr("LogFileType").equals("C"))
		{
			
		}
		
		renderJson();
	}

	/**
	 * 此方法将从DTS中导出的PN和SN等数据保存到数据库中的PnInfoFormDTS表中
	 */
	public void saveExcel()
	{
		Record re=new Record();
		ExcelReader excelReader=new ExcelReader();
		re = Db.findFirst("select * from EpromFilePathSet where WorkStationName = 'DTS'");
		String file=re.getStr("RootPath");
		//调用文件工具类，扫描该路径下的所有的文件。
		FileUtil.getFiles(new File(file));
		//查询数据库中，已经扫描并写入到数据库中的文件数量
		int number=Db.find("select * from EpromFileList where WorkStationName = 'DTS'").size();
		//判断扫描的文件数和数据库中文件数量是否一致，如果一致说明没有新增加的文件，
		if(number!=FileUtil.getN())
		{
			Map<String,String> files=FileUtil.getMap();
			Iterator<String> it=files.keySet().iterator();
			while(it.hasNext())
			{
				String filename=it.next();
				
				try
				{    
					
					
					if(Db.find("select * from EpromFileList where filename = ?",filename).size()!=0)
					{
						Db.save("EpromCheckLog", new Record().set("event","warning").set("content", filename+":this file has been processed！"));
						continue;
					}
					
					excelReader.saveExcel(new FileInputStream(new File(filename)));
					//将处理过的文件保存到数据库中。
					Db.save("EpromFileList",new Record().set("WorkStationName","DTS").set("filename",filename));
					//将扫描到的文件数量存入数据库中，方便下次比对是否有文件增加。
					Record re2=Db.findFirst("select * from EpromFilePathSet where RootPath = ?",file);
					Db.update("EpromFilePathSet",re2.set("totalFiles",FileUtil.getN()));
					
				}
				catch (FileNotFoundException e) {
					// TODO: handle exception
					setAttr("message","文件未找到！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", filename+":this file not found！"));
				}
				catch(InvalidFormatException e)
				{
					setAttr("message","文件格式不支持");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", filename+":Invalid Format"));
				}
				catch(Exception e)
				{
					setAttr("message","文件保存出错啦！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", filename+":"+e.getMessage()));
				}

				
				setAttr("message","文件导入成功！");
				Db.save("EpromCheckLog", new Record().set("event","success").set("content",filename+":file is save to database"));
			}

		}
		


		renderJson();
	}
	
	public void saveExcel2()
	{
		Record re=new Record();
		//创建文件扫描器
		FileScanner fileScanner=new FileScanner();
		ExcelReader excelReader=new ExcelReader();
		re = Db.findFirst("select * from EpromFilePathSet where WorkStationName = 'DTS'");
		String path=re.getStr("RootPath");
		List<File> files=fileScanner.getFileList(new File(path));
		System.out.println(files.size());
		//查询数据库中，已经扫描并写入到数据库中的文件数量
		int number=Db.find("select * from EpromFileList where WorkStationName = 'DTS'").size();
		System.out.println(number);
		//判断扫描的文件数和数据库中文件数量是否一致，如果一致说明没有新增加的文件，
		if(number!=files.size())
		{
			for(int i=0;i<files.size();i++)
			{
				File f=files.get(i);
				try
				{    
					System.out.println("work"+f.getAbsolutePath());
					if(Db.find("select * from EpromFileList where filename = ?",f.getName()).size()!=0)
					{
						setAttr("message","continue");
						Db.save("EpromCheckLog", new Record().set("event","warning").set("content", f.getName()+":this file has been processed！"));
						continue;
					}
					
					excelReader.saveExcel(new FileInputStream(f));
					//将处理过的文件保存到数据库中。
					Db.save("EpromFileList",new Record().set("WorkStationName","DTS").set("filename",f.getName()));
					//将扫描到的文件数量存入数据库中，方便下次比对是否有文件增加。
					Record re2=Db.findFirst("select * from EpromFilePathSet where RootPath = ?",f.getParent());
					Db.update("EpromFilePathSet",re2.set("totalFiles",re2.getInt("totalFiles")+1));
					Db.save("EpromCheckLog", new Record().set("event","success").set("content", f.getName()+":file is save to database"));
					
				}
				catch (FileNotFoundException e) {
					// TODO: handle exception
					setAttr("message","文件未找到！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":this file not found！"));
				}
				catch(InvalidFormatException e)
				{
					setAttr("message","文件格式不支持");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":Invalid Format"));
					
				}
				catch(Exception e)
				{
					setAttr("message","文件保存出错啦！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":"+e.getMessage()));
				}

				
				setAttr("message","文件导入成功！");
				
			}
		}
		else
		{
			Db.save("EpromCheckLog", new Record().set("event","warning").set("content","No new file found!"));
			setAttr("message","没有新的文件需要导入！");
		}
		renderJson();
	}
	
	public void saveShipmentData()
	{
		Record re=new Record();
		//创建文件扫描器
		FileScanner fileScanner=new FileScanner();
		ExcelReader excelReader=new ExcelReader();
		re = Db.findFirst("select * from EpromFilePathSet where WorkStationName = 'Shipment'");
		String path=re.getStr("RootPath");
		List<File> files=fileScanner.getFileList(new File(path));
		//查询数据库中，已经扫描并写入到数据库中的文件数量
		int number=Db.find("select * from EpromFileList where WorkStationName = 'Shipment'").size();
		//判断扫描的文件数和数据库中文件数量是否一致，如果一致说明没有新增加的文件，
		if(number!=files.size())
		{
			for(int i=0;i<files.size();i++)
			{
				File f=files.get(i);
				try
				{    
					
					if(Db.find("select * from EpromFileList where filename = ?",f.getName()).size()!=0)
					{
						setAttr("message","continue");
						Db.save("EpromCheckLog", new Record().set("event","warning").set("content", f.getName()+":this file has been processed！"));
						continue;
					}
					
					excelReader.saveShipmentExcel(new FileInputStream(f));
					//将处理过的文件保存到数据库中。
					Db.save("EpromFileList",new Record().set("WorkStationName","Shipment").set("filename",f.getName()));
					//将扫描到的文件数量存入数据库中，方便下次比对是否有文件增加。
					Record re2=Db.findFirst("select * from EpromFilePathSet where RootPath = ?",f.getParent());
					Db.update("EpromFilePathSet",re2.set("totalFiles",re2.getInt("totalFiles")+1));
					Db.save("EpromCheckLog", new Record().set("event","success").set("content", f.getName()+":file is save to database"));
					
				}
				catch (FileNotFoundException e) {
					// TODO: handle exception
					setAttr("message","文件未找到！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":this file not found！"));
				}
				catch(InvalidFormatException e)
				{
					setAttr("message","文件格式不支持");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":Invalid Format"));
					
				}
				catch(Exception e)
				{
					setAttr("message","文件保存出错啦！");
					Db.save("EpromCheckLog", new Record().set("event","error").set("content", f.getName()+":"+e.getMessage()));
				}

				
				setAttr("message","文件导入成功！");
				
			}
		}
		else
		{
			Db.save("EpromCheckLog", new Record().set("event","warning").set("content","No new file found!"));
			setAttr("message","没有新的文件需要导入！");
		}
		renderJson();
	}
	
	public void startCheck()
	{
		List<Record> list=Db.find("select * from PnInfoFromShipment where status=?","no");
		Record shipRecord,dtsRecord;
		for(int index=0;index<list.size();index++)
		{
			shipRecord=list.get(index);
			dtsRecord=Db.findFirst("select * from PnInfoFromDTS where customerSN = ?",shipRecord.getStr("cust_sn"));
			if(dtsRecord==null )
			{
				Db.save("EpromCheckLog",new Record().set("event","shipwarning").set("content", shipRecord.get("cust_sn")+":not found in DTS database maybe not do EEPROM ."));
			}
			else if(!"Passed".equalsIgnoreCase(dtsRecord.getStr("epromProg")))
			{
				Db.save("EpromCheckLog",new Record().set("event","shipwarning").set("content", shipRecord.get("cust_sn")+":Program not passed please confirm ."));
			}
			else if(!"PASS".equalsIgnoreCase(dtsRecord.getStr("epromVer")))
			{
				Db.save("EpromCheckLog",new Record().set("event","shipwarning").set("content", shipRecord.get("cust_sn")+":Verify not pass please confirm ."));
			}
			else
			{
				Db.update("PnInfoFromShipment",shipRecord.set("status", "ok"));
			}
		}
		
		renderJson();
	}
}
