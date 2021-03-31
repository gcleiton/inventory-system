package inventory_system.main;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.main.factories.AdapterFactory;
import inventory_system.main.factories.ViewFactory;
import inventory_system.view.protocols.View;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection connection = AdapterFactory.makeDatabaseConnection();
        connection.connect();

        Scanner scanner = ViewFactory.makeScanner();
        View productPage = ViewFactory.makeProductView(scanner, connection);

        productPage.call();
        scanner.close();
    }

}
