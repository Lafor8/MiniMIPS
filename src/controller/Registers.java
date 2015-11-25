package controller;

import java.util.ArrayList;
import java.util.List;

public class Registers {
	
	List IntegerRegister = new ArrayList<>(31);
	List<?> FloatingRegister = new ArrayList<>(11); // do we use all 31 registers?
	long HI;
	long LOW;
	
	public void Initialize(){
		long zero = 0000000000000001;
		System.out.println(IntegerRegister.size());
		int i = 0;
		
		do{
			IntegerRegister.add(i, zero);
			System.out.println(IntegerRegister.get(i));
			i++;
		}while (i < 31);
	}

	
	
}
