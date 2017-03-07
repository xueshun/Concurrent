package future;

public class FutureData implements Data{
	
	private RealData realData;
	
	private boolean isReady = false;
	
	public synchronized void setRealData(RealData realData){
		//如果已经加载完毕，就直接返回
		if(isReady){
			return;
		}
		
		//如果没有装载，进行装载真实对象
		this.realData = realData;
		isReady = true;
		//进行通知
		notify();
	}
	
	@Override
	public synchronized String getRequest() {
		// 如果没装载好，程序就一直处于阻塞状态
		while(!isReady){
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.realData.getRequest();
	}

}
