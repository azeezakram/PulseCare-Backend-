package com.pulsecare.backend.module.resource.bed.controller;

import com.pulsecare.backend.common.base.controller.CreatableController;
import com.pulsecare.backend.common.base.controller.DeletableController;
import com.pulsecare.backend.common.base.controller.FindableController;
import com.pulsecare.backend.common.base.controller.UpdatableController;
import com.pulsecare.backend.common.template.response.ResponseBody;
import com.pulsecare.backend.module.resource.bed.dto.BedReqDTO;
import com.pulsecare.backend.module.resource.bed.dto.BedResDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BedController extends
        FindableController<ResponseBody<BedResDTO>, ResponseBody<List<BedResDTO>>, Long>,
        CreatableController<BedReqDTO, ResponseBody<BedResDTO>>,
        UpdatableController<BedReqDTO, ResponseBody<BedResDTO>, Long>,
        DeletableController<ResponseBody<String>, Long> {
    ResponseEntity<ResponseBody<BedResDTO>> findByBedNoAndWardId(Integer wardId, String bedNo);
    ResponseEntity<ResponseBody<List<BedResDTO>>> batchCreate(Integer wardId, List<BedReqDTO> data);
}
