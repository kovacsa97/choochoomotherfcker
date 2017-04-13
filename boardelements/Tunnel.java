package boardelements;

public class Tunnel extends BoardElement {

	public Tunnel(int length) {
		super(length);
		System.out.println("New Tunnel created with parameters of type int");

	}
	
	public void destroy(){
		if (next!=null) next.lock();
		if (prev!=null) prev.lock();
	}
	
	@Override
	public void setEnds(BoardElement p, BoardElement n){
		System.out.println("setEnds was called inside class Tunnel with parameters of type " + p.toString() + ", "+ n.toString());
		destroy();
		next = n;
		n.unlock();
		prev = p;
		p.unlock();
		System.out.println("setEnds returned");
	}

}
