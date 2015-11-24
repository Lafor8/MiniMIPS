package controller;

import model.ITypeInstruction;
import model.RTypeInstruction;

public class ALU {

	public long apply(long param1, long param2, long IR) {
		long answer = 0;
		int op;
		
		if(IR >> 26 != 0)
			op = (int) (IR >> 26);
		else
			op = (int) (IR << 26);
			
		
		switch(op){
		case RTypeInstruction.DADDU:
		case ITypeInstruction.DADDIU:
			answer = param1 + param2; break;
		case RTypeInstruction.MULS:
			answer = param1 * param2; break;
		case RTypeInstruction.OR:
			answer = param1 | param2; break;
		case RTypeInstruction.SLT:
			if(param1 < param2)
				answer = 1;
			else 
				answer = 0; break;
		case ITypeInstruction.ANDI:
			answer = param1 & param2; break;
			
		// TO ADD Floating Number Instructions
			
		}
		return answer;
	}

}
