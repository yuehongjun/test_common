/**
 * 
 */
package com.cn.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestUseSerialGC
 * @Description: VM: -Xms100m -Xmx100m -XX:+UseSerialGC
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月3日 下午3:23:35
 * 
 */
public class TestUseSerialGC {

	static class OOMObject {
		public byte[] placeholder = new byte[64 * 1024];
	}

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < num; i++) {
			//稍作延时，令监视的曲线更加明显
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}

	/**
	 * @Title:
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param args
	 * @return:
	 * @throws
	 */
	public static void main(String[] args) {
		try {
			fillHeap(1000);
			Thread.sleep(999999999);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
