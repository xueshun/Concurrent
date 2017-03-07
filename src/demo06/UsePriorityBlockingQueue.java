package demo06;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Task> p =new PriorityBlockingQueue<Task>();
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("任务1");
		Task t2 = new Task();
		t2.setId(6);
		t2.setName("任务2");
		
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("任务3");
		
		p.add(t1);
		p.add(t2);
		p.add(t3);
		
		
		System.out.println("容器："+p);
		System.out.println(p.take().getId());
		System.out.println("容器："+p);
		/*for (Iterator iterator = p.iterator(); iterator.hasNext();) {
			Task task = (Task) iterator.next();
			System.out.println(task.getName());
		}*/
	}
}
