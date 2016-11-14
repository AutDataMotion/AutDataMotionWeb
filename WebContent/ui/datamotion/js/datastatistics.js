$('#doQuery_btn_datastatistics')
.click(
		function() {
			alert("oooo");
			//console.log("search click");
			if(!getCheckedTreeNodes()){
				
				return false;
			}
			// 获取查询参数
			var datasrch = {
				treecheckeds : {},
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
			// console.log(datasrch);
			// 发送查询请求
			$
					.ajax({
						type : "post",
						url : encodeURI(encodeURI(cxt
								+ "/jf/datamotion/statistics/doQuery")),
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
							console.log(response);
							
//							var returnData = response.data;
//							
//							var table = $('#myTable').DataTable();
//							table.clear().draw();
//							table.rows.add(returnData).draw();
						

						}
					});
		});