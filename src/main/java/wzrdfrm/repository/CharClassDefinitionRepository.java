package wzrdfrm.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wzrdfrm.model.classes.CharClassDefinition;

@Repository
@Qualifier(value="charClassDefinitionRepository")
public interface CharClassDefinitionRepository extends CrudRepository<CharClassDefinition, Long> {

}
