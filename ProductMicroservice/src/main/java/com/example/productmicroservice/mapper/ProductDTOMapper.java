package com.example.productmicroservice.mapper;


import com.example.productmicroservice.DTO.ProductDTO;
import com.example.productmicroservice.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProductDTOMapper {
    ProductDTOMapper INSTANCE = Mappers.getMapper(ProductDTOMapper.class);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);

    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);

}