package inventory_system.data.entities;

import inventory_system.domain.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class ViewEntity implements Entity {

    private Map<String, Object> attributes;

    public ViewEntity () {
        this.attributes = new HashMap<>();
    }

    public ViewEntity (Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Object getAttribute (String key) {
        if (this.attributes.get(key) == null) {
            return null;
        }
        return this.attributes.get(key).toString();
    }

    public void setAtribute (String key, Object value) {
        this.attributes.put(key, value);
    }

    public Map<String, Object> getAttributes () {
        return this.attributes;
    }

    public void setAttributes (Map<String, Object> attributes) {
        this.attributes = attributes;
    }

}
