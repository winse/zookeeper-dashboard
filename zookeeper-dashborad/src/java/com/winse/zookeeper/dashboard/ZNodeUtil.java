package com.winse.zookeeper.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NoAuthException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZNodeUtil {
	
	public static boolean delete(ZKNode node) throws IOException, KeeperException, InterruptedException{
		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		zk.delete(node.getPath(), -1);
		return true;
	}
	
	public static String create(ZKNode node) throws IOException, KeeperException, InterruptedException{
		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		return zk.create(node.getPath(), node.getData().getBytes(), Ids.OPEN_ACL_UNSAFE, node.getCreateMode());
	}
	
	public static Stat updateData(ZKNode node) throws IOException, KeeperException, InterruptedException{
		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		return zk.setData(node.getPath(), node.getData().getBytes(), -1);
	}
	
	public static Stat exits(ZKNode node) throws IOException, KeeperException, InterruptedException{
		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		return zk.exists(node.getPath(), false);
	}
	
	public static ZKNode getZKNode(String path, Stat stat) throws IOException, KeeperException, InterruptedException {
		ZKNode result = new ZKNode();
		result.setPath(path);

		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		try {
			byte[] datas = zk.getData(path, false, stat);
			result.setData(datas == null ? "" : new String(datas));
		} catch (NoAuthException e) {
			result.setData("...SECRETED...");
		}

		return result;
	}

	/**
	 * 显示根路径下的子节点
	 */
	public static List<ZKNode> list() throws IOException, KeeperException, InterruptedException {
		return list(null);
	}

	public static List<ZKNode> list(String parentPath) throws IOException, KeeperException, InterruptedException {
		if (parentPath == null || parentPath.isEmpty() || parentPath.equals("/")) {
			parentPath = "/";
		}

		List<ZKNode> result = new ArrayList<ZKNode>();
		
		ZooKeeper zk = ZKClient.getInstance().getZooKeeper();
		List<String> children;
		children = zk.getChildren(parentPath, true);
//		{// add myself
//			ZKNode pnode = new ZKNode();
//			pnode.setPath(parentPath);
//
//			pnode.setData(new String(zk.getData(parentPath, false, null)));
//
//			result.add(pnode);
//		}
		for (String child : children) {
			String childPath = parentPath == "/" ? parentPath + child : parentPath + "/" + child;
			ZKNode node = new ZKNode();
			node.setPath(childPath);
			
			try {
				Stat stat = new Stat();
				byte[] data = zk.getData(childPath, false, stat);
				if (data == null) {
					node.setData("null");
				} else {
					node.setData(new String(data));
				}
				node.setCreateMode(stat.getEphemeralOwner() == 0 ? CreateMode.PERSISTENT : CreateMode.EPHEMERAL);
			} catch (NoAuthException e) {
				node.setData("...SECRETED...");
			}

			result.add(node);
		}

		return result;
	}
	
	public static String getZNodeParentPath(String path) {
		int index = path.lastIndexOf("/");

		if (index == 0) {
			return "/";
		} else {
			return path.substring(0, index);
		}
	}
	
	//org.apache.zookeeper.ZooKeeperMain.printStat(Stat)
	public static String serilizeStat(Stat stat) {
		StringBuilder res = new StringBuilder();
		res.append("cZxid = 0x" + Long.toHexString(stat.getCzxid()));
		res.append("<br>");
		res.append("ctime = " + new Date(stat.getCtime()).toString());
		res.append("<br>");
		res.append("mZxid = 0x" + Long.toHexString(stat.getMzxid()));
		res.append("<br>");
		res.append("mtime = " + new Date(stat.getMtime()).toString());
		res.append("<br>");
		res.append("pZxid = 0x" + Long.toHexString(stat.getPzxid()));
		res.append("<br>");
		res.append("cversion = " + stat.getCversion());
		res.append("<br>");
		res.append("dataVersion = " + stat.getVersion());
		res.append("<br>");
		res.append("aclVersion = " + stat.getAversion());
		res.append("<br>");
		// ephemeralOwner 客户端zookeeper.ClientCnxn#sessionId
		res.append("* ephemeralOwner = 0x" + Long.toHexString(stat.getEphemeralOwner()));
		res.append("<br>");
		res.append("dataLength = " + stat.getDataLength());
		res.append("<br>");
		res.append("numChildren = " + stat.getNumChildren());

		return res.toString();
	}
}
