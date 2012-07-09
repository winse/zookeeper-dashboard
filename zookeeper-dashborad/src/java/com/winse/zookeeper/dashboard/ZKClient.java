package com.winse.zookeeper.dashboard;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKClient implements Watcher {

	private static ZKClient client = new ZKClient();

	public static ZKClient getInstance() {
		return client;
	}

	/* ------------------------------------------------------------------------- */
	
	private ZKSource conn = new ZKSource();
	private ZooKeeper zk;

	private ZKClient() {
	}

	/**
	 * 如果参数改变，关闭原有的zk， 重新建立一个zookeeper的实例。<br/>
	 * 否则什么都不做
	 */
	public ZKClient start(ZKSource conn) throws IOException, InterruptedException {
		if (!conn.equals(this.conn) || (zk != null && !zk.getState().isAlive())) {
			this.conn = conn;
			if (zk != null)
				zk.close();
			startZK();
		}

		return this;
	}

	private void startZK() throws IOException {
		zk = new ZooKeeper(conn.getServers(), conn.getTimeOut(), this);
	}

	public ZooKeeper getZooKeeper() throws IOException {
		if (zk == null) {
			startZK();
			// throw new org.apache.zookeeper.KeeperException.SystemErrorException();
		}
		return zk;
	}
	
	public ZKSource getCurrentSource(){
		return conn;
	}

	public void process(WatchedEvent event) {
		System.out.println("process --> " + event);
	}
	
	public static Object[] getAddressAndPort(ZKSource s){
		String host = s.getServers();
		int port = 2181;
		int pidx = host.lastIndexOf(':');
		if (pidx >= 0) {
			// otherwise : is at the end of the string, ignore
			if (pidx < host.length() - 1) {
				port = Integer.parseInt(host.substring(pidx + 1));
			}
			host = host.substring(0, pidx);
		}
		return new Object[]{host, port};
	}

}
