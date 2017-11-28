package com.cn.thread;
/**
 * volatile方法
 * @author Administrator
 *
 */
public class RunThreadVolatile implements Runnable{
	volatile boolean  isRunnting = true;
	public boolean isRunnting() {
		return isRunnting;
	}
	public void setRunnting(boolean isRunnting) {
		this.isRunnting = isRunnting;
	}
	@Override
	public void run() {
		System.out.println("进入run方法开始");
		while(isRunnting==true){
			//具体业务逻辑
		}
		System.out.println("进入run方法结束");
	}
	public static void main(String[] args) throws InterruptedException {
		RunThreadVolatile rt = new RunThreadVolatile();
		Thread thread = new Thread(rt);
		thread.start();
		Thread.sleep(3000);
		rt.setRunnting(false);
		System.out.println("isRunnting设置为false");
		Thread.sleep(1000);
		System.out.println(rt.isRunnting);
	}
}
