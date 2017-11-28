/**
 * 
 */
package com.cn.jvm.gc;

/**
 *  
 * @ClassName: MinorGC
 * @Description: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * PSYoungGen中的Eden:FromSpace为8:1，
 * @author yhj yuehongjun@aipark.com
 * @date 2017年11月2日 下午3:09:50
 * Heap
 * 	PSYoungGen :新生代
 * 	  eden space：伊甸园
 * 	  from space：幸存区1
 * 	  to   space：幸存区2
 *  ParOldGen  :老年代
 *  PSPermGen  :永久代	
 */
public class MinorGC {

	public static void main(String[] args) throws InterruptedException {
//		new MinorGC().test4();
		Thread.sleep(999999999);
	}
	
	private static final int _1MB = 1024 * 1024;
	private static final int _xMB = 1024 * 512;
	/**
	 * * 参考解释：http://www.linuxidc.com/Linux/2017-04/142507.htm
	 * //打印日志如下
	 * 1、[GC-- [PSYoungGen: 4932K->4932K(9216K)] 11076K->13124K(19456K), 0.0050819 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
	 * 2、[Full GC [PSYoungGen: 4932K->2511K(9216K)] [ParOldGen: 8192K->8192K(10240K)] 13124K->10703K(19456K) [PSPermGen: 2551K->2550K(21504K)], 0.0123169 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
	 * 3、Heap
	 * 4、PSYoungGen total 9216K, used 6774K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
	 * 5、eden space 8192K, 82% used [0x00000000ff600000,0x00000000ffc9d840,0x00000000ffe00000)
	 * 6、from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
	 * 7、to space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
	 * 8、ParOldGen total 10240K, used 8192K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
	 * 9、object space 10240K, 80% used [0x00000000fec00000,0x00000000ff400020,0x00000000ff600000)
	 * 10、PSPermGen total 21504K, used 2557K [0x00000000f9a00000, 0x00000000faf00000, 0x00000000fec00000)
	 * 11、object space 21504K, 11% used [0x00000000f9a00000,0x00000000f9c7f688,0x00000000faf00000)
	 * 为了解释方便，给日志加上了行号，首先第1行，GC说明发生了垃圾回收。其中[PSYoungGen: 4932K->4932K(9216K)]表示新生代应用Parallel Scavenger收集器，
	 * 垃圾收集前内存占用4932K，垃圾收集后内存占用4932K，因为allocation1和allocation2对象都还存活；该区域总内存为9216K（9M）。
	 * 其中11076K->13124K(19456K)表示Java堆内存回收前后占用内存和总内存。其中0.0050819 secs表示执行内存回收时间。
	 * 其中[Times: user=0.00 sys=0.00, real=0.00 secs]，user表示用户态消耗的CPU时间，sys表示内核态消耗的CPU时间，real表示操作开始到结束所经过的墙钟时间。
	 * 同理第2行表示为：新生代执行了Full GC，内存变化为由4932K变为2511K，说明有2M内存进入老年代；老年代采用Parallel Old收集器，
	 * 新创建的allocation3同样进入老年代，可以看到老年代内存占用为8192K（8M）；永久代（PermGen）内存没有太大变化。
	 * 第3-11行是程序执行完内存的情况新生代内存总共9M，内存占用6774K（最后allocation4直接分配在新生代中），新生代Eden区8192K，占用82%；
	 * from和to区域1024K，占用0%；老年代内存总共10M，占用8M（2M+6M）。其中永久代内存总共21M（21504K），占用2557K。
	 * @Title:    
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args
	 * @param: @throws InterruptedException      
	 * @return:       
	 * @throws
	 */
	public  void test1(){
		/**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
         * -Xms20M 设置堆大小为20M   -Xmx20M 避免堆自动扩展         -Xmn10M  设置年轻代大小 
         * -XX:+PrintGCDetails 打印日志信息
         * -XX:SurvivorRatio=8  设置Eden和Survivor大小比值
         */
		byte[] allocation1,allocation2, allocation3, allocation4;
		/*allocation1 = new byte[1 * _1MB];
		System.out.println("------------1");
		//System.gc();
		allocation2 = new byte[2 * _1MB];
		System.out.println("------------2");
		allocation3 = new byte[6 * _1MB];//新生代需要Minor GC，Full GC直接将其分配进OldGe
		System.out.println("------------3");
		allocation4 = new byte[4 * _1MB];//新生代分配内存
		System.out.println("------------4");*/
		allocation1 = new byte[_1MB/100];
		System.out.println("------------1");
		System.gc();
		/*allocation2 = new byte[4 * _1MB];
		System.out.println("------------2");
		allocation3 = new byte[4 * _1MB];//新生代需要Minor GC，Full GC直接将其分配进OldGe
		System.out.println("------------3");
		allocation4 = new byte[4 * _1MB];//新生代分配内存
		System.out.println("------------4");*/
	}
	
