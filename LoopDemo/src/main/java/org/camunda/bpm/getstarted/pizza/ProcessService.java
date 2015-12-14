package org.camunda.bpm.getstarted.pizza;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProcessService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void persistNewProcess(String task, String description) {
		ProcessEntity pe = new ProcessEntity();
		pe.setTask(task);
		pe.setDescription(description);
		pe.setDone(false);
		em.persist(pe);
	}
	
	public void completeProcess(Long id) {
		ProcessEntity old_task = em.find(ProcessEntity.class, id);
		em.remove(old_task);
		old_task.setDone(true);
		em.persist(old_task);
	}
	
	public void deleteProcess(Long id){
		ProcessEntity old_task = em.find(ProcessEntity.class, id);
		em.remove(old_task);
	}
	

	@SuppressWarnings("unchecked")
	public List<ProcessEntity> getCompletedProcessList() {
		Query q = em.createQuery("SELECT p FROM ProcessEntity p WHERE done = TRUE");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ProcessEntity> getUncompletedProcessList() {
		Query q = em.createQuery("SELECT p FROM ProcessEntity p WHERE done = FALSE");
		return q.getResultList();
	}
}
