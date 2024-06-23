import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import software.amazon.awssdk.services.ec2.model.Instance;

@Document(collection = "instances")
public class InstanceEntity {
    @Id
    private String id;
    private String instanceId;
    private String instanceType;
    // ... other fields

    public InstanceEntity() {
    }

    public InstanceEntity(Instance instance) {
        this.instanceId = instance.instanceId();
        this.instanceType = instance.instanceType().toString();
        // ... map other fields
    }

    // ... getters and setters
}
