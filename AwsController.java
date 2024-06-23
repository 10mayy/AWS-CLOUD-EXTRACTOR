import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.DescribeInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Instance;

import java.util.List;

@RestController
@RequestMapping("/aws")
public class AwsController {
    private final Ec2Client ec2Client;
    private final InstanceRepository instanceRepository;

    @Autowired
    public AwsController(Ec2Client ec2Client, InstanceRepository instanceRepository) {
        this.ec2Client = ec2Client;
        this.instanceRepository = instanceRepository;
    }

    @GetMapping("/instances")
    public List<Instance> getInstances() {
        DescribeInstancesResponse response = ec2Client.describeInstances();
        List<Instance> instances = response.reservations()
                .stream()
                .flatMap(reservation -> reservation.instances().stream())
                .toList();

        instances.forEach(instance -> {
            InstanceEntity entity = new InstanceEntity(instance);
            instanceRepository.save(entity);
        });

        return instances;
    }
}
