package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.*;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.EntityFactory;
import inventory_system.main.factories.RepositoryFactory;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);

            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("code", "158319");
            newEntity.setAtribute("name", "Processador i9");
            newEntity.setAtribute("description", "x86");
            newEntity.setAtribute("quantity", "15");
            newEntity.setAtribute("brand_id", "1");
            newEntity.setAtribute("status_id", "1");

            System.out.println(newEntity.getAttribute("test"));

            productRepository.add(newEntity);

            Entity product = productRepository.loadByCode("158319");
            System.out.println(product.getAttributes());

            List<Entity> products = productRepository.loadAll();
            System.out.println(products);

            product.setAtribute("model", "ABC12345");
            product.setAtribute("quantity", 16);
            product.setAtribute("unit_id", 1);

            productRepository.update("158319", product);

            product = productRepository.loadByCode("158319");
            System.out.println(product.getAttributes());

            productRepository.delete("158319");

            products = productRepository.loadAll();
            System.out.println(products);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
