package br.com.matheuszanatta.consumerother.job;

import br.com.matheuszanatta.consumerother.service.AttendantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RelieveWorkload {

    private final AttendantService attendantService;

    @Scheduled(fixedDelay = 40000)
    public void relieve() {
        log.info("Relieving workload...");
        attendantService.relieveAttendants();
    }
}
