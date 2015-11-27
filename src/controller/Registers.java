package controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Registers {
	
	List<BigInteger> IntegerRegister = new ArrayList<>(32);
	List<BigInteger> FloatingRegister = new ArrayList<>(32);
	BigInteger HI;
	BigInteger LOW;
	
	public void Initialize(){
		int i = 0;
		
		do{
			IntegerRegister.add(i, BigInteger.ZERO);
			FloatingRegister.add(i, BigInteger.ZERO);
			i++;
		}while (i < 32);
		
		HI = BigInteger.ZERO;
		LOW = BigInteger.ZERO;
	}
	
	public BigInteger getR(BigInteger index){
		return IntegerRegister.get(Integer.parseInt(index.toString()));
	}
	
	public BigInteger getF(BigInteger index){
		return FloatingRegister.get(Integer.parseInt(index.toString()));
	}
	
	public void setR(int index, BigInteger value){
		IntegerRegister.set(index, value);
	}
	
	public void setF(int index, BigInteger value){
		FloatingRegister.set(index, value);
	}

	public BigInteger getHI() {
		return HI;
	}

	public void setHI(BigInteger hI) {
		HI = hI;
	}

	public BigInteger getLOW() {
		return LOW;
	}

	public void setLOW(BigInteger lOW) {
		LOW = lOW;
	}
	

}
