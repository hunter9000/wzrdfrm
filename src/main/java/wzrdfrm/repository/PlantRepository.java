package wzrdfrm.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wzrdfrm.model.farm.Plant;

@Repository
@Qualifier(value="plantRepository")
public interface PlantRepository extends CrudRepository <Plant, Long> {
    Plant findByName(String name);
}
