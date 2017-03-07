package master_Worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Worker implements Runnable{

	
	private ConcurrentLinkedDeque<Task> workQueue;
	
	private ConcurrentHashMap<String, Object> resultMap;
	
	public void setWorkQueue(ConcurrentLinkedDeque<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	
	
	@Override
	public void run() {
		while(true){
			Task input = this.workQueue.poll();
			if(input == null){
				break;
			}
			//真正的去做业务处理
			Object output =  MyWorker.handle(input);
			
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
	}
	
	public static Object handle(Task input){
		return null;
	}

	

	

}
