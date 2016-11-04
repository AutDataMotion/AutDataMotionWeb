/**
 * <p>title:AbsTaskThread.java<／p>
 * <p>Description: <／p>
 * @date:2016年10月30日下午8:11:29
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package datamotion.common;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

/**  
 * 创建时间：2016年10月30日 下午8:11:29  
 * 项目名称：AutDataMotion   
 * 文件名称：AbsTaskThread.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年10月30日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: AbsTaskThread<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年10月30日
 */
public abstract class AbsTaskThread<F extends MdlFileEvent> implements
		InfTaskThread {
	private static Logger log = Logger.getLogger(AbsTaskThread.class);
	private volatile boolean flag_isDoingTask = false;
	// 待处理任务 队列
	volatile protected Queue<F> quefiles_undo = new ConcurrentLinkedQueue<F>();
	// 成功map filename 为 key
	volatile protected ConcurrentHashMap<String, F> mapfiles_dosuc = new ConcurrentHashMap<String, F>();
	// 失败map filename 为 key
	volatile protected ConcurrentHashMap<String, F> mapfiles_dofail = new ConcurrentHashMap<String, F>();

	// 同步锁
	final Lock lock = new ReentrantLock();// 锁对象
	final Condition singalQue = lock.newCondition();// 读写条件

	// 执行任务线程
	private ConsumerTask consumerTask;
	// 内存回收线程
	private TimerGCClear timerGCClear;
	// 错误处理线程
	private TimerRedoFailed timerRedoFailed;

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see datamotion.common.InfTaskThread#init()
	 */
	@Override
	public boolean init() {
		// TODO Auto-generated method stub

		return false;
	}

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see datamotion.common.InfTaskThread#start()
	 */
	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		if (null == consumerTask) {
			consumerTask = new ConsumerTask("consumer task thread");
		}
		if (null == timerGCClear) {
			timerGCClear = new TimerGCClear();
		}
		if (null == timerRedoFailed) {
			timerRedoFailed = new TimerRedoFailed();
		}
		consumerTask.status = 1;// running
		consumerTask.start();

		timerGCClear.start();

		timerRedoFailed.flagRun = true;
		timerRedoFailed.start();
		return true;
	}

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see datamotion.common.InfTaskThread#stop()
	 */
	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		if (consumerTask != null) {
			consumerTask.setStop();
		}
		if (timerGCClear != null) {
			timerGCClear.setStop();
		}
		if (null == timerRedoFailed) {
			timerRedoFailed.setStop();
		}
		return false;
	}

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see datamotion.common.InfTaskThread#restart()
	 */
	@Override
	public boolean restart() {
		// TODO Auto-generated method stub
		stop();
		init();
		start();
		return false;
	}

	public abstract boolean isCorrectFile(F afile);

	public abstract boolean doWorkSucAfter(F afile);

	public abstract boolean doWorkFailAfter(F afile);

	public abstract boolean dbAddFileInfo(F afile);

	public abstract boolean dbUpdateFileInfo(F afile);

	public abstract boolean notifyOthers(F afile);

	class ConsumerTask extends Thread {

		private final int STOP = -1;
		private final int SUSPEND = 0;
		private final int RUNNING = 1;
		public volatile int status = 1;

		public ConsumerTask(String aName) {
			super(aName);
		}

		@Override
		public void run() {
			while (status != STOP) {
				// 判断是否挂起
				if (status == SUSPEND) {
					try {
						// 若线程挂起则阻塞自己
						wait();
					} catch (InterruptedException e) {
						System.out.println("线程异常终止...");
					}
				} else {
					lock.lock();
					timeTaskEnd = System.currentTimeMillis();
					while (quefiles_undo.size() == 0) {
						try {
							System.out.println("现有存档任务队列已处理完毕，等待新文件到来...");
							flag_isDoingTask = false;
							singalQue.await();
						} catch (InterruptedException e) {
							e.printStackTrace();
							lock.unlock();
						}
					}
					flag_isDoingTask = true;
					lock.unlock();// lock是同步锁的意思，不能锁住耗时较长的代码块不放
					F file = quefiles_undo.poll();
					if (null == file) {
						log.debug("ConsumerTask run null == file");
						continue;
					}
					if (!isCorrectFile(file)) {
						log.debug("ConsumerTask run not CorrectFile(file)");
						continue;
					}
					if (!dbAddFileInfo(file)) {
						log.debug("ConsumerTask run failed dbAddFileInfo(file)");
						continue;
					}
					if (doWork(file)) {// queueTask 自带了同步代码 可以不放到Lock中
						mapfiles_dosuc.put(file.namesrc, file);// 处理成功
						// 处理成功后续处理
						doWorkSucAfter(file);
						// 通知其他系统
						notifyOthers(file);
					} else {
						mapfiles_dofail.put(file.namesrc, file);// 处理失败
						// 初始失败后续处理
						doWorkFailAfter(file);
					}
					// 更新数据库
					dbUpdateFileInfo(file);
				}
			}
		}

		public void setStop() {
			this.status = STOP;
		}


		public synchronized void myResume() {
			// 修改状态
			status = RUNNING;
			// 唤醒
			notifyAll();
		}
	}

	// 清理定时器 间隔次数
	private final static int timeSpanSecond = 1000;// 1分钟
	private final static int timeSpanMinute = timeSpanSecond * 60;// 1分钟
	private final static int timeSpanHoure = timeSpanMinute * 60;// 1小时
	private final static long TIME_BEG = System.currentTimeMillis();
	private long timeCopyEnd = TIME_BEG;
	private long timeTaskEnd = TIME_BEG;
	private long timeIdleCopy = timeSpanHoure * 12;// 12小时过期
	private long timeIdleTask = timeSpanHoure * 1;// 12小时过期

	protected abstract boolean clearData();

	private void __ClearData() {
		// 正在执行任务则直接返回
		if (flag_isDoingTask) {
			log.debug("任务正在执行，清理工作跳过...");
			return;
		}
		long timeCur = System.currentTimeMillis();
		// 任务处理结束后 空闲时差
		if (timeCur - timeTaskEnd < timeIdleTask) {
			log.debug("还未到清理时间，清理工作跳过...");
			return;
		}
	}

	/**
	 * <p>
	 * Title: TimerGCClear<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @author ZhongwengHao
	 * @date 2016年11月1日
	 */
	class TimerGCClear extends Timer {
		public void start() {
			this.schedule(new TimerTask() {
				@Override
				public void run() {
					__ClearData();
				}
			}, timeSpanMinute * 30, timeSpanHoure * 1);
		}

		/**
		 * 停止任务
		 */
		public void setStop() {
			this.cancel();
		}
	}

	protected abstract boolean reDoFailedWorks(F amdlWork);

	private boolean __RedoFailedWorks() {
		reDoFailedWorks(null);
		return true;
	}

	class TimerRedoFailed extends Thread {
		private volatile boolean flagRun = true;

		@Override
		public void run() {
			while (flagRun) {
				try {

					Thread.sleep(timeSpanMinute * 30);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				__RedoFailedWorks();
			}

		}

		public void setStop() {
			flagRun = false;
		}

	}

}
