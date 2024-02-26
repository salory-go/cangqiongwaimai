package com.sky.service;

import com.sky.dto.DishDTO;

/**
 * 项目名: sky-take-out
 * 文件名: DishService
 * 创建者: LZS
 * 创建时间:2024/2/26 19:50
 * 描述:
 **/
public interface DishService {

    /**
     * 新增菜品和对应的口味
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);
}
