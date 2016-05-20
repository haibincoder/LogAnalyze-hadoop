package com.hadoop.weather;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MaxTemperatureWithCombiner {
	public static void main(String[] args) throws IOException{
		if (args.length != 2){
			System.err.println("Usage:MaxTemperatureWithCombiner <input path> " + "<output path>");
			System.exit(-1);
		}
		//����JobConf
		JobConf conf = new JobConf(MaxTemperature.class);
		conf.setJobName("Max temperature");
		
		//�������·��
		FileInputFormat.addInputPath(conf, new Path( args[0]) );
		FileOutputFormat.setOutputPath(conf, new Path( args[1]) );
		
		//ָ��Mapper��Reducer
		conf.setMapperClass(MaxTemperatureMapper.class);
		conf.setCombinerClass(MaxTemperatureReducer.class);
		conf.setReducerClass(MaxTemperatureReducer.class);
		
		//map������������ͣ�Ĭ��ΪTextInputFormat
		conf.setOutputKeyClass(Text.class);
		//reduce������������ͣ�Ĭ��ΪTextInputFormat
		conf.setOutputValueClass(IntWritable.class);
		
		JobClient.runJob(conf);
	}

}
