package br.com.matheuszanatta.invext.controller;

import br.com.matheuszanatta.invext.service.AllocateRequisitionService;
import dto.RequisitionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/requisition")
@RequiredArgsConstructor
@RestController
public class RequisitionController {

    private final AllocateRequisitionService allocateRequisitionService;

    @PostMapping
    public void requisition(@RequestBody RequisitionDto requisitionDto) {
        allocateRequisitionService.allocate(requisitionDto);
    }
}
