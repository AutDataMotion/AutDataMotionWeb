package com.platform.interf;

/**
 * <p>Title: InfMainConf<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年4月2日
 */
public interface InfMainConf { 
	/**
	 * <p>Title: init<／p>
	 * <p>Description:初始化 <／p>
	 * @return
	 */
	public boolean init();
	/**
	 * <p>Title: start<／p>
	 * <p>Description: 启动<／p>
	 * @return
	 */
	public boolean start();
	/**
	 * <p>Title: refresh<／p>
	 * <p>Description:刷新 <／p>
	 * @return
	 */
	public boolean refresh();
	/**
	 * <p>Title: stop<／p>
	 * <p>Description:停止 <／p>
	 * @return
	 */
	public boolean stop();
}
