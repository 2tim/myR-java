package pricing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@EnableMongoRepositories(basePackageClasses = App.class)
//@RepositoryRestResource(collectionResourceRel = "listing", path = "listing")
public interface DataStore extends MongoRepository<Price, Long> {

    Price findById(Long id);

}