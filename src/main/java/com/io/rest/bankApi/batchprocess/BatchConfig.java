//package com.io.rest.bankApi.batchprocess;
//
//import com.io.rest.bankApi.dto.CustomerBankingInput;
//import com.io.rest.bankApi.entity.Customer;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableBatchProcessing
//public class BatchConfig {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//    private final String [] FIELD_NAMES = new String[]
//            {
//                  "CustomerId","Surname", "CreditScore",
//                   "Geography", "Gender", "Age",
//                   "Tenure",   "Balance", "HasCrCard",
//                  "Salary", "Satisfaction Score", "Card Type","Points"
//            }
//
//    @Bean
//    public FlatFileItemReader<CustomerBankingInput> reader() {
//        return new FlatFileItemReaderBuilder<CustomerBankingInput>()
//                .name("BankingDataProcessor")
//                .resource(new ClassPathResource("Customer-Churn-Records.csv"))
//                .delimited()
//                .names(FIELD_NAMES)
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<CustomerBankingInput>() {{
//                    setTargetType(CustomerBankingInput.class);
//                }})
//                .build();
//    }
//
//    @Bean
//    public BankingDataProcessor processor() {
//        return new BankingDataProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Customer>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO customer (customer_id,surname," +
//                        "credit_score,geography,gender,age," +
//                        "tenure,balance,has_cr_card,salary," +
//                        "satisfaction_score,card_type,points)" +
//                        " VALUES (:customerId,:surname,:creditScore,:geography,:gender," +
//                        ":age,:tenure,:balance,:hasCrCard,:salary,:satisfactionScore,:cardType,:points)")
//                .dataSource(dataSource)
//                .build();
//    }
//    @Bean
//    public Job importUserJob(JobRepository jobRepository,
//                             JobCompletionListener listener, Step step1) {
//      return  jobBuilderFactory.get("importUserJob")
//        .incrementer(new RunIdIncrementer())
//        .listener(listener)
//        .flow(step1)
//        .end()
//        .build();
//    }
//
//
//    @Bean
//    public Step step1(JdbcBatchItemWriter<Customer> writer) {
//      return stepBuilderFactory.get("step1").<CustomerBankingInput, Customer>chunk(10)
//              .reader(reader()).processor(processor()).writer(writer).build();
//
//
//
//
//
//    }
//
//
//
//
//
//
//
//
//
//
//}
