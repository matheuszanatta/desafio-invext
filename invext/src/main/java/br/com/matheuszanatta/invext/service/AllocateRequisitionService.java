package br.com.matheuszanatta.invext.service;

import dto.RequisitionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class AllocateRequisitionService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.card.exchange}")
    String cardExchange;

    @Value("${app.rabbitmq.loan.exchange}")
    String loanExchange;

    @Value("${app.rabbitmq.other.exchange}")
    String otherExchange;

    @Value("${app.rabbitmq.card.routing-key}")
    String cardRk;

    @Value("${app.rabbitmq.loan.routing-key}")
    String loanRk;

    @Value("${app.rabbitmq.other.routing-key}")
    String otherRk;

    public void allocate(RequisitionDto requisition) {
        requisition.setId(UUID.randomUUID());

        if (requisition.getTheme() == null)
            notifyOthers(requisition);
        else
            switch (requisition.getTheme()) {
                case CARD_ISSUES -> notifyCardIssues(requisition);
                case LOAN_CONTRACTING -> notifyLoanContracting(requisition);
                case OTHERS -> notifyOthers(requisition);
            }
        log.info("Requisition {} allocated successfully", requisition.getId());
    }

    private void notifyCardIssues(RequisitionDto requisition) {
        log.info("Allocating card issues");
        rabbitTemplate.convertAndSend(cardExchange, cardRk, requisition);
    }

    private void notifyLoanContracting(RequisitionDto requisition) {
        log.info("Allocating loan contracting");
        rabbitTemplate.convertAndSend(loanExchange, loanRk, requisition);
    }

    private void notifyOthers(RequisitionDto requisition) {
        log.info("Allocating others");
        rabbitTemplate.convertAndSend(otherExchange, otherRk, requisition);
    }
}
