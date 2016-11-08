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
}

var dataRoot={
		id:"",
		pid:"",
		level:0,
		name:"",
		isParent:false,
		isCompress:false,
		children:[]
};
var dataCheckedTreeNodes = [];

//复制树 递归方式
function copyTreeNode(src, des){

	//本身信息
	des.id = src.id;
	des.pid = src.pid;
	des.level = src.level;
	des.name = src.name;
	des.isParent = src.isParent;
	if(!(src.isParent)){
		//叶子节点
		des.children = null;
		return ;
	}
	//是否自己可压缩
	if(src.isCompress){
		//不用再复制孩子
		des.children = null;
		return ;
	}
	
	//不可压缩的 则复制checked 孩子
	des.children = new Array();
	//孩子节点 递归
	for(var i = 0;i<src.children.length;i++){
		if(src.children[i].checked){
			//选中的才可能添加
			var newChild = {};
			des.children.push(newChild);
			copyTreeNode(src.children[i], newChild);
		}
	}
}
//检查节点是否可以压缩
function checkCompress(src){
	if(!(src.isParent)){
		//叶子节点
		if(src.checked){
			src.isCompress = true;
		}else{
			src.isCompress = false;
		}
		
		return src.isCompress;
	}
	//孩子节点决定
	src.isCompress = true;
	for(var i = 0;i<src.children.length;i++){
		if((!src.children[i].checked) || (!checkCompress(src.children[i]))){
			src.isCompress = false;
			//break;
		}
	}
	return src.isCompress;
}

function getCheckedTreeNodes(){
	var TreeRoot = $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes(true)[0];
	if(!TreeRoot){
		return false;
	}

	//检查可压缩情况
	checkCompress(TreeRoot);
	console.log(TreeRoot);
	//清空
	dataRoot = {};
	//复制可压缩树
	copyTreeNode(TreeRoot, dataRoot);
	
	console.log(dataRoot);
//	dataCheckedTreeNodes.length = 0;
//	for(var i = 0;i<rootTree.length;i++){
//		dataCheckedTreeNodes.push({
//			id:rootTree[i].id,
//			pid:rootTree[i].pid,
//			level:rootTree[i].level,
//			name:rootTree[i].name,
//			isParent:rootTree[i].isParent
//		});
//	}
//	//console.log("==============");
//	console.log(dataCheckedTreeNodes);
	return true;
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