package com.winse.zookeeper.dashboard

import org.apache.zookeeper.data.Stat

class ZKStatus/* extends Stat*/{

	String stat;

	public ZKStatus(){

	}

	public ZKStatus(String stat){
		this.stat = stat
	}

	static constraints = {
	}
}
