package com.sphere.pay.assembler;


import com.sphere.pay.db.entity.SysResource;
import com.sphere.pay.dto.SysResourceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ApplicationConverter {

    List<SysResourceDTO> convertResourceDTO(List<SysResource> sysResourceList);

}
