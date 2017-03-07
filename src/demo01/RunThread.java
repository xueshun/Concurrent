package demo01;

/**
 * volatile关键字
 * @author Administrator
 *
 */
public class RunThread extends Thread{
	private volatile boolean isRunning = true;
	
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法...");
		while(isRunning == true){
			
		}
		System.out.println("线程终止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(3000);
		rt.setRunning(false);
		System.out.println("isRunning的值已经被设置为false");
		Thread.sleep(1000);
		System.out.println(rt.isRunning);
	}
}
