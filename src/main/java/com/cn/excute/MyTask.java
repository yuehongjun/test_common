package com.cn.excute;

public class MyTask implements Runnable{
	int i=0;
	String name = "";
	public MyTask(int i,String name){
		this.i = i;
		this.name = name;
	}
	public MyTask(){
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("==========i:"+i+"      ==========name"+name+"    "+Thread.currentThread().getName());
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}