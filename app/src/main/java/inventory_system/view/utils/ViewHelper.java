package inventory_system.view.utils;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.BrandRepository;
import inventory_system.data.protocols.database.CategoryRepository;
import inventory_system.data.protocols.database.StatusRepository;
import inventory_system.data.protocols.database.UnitRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.RepositoryFactory;
import inventory_system.main.factories.ViewFactory;
import inventory_system.view.protocols.View;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ViewHelper {

    public static void clearConsole () {
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }

    public static void showCategories (DatabaseConnection connection) throws SQLException {
        System.out.println("\nLista de Categorias: ");
        CategoryRepository categoryRepository = RepositoryFactory.makeCategoryRepository(connection);
        List<Entity> categories = categoryRepository.loadAll();

        for (Entity category : categories) {
            System.out.println("| " + category.getAttribute("id") + ". " + category.getAttribute("name") + " - " + category.getAttribute("description"));
        }
    }

    public static void showBrands (DatabaseConnection connection) throws SQLException {
        System.out.println("\nLista de Marcas: ");
        BrandRepository brandRepository = RepositoryFactory.makeBrandRepository(connection);
        List<Entity> brands = brandRepository.loadAll();

        for (Entity brand : brands) {
            System.out.println("| " + brand.getAttribute("id") + ". " + brand.getAttribute("name") + " - " + brand.getAttribute("description"));
        }
    }

    public static void showStatuses (DatabaseConnection connection) throws SQLException {
        System.out.println("\nLista de Status: ");
        StatusRepository statusRepository = RepositoryFactory.makeStatusRepository(connection);
        List<Entity> statuses = statusRepository.loadAll();

        for (Entity status : statuses) {
            System.out.println("| " + status.getAttribute("id") + ". " + status.getAttribute("name") + " - " + status.getAttribute("description"));
        }
    }

    public static void showUnits (DatabaseConnection connection) throws SQLException {
        System.out.println("\nLista de Unidades: ");
        UnitRepository unitRepository = RepositoryFactory.makeUnitRepository(connection);
        List<Entity> units = unitRepository.loadAll();

        for (Entity unit : units) {
            System.out.println("| " + unit.getAttribute("id") + ". " + unit.getAttribute("name") + "(" + unit.getAttribute("abbreviation") + ")" + " - " + unit.getAttribute("description"));
        }
    }

    public static void showSuccess () {
        try {
            System.out.println("========================================");
            System.out.println("O seu pedido foi processado com sucesso!");
            System.out.println("Estamos redirecionando você para página inicial");
            System.out.println("========================================");

            for (int i = 0; i < 3; i++) {
                System.out.print(". ");
                Thread.currentThread().sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void backToHome (Scanner scanner, DatabaseConnection connection) {
        View productView = ViewFactory.makeProductView(scanner, connection);
    }

}
