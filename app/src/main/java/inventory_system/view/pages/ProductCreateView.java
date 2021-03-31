package inventory_system.view.pages;

import inventory_system.data.protocols.adapters.DatabaseConnection;
import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.infra.adapters.JdbcDatabaseConnectionAdapter;
import inventory_system.main.factories.EntityFactory;
import inventory_system.view.protocols.View;
import inventory_system.view.utils.ViewHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductCreateView implements View {

    private Scanner scanner;
    private DatabaseConnection databaseConnection;
    private ProductRepository productRepository;

    public ProductCreateView (Scanner scanner, DatabaseConnection databaseConnection, ProductRepository productRepository) {
        this.scanner = scanner;
        this.databaseConnection = databaseConnection;
        this.productRepository = productRepository;
    }

    public void call() {
        try {
            ViewHelper.clearConsole();
            Entity productEntity = prepareEntity();

            System.out.println(productEntity);
            this.productRepository.add(productEntity);
            this.productRepository.att
            ViewHelper.showSuccess();
            ViewHelper.backToHome(this.scanner, this.databaseConnection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Entity prepareEntity () throws SQLException {
        Entity productEntity = EntityFactory.makeEntity();

        this.scanner.nextLine();

        productEntity.setAtribute("code", prepareCode());

        productEntity.setAtribute("name", prepareName());

        productEntity.setAtribute("model", prepareModel());

        productEntity.setAtribute("description", prepareDescription());

        productEntity.setAtribute("quantity", prepareQuantity());

        productEntity.setAtribute("category_id", prepareCategory());

        productEntity.setAtribute("brand_id", prepareBrand());

        productEntity.setAtribute("unit_id", prepareUnit());

        productEntity.setAtribute("status_id", prepareStatus());

        return productEntity;
    }

    private String prepareCode () {
        System.out.print("\nCodigo: ");
        String code = this.scanner.nextLine();
        return code;
    }

    private String prepareName () {
        System.out.print("\nNome: ");
        String name = this.scanner.nextLine();
        return name;
    }

    private String prepareModel () {
        System.out.print("\nModelo: ");
        String model = this.scanner.nextLine();
        return model;
    }

    private String prepareDescription () {
        System.out.print("\nDescricao: ");
        String description = this.scanner.nextLine();
        return description;
    }

    private int prepareQuantity () {
        System.out.print("\nQuantidade: ");
        int quantity = this.scanner.nextInt();
        return quantity;
    }

    private List<Integer> prepareCategory () throws SQLException {
        List<Integer> categoryIds = new ArrayList<>();
        ViewHelper.showCategories(this.databaseConnection);

        System.out.print("\nID da Categoria (-1 para parar): ");
        int categoryId = this.scanner.nextInt();
        while (categoryId >= 0) {
            categoryIds.add(categoryId);
            System.out.print("\nID da Categoria (-1 para parar): ");
            categoryId = this.scanner.nextInt();
        }

        return categoryIds;
    }

    private int prepareBrand () throws SQLException {
        ViewHelper.showBrands(this.databaseConnection);
        System.out.print("\nID da Marca: ");
        int brandId = this.scanner.nextInt();
        return brandId;
    }

    private int prepareUnit () throws SQLException {
        ViewHelper.showUnits(this.databaseConnection);
        System.out.print("\nID da Unidade: ");
        int unitId = this.scanner.nextInt();
        return unitId;
    }

    private int prepareStatus () throws SQLException {
        ViewHelper.showStatuses(this.databaseConnection);
        System.out.print("\nID do Status: ");
        int brandId = this.scanner.nextInt();
        return brandId;
    }
}