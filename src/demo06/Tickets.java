package demo06;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Tickets {
	public static void main(String[] args) {
		
		//初始化火车表池并添加火车票：避免线程同步可采用Vector替代ArrayList HashTable替代HashMap
		
		final Vector<String> tickets = new Vector<String>();
		
		//hashMap 是线程不安全的，但是被Collections.synchronizedMap修饰之后就是线程安全
		Map<String, String>map = Collections.synchronizedMap(new HashMap<String,String>());
		
		for (int i = 0; i < 1000; i++) {
			tickets.add("火车票"+i);
		}
		
		//???
		/*for (Iterator iterator = tickets.iterator();iterator.hasNext();) {
			String string = (String) iterator.next();
			tickets.remove(20);
		}*/
		
		for (int i = 0; i < 10; i++) {
			new Thread("线程"+i){
				public void run(){
					while(true){
						if(tickets.isEmpty()) break;
						System.out.println(Thread.currentThread().getName() + "------"+tickets.remove(0));
					}
				}
			}.start();
		}
		
	}
}
