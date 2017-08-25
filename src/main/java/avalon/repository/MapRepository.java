package avalon.repository;

import avalon.model.dungeons.DungeonMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Qualifier(value="mapRepository")
public interface MapRepository extends CrudRepository<DungeonMap, Long> {
    public DungeonMap findById(long id);
}
