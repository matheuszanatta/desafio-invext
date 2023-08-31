package br.com.matheuszanatta.consumerloan.consumer;

import br.com.matheuszanatta.consumerloan.service.AttendantService;
import com.rabbitmq.client.Channel;
import domain.Attendant;
import dto.RequisitionDto;
import dto.Theme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class LoanConsumer {

    private final AttendantService attendantService;

    @RabbitListener(queues = "${app.rabbitmq.loan.queue}")
    public void listen(RequisitionDto requisition, Channel channel) throws IOException {
        Attendant attendant = attendantService.getAvailableAttendant(Theme.LOAN_CONTRACTING);
        if (attendant == null) {
            log.error("No attendant available for loan requisition id: {}", requisition.getId());
            channel.basicReject(0L, true);
            return;
        }

        log.info("Loan requisition received: {}", requisition.getId());
        attendant.setCurrentRequisitions(attendant.getCurrentRequisitions() + 1);
        channel.basicAck(0L, true);
    }
}
