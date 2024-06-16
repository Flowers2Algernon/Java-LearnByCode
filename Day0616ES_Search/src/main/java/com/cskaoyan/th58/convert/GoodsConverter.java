package com.cskaoyan.th58.convert;

import com.cskaoyan.th58.dto.GoodsDTO;
import com.cskaoyan.th58.dto.SearchAttrDTO;
import com.cskaoyan.th58.model.Goods;
import com.cskaoyan.th58.model.SearchAttr;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsConverter {

    GoodsDTO goodsPO2DTO(Goods goods);

    List<GoodsDTO> goodsPOs2DTOs(List<Goods> goods);

    SearchAttrDTO searchAttrPO2DTO(SearchAttr searchAttr);
}