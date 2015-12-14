package org.camunda.bpm.getstarted.pizza;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;

@Named
@ConversationScoped
public class PersistProcessPM implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessService service;
	
	public void persistProcess(DelegateExecution delegateExecution){
		
	    Map<String, Object> variables = delegateExecution.getVariables();

		
		service.persistNewProcess(
				(String)variables.get("task"), 
				(String)variables.get("description")
		);
		
	}
	
	public void completeProcess(Long id){
		service.completeProcess(id);
	}
	
	public void deleteProcess(Long id){
		service.deleteProcess(id);
	}
	
	public List<ProcessEntity> getProcessList() {
		
		return service.getUncompletedProcessList();
	}
	
	public List<ProcessEntity> getCompletedList() {
		
		return service.getCompletedProcessList();
	}
	
}
