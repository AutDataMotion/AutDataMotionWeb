$('#btnsrch')
		.click(
				function() {
					console.log("search click");
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 获取查询参数
					var datasrch = {
						treecheckeds : [],
						timebegcollect : '',
						timeendcollect : '',
						timebegreceive : '',
						timeendreceive : '',
						timebegdb : '',
						timeenddb : '',
						status : 0
					};
					datasrch.timebegcollect = $('#dateinfo_collectStartTime')
							.val();
					datasrch.timeendcollect = $('#dateinfo_collectEndTime')
							.val();
					datasrch.timebegreceive = $('#dateinfo_receiveTimeBeg')
							.val();
					datasrch.timeendreceive = $('#dateinfo_receiveTimeEnd')
							.val();

					datasrch.timebegdb = $('#dateinfo_DBstartTime').val();
					datasrch.timeenddb = $('#dateinfo_DBEndTime').val();
					datasrch.status = $('#status').val();
					datasrch.treecheckeds = dataRoot;
					console.log(datasrch);
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/search")),
								data : {
									v : JSON.stringify(datasrch)
								},
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
									// if (response.length > 1 && response !=
									// '1') {
									// for ( var ind in response) {
									// g_BackData = response[ind];
									// g_StepData[g_BackData.number] =
									// g_BackData;
									// parseJsonData(firstLoadHtml);
									// }
									// }
									// 遍历 json 更新 dataTable

									var returnData = response.data;
									
									var table = $('#myTable').DataTable();
									table.clear().draw();
									table.rows.add(returnData).draw();
								}
							});
				});
//本地下载
function localDownLoad(index,data){//选中的记录状态更新
	// 发送查询请求
//	alert("下载成功");
//	table.row(index).remove().draw( false );
//	table.row.add(data).draw(false);
}
// 重新下载
function reDownLoad(index,data){//选中的记录重新下载（修改status_＝1）
	// 发送查询请求
	$.ajax( {    
	        "type": "get",     
	        "contentType": "application/json",    
	        "url": encodeURI(encodeURI(cxt
					+ "/jf/datamotion/t6_dwnloadfile/reDownLoad")),     
	        "dataType": "json",    
	        "data": {"id": data[0], "pathsrc": data[1], "namesrc": data[2], "pathdest": data[3]}, // 以json格式传递  
	        "success": function(response) {    
             var returnData = response;
             var table = $('#myTable').DataTable();
            
             if(returnData=="1")//下载成功
				{
					data[15]="1";
					
					table.row(index).remove().draw( false );
					table.row.add(data).draw(false);
//					alert("下载成功");
				}
             if(returnData=="-1")//下载失败
				{
            	 alert("下载失败");
				}
	        }    
	    });   
}

//删除
function doDeleteSeleted(index,data){//选中的记录删除（修改status_＝3）
	// 发送查询请求
	$.ajax( {    
	        "type": "get",     
	        "contentType": "application/json",    
	        "url": encodeURI(encodeURI(cxt
					+ "/jf/datamotion/t6_dwnloadfile/doDeleteSeleted")),     
	        "dataType": "json",    
	        "data": {"id": data[0], "pathsrc": data[1], "namesrc": data[2], "pathdest": data[3]}, // 以json格式传递  
	        "success": function(response) {    
             var returnData = response;
             var table = $('#myTable').DataTable();
            
             if(returnData=="1")//删除成功
				{
					data[15]="3";
					table.row(index).remove().draw( false );
					table.row.add(data).draw(false);
					alert("删除成功");
				}
				if(returnData=="-1")//下载失败
				{
            	 alert("删除失败");
				}
	        }    
	    });   
}
			