package wzrdfrm.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wzrdfrm.model.farm.UsableItem;

@Repository
@Qualifier(value = "usableItemRepository")
public interface UsableItemRepository extends CrudRepository<UsableItem, Long> {
    UsableItem findByName(String name);
}
