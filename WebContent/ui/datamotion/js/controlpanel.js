$('#init')
		.click(
				function() {
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/initControlPanel")),
//								data : {
//									v : JSON.stringify(datasrch)
//								},
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
									var undoNum_download = response.undoNum_download;
									var dosucNum_download = response.dosucNum_download;
									var dofailNum_download = response.dofailNum_download;
									var timeStart_download = response.timeStart_download;
									var status_download = response.status_download;
									$("#download_undoNum").html(undoNum_download);
									$("#download_dosucNum").html(dosucNum_download);
									$("#download_dofailNum").html(dofailNum_download);
									$("#download_timeStart").html(timeStart_download);
									if(status_download == 1){
										$("#download_status").html("正在运行");
									}
									else if(status_download == 0){
										$("#download_status").html("停止");
									}
									
									var undoNum_backup = response.undoNum_backup;
									var dosucNum_backup = response.dosucNum_backup;
									var dofailNum_backup = response.dofailNum_backup;
									var timeStart_backup = response.timeStart_backup;
									var status_backup = response.status_backup;
									$("#backup_undoNum").html(undoNum_backup);
									$("#backup_dosucNum").html(dosucNum_backup);
									$("#backup_dofailNum").html(dofailNum_backup);
									$("#backup_timeStart").html(timeStart_backup);
									if(status_backup == 1){
										$("#backup_status").html("正在运行");
									}
									else if(status_backup == 0){
										$("#backup_status").html("停止");
									}
									
									var undoNum_archive = response.undoNum_archive;
									var dosucNum_archive = response.dosucNum_archive;
									var dofailNum_archive = response.dofailNum_archive;
									var timeStart_archive = response.timeStart_archive;
									var status_archive = response.status_archive;
									$("#archiveFile_undoNum").html(undoNum_archive);
									$("#archiveFile_dosucNum").html(dosucNum_archive);
									$("#archiveFile_dofailNum").html(dofailNum_archive);
									$("#archiveFile_timeStart").html(timeStart_archive);
									if(status_archive == 1){
										$("#archiveFile_status").html("正在运行");
									}
									else if(status_archive == 0){
										$("#archiveFile_status").html("停止");
									}
									
									var undoNum_checkout = response.undoNum_checkout;
									var dosucNum_checkout = response.dosucNum_checkout;
									var dofailNum_checkout = response.dofailNum_checkout;
									var timeStart_checkout = response.timeStart_checkout;
									var status_checkout = response.status_checkout;
									$("#checkOutFile_undoNum").html(undoNum_checkout);
									$("#checkOutFile_dosucNum").html(dosucNum_checkout);
									$("#checkOutFile_dofailNum").html(dofailNum_checkout);
									$("#checkOutFile_timeStart").html(timeStart_checkout);
									if(status_checkout == 1){
										$("#checkOutFile_status").html("正在运行");
									}
									else if(status_checkout == 0){
										$("#checkOutFile_status").html("停止");
									}
								}
							});
				});

//---------------------start 下载----------------------//
//开始下载
$('#start_Download')
		.click(
				function() {
					console.log("start_Download");
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/start_Download")),
//								data : {
//									v : JSON.stringify(datasrch)
//								},
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

// 重启下载
$('#restart_Download')
		.click(
				function() {
					// 获取查询参数
					var datadownload = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/restart_Download")),
								data : datadownload,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

// 停止下载
$('#stop_Download')
		.click(
				function() {
					// 获取查询参数
					var datacheckout = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/stop_Download")),
								data : datacheckout,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

//---------------------end 下载----------------------//
//---------------------start 备份----------------------//
//开始备份
$('#start_BackUp')
		.click(
				function() {
					console.log("start_BackUp");
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/start_BackUp")),
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
								}
							});
				});

// 重启备份
$('#restart_BackUp')
		.click(
				function() {
					// 获取查询参数
					var datadownload = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/restart_BackUp")),
								data : datadownload,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

// 停止备份
$('#stop_BackUp')
		.click(
				function() {
					// 获取查询参数
					var datacheckout = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/stop_BackUp")),
								data : datacheckout,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});
//---------------------end 备份----------------------//

//---------------------start 归档----------------------//
//开始归档
$('#start_ArchiveFile')
		.click(
				function() {
					console.log("start_ArchiveFile");
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/start_ArchiveFile")),
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
								}
							});
				});

//重启归档
$('#restart_ArchiveFile')
		.click(
				function() {
					// 获取查询参数
					var datadownload = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/restart_ArchiveFile")),
								data : datadownload,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

//停止归档
$('#stop_ArchiveFile')
		.click(
				function() {
					// 获取查询参数
					var datacheckout = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/stop_ArchiveFile")),
								data : datacheckout,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});
//---------------------end 归档----------------------//

//---------------------start 发布----------------------//
//开始发布
$('#start_CheckOutFile')
		.click(
				function() {
					console.log("start_CheckOutFile");
					if(!getCheckedTreeNodes()){
						
						return false;
					}
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/start_CheckOutFile")),
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
								}
							});
				});

//重启发布
$('#restart_CheckOutFile')
		.click(
				function() {
					// 获取查询参数
					var datadownload = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/restart_CheckOutFile")),
								data : datadownload,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});

//停止发布
$('#stop_CheckOutFile')
		.click(
				function() {
					// 获取查询参数
					var datacheckout = {
						// key_ list
						keys : []
					};
					// 发送查询请求
					$
							.ajax({
								type : "post",
								url : encodeURI(encodeURI(cxt
										+ "/jf/datamotion/t6_dwnloadfile/stop_CheckOutFile")),
								data : datacheckout,
								dataType : 'json',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8",
								crossDomain : false,
								// 是否使用异步发送
								async : false,
								cache : false,
								success : function(response) {
								}
							});
				});
//---------------------end 发布----------------------//