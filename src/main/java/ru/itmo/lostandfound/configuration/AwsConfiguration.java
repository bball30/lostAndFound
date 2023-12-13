package ru.itmo.lostandfound.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация подключения к Amazon Web Services (AWS)
 *
 * @author Andryss
 */
@Configuration
public class AwsConfiguration {

    /**
     * Ключ доступа к AWS
     */
    @Value("${aws.access-key}")
    private String awsAccessKey;

    /**
     * Секретный ключ доступа к AWS
     */
    @Value("${aws.secret-key}")
    private String awsSecretKey;

    /**
     * Локация S3 хранилища, к которому будет производиться подключение
     */
    @Value("${aws.service-endpoint}")
    private String awsServiceEndpoint;

    /**
     * Регион, через который будут поступать запросы к AWS
     */
    @Value("${aws.signing-region}")
    private String awsSigningRegion;

    @Bean
    public AmazonS3 amazonS3() {
        try {
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(awsAccessKey, awsSecretKey)
                    ))
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(awsServiceEndpoint, awsSigningRegion)
                    )
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
