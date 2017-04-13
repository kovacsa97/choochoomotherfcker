package boardelements;

public class Rail extends BoardElement{

	public Rail(int length) {
		super(length);
		//System.out.println("New Rail created");

	}
	
	@Override
	public String toString(){
		return "Rail";
	}
}
