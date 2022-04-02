package com.rouxy.phantom.task;

import java.util.List;

import com.rouxy.phantom.FakePlayerTaskManager;

import net.sf.l2j.commons.concurrent.ThreadPool;

/**
 * @author Rouxy
 *
 */
public class AITaskRunner implements Runnable
{	
	@Override
	public void run()
	{		
		FakePlayerTaskManager.INSTANCE.adjustTaskSize();
		List<AITask> aiTasks = FakePlayerTaskManager.INSTANCE.getAITasks();		
		aiTasks.forEach(aiTask -> ThreadPool.execute(aiTask));
	}	
}