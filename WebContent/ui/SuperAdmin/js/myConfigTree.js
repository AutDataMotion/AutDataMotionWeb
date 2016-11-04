var key_;
/*修改节点：点击事件*/
function beforeEditName(treeId, treeNode) {    
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    setTimeout(function() {
        if (confirm("确认修改节点--" + treeNode.name + "--的名称吗？")) {
            setTimeout(function() {
                zTree.editName(treeNode);
            }, 0);
        }
    }, 0);
    return false;
}
function beforeRename(treeId, treeNode, newName, isCancel) {
    if (newName.length == 0) {
        setTimeout(function() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.cancelEditName();
            alert("节点名称不能为空！");
        }, 0);
        return false;
    }
    $.post(
        'modifyTreeNode',
       	{
        	key_:treeNode.id,
        	newName:newName
       	}
    );
    return true;
}
        
/*删除节点：点击事件*/
function beforeRemove(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo");
    zTree.selectNode(treeNode);
    if(confirm("确认删除节点 --" + treeNode.name + "--吗？")){
    	var childObj=treeNode.getParentNode().children;
    	var childkeys="";
    	for(var i=0;i<childObj.length;i++){
    		if(childObj[i].id==treeNode.id)continue;
    		if(i==childObj.length-1)
    			childkeys+=childObj[i].id;
    		else childkeys+=childObj[i].id+"-";
    	}
    	if(childkeys[childkeys.length-1]=='-'){
    		childkeys=childkeys.substring(0,childkeys.length-1);
    	}
    	$.post(
        	'delTreeNode',
            {
             	key_:treeNode.id,
             	childkeys:childkeys
            }
        );
        return true;
    };
    return false;
}

//显示删除按钮
function showRemoveBtn(treeId, treeNode) {
	if(treeNode.isParent==true){
		var childObj=treeNode.children;
		//如果该节点的孩子节点存在是父节点，则不显示删除按钮
		for(var i=0;i<childObj.length;i++){
			if(childObj[i].isParent==true)
				return false;
		}
	} 
	//根节点不显示删除按钮
	if(treeNode.pid==null)return false;
	return true;
}

