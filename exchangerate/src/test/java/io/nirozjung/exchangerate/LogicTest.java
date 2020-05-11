package io.nirozjung.exchangerate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.nirozjung.exchangerate.models.EnumTrend;


//you have a Hasmap list with key date and value
//
//you need to check if the values are
//ascending : if values are strictly ascedning
//descending: if values strictly descends
//constant: if all values are same 
//undefined: in other cases

public class LogicTest {

	private static List<Integer> numList = Arrays.asList(1, 2, 6, 6, 6, 6, 6, 6);

//	private static Map<String, Integer> map = Map.of("a",1,"b","c",2,"d",3,"e",4,"f",5,"g",6,"h",7,"i","j",8);

	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<>();

		Set<EnumTrend> trendPattern = new HashSet<EnumTrend>();
		boolean ascend = false;
		boolean descend = false;

		trendPattern = checkTrendpatterns(numList);

		boolean undefined = trendPattern.contains(EnumTrend.ASC) && trendPattern.contains(EnumTrend.DESC);
		boolean constant = !(trendPattern.contains(EnumTrend.ASC)) && !trendPattern.contains(EnumTrend.DESC);

		if (!undefined && !constant) {
			if (trendPattern.contains(EnumTrend.ASC)) {
				ascend = true;
			} else {
				descend = true;
			}
		}

		System.out.println(trendPattern);
		System.out.println(
				"undefined: " + undefined + ", constant: " + constant + ", ascend " + ascend + ", descend " + descend);

	}

	// check the values next to each
	private static Set<EnumTrend> checkTrendpatterns(List<Integer> numbers) {

		Set<EnumTrend> trends = new HashSet<EnumTrend>();
		Iterator<Integer> numIterator = numbers.iterator();

		Integer value = 0;

		while (numIterator.hasNext()) {
			Integer nextValue = (Integer) numIterator.next();

			// dont run in first iteration
			if (nextValue == numbers.get(0)) {
				value = nextValue;
				continue;
			}
			if (value < nextValue) {
				trends.add(EnumTrend.ASC);
			} else if (value > nextValue) {
				trends.add(EnumTrend.DESC);
			} else if (value == nextValue) {
				trends.add(EnumTrend.CONSTANT);
			}
			value = nextValue;
		}

		return trends;
	}



}
