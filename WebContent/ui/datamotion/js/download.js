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

// 重新下载在dwnloadfile.html中

//删除在dwnloadfile.html中
