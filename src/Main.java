import java.math.BigInteger;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.InstructionIdentifier;

import model.MIPSInstruction;
import model.RTypeInstruction;

public class Main {

	public static void main(String[] args) {
	
	}
}

//DADDU R10, R1, R2
//DMULT R1 , R2
//L1:OR R11, R1,R2
//SLT R13, R1, R2
//
////BEQ R13, R0, L2
//LW R14, 0000(R1)
//LWU R15, 0008(R2)
//SW R1, 0010(R3)
//DSLL R16, R1, 2
//ANDI R17, R1, 12
//DADDIU R18, R1, 4
////BEQ R13, R0, L1
//
////J L2
//
//L.S F10, 0018(R4)
//S.S F1, 0020 (R5)
//L2:ADD.S F11,F1,F2
//MUL.S F12, F1,F2