//全局变量
var setting = {    
	check: {
		enable: true
	},
    data: {
        simpleData: {
            enable: true
        }       
    }, 
    callback: {
        onClick: onClick,
        onCheck:onCheck
    }
};

//节点的点击事件
function onClick(event, treeId, treeNode) {
	console.log(treeNode.name);
};	

//复选框选中事件
	
function onCheck(event, treeId, treeNode){
	//console.log($.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true));
	//console.log(treeNodes);
	alert(getPathText(treeNode))
}
function getPathText(node){//关键代码，通过treeNode遍历父亲节点，根节点再次调用getParentNode得到null终止循环
    var s=node.name;
    while(node=node.getParentNode()){
    		s=node.name+'/'+s;
    }
    	
    return s;
 }
var dataNodeMdl={
		id:"",
		pid:"",
		level:0,
		name:""
};
var dataCheckedTreeNodes = new Array();
function getCheckedTreeNodesArr(){
	var rootTree = $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes(true);
	console.log(rootTree);
	dataCheckedTreeNodes.length = 0;
	for(var i = 0;i<rootTree.length;i++){
		dataCheckedTreeNodes.push({
			id:rootTree[i].id,
			pid:rootTree[i].pid,
			level:rootTree[i].level,
			name:rootTree[i].name,
			isParent:rootTree[i].isParent
		});
	}
	//console.log("==============");
	console.log(dataCheckedTreeNodes);
}
//初始化树
function getTreeData () {
	$.ajax({
		url:'../t11_initfoldertree/getTreeNode',
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