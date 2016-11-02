//全局变量
var setting = {         
    data: {
        simpleData: {
            enable: true
        },
        callback: {
            onClick: onClick
        }
    }, 
};

//叶子节点的点击事件
function onClick(event, treeId, treeNode) {
	if(treeNode!=null&&treeNode.isParent==false){
    	console.log(treeNode.name);
    };	
}

//初始化树
function getTreeData () {
	$.ajax({
		url:'t11_initfoldertree/getTreeNode',
		asyn:false,
		method:'post',  
		dataType:'json',  
		success:function(data){
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