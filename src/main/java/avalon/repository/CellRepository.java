package avalon.repository;

import avalon.model.dungeons.DungeonCell;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value="cellRepository")
public interface CellRepository extends CrudRepository<DungeonCell, Long> {
    public DungeonCell findById(long id);
}
