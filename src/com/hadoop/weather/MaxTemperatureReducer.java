package com.hadoop.weather;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public abstract class MaxTemperatureReducer extends MapReduceBase 
	implements Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterator<IntWritable> values,
			OutputCollector<Text, IntWritable> ouput, Reporter reporter) 
			throws IOException{
		
		int maxValue =  Integer.MIN_VALUE;
		while (values.hasNext()){
			maxValue = Math.max(maxValue, values.next().get() );
		}
		ouput.collect(key, new IntWritable(maxValue) );
	}	
	
	

}










