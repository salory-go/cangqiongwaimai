package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 项目名: sky-take-out
 * 文件名: SetMealDish
 * 创建者: LZS
 * 创建时间:2024/2/27 10:34
 * 描述:
 **/
@Mapper
public interface SetMealDishMapper {

    /**
     * 根据id查询菜品是否存在套餐里
     * @param dishIds
     * @return
     */
    List<Long> getSetMealDishByIds(List<Long> dishIds);
}
