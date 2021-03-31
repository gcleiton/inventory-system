package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
<<<<<<< HEAD
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.ViewFactory;
import inventory_system.view.protocols.View;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection connection = AdapterFactory.makeDatabaseConnection();
        connection.connect();
=======
import inventory_system.data.protocols.database.*;
import inventory_system.domain.entities.Entity;
import inventory_system.domain.usecases.UpdateProduct;
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.EntityFactory;
import inventory_system.main.factories.RepositoryFactory;
import inventory_system.main.factories.UseCaseFactory;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        testAttachCategories();
    }

    public static void testAttachCategories () {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);

            // Operação de busca pelo código
            Entity product = productRepository.loadByCode("918123");
            System.out.println("\nDados do produto: ");
            System.out.println(product.getAttributes());

            List<Entity> categories = productRepository.loadCategories("918123");
            System.out.println("\nDados das categorias do produto: ");
            for (Entity category : categories) {
                System.out.println(category.getAttributes());
            }

            List<Integer> categoryIds = new ArrayList<Integer>();
            categoryIds.add(1);

            productRepository.attachCategories("918123", categoryIds);

            categories = productRepository.loadCategories("918123");
            System.out.println("\nDados das categorias do produto depois do attach: ");
            for (Entity category : categories) {
                System.out.println(category.getAttributes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLoadCategories () {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);

            // Operação de busca pelo código
            Entity product = productRepository.loadByCode("918123");
            System.out.println("\nDados do produto: ");
            System.out.println(product.getAttributes());

            List<Entity> categories = productRepository.loadCategories("918123");
            System.out.println("\nDados das categorias do produto: ");
            for (Entity category : categories) {
                System.out.println(category.getAttributes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testLoadQuantityReports() {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);
            QuantityReportRepository quantityReportRepository = RepositoryFactory.makeQuantityReportRepository(conn);
            UpdateProduct updateProduct = UseCaseFactory.makeUpdateProductUseCase(productRepository, quantityReportRepository);

            // O método de criação precisa receber uma instância de Entity. Para tanto, é realizado o preenchimento dos dados
            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("code", "155182");
            newEntity.setAtribute("name", "Teste");
            newEntity.setAtribute("description", "Teste da Intel");
            newEntity.setAtribute("quantity", 15);
            newEntity.setAtribute("brand_id", 1);
            newEntity.setAtribute("status_id", 1);

            // Operação insert
            productRepository.add(newEntity);

            // Operação de busca pelo código
            Entity product = productRepository.loadByCode("155182");
            System.out.println("\nDados do produto criado: ");
            System.out.println(product.getAttributes());

            product.setAtribute("quantity", 16);
            updateProduct.update("155182", product);

            product.setAtribute("quantity", 12);
            updateProduct.update("155182", product);

            product.setAtribute("quantity", 12);
            updateProduct.update("155182", product);

            product = productRepository.loadByCode("155182");
            System.out.println("\nDados do produto atualizado: ");
            System.out.println(product.getAttributes());

            List<Entity> reports = productRepository.loadQuantityReports("155182");
            for (Entity report : reports) {
                System.out.println(report.getAttributes());
            }

            productRepository.delete("155182");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUpdateProductUseCase() {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);
            QuantityReportRepository quantityReportRepository = RepositoryFactory.makeQuantityReportRepository(conn);
            UpdateProduct updateProduct = UseCaseFactory.makeUpdateProductUseCase(productRepository, quantityReportRepository);

            // O método de criação precisa receber uma instância de Entity. Para tanto, é realizado o preenchimento dos dados
            Entity newEntity = EntityFactory.makeEntity();
            newEntity.setAtribute("code", "158319");
            newEntity.setAtribute("name", "Processador");
            newEntity.setAtribute("description", "Processador da Intel");
            newEntity.setAtribute("quantity", 15);
            newEntity.setAtribute("brand_id", 1);
            newEntity.setAtribute("status_id", 1);

            // Operação insert
            productRepository.add(newEntity);

            // Operação de busca pelo código
            Entity product = productRepository.loadByCode("158319");
            System.out.println("\nDados do produto criado: ");
            System.out.println(product.getAttributes());

            product.setAtribute("quantity", 16);

            // Operação de atualização do produto
            updateProduct.update("158319", product);

            product = productRepository.loadByCode("158319");
            System.out.println("\nDados do produto atualizado: ");
            System.out.println(product.getAttributes());

            productRepository.delete("158319");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test () {
        try {
            // Parte de conexão do banco de dados. Além disso, conn será injetado no repositório
            DatabaseConnection conn = AdapterFactory.makeDatabaseConnection();
            conn.connect();

            ProductRepository productRepository = RepositoryFactory.makeProductRepository(conn);

            productRepository.delete("158319");

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
>>>>>>> feat/data-persistence

        Scanner scanner = ViewFactory.makeScanner();
        View productPage = ViewFactory.makeProductView(scanner, connection);

        productPage.call();
        scanner.close();
    }

}