//节点的点击事件（只有点击才显示新增按钮）
function onClick(event, treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_"+treeNode.tId);
    
    var treeNodeTemp=treeNode;
	while(parseInt(treeNodeTemp.level)>=1){
		$("#info0"+treeNodeTemp.level).val(treeNodeTemp.name);
		treeNodeTemp=treeNodeTemp.getParentNode();
	}
	for(var i=parseInt(treeNode.level)+1;i<=5;i++){
		$("#info0"+i).val("全部");
	}
    
    /*增加节点：点击事件*/
    if (btn) btn.bind("click", function(){   
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var parentNode=treeNode.id;
        if(parentNode.indexOf==0)parentNode=['0'];
        else parentNode=parentNode.split("_");
        var currentId;
        /*
         	树的命名规则：类似霍夫曼
         	如果当前节点是父节点，则获取孩子节点编号最大的孩子节点，在该基础上编号+1
         	如果当前节点不是父节点，则在该基础上加上'_1'
        */
        if(treeNode.isParent==false){
            currentId=treeNode.id+"_1";
        }else {
        	//获取编号最大的一个孩子节点
        	var idArray=[],len,childId;
        	for(var i=0;i<treeNode.children.length;i++){
        		childId=treeNode.children[i].id;
        		len=childId.length;
        		idArray.push(childId[len-1]);
        	}	
        	var childNode=_.max(idArray);
        	var restChild=childId.substring(0,childId.length-1);
        	currentId=restChild+(parseInt(childNode)+1);
        }
        zTree.addNodes(treeNode, {id:currentId, pid:treeNode.id, name:"new node" + (currentId)});
        $.post(
        	'addTreeNode',
        	{//传入参数
        		key_:currentId,
        		parentkeys:treeNode.id,
        		level:treeNode.level+1,
        		namechi:"new node"+currentId
        	}
        );
        return false;
    });
    
    
    //$("#aircraft").val('');
	//$("#sensor").val('');
//	$("#datatype").val('');
//	$("#camera").val('');
//	$("#datalevel").val('');
//	$("#isdwnload").val('');
//	$("#pathftp").val('');
//	$("#pathdwnload").val('');
//	$("#isbackup").val('');
//    $("#pathbackup").val('');
//    $("#isarchive").val('');
//	$("#patharchive").val('');
//	$("#ischeckout").val('');
//	$("#pathcheckout").val('');
//	$("#namemdlsrc").val('');
//	$("#namemdldes").val('');
//	$("#ishavaaux").val('');
//	$("#auxfiletypes").val('');
	if(treeNode!=null&&treeNode.isParent==false){
		key_=treeNode.id;
    	$.ajax({
    		url:'../t12_initmodule/getModelInfo',
    		asyn:false,
    		method:'post',  
    		data:{key_:treeNode.id},
    		success:function(data){
    			var aircraft,sensor,datatype,camera,datalevel,isdwnload,
    			pathftp,pathdwnload,isbackup,pathbackup,isarchive,patharchive,
    			ischeckout,pathcheckout,namemdlsrc,namemdldes,ishavaaux,auxfiletypes;
    			if(data.length==0){
    				aircraft='天宫一号';
    				sensor='高度计';
    				datatype='IMG';
    				camera='VNI';
    				datalevel='0B';
    				isdwnload='是';
        			pathftp='';
        			pathdwnload='';
        			isbackup='是';
        			pathbackup='';
        			isarchive='是';
        			patharchive='';
        			ischeckout='是';
        			pathcheckout='';
        			namemdlsrc='';
        			namemdldes='';
        			ishavaaux='是';
        			auxfiletypes='';
    			}
    			else {
    				aircraft= data[0]['aircraft'];
    				sensor=data[0]['sensor'];
    				datatype=data[0]['datatype'];
    				camera=data[0]['camera'];
    				datalevel=data[0]['datalevel'];
    				isdwnload=data[0]['isdwnload'];
        			pathftp=data[0]['pathftp'];
        			pathdwnload=data[0]['pathdwnload'];
        			isbackup=data[0]['isbackup'];
        			pathbackup=data[0]['pathbackup'];
        			isarchive=data[0]['isarchive'];
        			patharchive=data[0]['patharchive'];
        			ischeckout=data[0]['ischeckout'];
        			pathcheckout=data[0]['pathcheckout'];
        			namemdlsrc=data[0]['namemdlsrc'];
        			namemdldes=data[0]['namemdldes'];
        			ishavaaux=data[0]['ishavaaux'];
        			auxfiletypes=data[0]['auxfiletypes'];
    			}
    			$("button[data-id='aircraft']>div:first").text(aircraft);
    			$("button[data-id='sensor']>div:first").text(sensor);
    			$("button[data-id='datatype']>div:first").text(datatype);
    			$("button[data-id='camera']>div:first").text(camera);
    			$("button[data-id='datalevel']>div:first").text(datalevel);
    			$("button[data-id='isdwnload']>div:first").text(isdwnload==true?'是':'否');
    			$("button[data-id='isbackup']>div:first").text(isbackup==true?'是':'否');
    			$("button[data-id='isarchive']>div:first").text(isarchive==true?'是':'否');
    			$("button[data-id='ischeckout']>div:first").text(ischeckout==true?'是':'否');
    			$("button[data-id='ishavaaux']>div:first").text(ishavaaux==true?'是':'否');
				$("#pathftp").val(pathftp);
				$("#pathdwnload").val(pathdwnload);
			    $("#pathbackup").val(pathbackup);
				$("#patharchive").val(patharchive);
				$("#pathcheckout").val(pathcheckout);
				$("#namemdlsrc").val(namemdlsrc);
				$("#namemdldes").val(namemdldes);
				$("#auxfiletypes").val(auxfiletypes);
    		},
    		error:function(){
    			console.log("error");
    		}
    	});
    };	
}
        
function beforeCheck(event, treeId, treeNode){
	console.log("before");
}
function onCheck(event, treeId, treeNode){
	console.log($.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true));
	//console.log(treeNodes);
}

