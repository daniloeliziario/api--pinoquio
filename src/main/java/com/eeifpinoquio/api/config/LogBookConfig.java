package com.eeifpinoquio.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.DefaultHttpLogWriter;
import org.zalando.logbook.DefaultSink;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.json.JsonHttpLogFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class LogBookConfig {
	
	@Bean
	public Logbook logbook() {
	    return Logbook.builder()
	    		.sink(new DefaultSink(
	    				new JsonHttpLogFormatter(new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)),
	    		        new DefaultHttpLogWriter()
	    		        ))
	    		.build();
	}

}
