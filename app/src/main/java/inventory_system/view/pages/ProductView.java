package inventory_system.view.pages;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.main.factories.EntityFactory;
import inventory_system.main.factories.RepositoryFactory;
import inventory_system.main.factories.ViewFactory;
import inventory_system.view.protocols.View;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductView implements View {

    private Scanner scanner;
    private DatabaseConnection databaseConnection;

    public ProductView (Scanner scanner, DatabaseConnection databaseConnection) {
        this.scanner = scanner;
        this.databaseConnection = databaseConnection;
    }

    public void call () {
        try {
            showMenus();

            System.out.print("Digite qual opcao deseja selecionar: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    onCreate();
                    break;
                case 2:
                    onList();
                    break;
                case 3:
                    onSearch();
                    break;
                case 4:
                    onExit();
                    break;
            }
        } catch (SQLException e) {

        }

    }

    public void onCreate () throws SQLException {
        ProductRepository productRepository = RepositoryFactory.makeProductRepository(this.databaseConnection);
        View productCreateView = ViewFactory.makeProductCreateView(this.scanner, this.databaseConnection, productRepository);
        productCreateView.call();
    }

    public void onList () {

    }

    public void onSearch () {

    }

    public void onExit () {

    }

    public void showMenus () {
        System.out.println("\n\n### Inventory System - Gerenciamento de Produtos ###");
        System.out.println("\n=========================");
        System.out.println("|     1 - Criar         ");
        System.out.println("|     2 - Listar      ");
        System.out.println("|     3 - Buscar        ");
        System.out.println("|     0 - Sair          ");
        System.out.println("=========================\n");
    }


}
