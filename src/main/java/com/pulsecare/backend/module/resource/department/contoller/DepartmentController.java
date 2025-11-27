package com.pulsecare.backend.module.resource.department.contoller;

import com.pulsecare.backend.common.base.controller.CreatableController;
import com.pulsecare.backend.common.base.controller.DeletableController;
import com.pulsecare.backend.common.base.controller.FindableController;
import com.pulsecare.backend.common.base.controller.UpdatableController;
import com.pulsecare.backend.common.template.response.ResponseBody;
import com.pulsecare.backend.module.resource.department.dto.DeptRequestDTO;
import com.pulsecare.backend.module.resource.department.dto.DeptResponseDTO;

import java.util.List;

public interface DepartmentController extends
        FindableController<ResponseBody<DeptResponseDTO>, ResponseBody<List<DeptResponseDTO>>, Integer>,
        CreatableController<DeptRequestDTO, ResponseBody<DeptResponseDTO>>,
        UpdatableController<DeptRequestDTO, ResponseBody<DeptResponseDTO>, Integer>,
        DeletableController<ResponseBody<String>, Integer> {
}
