package avalon.repository;

import avalon.model.items.material.MaterialEffect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="materialEffectRepository")
public interface MaterialEffectRepository extends CrudRepository<MaterialEffect, Long> {
    public MaterialEffect findById(long id);
//    public List<MaterialEffect> findAll();
}