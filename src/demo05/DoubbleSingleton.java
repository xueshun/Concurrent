package demo05;

public class DoubbleSingleton {
	private static DoubbleSingleton ds;
	
	public static DoubbleSingleton getDs(){
		if(ds == null){
			try {
				//模式初始化对象准备时间
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			synchronized (DoubbleSingleton.class) {
				if(ds == null){ //？？？？
					ds = new DoubbleSingleton();
				}
			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(DoubbleSingleton.getDs().hashCode());
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(DoubbleSingleton.getDs().hashCode());
			}
		},"t2");
		
		Thread t3 = new Thread(new  Runnable() {
			public void run() {
				System.out.println(DoubbleSingleton.getDs().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
}
