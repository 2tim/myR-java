package pricing;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "listing", path = "listing")
public interface DataStore extends MongoRepository<Price, Long> {

    Price findById(Long id);

}