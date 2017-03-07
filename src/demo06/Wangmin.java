package demo06;

import java.security.GeneralSecurityException;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Wangmin implements Delayed{
	
	private String name;
	private String id; //身份证
	private long endTime; //截止时间
	private TimeUnit timeUnit = TimeUnit.SECONDS;//定义时间工具类

	public Wangmin(String name, String id, long endTime) {
		this.name = name;
		this.id = id;
		this.endTime = endTime;
	}
	
	public String getName(){
		return this.name;
	}
	public String getId(){
		return this.id;
	}
	

	@Override
	public long getDelay(TimeUnit unit) {
		return endTime - System.currentTimeMillis();
	}

	@Override
	public int compareTo(Delayed delayed) {
		Wangmin w = (Wangmin) delayed;
		
		return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1:0;
	}

	
	
	
}
