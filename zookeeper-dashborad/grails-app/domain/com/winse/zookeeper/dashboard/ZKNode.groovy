package com.winse.zookeeper.dashboard

import org.apache.zookeeper.CreateMode;

class ZKNode {

	String path;
	String data;
	// List<ACL> acl = Ids.OPEN_ACL_UNSAFE;
	CreateMode createMode;

	static constraints = {
		path()
		data()
		createMode()
	}
	
}
