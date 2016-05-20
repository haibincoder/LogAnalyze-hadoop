package com.hadoop.fs;

import java.io.InputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileSystemCat {
	public static void main(String[] args) throws Exception{
		String uri = args[0];
		//获取Configuration对象
		Configuration conf = new Configuration();
		//获取FilsSystem对象
		FileSystem fs = FileSystem.get(URI.create(uri), conf);
		
		InputStream in = null;
		try{
			in = fs.open(new Path(uri));
			IOUtils.copyBytes(in, System.out, 4096);
		}finally{
			IOUtils.closeStream(in);
		}
	}

}





