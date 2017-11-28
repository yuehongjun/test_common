package com.cn.excute;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
 
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
 
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    	Class<? extends Runnable> class1 = r.getClass();
    	try {
			Method method = class1.getMethod("getI");
			try {
				Object invoke = method.invoke(r,null);
				String id = invoke.toString();
				System.out.println("==================被抛弃的Id："+id);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	System.out.println(r.toString()+ " is rejected"+executor.toString());
    }
 
}