package demo03;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {
	
	//1.自定义一个队列
	private final LinkedList<Object> list = new LinkedList<Object>();
	
	//2.累加器
	private final AtomicInteger count = new AtomicInteger(0);
	
	
	private final int maxSize; //队列的最大长度
	private final int minSize = 0; //队列的最小长度
	
	private final Object lock = new Object(); //锁
	
	public MyQueue(int size){
		this.maxSize = size;
	}
	
	//添加元素
	public void put(Object obj){
		synchronized (lock){
			while(count.get() == maxSize){
				try {
					lock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			list.add(obj);
			count.getAndIncrement();
			System.out.println("元素"+obj+"被添加");
			lock.notify();
		}
	}
	
	//移除元素
	public Object take(){
		Object temp = null;
		synchronized (lock) {
			while(count.get() == minSize){
				try {
					lock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			count.getAndDecrement();
			temp = list.removeFirst();
			System.out.println("元素"+temp + "被移除");
			lock.notify();
		}
		return temp;
	}
	
	public int size(){
		return count.get();
	}
	
	public static void main(String[] args) throws Exception {
		final MyQueue m = new MyQueue(5);
		m.put("a");
		m.put("b");
		m.put("c");
		m.put("d");
		m.put("e");
		System.out.println("当前元素个数：" + m.size());
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				m.put("h");
				m.put("i");
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					Object t1 = m.take();
					Thread.sleep(1000);
					Object t2 = m.take();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"t2");
		
		t1.start();
		Thread.sleep(1000);
		t2.start();

	}
	
}
