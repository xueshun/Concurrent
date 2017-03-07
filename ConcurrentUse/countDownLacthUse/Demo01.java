package countDownLacthUse;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLacth
 * @author Administrator
 *	他经常适用于监听某些初始化操作，等初始化完毕后，通知主线程工作
 *	
 *	zookeeper slor
 *
 *	CountDownLatch 与  CyclicBarrier的区别
 *		CountDownLatch：是一个线程等待其他线程给自己反馈，当达到要求的时候，次线程测执行
 *
 *		CyclicBarrier : 是所有线程等待最后一个线程完成，但最后一个任务执行完成，所有的线程则可以
 *						执行其他任务
 *	
 */
public class Demo01 {
	public static void main(String[] args) {
		//参数 就是要监听几个线程countDown
		final CountDownLatch countDown = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Runnable() {	
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1" + "等待其他线程处理完成....");
					//阻塞
					countDown.await();
					System.out.println("t1线程继续执行.....");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2线程进行初始化操作...");
					//睡眠3秒钟
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
	}
}
