package avalon.request;

import avalon.model.items.equipment.EquipmentSlot;

public class EquipmentRequest {

    public Long equipmentId;
    public EquipmentSlot slot;

    public boolean validate() {
        return equipmentId != null && slot != null;
    }
}
