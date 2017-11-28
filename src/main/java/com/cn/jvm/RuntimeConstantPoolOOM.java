/**
 * 
 */
package com.cn.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo4
 * JDK1.6及以前的版本中，由于常量池分配在永久区内，我们可以通过-XX:PermSize和-XX:MaxPermSize限制方法区大小
 * 运行时常量池导致的内存溢出异常
 * JDK1.7运行这段代码的结果是，While将一直循环下去
 * @ClassName: RuntimeConstantPoolOOM
 * @Description:VM Args: -XX:PermSize=10m -XX:MaxPermSize=10m
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月1日 下午2:56:56
 * 
 */
public class RuntimeConstantPoolOOM {

	public static void main(String[] args) {
		// 使用List保持着常量池的引用，避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的permSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}

	}
}
