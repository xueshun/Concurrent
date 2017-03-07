package demo01;

/**
 * 脏读
 * @author Administrator
 *  在对一个对象的方法加锁的时候，需要考虑业务的整体性，即为setValue/getValue方法同时加锁synchronized同步关键字
 *  保证业务(service)的原子性，不然会出现业务错误
 */
public class DirtyRead {
	
	private String usernamer = "bjsxt";
	private String password = "123";
	
	public synchronized void setValue(String username,String password){
		this.usernamer = username;
		
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.password = password;
		
		System.out.println("setValue最终结果： username = "+username+",password" +password);
	}
	
	public synchronized void getValue(){
		System.out.println("getValue方法得到： username = "+this.usernamer +", password = " + this.password);
	}
	
	public static void main(String[] args) throws Exception {
		final DirtyRead dr = new DirtyRead();
		Thread t1 =new Thread(new Runnable() {
			
			@Override
			public void run() {
				dr.setValue("z3", "456");
			}
		});
		t1.start();
		Thread.sleep(1000);
		dr.getValue();
	}
}
