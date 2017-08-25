package avalon.model.items;

import avalon.model.items.material.Material;

public interface CountableMaterial {
    public Material getMaterial();
    public void setMaterial(Material mat);

    public Integer getQuantity();
    public void setQuantity(Integer quantity);
}
