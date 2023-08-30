package Negocio.Reserva;

public class Pair<A, B> {
	A first;
	B second;
	
	public Pair(){}
	
	public Pair(A f, B s){
		first = f;
		second = s;
	}
	
	public A getFirst() {
		return first;
	}
	public void setFirst(A first) {
		this.first = first;
	}
	public B getSecond() {
		return second;
	}
	public void setSecond(B second) {
		this.second = second;
	}
	
}
