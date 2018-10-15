package com.codeatomic.springbootquickstart;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class MyTaskOne implements Tasklet ,StepExecutionListener
{
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception 
	{
        System.out.println("MyTaskOne done..");
        return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		System.out.println("afterStep MyTaskOne" );
		return ExitStatus.COMPLETED;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		System.out.println("afterStep MyTaskOne" );
		arg0.getJobExecution().getExecutionContext().put("Test", "Kiran;13"); 
	}

}
