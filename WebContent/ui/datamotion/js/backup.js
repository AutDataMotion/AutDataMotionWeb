var testData=[
        {	"key_":"0321",
			"pathsrc":"c://path",
			"namesrc":"backupfilename1",
			"pathdest":"c://path//test",
			"timedo":"2016-11-02 11:10:20",
			"filesize":100,
			"station":"TS",
			"aircraft":"TG-1",
			"sensor":"ZW",
			"datatype":"dat",
			"datalevel":"L0",
			"camera":"SCI",
			"timerecive":"2015-12-12 08:20:59",
			"timecollectstart":"2015-12-12 08:20:59",
			"timecollectend":"2015-12-12 09:20:59",
			"suffix":"dat",
			"status":0,
			"timeadd":"2015-12-12 08:20:59",
			"labelids":""
        },
        {	"key_":"0322",
			"pathsrc":"c://path",
			"namesrc":"backupfilename2",
			"pathdest":"c://path//test",
			"timedo":"2016-11-03 11:10:20",
			"filesize":100,
			"station":"TS",
			"aircraft":"TG-2",
			"sensor":"ZW",
			"datatype":"dat",
			"datalevel":"L1",
			"camera":"SCI",
			"timerecive":"2015-12-13 08:20:59",
			"timecollectstart":"2015-12-13 08:20:59",
			"timecollectend":"2015-12-13 09:20:59",
			"suffix":"dat",
			"status":1,
			"timeadd":"2015-12-13 08:20:59",
			"labelids":""
        }
		
];

$('#doQuery_btn_backup')
.click(
		function() {
			alert("oooo");
			console.log("search click");
			
			var dataSet1 = [
			               [0,"c://path","name","c://path","2015-12-12 08:20:59",100,"TS","TG-2","ZW","dat","L1","SCI","2015-12-12 08:20:59","2015-12-12 08:20:59","2015-12-12 08:20:59",1,"2015-12-12 08:20:59",""],
			               [1,"c://path","name","c://path","2015-12-12 08:20:59",100,"TS","TG-2","ZW","dat","L1","SCI","2015-12-12 08:20:59","2015-12-12 08:20:59","2015-12-12 08:20:59",1,"2015-12-12 08:20:59",""]
			               
			               ];
			var table = $('#myTable').DataTable();
			table.clear().draw();
			table.rows.add(dataSet1).draw();
			/*
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
			getCheckedTreeNodesArr();
			datasrch.timebegcollect = $('#dateinfo_collectStartTime_backup')
					.val();
			datasrch.timeendcollect = $('#dateinfo_collectEndTime_backup')
					.val();
			datasrch.timebegreceive = $('#dateinfo_receiveStartTime_backup')
					.val();
			datasrch.timeendreceive = $('#dateinfo_receiveEndTime_backup')
					.val();

			datasrch.timebegdb = $('#dateinfo_backupStartTime').val();
			datasrch.timeenddb = $('#dateinfo_backupEndTime').val();
			datasrch.status = $('#backupstatus').val();
			datasrch.treecheckeds = dataCheckedTreeNodes;
			console.log(datasrch);
			// 发送查询请求
			$.ajax({
						type : "post",
						url : encodeURI(encodeURI(cxt
								+ "/jf/datamotion/t7_backupfile/doQuery")),
						data : {info:JSON.stringify(datasrch)},
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
							//遍历 json 更新 dataTable
							
						}
					});
			*/
		});
/*
$('#doQuery_btn_backup')
.click(
		function() {
			console.log("search click");
			// 获取查询参数
			var datasrch = {
				info : {},
				timereceive:'',
				timebegcollect : '',
				timeendcollect : '',
				timebegdb : '',
				timeenddb : '',
				status : 0
			};
			getCheckedTreeNodesArr();
			datasrch.timereceive = $('#dateinfo_receiveTime_backup')
			.val();
			datasrch.timebegcollect = $('#dateinfo_collectStartTime_backup')
					.val();
			datasrch.timeendcollect = $('#dateinfo_collectEndTime_backup')
					.val();
			datasrch.timebegdb = $('#dateinfo_backupStartTime').val();
			datasrch.timeenddb = $('#dateinfo_backupEndTime').val();
			datasrch.status = $('#backupstatus').val();
			datasrch.info = dataCheckedTreeNodes;
			console.log(datasrch);
			// 发送查询请求
			$.ajax({
						type : "post",
						url : encodeURI(encodeURI(cxt
								+ "/jf/datamotion/t7_backupfile/doQuery")),
						data : datasrch,
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
							//遍历 json 更新 dataTable
							
						}
					});
		});
*/
//全部本地下载
$('#doAllLocalDownload_btn')
.click(
		function() {
			// 获取查询参数
			var datadownload = {
				// key_ list
				keys : []
			};
			// 发送查询请求
			$.ajax({
						type : "post",
						url : encodeURI(encodeURI(cxt
								+ "/jf/datamotion/t7_backupfile/doAllLocalDownload")),
						data : datadownload,
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

//全部重新备份
$('#doAllNewBackup_btn')
.click(
		function() {
			// 获取查询参数
			var datacheckout = {
				// key_ list
				keys : []
			};
			// 发送查询请求
			$.ajax({
						type : "post",
						url : encodeURI(encodeURI(cxt
								+ "/jf/datamotion/t7_backupfile/doAllNewBackup")),
						data : datacheckout,
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
