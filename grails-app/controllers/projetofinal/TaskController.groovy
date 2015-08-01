package projetofinal

import grails.converters.JSON


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TaskController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Task.list(params), model:[taskInstanceCount: Task.count(), categories: Category.list()]
    }

	def list(){
		render text: [tasks:Task.list([sort: 'requiredBy', order:'desc'])] as JSON, contentType: 'application/json'
	}

	def get(){
		render text: [task:Task.findById(params.id)] as JSON, contentType: 'application/json'
	}
	
    def show(Task taskInstance) {
        respond taskInstance
    }

    def create() {
        respond new Task(params)
    }

    @Transactional
    def save(Task taskInstance) {
		alert("Entrou no salve")
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view:'create'
            return
        }

        taskInstance.save flush:true
		
		render text: [task:taskInstance] as JSON, contentType: 'application/json'
		
    }

    def edit(Task taskInstance) {
		
        respond taskInstance
    }

    @Transactional
    def update() {
		Task taskInstance = Task.findById(request.JSON.id)
		alert("Entrou no UPDATE")
		taskInstance.properties = request.JSON
        if (taskInstance == null) {
            notFound()
            return
        }

        if (taskInstance.hasErrors()) {
            respond taskInstance.errors, view:'edit'
            return
        }

  
		render text: [task:taskInstance] as JSON, contentType: 'application/json'
  
		      /* request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Task.label', default: 'Task'), taskInstance.id])
                redirect taskInstance
            }
            '*'{ respond taskInstance, [status: OK] }
        }*/
    }

    @Transactional
    def delete() {
		Task taskInstance = Task.findById(request.JSON.id)
		
        if (taskInstance == null) {
            notFound()
            return
        }

        taskInstance.delete flush:true
		render text: [task:taskInstance] as JSON, contentType: 'application/json'
		
        /*request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Task.label', default: 'Task'), taskInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }*/
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'taskInstance.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
