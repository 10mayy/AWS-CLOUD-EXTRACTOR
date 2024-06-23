import org.springframework.data.mongodb.repository.MongoRepository;

public interface InstanceRepository extends MongoRepository<InstanceEntity, String> {
}
