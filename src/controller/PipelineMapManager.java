package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.MIPSInstruction;

public class PipelineMapManager {

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
			
		pipelineMap.put(cycleNo, cycleMap);
	}
}
