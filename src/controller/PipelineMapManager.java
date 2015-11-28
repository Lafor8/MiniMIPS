package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.MIPSInstruction;

public class PipelineMapManager {

	public static final int IF_STAGE = 0;
	public static final int ID_STAGE = 1;
	public static final int EX_STAGE = 2;
	public static final int MEM_STAGE = 3;
	public static final int WB_STAGE = 4;

	public HashMap<Integer, HashMap<Integer, MIPSInstruction>> pipelineMap;
	public static PipelineMapManager pipelineMapManager;

	public static PipelineMapManager getInstance() {
		if (pipelineMapManager == null)
			pipelineMapManager = new PipelineMapManager();
		return pipelineMapManager;
	}

	private PipelineMapManager() {
		pipelineMap = new HashMap<>();
	}

	public void addEntry(int cycleNo, int step, MIPSInstruction inst) {
		HashMap<Integer, MIPSInstruction> cycleMap;

		if (pipelineMap.containsKey(cycleNo))
			cycleMap = pipelineMap.get(cycleNo);
		else
			cycleMap = new HashMap<>();

		cycleMap.put(step, inst);

		pipelineMap.put(cycleNo, cycleMap);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < pipelineMap.size(); ++i) {
			sb.append("\t" + i);
		}
		sb.append("\n");

		
		
		for (Entry<Integer, HashMap<Integer, MIPSInstruction>> cycleMap : pipelineMap.entrySet()) {
			sb.append(cycleMap.getKey());
			sb.append("\t" + cycleMap.getValue());

			sb.append("\n");
		}

		return sb.toString();
	}
}
