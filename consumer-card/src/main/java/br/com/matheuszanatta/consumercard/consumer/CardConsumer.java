package br.com.matheuszanatta.consumercard.consumer;

import br.com.matheuszanatta.consumercard.service.AttendantService;
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
public class CardConsumer {

    private final AttendantService attendantService;

    @RabbitListener(queues = "${app.rabbitmq.card.queue}")
    public void listen(RequisitionDto requisition, Channel channel) throws InterruptedException, IOException {
        Attendant attendant = attendantService.getAvailableAttendant(Theme.CARD_ISSUES);
        if (attendant == null) {
            log.error("No attendant available for card requisition id: {}", requisition.getId());
            channel.basicReject(0L, true);
            return;
        }

        log.info("Card requisition received: {}", requisition.getId());
        attendant.setCurrentRequisitions(attendant.getCurrentRequisitions() + 1);
        channel.basicAck(0L, true);
    }
}
