package com.jdc.weekend;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jdc.weekend.model.BaseRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class EmployeeApiJpaConfig {

}
