package ru.selsup.tarasov.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.selsup.tarasov.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "vendorCode", target = "vendorCode")
    @Mapping(source = "lastBuyingPrice", target = "lastBuyingPrice")
    @Mapping(source = "lastSellingPrice", target = "lastSellingPrice")
    ProductDTO productToProductDTO(Product product);
}
