package inventory_system.data.usecases;

import inventory_system.data.entities.ViewEntity;
import inventory_system.data.protocols.database.ProductRepository;
import inventory_system.data.protocols.database.QuantityReportRepository;
import inventory_system.domain.entities.Entity;
import inventory_system.domain.usecases.UpdateProduct;
import inventory_system.main.enumerations.QuantityType;

import java.sql.SQLException;

public class UpdateProductUseCase implements UpdateProduct {

    private ProductRepository productRepository;
    private QuantityReportRepository quantityReportRepository;

    public UpdateProductUseCase (ProductRepository productRepository, QuantityReportRepository quantityReportRepository) {
        this.productRepository = productRepository;
        this.quantityReportRepository = quantityReportRepository;
    }

    public void update(String code, Entity entity) throws SQLException {
        Entity loadedProduct = this.productRepository.loadByCode(code);

        Integer beforeQuantity = Integer.valueOf((String) loadedProduct.getAttribute("quantity"));
        Integer updatedQuantity = Integer.valueOf((String) entity.getAttribute("quantity"));

        if (this.wasQuantityChanged(beforeQuantity, updatedQuantity)) {
            Entity reportEntity = new ViewEntity();
            reportEntity.setAtribute("product_id", code);
            reportEntity.setAtribute("quantity", Math.abs(beforeQuantity - updatedQuantity));

            if (this.wasQuantityAddition(beforeQuantity, updatedQuantity)) {
                reportEntity.setAtribute("type_id", QuantityType.ADDITION.getValue());

            } else {
                reportEntity.setAtribute("type_id", QuantityType.SUBTRACTION.getValue());
            }

            this.quantityReportRepository.add(reportEntity);
        }

        this.productRepository.update(code, entity);

    }

    private boolean wasQuantityChanged (int oldQuantity, int newQuantity) {
        return !(oldQuantity == newQuantity);
    }

    private boolean wasQuantityAddition (int oldQuantity, int newQuantity) {
        return oldQuantity < newQuantity;
    }
}
