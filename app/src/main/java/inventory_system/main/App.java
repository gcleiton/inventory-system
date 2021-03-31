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
        test();
    }

    public static void test () {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);

            // O método de criação precisa receber uma instância de Entity. Para tanto, é realizado o preenchimento dos dados
            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("code", "158319");
            newEntity.setAtribute("name", "Processador");
            newEntity.setAtribute("description", "Processador da Intel");
            newEntity.setAtribute("quantity", "15");
            newEntity.setAtribute("brand_id", "1");
            newEntity.setAtribute("status_id", "1");

            // Operação insert
            productRepository.add(newEntity);

            // Operação de busca pelo código
            Entity product = productRepository.loadByCode("158319");
            System.out.println("\nDados do produto criado: ");
            System.out.println(product.getAttributes());

            // Operação de busca por todos dados da tabela
            List<Entity> products = productRepository.loadAll();
            System.out.println("\nDados de todos produtos criados: ");
            for (Entity prod : products) {
                System.out.println(prod.getAttributes());
            }

            // Alteração dos dados da Entity Product
            product.setAtribute("name", "Processador x86 da Intel");
            product.setAtribute("description", "Processador x86 da Intel da Milésima Geração");
            product.setAtribute("model", "ABC12345");
            product.setAtribute("quantity", 16);
            product.setAtribute("unit_id", 1);
            // Operação de atualização do produto
            productRepository.update("158319", product);

            product = productRepository.loadByCode("158319");
            System.out.println("\nDados do produto atualizado: ");
            System.out.println(product.getAttributes());

            // Operação de remoção do produto
            productRepository.delete("158319");

            System.out.println("\nDados de todos produtos criados: ");
            products = productRepository.loadAll();
            for (Entity prod : products) {
                System.out.println(prod.getAttributes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
