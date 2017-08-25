package avalon.response;

import avalon.model.items.material.Material;

public class MaterialResponse {

    public Material mat;

    public MaterialResponse(Material mat) {
        this.mat = mat;
    }

    public Material getMat() {
        return mat;
    }
    public void setMat(Material mat) {
        this.mat = mat;
    }
}
