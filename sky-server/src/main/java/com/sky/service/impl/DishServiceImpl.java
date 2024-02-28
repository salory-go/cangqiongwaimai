package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetMealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名: sky-take-out
 * 文件名: DishServiceImpl
 * 创建者: LZS
 * 创建时间:2024/2/26 19:52
 * 描述:
 **/
@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetMealDishMapper setMealDishMapper;

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        log.info("开始新增菜品(service): {}",dish);
        //菜品表插入一个数据
        dishMapper.insert(dish);
        //获取insert语句生成的主键值
        Long id = dish.getId();
        //口味表插入n条数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if(flavors.size()>0 && flavors != null){
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(id);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("service开始查询菜品：{}",dishPageQueryDTO);
        PageHelper.startPage(dishPageQueryDTO.getPage(),dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        Long total = page.getTotal();
        List<DishVO> records = page.getResult();
        return new PageResult(total,records);
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        log.info("service开始菜品删除:{}", ids);
        //判断当前菜品是否可以删除--当前菜品是否起售
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if(dish.getStatus() == StatusConstant.ENABLE)
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
        }
        //判断当前菜品是否可以删除--当前菜品是否在套餐里
        List<Long> setMealIds = setMealDishMapper.getSetMealDishByIds(ids);
        if(setMealIds.size()>0&&setMealIds!=null){
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
//        //删除菜品表的数据
//        for (Long id : ids) {
//            dishMapper.deleteById(id);
//            //删除口味表的数据
//            dishFlavorMapper.deleteByDishId(id);
//        }

        //批量删除菜品表的数据
        dishMapper.deleteByIds(ids);
        //批量删除口味表的数据
        dishFlavorMapper.deleteByDishIds(ids);
    }
}
