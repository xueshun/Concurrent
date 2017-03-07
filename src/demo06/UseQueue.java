package demo06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class UseQueue {
	public static void main(String[] args) throws Exception {
		//高性能无阻塞无边界队列ConcurrentLinkedQueue
		/*ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		
		System.out.println(q.poll()); //从头部取出，并删除
		System.out.println(q.size());
		System.out.println(q.peek()); /从头部取出，不删除
		System.out.println(q.size());*/
		
		/*ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.put("d");
		array.put("e");
		System.out.println(array.offer("f", 2, TimeUnit.SECONDS));*/
		
		/*//阻塞队列
		LinkedBlockingQueue<String> l = new LinkedBlockingQueue<String>();
		l.offer("1");
		l.offer("2");
		l.offer("3");
		l.offer("4");
		l.offer("5");
		l.offer("6");
		System.out.println(l.size());
		List<String> list = new ArrayList<String>();
		System.out.println(l.drainTo(list,3));//从l队列中取出3个放到list集合中
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string);
		}*/
		
		
		
		SynchronousQueue<String> q =new SynchronousQueue<String>();
		q.add("ddd");//java.lang.IllegalStateException: Queue full
	}
}	
