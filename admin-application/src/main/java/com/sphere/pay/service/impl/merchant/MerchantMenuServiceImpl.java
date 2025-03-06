package com.sphere.pay.service.impl.merchant;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sphere.pay.AdminConstant;
import com.sphere.pay.assembler.ApplicationConverter;
import com.sphere.pay.db.entity.MerchantMenu;
import com.sphere.pay.db.mapper.MerchantMenuMapper;
import com.sphere.pay.dto.MerchantMenuTreeDTO;
import com.sphere.pay.exception.AdminException;
import com.sphere.pay.param.MerchantMenuAddParam;
import com.sphere.pay.param.MerchantMenuTreeParam;
import com.sphere.pay.param.MerchantMenuUpdateParam;
import com.sphere.pay.service.merchant.MerchantMenuService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class MerchantMenuServiceImpl extends ServiceImpl<MerchantMenuMapper, MerchantMenu>
        implements MerchantMenuService {

    @Resource
    ApplicationConverter applicationConverter;

    @Override
    public List<MerchantMenuTreeDTO> getMerchantMenuTreeList(MerchantMenuTreeParam param) {
        QueryWrapper<MerchantMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StringUtils.isNotBlank(param.getEnName()), MerchantMenu::getEnName, param.getEnName())
                .eq(Objects.nonNull(param.getVisible()), MerchantMenu::getEnName, param.getVisible())
                .orderByDesc(MerchantMenu::getType)
                .orderByAsc(MerchantMenu::getOrderNum);
        List<MerchantMenu> menuList = this.list(queryWrapper);

        return null;
    }

    @Override
    public boolean addMerchantMenu(MerchantMenuAddParam param) {
        QueryWrapper<MerchantMenu> resourceQuery = new QueryWrapper<>();
        resourceQuery.lambda().eq(MerchantMenu::getParentId, param.getParentId())
                .eq(MerchantMenu::getEnName, param.getEnName())
                .last(AdminConstant.LIMIT_SQL);
        MerchantMenu menu = this.getOne(resourceQuery);
        if (Objects.nonNull(menu)) {
            throw new AdminException("exist");
        }

        menu = new MerchantMenu();
        menu.setName(param.getEnName());
        menu.setEnName(param.getEnName());
        menu.setParentId(param.getParentId());
        menu.setOrderNum(param.getOrderNum());
        menu.setUrl(param.getUrl());
        menu.setType("C");
        menu.setVisible(1);
        menu.setIcon(param.getIcon());
        return this.save(menu);
    }

    @Override
    public boolean updateMerchantMenu(MerchantMenuUpdateParam param) {
        UpdateWrapper<MerchantMenu> menuUpdate = new UpdateWrapper<>();
        menuUpdate.lambda()
                .set(StringUtils.isNotBlank(param.getEnName()), MerchantMenu::getEnName, param.getEnName())
                .set(Objects.nonNull(param.getOrderNum()), MerchantMenu::getOrderNum, param.getOrderNum())
                .set(Objects.nonNull(param.getVisible()), MerchantMenu::getVisible, param.getVisible())
                .eq(MerchantMenu::getId, param.getId());
        return this.update(menuUpdate);
    }
}
