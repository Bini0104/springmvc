package com.ohgiraffers.crud.menu.model.dao;

import com.ohgiraffers.crud.menu.model.dto.CategoryDTO;
import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<MenuDTO> findAllMenu();

    List<CategoryDTO> findAllCategory();

    void registnewMenu(MenuDTO newMenu);

    void modifyMenu(MenuDTO modifyNewMenu);

    void deleteMenuByCode(MenuDTO deleteMenu);

    void selectMenuByCode(MenuDTO selectByCodeMenu);
}
