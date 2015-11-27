package controller;

import java.math.BigInteger;
import java.util.HashMap;

public class DataMemory {
	
	HashMap<BigInteger,BigInteger> dataMemory;

	public BigInteger getDataFromMemory(BigInteger index){
		if(!dataMemory.containsKey(index))
			dataMemory.put(index, BigInteger.ZERO);
		
		return dataMemory.get(index);
	}

	public void setDataFromMemory(BigInteger index, BigInteger value){
		dataMemory.put(index, value);
	}

}
