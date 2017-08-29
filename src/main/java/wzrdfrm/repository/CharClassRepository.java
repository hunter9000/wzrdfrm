package wzrdfrm.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wzrdfrm.model.classes.CharClass;
import wzrdfrm.model.farm.Farm;

import javax.transaction.Transactional;

@Repository
@Qualifier(value="charClassRepository")
public interface CharClassRepository extends CrudRepository<CharClass, Long> {
    @Transactional
    void deleteByFarm(Farm farm);

    Iterable<CharClass> findAllByFarm(Farm farm);
}
