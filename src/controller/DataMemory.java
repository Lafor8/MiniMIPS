package controller;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

import utilities.MiniMipsUtilities;

public class DataMemory {

	public HashMap<BigInteger, BigInteger> dataMemory;

	public DataMemory() {
		dataMemory = new HashMap<>();
	}

	public BigInteger getDataFromMemory(BigInteger index) {
		if (!dataMemory.containsKey(index))
			dataMemory.put(index, BigInteger.ZERO);

		return dataMemory.get(index);
	}

	public void setDataToMemory(BigInteger index, BigInteger value) {
		dataMemory.put(index, value);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Data Memory:\n");

		if (dataMemory.entrySet().size() == 0)
			sb.append("No Data Segments Affected\n");

		TreeMap<BigInteger, BigInteger> map = new TreeMap<>();
		map.putAll(dataMemory);

		for (Entry<BigInteger, BigInteger> dataEntry : map.entrySet()) {
			sb.append(MiniMipsUtilities.getPaddedHex(dataEntry.getKey()).substring(4));
			sb.append(" ");
			sb.append(MiniMipsUtilities.getPaddedHex(dataEntry.getValue()));
			sb.append("\n");
		}

		return sb.toString();
	}
}
