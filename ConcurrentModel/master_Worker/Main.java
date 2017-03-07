package master_Worker;

import java.util.Random;


public class Main {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		Master master = new Master(new Worker(), Runtime.getRuntime().availableProcessors());
		
		Random r = new Random();
		for (int i = 0; i <= 100; i++) {
			Task t = new Task();
			t.setId(i);
			t.setName("任务"+i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		
		master.execute();
		long startTime = System.currentTimeMillis();
		
		while(true){
			if(master.isComplete()){
				long endTime = System.currentTimeMillis()-startTime;
				int ret = master.getResult();
				System.out.println(ret);
				System.out.println("最终结果："+ret +"，执行时间"+endTime);
				break;
			}
		}
	}
}
