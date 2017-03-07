package demo06;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseConcurrentMap {
	
	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String,Object>();
		chm.put("k1","v1");
		chm.put("k2","v2");
		chm.put("k3","v3");
		
		chm.putIfAbsent("k4", "vvvv"); //putIfAbsent map的键值是否存在，存在就不再添加，反则添加
		
		for (Map.Entry<String, Object> me: chm.entrySet()) {
			System.out.println("key:"+me.getKey()+",value:"+me.getValue());
		}
	}
}
