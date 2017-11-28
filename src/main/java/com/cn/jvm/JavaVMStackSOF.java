package com.cn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * demo2
 * 虚拟机栈和本地方法栈OOM测试
 * 
 * @ClassName: JavaVMStackSOF
 * @Description: VM Args: -Xss128k
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月1日 上午11:15:33
 * 
 * 使用-Xss参数减少栈内存容量。结果：抛出StackOverflowError异常，异常出现时输出的堆栈深度相应缩小
 *
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	private int i = 1;

	public void stackLeak() {
		// System.out.println("======="+(++i));
		// System.out.println(stackLength++);
		stackLeak();
	}

	public static void main(String[] args) {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Exception e) {
			System.out.println("stack length:" + oom.stackLength);
			// throw e;
		}
	}

}
