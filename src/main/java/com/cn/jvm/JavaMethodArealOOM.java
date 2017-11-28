/**
 * 
 */
package com.cn.jvm;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Demo5 借助CGLib使方法区出现内存溢出异常
 * java.lang.OutOfMemoryError:PermGen space
 * 方法区用于存放class的相关信息，如类名，访问修饰符，常量池，字段描述，方法描述等 对于该区域的测试，基本思路是运行时产生大量的类去填满方法区
 * 
 * @ClassName: JavaMethodArealOOM
 * @Description:VM Args: -XX:PermSize=10m -XX:MaxPermSize=10m
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月1日 下午2:56:56
 * 
 */
public class JavaMethodArealOOM {

	public static void main(String[] args) {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				@Override
				public Object intercept(Object obj, Method method,
						Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invoke(obj, args);
				}
			});
			enhancer.create();
		}
	}

	static class OOMObject {
	}
}
