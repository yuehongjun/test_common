package com.cn.excute;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UseThreadPoolExecutor {

	public static void main(String[] args) {
		System.out.println("----------------------"+Executors.defaultThreadFactory());
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
	    ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
				1,//coreSize 
				20, //MaxSize
				20, //
				TimeUnit.SECONDS, //
				new ArrayBlockingQueue<Runnable>(10),
				threadFactory, 
				rejectionHandler
		);
		
		MyTask mt1 = new MyTask(1, "任务1");
		MyTask mt2 = new MyTask(2, "任务2");
		MyTask mt3 = new MyTask(3, "任务3");
		MyTask mt4 = new MyTask(4, "任务4");
		MyTask mt5 = new MyTask(5, "任务5");
		MyTask mt6 = new MyTask(6, "任务6");
		MyTask mt7 = new MyTask(7, "任务7");
		MyTask mt8 = new MyTask(8, "任务8");
		MyTask mt9 = new MyTask(9, "任务9");
		threadPool.execute(mt1);
		threadPool.execute(mt2);
		threadPool.execute(mt3);
		threadPool.execute(mt4);
		threadPool.execute(mt5);
		threadPool.execute(mt6);
		threadPool.execute(mt7);
		threadPool.execute(mt8);
		threadPool.execute(mt9);
		//threadPool.getActiveCount():活跃线程数
		//threadPool.getPoolSize():线程池里的时刻线程数
		//threadPool.getTaskCount():线程池里执行线程总数
		//threadPool.getLargestPoolSize():线程池里的曾经最大线程数
		/*while(true)
		System.out.println("ooooooooo"+threadPool.getActiveCount()+"--"+threadPool.getPoolSize()+"--"+threadPool.getTaskCount()+"--"+threadPool.getLargestPoolSize());*/
	}
}
