/**
 * <p>title:StatusMy.java<／p>
 * <p>Description: <／p>
 * @date:2016年7月6日下午5:50:36
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.constant;

/**  
 * 创建时间：2016年7月6日 下午5:50:36  
 * 项目名称：TG2DataStore   
 * 文件名称：StatusMy.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年7月6日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: StatusMy<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年7月6日
 */
public enum StatusMy {
	
	//MWI
	MWI_MOD_BandMag(2,"MWI_MOD_BandMag"),
	MWI_CAMERA_SWI(2,"SWI"),
	MWI_CAMERA_INF(1,"INF"),
	MWI_CAMERA_VNI(0,"VNI"),
	
	
	//FileWatchEvent 
	FILE_CREATE(0,"FILE_CREATE"),
	FILE_MODIFY(1,"FILE_MODIFY"),
	FILE_DELETE(2,"FILE_DELETE"),
	FILE_COPY_DOING(3,"FILE_COPY_DOING"),
	FILE_COPY_DONE(4,"FILE_COPY_DONE"),
	FILE_DEL_DONE(2,"FILE_DELETE"),
	//====等级
		L0B(-1,"0B"),
		L0C(0,"0C"),
		L0(0,"L0"),
		L1(1,"L1"),
		L2(2,"L2"),
		L3(3,"L3"),
		L4(4,"L4"),
	//MWI
	MWI(70,"MWI"),
	//ZWR
	ZWR(50,"ZWR"),
	//ZWF
	ZWF(30,"ZWF"),
	
	//IALT
	IALT_Notify(19,"Notify"),
	IALT_DBInsert(18,"DBInsert"),
	IALT_QuickViewMap(17,"QuickViewMap"),
	IALT_RetriveInfo(16,"RetrieveInfo"),
	IALT_CopyParamSection(15,"CopyParamSection"),
	IALT_HROM(14,"HOM ROM"),
	IALT_Scene(13,"Scene"),
	IALT_Param(12,"Params"),
	IALT_READ_0B(11,"Read 0B"),
	IALT(10,"IALT"),
	
	
	//数据库日志标识
	
	LOG_FILE_COME(-5,"新文件"),
	LOG_ERR_NO_DAT(-4,"没有找到辅助文件"),
	LOG_ERR_NO_AUX(-3,"没有找到辅助文件"),
	LOG_SUC_OK(-2, "成功"),
	LOG_ERR_FAIL(-1, "失败"),
	
	//处理阶段
	STEP_L0_INDB(140,"L0数据元信息入库"),
	STEP_L0_MOVE(130, "移动文件到LO目录"),
	STEP_BACKUP(120, "备份原数据"),
	STEP_OUT_QUE(110, "出任务队列"),
	STEP_IN_QUE(100, "入任务队列"),
	STEP_NULL(99, "空任务"),

	//流程模块
	FLOW_CHECKOUT(50,"检出模块"),
	FLOW_ARCHIVE(40,"归档模块"),
	FLOW_BACKUP(30,"备份模块"),
	FLOW_DOWNLAD(20,"下载模块"),
	FLOW_UNKNOWN(10,"未知"),
	//处理状态
	PROC_ESCAPE(40, "跳过"),
	PROC_FAIL(30, "失败"),
	PROC_SUC(20, "成功"),
	PROC_DOING(10, "正在执行"),
	PROC_UNDO(0, "正在执行")
	;
	
	public int index;
	public String name;
	
	private StatusMy(int index, String name){
		this.index = index;
		this.name = name;
	}
	
}
