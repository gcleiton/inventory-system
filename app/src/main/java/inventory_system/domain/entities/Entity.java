package inventory_system.domain.entities;

import java.util.Map;

public interface Entity {

    public Object getAttribute (String key);
    public void setAtribute (String key, Object value);
    public Map<String, Object> getAttributes ();
    public void setAttributes (Map<String, Object> attributes);

}
