package com.codeatomic.springbootquickstart;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig
{
	 @Autowired
	    private JobBuilderFactory jobs;
	 
	    @Autowired
	    private StepBuilderFactory steps;
	    
	    @Bean
	    public CustFlatFileItemReader itemReader()
	    {
	    	CustFlatFileItemReader ffib = new CustFlatFileItemReader();
	    	ffib.setLineMapper(new DefaultLineMapper<>());
	        LineMapper<StudentDTO> studentLineMapper = createStudentLineMapper();
	        ffib.setLineMapper(studentLineMapper);
	    	return ffib;
	    }
	     
	    @Bean
	    public Step stepOne(){
	        return steps.get("stepOne")
	                .tasklet(new MyTaskOne())
	                .build();
	    }
	     
	    @Bean
	    public Step stepTwo(){
	        return steps.get("stepTwo")
	                .tasklet(itemReader()) 
	                .build();
	    }  
	     
	    @Bean
	    public Job demoJob(){
	        return jobs.get("demoJob")
	                .incrementer(new RunIdIncrementer())
	                .start(stepOne())
	                .next(stepTwo())
	                .build();
	    }
	    
	    private LineMapper<StudentDTO> createStudentLineMapper() {
	        DefaultLineMapper<StudentDTO> studentLineMapper = new DefaultLineMapper<>();
	 
	        LineTokenizer studentLineTokenizer = createStudentLineTokenizer();
	        studentLineMapper.setLineTokenizer(studentLineTokenizer);
	 
	        FieldSetMapper<StudentDTO> studentInformationMapper = createStudentInformationMapper();
	        studentLineMapper.setFieldSetMapper(studentInformationMapper);
	 
	        return studentLineMapper;
	    }
	 
	    private LineTokenizer createStudentLineTokenizer() {
	        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
	        studentLineTokenizer.setDelimiter(";");
	        studentLineTokenizer.setNames(new String[]{"name", "age"});
	        return studentLineTokenizer;
	    }
	 
	    private FieldSetMapper<StudentDTO> createStudentInformationMapper() {
	        BeanWrapperFieldSetMapper<StudentDTO> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
	        studentInformationMapper.setTargetType(StudentDTO.class);
	        return studentInformationMapper;
	    }
}
