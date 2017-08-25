package avalon.request;

import org.apache.commons.lang3.StringUtils;

public class NewCharacterRequest {

    public String name;

    public boolean validate() {
        return !StringUtils.isEmpty(name);
    }

}
