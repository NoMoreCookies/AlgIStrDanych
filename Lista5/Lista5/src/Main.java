
import java.util.Comparator;

import core.AbstractSortingAlgorithm;
import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) {
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		
		Generator<MarkedValue<Integer>> generator1 = new MarkingGenerator<Integer>(new RandomIntegerArrayGenerator(100));
		Generator<MarkedValue<Integer>> generator2 = new MarkingGenerator<Integer>(new LinkedListGenerator<>(new RandomIntegerArrayGenerator(100)));

		AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm1 = new MergeSort<MarkedValue<Integer>>(markedComparator);
		AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm2 = new MergeSortList<MarkedValue<Integer>>(markedComparator);
		AbstractSortingAlgorithm<MarkedValue<Integer>> algorithm3 = new QuickSort<MarkedValue<Integer>>(markedComparator,true);

		testing.results.Result result1 = Tester.runNTimes(algorithm1, generator1, 1000, 50);
		testing.results.Result result2 = Tester.runNTimes(algorithm2, generator2, 1000, 50);
		testing.results.Result result3 = Tester.runNTimes(algorithm3, generator1, 1000, 50);

		printStatistic("time [ms]", result1.averageTimeInMilliseconds(), result1.timeStandardDeviation());
		printStatistic("comparisons", result1.averageComparisons(), result1.comparisonsStandardDeviation());


		printStatistic("time [ms]", result2.averageTimeInMilliseconds(), result2.timeStandardDeviation());
		printStatistic("comparisons", result2.averageComparisons(), result2.comparisonsStandardDeviation());

		printStatistic("time [ms]", result3.averageTimeInMilliseconds(), result3.timeStandardDeviation());
		printStatistic("comparisons", result3.averageComparisons(), result3.comparisonsStandardDeviation());
	}
	
	private static void printStatistic(String label, double average, double stdDev) {
		System.out.println(label + ": " + double2String(average) + " +- " + double2String(stdDev));
	}
	
	private static String double2String(double value) {
		return String.format("%.12f", value);
	}
}
