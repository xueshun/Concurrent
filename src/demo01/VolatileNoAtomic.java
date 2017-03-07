package demo01;

/**
 * volatile不具有原子性
 *   只具备多线程的可见性
 *  要实现原子性建议使用atomic类的系列对象，支持原子性操作，(注意atomic只能保证本身方法原子性，并不能
 *  保证多次操作的原子性)
 */
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileNoAtomic extends Thread {
	//private static volatile int count;
	private static AtomicInteger count = new AtomicInteger(0);
	
	private static void addCount(){
		for(int i = 0; i<1000; i++){
			//count ++;
			count.incrementAndGet();
		}
		System.out.println(count);
	}
	
	public void run(){
		addCount();
	}
	
	public static void main(String[] args) {
		VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}
		
		for (int i = 0; i <10; i++) {
			arr[i].start();
		}
	}
}
