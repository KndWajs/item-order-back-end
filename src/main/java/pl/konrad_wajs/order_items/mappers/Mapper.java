package pl.konrad_wajs.order_items.mappers;

public interface Mapper<Dto, EntityInterface> {

    Dto toDTO(EntityInterface entity);

    EntityInterface toEntity(Dto dto);
}
