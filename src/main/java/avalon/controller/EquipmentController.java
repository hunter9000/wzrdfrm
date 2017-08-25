package avalon.controller;

import avalon.interceptor.CharacterSheetOwnerRequired;
import avalon.model.character.Character;
import avalon.model.items.equipment.Equipment;
import avalon.model.items.equipment.EquipmentSlot;
import avalon.model.items.equipment.EquippedItem;
import avalon.repository.CharRepository;
import avalon.request.EquipmentRequest;
import avalon.security.BadRequestException;
import avalon.util.AuthUtils;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@RestController
public class EquipmentController {

    @Autowired
    private CharRepository charRepository;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value="/api/char/{charId}/equipment/", method=RequestMethod.POST)
    @CharacterSheetOwnerRequired
    public Character equipItem(@RequestBody EquipmentRequest equipmentRequest) {
        Character character = AuthUtils.getCharacter(request);

        if (!equipmentRequest.validate()) {
            throw new BadRequestException("");
        }

        // validate the item belongs to char
        // find the equipment item from the character
        Set<Equipment> equipment = character.getInventoryEquipment();
        Equipment item = IterableUtils.find(equipment, (Equipment e) -> e.getId().equals(equipmentRequest.equipmentId));
        if (item == null) {
            throw new BadRequestException("Bad item id requested");
        }
        // check that item slot matches slot
        if (item.getItem().getBodySlot() != equipmentRequest.slot) {
            throw new BadRequestException("Item cannot be equipped to requested slot");
        }

        // find if it's already equipped
        Map<EquipmentSlot, EquippedItem> equippedItems = character.getEquippedItems();
        Map.Entry<EquipmentSlot, EquippedItem> alreadyEquipped = IterableUtils.find(equippedItems.entrySet(),
                                                                                    (Map.Entry<EquipmentSlot, EquippedItem> entry) -> entry.getValue().getId().equals(item.getId()));
        if (alreadyEquipped != null) {
            // clear the old equipment slot
            equippedItems.put(alreadyEquipped.getKey(), null);
        }
        // set item to the new slot
        EquippedItem newEquipment = new EquippedItem();
        newEquipment.setCharacter(character);
        newEquipment.setEquipment(item);
        newEquipment.setEquipmentSlot(equipmentRequest.slot);
        equippedItems.put(equipmentRequest.slot, newEquipment);



        charRepository.save(character);

        return character;
    }

}
