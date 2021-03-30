package inventory_system.main.factories;

import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.data.protocols.database.QuantityReportRepository;
import inventory_system.data.usecases.UpdateProductUseCase;

public class UseCaseFactory {

    public static UpdateProductUseCase makeUpdateProductUseCase (ProductRepository productRepository, QuantityReportRepository quantityReportRepository) {
        return new UpdateProductUseCase(productRepository, quantityReportRepository);
    }

}
