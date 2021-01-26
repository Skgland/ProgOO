package programming.set7.statistics;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Bennet Blessmann
 *         Created on 10.12.2016.
 */
public class StatisticsCollector {

	private ArrayList<Double> l = new ArrayList<Double>();

	private int count = 0;
	private double sum = 0;
	private double max = Double.MIN_VALUE;
	private double min = Double.MAX_VALUE;

	public void addItem(double d){
		l.add(d);
		sum+=d;
		max = max>d?max:d;
		min = min <d? min :d;
		count++;
	}

	public void addItems(Double[] dd){
		Collections.addAll(l,dd);
		for(Double d:dd){
			sum+=d;
			max = max>d?max:d;
			min = min <d? min :d;
			count++;
		}
	}

	public int getCount(){
		return count;
	}

	public double getSum(){
		return sum;
	}

	public double getMaximum() {
		if(getCount()==0){
			return Double.NEGATIVE_INFINITY;
		}
		return max;
	}

	public double getMinimum() {
		if(getCount()==0){
			return Double.POSITIVE_INFINITY;
		}
		return min;
	}

	public double getAverage(){
		if(getCount()==0) {
			return 0;
		}
		return sum/getCount();
	}

	public double getStandardDeviation(){
		return Math.sqrt(getVariance());
	}

	private double getVariance() {
		if(getCount()==0){
			return 0;
		}
		double average = getAverage();
		double temp = 0;
		for(Double d:l){
			temp += Math.pow(average-d,2);
		}
		return temp/getCount();
	}
}
