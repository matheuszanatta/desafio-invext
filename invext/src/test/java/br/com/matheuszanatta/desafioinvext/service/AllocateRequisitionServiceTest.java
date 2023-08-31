package br.com.matheuszanatta.invext.service;

import dto.RequisitionDto;
import dto.Theme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AllocateRequisitionServiceTest {

    @InjectMocks
    private AllocateRequisitionService allocateRequisitionService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(allocateRequisitionService, "cardExchange", "card.exchange");
        ReflectionTestUtils.setField(allocateRequisitionService, "loanExchange", "loan.exchange");
        ReflectionTestUtils.setField(allocateRequisitionService, "otherExchange", "other.exchange");
        ReflectionTestUtils.setField(allocateRequisitionService, "cardRk", "card.routing-key");
        ReflectionTestUtils.setField(allocateRequisitionService, "loanRk", "loan.routing-key");
        ReflectionTestUtils.setField(allocateRequisitionService, "otherRk", "other.routing-key");
    }

    @Test
    @DisplayName("Allocate Requisition to Card Issues")
    void allocateCardIssues() {
        var exchange = "card.exchange";
        var routingKey = "card.routing-key";
        var requisition = newRequisition(Theme.CARD_ISSUES);

        allocateRequisitionService.allocate(requisition);

        verify(rabbitTemplate).convertAndSend(eq(exchange), eq(routingKey), any(RequisitionDto.class));
    }

    @Test
    @DisplayName("Allocate Requisition to Loan Contracting")
    void allocateLoanContracting() {
        var exchange = "loan.exchange";
        var routingKey = "loan.routing-key";
        var requisition = newRequisition(Theme.LOAN_CONTRACTING);

        allocateRequisitionService.allocate(requisition);

        verify(rabbitTemplate).convertAndSend(eq(exchange), eq(routingKey), any(RequisitionDto.class));
    }

    @Test
    @DisplayName("Allocate Requisition to Other")
    void allocateOther() {
        var exchange = "other.exchange";
        var routingKey = "other.routing-key";
        var requisition = newRequisition(Theme.OTHERS);

        allocateRequisitionService.allocate(requisition);

        verify(rabbitTemplate).convertAndSend(eq(exchange), eq(routingKey), any(RequisitionDto.class));
    }

    private RequisitionDto newRequisition(Theme theme) {
        var requisition = new RequisitionDto();
        requisition.setMessage("Test");
        requisition.setTheme(theme);
        requisition.setStatus("Test");
        return requisition;
    }
}
