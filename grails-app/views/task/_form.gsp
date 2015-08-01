<%@ page import="projetofinal.Task" %>



<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'task', 'error')} ">
	<label for="task">
		<g:message code="task.task.label" default="Task" />
		
	</label>
	<g:textField name="task" value="${taskInstance?.task}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'completed', 'error')} ">
	<label for="completed">
		<g:message code="task.completed.label" default="Completed" />
		
	</label>
	<g:checkBox name="completed" value="${taskInstance?.completed}" />
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="task.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	
	<g:select id="category" name="category.id" noSelection="${['':'']}" from="${categories}" optionKey="id" required="" />
	
</div>

<div class="fieldcontain ${hasErrors(bean: taskInstance, field: 'requiredBy', 'error')} required">
	<label for="requiredBy">
		<g:message code="task.requiredBy.label" default="Required By" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="requiredBy" precision="day"  value="${taskInstance?.requiredBy}"  />
</div>

