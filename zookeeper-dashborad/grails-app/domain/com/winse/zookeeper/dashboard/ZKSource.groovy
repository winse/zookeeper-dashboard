package com.winse.zookeeper.dashboard

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Collections;

import org.apache.zookeeper.common.PathUtils;


class ZKSource {

	String servers = "localhost:2181";
	int timeOut = 30 * 1000;

	static constraints = {
	}

	boolean equals( param) {
		if(param.is(this)) return true;
		if(param.getClass() == this.getClass()){
			if(param.servers.equals(this.servers)) return true;
		}

		return false;
	}

}
