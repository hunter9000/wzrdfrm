package avalon.repository;

import avalon.model.character.Character;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(value="charRepository")
public interface CharRepository extends CrudRepository<Character, Long> {
    public Character findById(long id);
    public List<Character> findByUserId(long id);
//    public List<MaterialEffectModel> findAll();
}