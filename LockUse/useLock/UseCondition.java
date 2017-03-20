package useLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock  synchronized 
 * 
 * signal   notify
 * await     wait
 * @author Administrator
 *
 */
public class UseCondition {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void method01(){
		
		try {
			lock.lock();
			System.out.println("当前线程"+Thread.currentThread().getName()+"进入了等待状态....");
			Thread.sleep(3000);
			System.out.println("当前线程"+Thread.currentThread().getName()+"释放锁.....");
			condition.await(); //object wait
			System.out.println("当前线程"+Thread.currentThread().getName()+"继续执行.....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	
	public void method02(){
		try {
			lock.lock();
			System.out.println("当前线程："+Thread.currentThread().getName()+"进入...");
			Thread.sleep(3000);
			System.out.println("当前线程："+Thread.currentThread().getName()+"发出唤醒...");
			condition.signal();//Object notify
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				uc.method01();
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				uc.method02();
			}
		},"t2");
		
		t1.start();
		t2.start();
	}
}
