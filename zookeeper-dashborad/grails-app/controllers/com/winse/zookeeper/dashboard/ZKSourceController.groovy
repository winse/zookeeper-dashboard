package com.winse.zookeeper.dashboard


class ZKSourceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [ZKSourceInstanceList: ZKSource.list(params), ZKSourceInstanceTotal: ZKSource.count()]
    }

    def create() {
        [ZKSourceInstance: new ZKSource(params)]
    }

    def save() {
        def ZKSourceInstance = new ZKSource(params)
        if (!ZKSourceInstance.save(flush: true)) {
            render(view: "create", model: [ZKSourceInstance: ZKSourceInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), ZKSourceInstance.id])
        redirect(action: "show", id: ZKSourceInstance.id)
    }

    def show() {
        def ZKSourceInstance = ZKSource.get(params.id)
        if (!ZKSourceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), params.id])
            redirect(action: "list")
            return
        }

        [ZKSourceInstance: ZKSourceInstance]
    }

    def edit() {
        def ZKSourceInstance = ZKSource.get(params.id)
        if (!ZKSourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), params.id])
            redirect(action: "list")
            return
        }

        [ZKSourceInstance: ZKSourceInstance]
    }

    def update() {
        def ZKSourceInstance = ZKSource.get(params.id)
        if (!ZKSourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (ZKSourceInstance.version > version) {
                ZKSourceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ZKSource.label', default: 'ZKSource')] as Object[],
                          "Another user has updated this ZKSource while you were editing")
                render(view: "edit", model: [ZKSourceInstance: ZKSourceInstance])
                return
            }
        }

        ZKSourceInstance.properties = params

        if (!ZKSourceInstance.save(flush: true)) {
            render(view: "edit", model: [ZKSourceInstance: ZKSourceInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), ZKSourceInstance.id])
        redirect(action: "show", id: ZKSourceInstance.id)
    }

    def delete() {
        def ZKSourceInstance = ZKSource.get(params.id)
        if (!ZKSourceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), params.id])
            redirect(action: "list")
            return
        }

            ZKSourceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'ZKSource.label', default: 'ZKSource'), params.id])
            redirect(action: "list")
    }
	
//	def scaffold = ZKSource
	
	def connection(){
		def ZKSourceInstance = ZKSource.get(params.id)
		ZKClient.getInstance().start(ZKSourceInstance);
		redirect(controller:"ZKNode")
	}
}
