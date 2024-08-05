package ru.selsup.tarasov.DTO;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.selsup.tarasov.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductRemainingMapping {
    ProductRemainingMapping INSTANCE = Mappers.getMapper(ProductRemainingMapping.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "vendorCode", target = "vendorCode")
    @Mapping(target = "remainingGoods", expression = "java(remainingGoods)")
    ProductRemainingDTO productToRemainingDTO(Product product, @Context int remainingGoods);
}
