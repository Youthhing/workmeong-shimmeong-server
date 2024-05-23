package goormthon10.workmeongshimmeong.common.s3;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class S3Config {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        ClientConfiguration clientConfiguration = new ClientConfiguration();
//        clientConfiguration.setProtocol(Protocol.HTTP);
        clientConfiguration.setProtocol(Protocol.HTTPS);
        clientConfiguration.setProxyPort(3128);
        clientConfiguration.setProxyHost("krmp-proxy.9rum.cc");

        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
