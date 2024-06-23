import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class AwsConfig {
    @Bean
    public Ec2Client ec2Client() {
        return Ec2Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("YOUR_ACCESS_KEY", "YOUR_SECRET_KEY")))
                .build();
    }
}
