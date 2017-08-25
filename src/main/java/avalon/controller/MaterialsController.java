package avalon.controller;

import avalon.model.items.material.Material;
import avalon.model.items.material.MaterialEffect;
import avalon.model.items.material.EffectType;
import avalon.repository.MaterialEffectRepository;
import avalon.repository.MaterialRepository;
import avalon.response.SuccessResponse;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaterialsController {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private MaterialEffectRepository materialEffectRepository;

    @RequestMapping(value = "/api/materials/", method = RequestMethod.GET)
    public Iterable<Material>  material() {
        Iterable<Material> mats = materialRepository.findAll();

        return mats;
    }

    @RequestMapping(value="/api/materials/", method=RequestMethod.POST)
    public SuccessResponse createMaterial(@RequestBody Material mat) {
        for (MaterialEffect effect : mat.getEffectList()) {
            effect.setMaterial(mat);
        }
        Material savedMat = materialRepository.save(mat);

        return new SuccessResponse(true, "Material created");
    }

    @RequestMapping(value="/api/materials/{matId}/", method=RequestMethod.DELETE)
    public SuccessResponse deleteMaterial(@PathVariable long matId) {
        System.out.println("deleteing mat " + matId);
        Material mat = materialRepository.findOne(matId);
        for (MaterialEffect eff : mat.getEffectList()) {
            materialEffectRepository.delete(eff);
        }

        materialRepository.delete(matId);
        return new SuccessResponse(true, "Material deleted");
    }

    @RequestMapping(value="/api/materials/{matId}/effect/", method=RequestMethod.POST)
    public SuccessResponse createEffect(@PathVariable long matId, @RequestBody MaterialEffect effect) {
        Material mat = materialRepository.findOne(matId);
//        mat.getEffectList().add(effect);

        effect.setMaterial(mat);

        materialEffectRepository.save(effect);

        Material newMat = materialRepository.save(mat);

        return new SuccessResponse(true, "Effect added");
    }

    @RequestMapping(value="/api/materials/{matId}/effect/{effId}", method=RequestMethod.DELETE)
    public SuccessResponse deleteEffect(@PathVariable long matId, @PathVariable long effId) {
        // TODO: check that the effect belongs to this mat

        materialEffectRepository.delete(effId);

        return new SuccessResponse(true, "Effect deleted");
    }


    @RequestMapping(value="/api/effectTypes", method=RequestMethod.GET)
    public List<EffectType> getEffectTypes() {
        return Collections.arrayToList(EffectType.values());
    }

}
