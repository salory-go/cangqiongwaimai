package com.sky.mapper;

import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 项目名: sky-take-out
 * 文件名: DishFlavorMapper
 * 创建者: LZS
 * 创建时间:2024/2/26 20:28
 * 描述:
 **/
@Mapper
public interface DishFlavorMapper {
    /**
     * 新增菜品
     */
    void insertBatch(List<DishFlavor> dishFlavorList);
}
