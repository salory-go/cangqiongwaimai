package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
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
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id来删除指定口味数据
     * @param dishId
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteByDishIds(List<Long> ids);
}
