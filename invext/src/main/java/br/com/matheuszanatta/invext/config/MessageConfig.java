package br.com.matheuszanatta.invext.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    // Cards
    @Value("${app.rabbitmq.card.exchange}")
    private String cardExchange;

    @Value("${app.rabbitmq.card.queue}")
    private String cardQueue;

    @Value("${app.rabbitmq.card.routing-key}")
    private String cardRk;

    // Loan
    @Value("${app.rabbitmq.loan.exchange}")
    private String loanExchange;

    @Value("${app.rabbitmq.loan.queue}")
    private String loanQueue;

    @Value("${app.rabbitmq.loan.routing-key}")
    private String loanRk;

    // Others
    @Value("${app.rabbitmq.other.exchange}")
    private String otherExchange;

    @Value("${app.rabbitmq.other.queue}")
    private String otherQueue;

    @Value("${app.rabbitmq.other.routing-key}")
    public String othersRk;

    @Bean
    public TopicExchange cardExchange() {
        return new TopicExchange(cardExchange);
    }

    @Bean
    public TopicExchange loanExchange() {
        return new TopicExchange(loanExchange);
    }

    @Bean
    public TopicExchange otherExchange() {
        return new TopicExchange(otherExchange);
    }

    @Bean
    public Queue cardQueue() {
        return new Queue(cardQueue);
    }

    @Bean
    public Queue loanQueue() {
        return new Queue(loanQueue);
    }

    @Bean
    public Queue otherQueue() {
        return new Queue(otherQueue);
    }

    @Bean
    public Binding declareBindingCards() {
        return BindingBuilder.bind(cardQueue()).to(cardExchange()).with(cardRk);
    }

    @Bean
    public Binding declareBindingLoan() {
        return BindingBuilder.bind(loanQueue()).to(loanExchange()).with(loanRk);
    }

    @Bean
    public Binding declareBindingOthers() {
        return BindingBuilder.bind(otherQueue()).to(otherExchange()).with(othersRk);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
