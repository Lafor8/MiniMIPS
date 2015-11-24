package controller;

public class AdderALU extends ALU {

	public long add(long pc, int i) {
		// TODO Auto-generated method stub
		
		long newPC = pc + i;
		return newPC;
	}

}
