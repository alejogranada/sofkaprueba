package com.example.cuenta_movimientos_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Definir los nombres de las colas
    public static final String COLA_CLIENTE_SOLICITUD = "cola_cliente_solicitud";
    public static final String COLA_CLIENTE_RESPUESTA = "cola_cliente_respuesta";

    // Exchange que gestionará el enrutamiento de los mensajes
    public static final String EXCHANGE_CLIENTE = "exchange_cliente";

    // Crear la cola donde se enviarán las solicitudes para obtener información del cliente
    @Bean
    public Queue solicitudClienteQueue() {
        return new Queue(COLA_CLIENTE_SOLICITUD);
    }

    // Crear la cola donde se recibirán las respuestas con los datos del cliente
    @Bean
    public Queue respuestaClienteQueue() {
        return new Queue(COLA_CLIENTE_RESPUESTA);
    }

    // Crear un exchange de tipo topic para la gestión de los mensajes
    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(EXCHANGE_CLIENTE);
    }

    // Enlazar la cola de solicitud con el exchange
    @Bean
    public Binding solicitudClienteBinding(Queue solicitudClienteQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(solicitudClienteQueue).to(clienteExchange).with(COLA_CLIENTE_SOLICITUD);
    }

    // Enlazar la cola de respuesta con el exchange
    @Bean
    public Binding respuestaClienteBinding(Queue respuestaClienteQueue, TopicExchange clienteExchange) {
        return BindingBuilder.bind(respuestaClienteQueue).to(clienteExchange).with(COLA_CLIENTE_RESPUESTA);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());  // Usa JSON como convertidor
        return template;
    }


}