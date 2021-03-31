package inventory_system.domain.usecases;

import inventory_system.domain.entities.Entity;

import java.sql.SQLException;

public interface UpdateProduct {
    /**
     *
     * Método que atualiza os dados do produto a partir do código.
     * Além disso, verifica se houve mudança na quantidade e grava
     * o devido registro na tabela de reports da quantidade
     *
     * @param code - Código do produto
     * @param entity - Entidade do produto com os dados atualizados
     * @throws SQLException
     */
    public void update (String code, Entity entity) throws SQLException;
}
