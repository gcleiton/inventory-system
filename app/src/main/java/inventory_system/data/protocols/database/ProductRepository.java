package inventory_system.data.protocols.database;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {

    public List<Entity> loadAll () throws SQLException;

    public Entity loadByCode (String code) throws SQLException;

    public void add (Entity entity) throws SQLException;

    public void update (String code, Entity entity) throws SQLException;

    public void delete (String code) throws SQLException;

    /**
     *
     * A partir do código do produto retorna uma lista de reportes (logs)
     * de alterações da quantidade do produto indicado
     *
     * @param code - Código do produto
     * @return List<Entity> - Lista de categorias
     * @throws SQLException
     */
    public List<Entity> loadQuantityReports (String code) throws SQLException;

    /**
     *
     * A partir do código do produto retorna uma lista de categorias
     *
     * @param code - Código do produto
     * @return List<Entity> - Lista de categorias
     * @throws SQLException
     */
    public List<Entity> loadCategories (String code) throws SQLException;

    /**
     *
     * Método que permite associar uma lista de ids de categorias
     * a um produto a partir de seu código
     *
     * @param code - Código do produto
     * @param categoryIds - Lista de ids das categorias
     * @throws SQLException
     */
    public void attachCategories (String code, List<Integer> categoryIds) throws SQLException;

}
