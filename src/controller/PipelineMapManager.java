package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.MIPSInstruction;

public class PipelineMapManager {

	public static final int IF_STAGE = 0;
	public static final int ID_STAGE = 1;
	public static final int EX_STAGE = 2;
	public static final int MEM_STAGE = 3;
	public static final int WB_STAGE = 4;
	
	public HashMap<Integer,HashMap<Integer,MIPSInstruction>> pipelineMap;
	public static PipelineMapManager pipelineMapManager;
	
	public static PipelineMapManager getInstance(){
		if(pipelineMapManager == null)
			pipelineMapManager = new PipelineMapManager();
		return pipelineMapManager;
	}
	
	private PipelineMapManager(){}
	
	public void addEntry(int cycleNo, int step, MIPSInstruction inst){
		HashMap<Integer,MIPSInstruction> cycleMap;
		
		if(pipelineMap.containsKey(cycleNo))
			cycleMap = pipelineMap.get(cycleNo);
		else
			cycleMap = new HashMap<>();
		
		cycleMap.put(step, inst);
			
		pipelineMap.put(cycleNo, cycleMap);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
}
