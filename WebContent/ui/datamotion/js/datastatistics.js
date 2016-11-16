function initPage()
{
	var status = $('#status').val();
	//alert(status);
	//datasrch.treecheckeds = dataRoot;
	// console.log(datasrch);
	// 发送查询请求
	$
			.ajax({
				type : "post",
				url : encodeURI(encodeURI(cxt
						+ "/jf/datamotion/statistics/doInit")),
				data : {
					v : status
				},
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				crossDomain : false,
				// 是否使用异步发送
				async : false,
				cache : false,
				success : function(response) {
					console.log(response);
					//alert(K_num);
					Modify_K_G_Z(response);
				}
			});
}
function Modify_K_G_Z(response)
{
	var K_num=response["MWI"]["个数"];
	var K_vol=response["MWI"]["容量"];
	document.getElementById('K_num').innerHTML=K_num;
	document.getElementById('K_vol').innerHTML=K_vol;
	var G_num=response["IALT"]["个数"];
	var G_vol=response["IALT"]["容量"];
	document.getElementById('G_num').innerHTML=G_num;
	document.getElementById('G_vol').innerHTML=G_vol;
	var Z_num=response["ZW"]["个数"];
	var Z_vol=response["ZW"]["容量"];
	document.getElementById('Z_num').innerHTML=Z_num;
	document.getElementById('Z_vol').innerHTML=Z_vol;
}
$('#doQuery_btn_datastatistics')
.click(
		function() {
			//alert("oooo");
			//initPage();//更新 宽波段，高度计，紫外
			//console.log("search click");
//			if(!getCheckedTreeNodes()){
//				
//				return false;
//			}
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
							Modify_K_G_Z(response);
							
							//var legends=response["datalevel"];
							
							option1.xAxis[0].data.splice(0,option1.xAxis[0].data.length);//清空数组 ;
							option2.yAxis[0].data.splice(0,option2.yAxis[0].data.length);//清空数组 ;
							for(var n=0;n<8;n++)
							{
								option1.series[n].data.splice(0,option1.series[n].data.length);//清空数组 ;
								option2.series[n].data.splice(0,option2.series[n].data.length);//清空数组 ;
							}
							
							for(var i=0;i<response["载荷"].length;i++)
							{
								var Axisname=response["载荷"][i]["CH"];
								//alert(Axisname);
								option1.xAxis[0].data.push(Axisname);
								option2.yAxis[0].data.push(Axisname);
								
								
								for(var m=0;m<response["载荷"][i]["datanum"].length;m++)
								{
									//option1.series[m].data.splice(0,option1.series[m].data.length);//清空数组 ;
									//option2.series[m].data.splice(0,option2.series[m].data.length);//清空数组 ;
									
									var datanum=response["载荷"][i]["datanum"][m];
									var datavolume=response["载荷"][i]["datavolume"][m];
									//alert(datanum+","+datavolume);
									option1.series[m].data.push(datanum);
									option2.series[m].data.push(datavolume);
								}
							}
							myChart1.clear();
							myChart1.setOption(option1);
							myChart2.clear();
							myChart2.setOption(option2);
							/*
							{
					            name:'L0',
					            type:'bar',
					            stack: '总量',
					            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
					            //data:[320, 302, 301]
					            data:[]
					        },
					        */
						

						}
					});
		});