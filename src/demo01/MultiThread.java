package demo01;

/**
 * 有一种情况则是相同的锁，即在静态方法上加synchronized关键字
 * 	表示锁定.class类。类一级别的锁
 * @author Administrator
 *
 */
public class MultiThread {
	private static int num = 0;
	
	public static synchronized void printNum(String tag){
		try {
			if(tag.equals("a")){
				num = 100;
				System.out.println("tag a, set num over!");
				Thread.sleep(1000);
			}else{
				num = 200;
				System.out.println("tag b, set num over!");
			}
			System.out.println("tag "+ tag + ", num =" +num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final MultiThread m1 = new MultiThread();
		final MultiThread m2 = new MultiThread();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				m1.printNum("a");
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				m2.printNum("b");
			}
		});
		
		t1.start();
		t2.start();
	}
}
