//全局变量
var setting = {         
    data: {
        simpleData: {
            enable: true
        }
    }, 
};
//初始化树
function getTreeData () {
	$.ajax({
		url:'getTreeNode',
		asyn:false,
		method:'post',  
		dataType:'json',  
		success:function(data){
			console.log(data);
			for(var i=0;i<data.length;i++){
				data[i]['open']=true;
			}
			$.fn.zTree.init($("#treeDemo"), setting, data);
		},
		error:function(){
			console.log("error");
		}
	});
};
$(function(){
	getTreeData();
});