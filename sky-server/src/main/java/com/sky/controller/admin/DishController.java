package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目名: sky-take-out
 * 文件名: DishController
 * 创建者: LZS
 * 创建时间:2024/2/26 19:19
 * 描述:
 **/
@RestController
@Slf4j
@Api
@RequestMapping(("/admin/dish"))
public class DishController {

    @Autowired
    DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @ApiOperation("新增菜品")
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("新增菜品：{}",dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    @RequestMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result page(DishPageQueryDTO dishPageQueryDTO){
        log.info("菜品分页查询: {}",dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * 删除与批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public  Result delete(@RequestParam List<Long> ids) {
        log.info("开始菜品删除:{}",ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }
}
