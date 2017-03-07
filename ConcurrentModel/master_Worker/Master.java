package master_Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {
	//1.应该有承装任务的集合
	private ConcurrentLinkedDeque<Task> workQueue = new ConcurrentLinkedDeque<Task>();
	
	//2使用普通的hashmap去承装worker对象
	private HashMap<String ,Thread> workers = new HashMap<String,Thread>();
	
	//3.使用一个容器承装每一个worker并非执行任务的结果集
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<String, Object>();
	
	//4.构造方法
	public Master(Worker worker,int workerCount){
		//每一个worker都需要master的引用 
		//workQueue 任务的领取
		//resultMap 任务的提交
		worker.setWorkQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		
		for (int i = 0; i < workerCount; i++) {
			//key表示每个worker的名字，value表示线程执行对象
			workers.put("子节点"+Integer.toString(i), new Thread(worker));
		}
	}
	
	//5.提交方法
	public void submit(Task task){
		this.workQueue.add(task);
	}
	
	//6.需要一个执行方法，(启动应用程序，让所有的worker工作)
	public void execute(){
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			me.getValue().start();
		}
	}
	//7.判断线程是否执行完毕
	public boolean isComplete() {
		for (Map.Entry<String, Thread> me : workers.entrySet()) {
			if(me.getValue().getState() != Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	
	//8.返回结果集数据
	public int getResult() {
		int ret = 0;
		for (Map.Entry<String, Object> me : resultMap.entrySet()) {
			//汇总
			ret += (Integer)me.getValue();
		}
		return ret;
	}
	
	
	
}
