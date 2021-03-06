package useLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读写锁，其核心就是实现了读写分离的锁，在高并发访问下，尤其
 * 是在读多写少的情况下，性能要远高于重入锁
 * 
 * 读读共享，写写互斥，读写互斥
 * @author Administrator
 *
 */
public class UseReentrantReadWriteLock {
	
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private ReadLock readLock = rwLock.readLock();
	private WriteLock writeLock = rwLock.writeLock();
	
	public void read(){
		try {
			readLock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"退出....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			readLock.unlock();
		}
	}
	
	public void write(){
		try {
			writeLock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
			Thread.sleep(3000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();
		
		Thread t1 =new Thread(new Runnable() {
			
			@Override
			public void run() {
				urrw.read();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				urrw.read();
			}
		},"t2");
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				urrw.write();
			}
		},"t3");
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				urrw.write();
			}
		});
		
		//t1.start();
		//t2.start();
		//t3.start();
		//t4.start();
		
		//t1.start();
		//t3.start();
		
		t3.start();
		t4.start();
		
	}
}
