package cyclicBarrierUse;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier使用
 * @author Administrator
 *	假设有只有的一个场景，每个线程代表一个跑步的运动员，当运动员都准备好后，才一起出发
 *	 只要有一个人没有准备好，大家都等待
 */
public class Demo01 {
	static class Runner implements Runnable{
		
		private CyclicBarrier barrier;
		private String name;
		
		
		public Runner(CyclicBarrier barrier, String name) {
			this.barrier = barrier;
			this.name = name;
		}


		@Override
		public void run() {
			try {
				Thread.sleep(1000*(new Random()).nextInt(5));
				System.out.println(name+" 准备OK.");
				//等待
				barrier.await(); 
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(name+"GO!!!!");
		}
	}
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		executor.submit(new Thread(new Runner(barrier, "zhangsan")));
		executor.submit(new Thread(new Runner(barrier, "lisi")));
		executor.submit(new Thread(new Runner(barrier, "wangwu")));
		
		executor.shutdown();
	}
}
