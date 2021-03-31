package inventory_system.main.factories;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.view.pages.ProductCreateView;
import inventory_system.view.pages.ProductView;
import inventory_system.view.protocols.View;

import java.util.Scanner;

public class ViewFactory {

    public static Scanner makeScanner () {
        return new Scanner(System.in);
    }

    public static View makeProductView (Scanner scanner, DatabaseConnection connection) {
        return new ProductView(scanner, connection);
    }

    public static View makeProductCreateView (Scanner scanner, DatabaseConnection databaseConnection, ProductRepository productRepository) {
        return new ProductCreateView(scanner, databaseConnection,productRepository);
    }
}
