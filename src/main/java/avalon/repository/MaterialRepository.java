package avalon.repository;

import avalon.model.items.material.Material;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="materialRepository")
public interface MaterialRepository extends CrudRepository<Material, Long> {
//    public Material findById(long id);
//    public List<Material> findAll();
}

