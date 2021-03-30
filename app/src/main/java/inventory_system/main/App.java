package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.CategoryRepository;
import inventory_system.data.protocols.database.StatusRepository;
import inventory_system.data.protocols.database.UnitRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.EntityFactory;
import inventory_system.main.factories.RepositoryFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        try {
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            CategoryRepository categoryRepository = RepositoryFactory.makeCategoryRepository(conn);

            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("name", "Hardware");
            newEntity.setAtribute("description", "");

            categoryRepository.add(newEntity);

            Entity category = categoryRepository.loadById(1);
            System.out.println(category.getAttributes());

            List<Entity> brands = categoryRepository.loadAll();
            System.out.println(brands);

            category.setAtribute("description", "Produtos de hardware");

            categoryRepository.update(1, category);

            category = categoryRepository.loadById(1);
            System.out.println(category.getAttributes());

            categoryRepository.delete(1);

            brands = categoryRepository.loadAll();
            System.out.println(brands);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
