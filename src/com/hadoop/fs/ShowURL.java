package com.hadoop.fs;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

public class ShowURL {
	static final String PATH = "hdfs://172.22.120.191:9000/user/hadoop/eclipse/access_2013_05_31.log";
	
	public static void main(String[] args) throws Exception{
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory() );
		final URL url = new URL(PATH);
		final InputStream in = url.openStream();
		IOUtils.copyBytes(in, System.out, 1024, true);
	}
}