function onRemove(e, treeId, treeNode) {
    console.log("delete:"+treeNode.id);
        }
  
function onRightClick(event, treeId, treeNode) {
    console.log("rightClick dosomething.....");
}

function beforeDrag(treeId, treeNodes) {
    return false;
}
        
        
function onRename(e, treeId, treeNode, isCancel) {
	console.log("修改？");
    console.log((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
}

function showRenameBtn(treeId, treeNode) {
    //return !treeNode.isLastNode;
    return treeNode;
}

function getTime() {
    var now= new Date(),
            h=now.getHours(),
            m=now.getMinutes(),
            s=now.getSeconds(),
            ms=now.getMilliseconds();
    return (h+":"+m+":"+s+ " " +ms);
}

//鼠标移动是否显示按钮
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_"+treeNode.tId).unbind().remove();
    //$("#"+treeNode.tId+"_edit").unbind().remove();
    //$("#"+treeNode.tId+"_remove").unbind().remove();
};
		
//初始化树
$(document).ready(function () {
    $.fn.zTree.init($("#treeDemo"), setting, treeNodes);
});
        
//系统配置的保存按钮
$("#modelSave").click(function(){
	//文件信息
	var aircraft=$("#aircraft").val();
	var sensor=$("#sensor").val();
	var datatype=$("#datatype").val();
	var camera=$("#camera").val();
	var datalevel=$("#datalevel").val();
	//下载配置
	var isdwnload=$("#isdwnload").val();
	var pathftp=$("#pathftp").val();
	var pathdwnload=$("#pathdwnload").val();
	//备份配置
	var isbackup=$("#isbackup").val();
	var pathbackup=$("#pathbackup").val();
	//归档配置
	var isarchive=$("#isarchive").val();
	var patharchive=$("#patharchive").val();
	//检出配置
	var ischeckout=$("#ischeckout").val();
	var pathcheckout=$("#pathcheckout").val();
	var namemdlsrc=$("#namemdlsrc").val();
	var namemdldes=$("#namemdldes").val();
	var ishavaaux=$("#ishavaaux").val();
	var auxfiletypes=$("#auxfiletypes").val();
	console.log(key_+" "+ishavaaux);
	$.ajax({
		url:'../t12_initmodule/saveModelInfo',
		asyn:false,
		method:'post',  
		dataType:'json', 
		data:{
			fkeystreenode:key_,
			aircraft:aircraft,sensor:sensor,datatype:datatype,
			camera:camera,datalevel:datalevel,
			isdwnload:isdwnload,pathftp:pathftp,pathdwnload:pathdwnload,
			isbackup:isbackup,pathbackup:pathbackup,
			isarchive:isarchive,patharchive:patharchive,
			ischeckout:ischeckout,pathcheckout:pathcheckout,namemdlsrc:namemdlsrc,
			namemdldes:namemdldes,ishavaaux:ishavaaux,auxfiletypes:auxfiletypes
		},
		beforeSend:function(){
			if(pathftp==null||pathftp.length==0){
				alert("FTP路径不能为空！");
				return false;
			}else if(pathdwnload==null||pathdwnload.length==0){
				alert("下载路径不能为空！");
				return false;
			}else if(pathbackup==null||pathbackup.length==0){
				alert("备份路径不能为空！");
				return false;
			}else if(patharchive==null||patharchive.length==0){
				alert("归档路径不能为空！");
				return false;
			}else if(pathcheckout==null||pathcheckout.length==0){
				alert("检出路径不能为空！");
				return false;
			}else if(namemdlsrc==null||namemdlsrc.length==0){
				alert("原文件名规则不能为空！");
				return false;
			}
			else if(namemdlsrc==null||namemdlsrc.length==0){
				alert("改名规则不能为空！");
				return false;
			}
			else if(auxfiletypes==null||auxfiletypes.length==0){
				alert("辅助数据类型不能为空！");
				return false;
			}
			else return true;
		},
		success:function(data){
			alert(data);
		},
		error:function(){
			console.log("error");
		}
	});
});