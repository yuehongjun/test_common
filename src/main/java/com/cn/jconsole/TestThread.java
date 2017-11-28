/**
 * 
 */
package com.cn.jconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @ClassName: TestThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月3日 下午5:57:39
 * 
 */
public class TestThread {

	/**
	 * 
	 * @Title: 线程死循环演示
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return:
	 * @throws
	 */
	public static void createBusyThread() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true)
					;
			}
		}, "testBusyThread");
		thread.start();
	}

	/**
	 * 
	 * @Title: 线程锁等待演示
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param:
	 * @return:
	 * @throws
	 */
	public static void createLockThread(final Object lock) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();

		Object object = new Object();
		createLockThread(object);
	}
}
