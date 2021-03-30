package inventory_system.main.factories;

import inventory_system.data.entities.ViewEntity;
import inventory_system.domain.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityFactory {

    public static Entity makeEntity () {
        return new ViewEntity();
    }

    public static Entity makeEntity (Map<String, Object> attributes) {
        return new ViewEntity(attributes);
    }

    public static List<Entity> makeEntities (List<Map<String, Object>> data) {
        List<Entity> entities = new ArrayList<>();
        for (Map<String, Object> status : data) {
            entities.add(EntityFactory.makeEntity(status));
        }
        return entities;
    }
}
