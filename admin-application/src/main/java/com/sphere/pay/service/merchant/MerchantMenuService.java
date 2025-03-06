package com.sphere.pay.service.merchant;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sphere.pay.db.entity.MerchantMenu;
import com.sphere.pay.dto.MerchantMenuTreeDTO;
import com.sphere.pay.param.MerchantMenuAddParam;
import com.sphere.pay.param.MerchantMenuTreeParam;
import com.sphere.pay.param.MerchantMenuUpdateParam;

import java.util.List;

public interface MerchantMenuService extends IService<MerchantMenu> {

    List<MerchantMenuTreeDTO> getMerchantMenuTreeList(MerchantMenuTreeParam param);

    boolean addMerchantMenu(MerchantMenuAddParam param);

    boolean updateMerchantMenu(MerchantMenuUpdateParam param);

}