	/*
	 * 大对象直接进入老年代
	 * 　大对象是指需要大量连续内存空间的Java对象，如字符串以及数组。
	 * 虚拟机提供参数-XX:PretenureSizeThreshold参数来指定大对象，
	 * 大于该值的对象都是大对象。如下我们指定大于3M的对象都是大对象，
	 * 可以从打印日志中看出allocation3直接被分配到老年代中
	 */
	public static void test2() {
        /**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
         * -Xms20M 设置堆大小为20M   -Xmx20M 避免堆自动扩展         -Xmn10M  设置年轻代大小 
         * -XX:+PrintGCDetails 打印日志信息
         * -XX:SurvivorRatio=8  设置Eden和Survivor大小比值
         * --XX:+UseSerialGC 使用Serial + Serial Old收集器组合进行内存回收
         * -XX:PretenureSizeThreshold=3145728  指定超过3M的对象直接进入老年代
         */
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[1 * _1MB];
        //allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[6 * _1MB]; //新生代需要Minor GC，Full GC
        //allocation4 = new byte[4 * _1MB];
    }
	
	/**
	 * 长期存活的对象将进入老年代
	 * 既然虚拟机采用了分代收集的思想来管理内存，那么内存回收时就必须能识别哪些对象应放在新生代，
	 * 哪些对象应放在老年代。为了做到这一点，虚拟机给每个对象定义了一个对象年龄计数器。
	 * 如果对象在Eden出生并经过第一次Minor GC后仍然存活且能被Survivor容纳的话，
	 * 将被移动到Survivor空间，并且对象年龄设为1，对象在Survivor区中每经过一次Minor GC，年龄就增加1岁，
	 * 当年龄增加到一定程度（默认是15岁），就会晋升到老年代。对象晋升到老年代的年龄阈值，可以通过参数-XX:MaxTenuringThreshold设置
	 */
	public static void test3() {
        /**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
         * -Xms20M 设置堆大小为20M   -Xmx20M 避免堆自动扩展         -Xmn10M  设置年轻代大小 
         * -XX:+PrintGCDetails 打印日志信息
         * -XX:SurvivorRatio=8  设置Eden和Survivor大小比值
         * -XX:MaxTenuringThreshold  当年龄大于该值时，放入老年代
         */
        // TODO Auto-generated method stub
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[5 * _1MB];  //新生代执行Minor GC，将allocation1移入Survivor，将allocation2移入老年代
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];  //直接在新生代上分配空间
    }
	
	/**
	 * 动态对象年龄判定
	 * 为了能更好地适应不同程序的内存状况，虚拟机并不是永远地要求对象的年龄必须到达MaxTenuringThreshold才能晋升到老年代，
	 * 如果Survivor空间中相同年龄对象大小的总和大于Survivor空间的一半，年龄大于等于该年龄的对象就可以直接进入老年代，
	 * 无需等到MaxTenuringThreshold中要求的年龄。
	 * 
	 */
	public static void test4() {
		 /**
         * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
         * -Xms20M 设置堆大小为20M   -Xmx20M 避免堆自动扩展         -Xmn10M  设置年轻代大小 
         * -XX:+PrintGCDetails 打印日志信息
         * -XX:SurvivorRatio=8  设置Eden和Survivor大小比值
         */
        // TODO Auto-generated method stub
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];  //Minor GC 将allocation1和allocation2放入Survivor，将allocation3放入老年代，allocation4放入新生代
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
	
}
