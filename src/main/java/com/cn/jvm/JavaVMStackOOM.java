/**
 * 
 */
package com.cn.jvm;

/**
 * demo3
 * 创建线程导致内存溢出异常
 * @ClassName: JavaVMStackOOM
 * @Description: VM Args: -Xss2M(这时候不妨设置大一些)
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月1日 下午2:15:08
 * 
 * java的线程是映射到操作系统的内核线程上的，因此以下代码执行时有很大的风险，可能会造成操作系统假死。 
 * 
 */
public class JavaVMStackOOM {
	private void dontStop() {
		while (true) {

		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("多线程名称为："+Thread.currentThread().getName());
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
