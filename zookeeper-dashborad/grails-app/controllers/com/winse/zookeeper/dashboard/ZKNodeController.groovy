package com.winse.zookeeper.dashboard

import org.apache.zookeeper.KeeperException.NoAuthException;
import org.apache.zookeeper.data.Stat

class ZKNodeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
		def ZKSourceNode
		if(params && params?.path){
			ZKSourceNode = params.path;
		}else{
			ZKSourceNode = "/"
		}
		
		def ress = [];
		try{
			ress = ZNodeUtil.list(ZKSourceNode)
		}catch(NoAuthException e){
			flash.message = message(code: '[{0}] found {1}, but it secreted.', args: [
					message(code: 'ZKNode.label', default: 'ZKNode'),
					params.path
				])
		}
        [ZKNodeInstanceList: ress, ZKNodeInstanceTotal: ress.size(),ZKSourceNodePath:ZKSourceNode]
    }
	
	// 页面jsp中调用
	public final static String[] getParentAndName(String path){
		def result = [];

		int index = path.lastIndexOf("/");

		def pathParent
		if(index==0){
			pathParent = "/"
		}else{
			pathParent = path.substring(0, index)
		}
		result<<pathParent

		result << (index==0?"":"/ ") + path.substring(index+1)
		return result;
	}

    def create() {
        [ZKNodeInstance: new ZKNode(params)]
    }

    def save() {
        def ZKNodeInstance = new ZKNode(params)
        if (!ZNodeUtil.create(ZKNodeInstance)) {
            render(view: "create", model: [ZKNodeInstance: ZKNodeInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), ZKNodeInstance.path])
		redirect(action: "list", params:[path: ZNodeUtil.getZNodeParentPath(ZKNodeInstance.path)])
    }

	def show() {
		if (!params.path) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'ZKNode.label', default: 'ZKNode'),
				params.id
			])
			redirect(action: "list")
			return
		}
		def stat = new Stat();
		def ZKNodeInstance = ZNodeUtil.getZKNode(params.path, stat)
		[ZKNodeInstance:ZKNodeInstance, stat:ZNodeUtil.serilizeStat(stat)]
	}
	
    def edit() {
        def ZKNodeInstance = ZNodeUtil.getZKNode(params.path, null)
        if (!ZKNodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), params.path])
            redirect(action: "list")
            return
        }

        [ZKNodeInstance: ZKNodeInstance]
    }

	/**
	 * TODO
	 * setData...
	 */
    def update() {
        def ZKNodeInstance = ZNodeUtil.getZKNode(params.path, null)
        if (!ZKNodeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), params.path])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (ZKNodeInstance.version > version) {
                ZKNodeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ZKNode.label', default: 'ZKNode')] as Object[],
                          "Another user has updated this ZKNode while you were editing")
                render(view: "edit", model: [ZKNodeInstance: ZKNodeInstance])
                return
            }
        }

        ZKNodeInstance.properties = params

        if (!ZNodeUtil.updateData(ZKNodeInstance)) {
            render(view: "edit", model: [ZKNodeInstance: ZKNodeInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), ZKNodeInstance.path])
        redirect(action: "show", params:[path: ZKNodeInstance.path])
    }

    def delete() {
        def ZKNodeInstance = ZNodeUtil.getZKNode(params.path, null)
        if (!ZKNodeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), params.path])
            redirect(action: "list")
            return
        }

        ZNodeUtil.delete(ZKNodeInstance)
		flash.message = message(code: 'default.deleted.message', args: [message(code: 'ZKNode.label', default: 'ZKNode'), params.path])
        redirect(action: "list", params:[path: ZNodeUtil.getZNodeParentPath(ZKNodeInstance.path)])
    }
}
