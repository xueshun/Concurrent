package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 
 * @author Administrator
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		//线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		//只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		//模拟20个客户端访问
		for (int i = 0; i < 20; i++) {
			final int NO = i;
			Runnable run =  new Runnable() {
				public void run() {
					try {
						//获取许可
						semp.acquire();
						System.out.println("Accessing:"+NO);
						//模拟实际业务
						Thread.sleep((long)(Math.random()*10000));
						//访问结束，释放
						semp.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(semp.getQueueLength());
		
		//退出线程池
		exec.shutdown();
	}
}
 