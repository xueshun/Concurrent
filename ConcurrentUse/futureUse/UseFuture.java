package futureUse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Future模式非常适合处理耗时很长的业务逻辑时进行使用，
 * 	可以有效的减小系统的响应时间，提高系统的吞吐量
 * @author Administrator
 *
 */
public class UseFuture implements Callable<String>{
	
	private String para;
	
	public UseFuture(String para) {
		this.para = para;
	}

	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(5000);
		String result = this.para+"处理完成";
		return result;
	}
	
	//主控制函数
	public static void main(String[] args) throws Exception {
		String queryStr = "query";
		String queryStr1 = "query1";

		//构造FutureTask，并且传入需要真正进行业务逻辑处理的类，该类一定是实现Callable接口的类
		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
		
		
		FutureTask<String> future2= new FutureTask<String>(new UseFuture(queryStr1));
		
		//创建一个固定线程的线程池切线程数为1
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		//这里提交任务future,则开启线程执行RealData的Call()方法执行
		/*submit 和executor的区别：
		 * 		1.submit可以传入实现Callable接口的实例对象
		 * 		2.submit方法有返回值
		 */
		
		Future f1 = executor.submit(future);
		Future f2 = executor.submit(future2);
		
		System.out.println("请求完毕");
		
		/*while(true){
			if(f1.get( )== null){
				System.out.println("当前任务执行完毕");
			}
		}*/
		
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理实际的业务逻辑...");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("数据： "+future.get());
		System.out.println("数据："+future2.get());
		
		executor.shutdown();
	}
	
}
