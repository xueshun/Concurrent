package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		
	}
}
