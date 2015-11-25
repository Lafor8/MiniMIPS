package controller;

import model.ITypeInstruction;
import model.RTypeInstruction;

public class ALU {

	public long apply(long param1, long param2, String IR) {
		long answer = 0;
		int op;
		
		if(IR.substring(0, 6).equals("000000"))
			op = Integer.parseInt(IR.substring(26, 32), 2);
		else
			op = Integer.parseInt(IR.substring(0, 6), 2);
		// TO ADD op for Floating Number Instructions
		
		switch(op){
		case RTypeInstruction.DADDU:
		case ITypeInstruction.DADDIU:
			answer = param1 + param2; break;
		case RTypeInstruction.DMULT:
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
