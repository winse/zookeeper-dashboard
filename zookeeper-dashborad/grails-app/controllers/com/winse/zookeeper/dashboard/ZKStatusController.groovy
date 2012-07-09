package com.winse.zookeeper.dashboard

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket

class ZKStatusController {

	static allowedMethods = []
	
//stmk,
	static final String[] CMDS = "wchc,cons,crst,gtmk,srvr,conf,wchp,wchs,srst,ruok,stat,envi,dump".split(",");

	def index() {
		redirect(action: "show", params: params)
	}

	def show() {
		Object[] hostAndPort = com.winse.zookeeper.dashboard.ZKClient.getAddressAndPort(ZKClient.getInstance().getCurrentSource())
		String cmd = params.id;
		cmd = (cmd==null?"ruok":cmd);
		String stat = send4LetterWord(hostAndPort[0], hostAndPort[1], cmd)
		if (stat==null || stat.isEmpty()) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'ZKStatus.label', default: 'ZKStatus'),
				params.instance
			])
			return
		}

		[ZKStatusInstance: new ZKStatus(stat), cmd:cmd]
	}

	/*
	 * copy for Web
	 */
	public static String send4LetterWord(String host, int port, String cmd)
	throws IOException {
		Socket sock = new Socket(host, port);
		BufferedReader reader = null;
		try {
			OutputStream outstream = sock.getOutputStream();
			outstream.write(cmd.getBytes());
			outstream.flush();
			// this replicates NC - close the output stream before reading
			sock.shutdownOutput();

			reader =
					new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line + "<br/>");
			}
			return sb.toString();
		} finally {
			sock.close();
			if (reader != null) {
				reader.close();
			}
		}
	}
}
