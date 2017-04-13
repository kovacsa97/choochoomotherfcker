package update;

public class TestingTimer extends Timer {

	@Override
	public void start(){}
	
	@Override
	public void stop(){}
	
	public void step(int step){
		for(int i = 0; i < step; i++){
			int size = toUpdate.size();
			for (int j=0; j<size; j++){
				try {
					toUpdate.get(i).update();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}
	}
	
}
