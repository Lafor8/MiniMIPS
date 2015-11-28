package controller;

import java.math.BigInteger;
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

		String table[][] = new String[100][pipelineMap.size()];

		HashMap<BigInteger, Integer> tableAux = new HashMap<>();
		int runningVal = 0;

		for (Entry<Integer, HashMap<Integer, MIPSInstruction>> cycleMap : pipelineMap.entrySet()) {
			HashMap<Integer, MIPSInstruction> InstMap = cycleMap.getValue();

			for (Entry<Integer, MIPSInstruction> InstEntry : InstMap.entrySet()) {
				MIPSInstruction inst = InstEntry.getValue();

				int index;
				if (inst != null) {
					if (!tableAux.containsKey(inst.address))
						tableAux.put(inst.address, runningVal++);

					index = tableAux.get(inst.address);

					table[index][cycleMap.getKey()] = getStageName(InstEntry.getKey());
				}
			}
		}

		System.out.println("Table Size: " + runningVal + "x" + pipelineMap.size());

		for (int i = 0; i < runningVal; ++i) {
			sb.append("\t");
			for (int j = 0; j < pipelineMap.size(); ++j) {
				if (table[i][j] != null)
					sb.append(table[i][j]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String getStageName(int stageNo) {
		String str = "";

		switch (stageNo) {
		case PipelineMapManager.IF_STAGE:
			str = "IF";
			break;
		case PipelineMapManager.ID_STAGE:
			str = "ID";
			break;
		case PipelineMapManager.EX_STAGE:
			str = "EX";
			break;
		case PipelineMapManager.MEM_STAGE:
			str = "MEM";
			break;
		case PipelineMapManager.WB_STAGE:
			str = "WB";
			break;
		}
		return str;
	}
}
