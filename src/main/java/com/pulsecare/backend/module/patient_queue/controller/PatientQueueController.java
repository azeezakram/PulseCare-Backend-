package com.pulsecare.backend.module.patient_queue.controller;

import com.pulsecare.backend.common.base.controller.CreatableController;
import com.pulsecare.backend.common.base.controller.DeletableController;
import com.pulsecare.backend.common.base.controller.FindableController;
import com.pulsecare.backend.common.base.controller.UpdatableController;
import com.pulsecare.backend.common.template.response.ResponseBody;
import com.pulsecare.backend.module.patient_queue.dto.PatientQueueReqDTO;
import com.pulsecare.backend.module.patient_queue.dto.PatientQueueResDTO;
import com.pulsecare.backend.module.triage.dto.TriageReqDTO;
import com.pulsecare.backend.module.triage.dto.TriageResDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientQueueController extends
        FindableController<ResponseBody<PatientQueueResDTO>, ResponseBody<List<PatientQueueResDTO>>, Long>,
        CreatableController<PatientQueueReqDTO, ResponseBody<PatientQueueResDTO>>,
        UpdatableController<PatientQueueReqDTO, ResponseBody<PatientQueueResDTO>, Long>,
        DeletableController<ResponseBody<String>, Long> {
}
