package com.codeatomic.springbootquickstart;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ByteArrayResource;

public class CustFlatFileItemReader extends FlatFileItemReader<StudentDTO> implements StepExecutionListener, Tasklet
{
	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		//open(arg0.getExecutionContext());
		System.out.println("afterStep CustFlatFileItemReader" );
		return ExitStatus.COMPLETED;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		
		String s = arg0.getJobExecution().getExecutionContext().getString("Test");
		System.out.println("beforeStep CustFlatFileItemReader " + s);
		setResource(new ByteArrayResource(s.getBytes()));
		try {
			doOpen();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		
		System.out.println("Read data " + read());
		return RepeatStatus.FINISHED;
	}
}
