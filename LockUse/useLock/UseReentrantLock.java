package useLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 重入锁  加锁后 要记得解锁
 * @author Administrator
 *
 */
public class UseReentrantLock {
	
	private Lock lock = new ReentrantLock();
	
	public void method01(){
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入了Method01.....");
			Thread.sleep(1000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出了method01......");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
			Thread.sleep(2000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseReentrantLock ur = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				ur.method01();
				ur.method2();
			}
		},"t1");
		
		t1.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(((ReentrantLock) ur.lock).getQueueLength());
	}
}
