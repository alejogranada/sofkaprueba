package com.example.cliente_persona_service.converter;

import com.example.cliente_persona_service.dto.ClienteSolicitudEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

@Component
public class CustomMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper;

    public CustomMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message toMessage(Object object, MessageProperties messageProperties) throws MessageConversionException {
        try {
            byte[] body = objectMapper.writeValueAsBytes(object);
            messageProperties.setContentType("application/json");
            return new Message(body, messageProperties);
        } catch (Exception e) {
            throw new MessageConversionException("Error converting to Message", e);
        }
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        try {
            return objectMapper.readValue(message.getBody(), ClienteSolicitudEvent.class);
        } catch (Exception e) {
            throw new MessageConversionException("Error converting from Message", e);
        }
    }

}
