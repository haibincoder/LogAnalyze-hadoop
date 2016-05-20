package com.hadoop.weather;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MaxTemperature {
	public static void main(String[] args) throws IOException{
		if (args.length != 2){
			System.err.println("Usage: MaxTemperature<input path> <output path>");
			System.exit(-1);
		}		
		
		//String input = "hdfs://172.22.120.191:9000/user/hadoop/temperature/";
		//String output = "hdfs://172.22.120.191:9000/user/hadoop/output_weather";
		
		//����JobConf
		JobConf conf = new JobConf(MaxTemperature.class);
		conf.setJobName("Max temperature");
		
		//�������·��
		//FileInputFormat.addInputPath(conf, new Path( args[0]) );
		//FileOutputFormat.setOutputPath(conf, new Path( args[1]) );
		FileInputFormat.addInputPath(conf, new Path( args[0] ));
		FileOutputFormat.setOutputPath(conf, new Path( args[1] ));
		
		//ָ��Mapper��Reducer
		conf.setMapperClass(MaxTemperatureMapper.class);
		conf.setJarByClass(MaxTemperature.class);
		conf.setReducerClass(MaxTemperatureReducer.class);
		
		//map������������ͣ�Ĭ��ΪTextInputFormat
		conf.setOutputKeyClass(Text.class);
		//reduce������������ͣ�Ĭ��ΪTextInputFormat
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
		
	}

}










