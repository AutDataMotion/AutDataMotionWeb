$('#btnsrch')
		.click(
				function() {
					// 获取查询参数
					var datasrch ={
						info : {},
						timebegcollect:'',
						timeendcollect:'',
						timebegdb:'',
						timeenddb:'',
						status:0
					};
					datasrch.timebegcollect
					
					// 发送查询请求
					$.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt+"/jf/datamotion/t9_checkoutfiles/search")),
					    		data:datasrch,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
									if (response.length > 1 && response != '1') {
										for ( var ind in response) {
											g_BackData = response[ind];
											g_StepData[g_BackData.number] = g_BackData;
											parseJsonData(firstLoadHtml);
										}
									}
								}
							});
				});

//全部下载
$('#btndownload')
.click(
		function() {
			// 获取查询参数
			var datadownload ={
				//key_ list	
				keys : [];	
			};
			// 发送查询请求
			$.ajax({
				type : "post",
				url : encodeURI(encodeURI(cxt+"/jf/datamotion/t9_checkoutfiles/downloadAll")),
	    		data:datadownload,
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				crossDomain : false,
				// 是否使用异步发送
				async : false,
				cache : false,
						success : function(response) {
							if (response.length > 1 && response != '1') {
								for ( var ind in response) {
									g_BackData = response[ind];
									g_StepData[g_BackData.number] = g_BackData;
									parseJsonData(firstLoadHtml);
								}
							}
						}
					});
		});

//全部检出
$('#btncheckout')
.click(
		function() {
			// 获取查询参数
			var datacheckout ={
				//key_ list
				keys : [];	
			};
			// 发送查询请求
			$.ajax({
				type : "post",
				url : encodeURI(encodeURI(cxt+"/jf/datamotion/t9_checkoutfiles/checkoutAll")),
	    		data:datacheckout,
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				crossDomain : false,
				// 是否使用异步发送
				async : false,
				cache : false,
						success : function(response) {
							if (response.length > 1 && response != '1') {
								for ( var ind in response) {
									g_BackData = response[ind];
									g_StepData[g_BackData.number] = g_BackData;
									parseJsonData(firstLoadHtml);
								}
							}
						}
					});
		});
