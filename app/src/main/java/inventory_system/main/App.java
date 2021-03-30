package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.StatusRepository;
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

            BrandRepository brandRepository = RepositoryFactory.makeBrandRepository(conn);

            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("name", "teste");
            newEntity.setAtribute("description", "Nike - Marca esportiva");

            brandRepository.add(newEntity);

            Entity brand = brandRepository.loadById(1);
            System.out.println(brand.getAttributes());

            List<Entity> brands = brandRepository.loadAll();
            System.out.println(brands);

            brand.setAtribute("name", "Nike");

            brandRepository.update(1, brand);

            brand = brandRepository.loadById(1);
            System.out.println(brand.getAttributes());

            brandRepository.delete(1);

            brands = brandRepository.loadAll();
            System.out.println(brands);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
