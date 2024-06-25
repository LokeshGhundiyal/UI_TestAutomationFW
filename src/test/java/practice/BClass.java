package practice;

public class BClass extends AClass {

	public void trialBClass() {
		System.out.println(a + 20);
	}
	
	public static void main(String[] args) {
		AClass ab = new AClass();
		ab.trial();
		System.out.println(ab.a + 20);
		BClass tb = new BClass();
		tb.trialBClass();

	}
}
