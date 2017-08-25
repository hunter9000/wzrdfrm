package wzrdfrm.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wzrdfrm.model.farm.Farm;

@Repository
@Qualifier(value="farmRepository")
public interface FarmRepository extends CrudRepository<Farm, Long> {

}
