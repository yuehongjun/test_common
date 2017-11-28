package com.cn.jvm;

import java.util.ArrayList;
import java.util.List;

/**demo1
 * java堆内存溢出异常测试
 * 
 * @ClassName: HeadOOM 
 * @Description: VM Args: -Xms20m -Xmx20m -xx:+HeapDumpOnOutOfMemoryError
 * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 * @author yhj yuehongjun@aipark.com  
 * @date 2017年11月1日 上午11:15:33 
 *
 *java堆内存溢出
 *运行错误信息：Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class HeadOOM {
	
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> list = new ArrayList<OOMObject>(); 
		int i=0;
		while(true){
			System.out.println(++i);
			list.add(new OOMObject());
		}
	}

}
