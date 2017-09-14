package com.tools;

import java.util.ArrayList;
import java.util.List;


public class ListUtil {
	
	public static<T> List<T> convertListForIndex(List<T> list, int start,int end) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		List<T> reSkList = new ArrayList<T>();
		for (int i = start; i < end; i++) {
			reSkList.add(list.get(i));
		}

		return reSkList;
	}
}
