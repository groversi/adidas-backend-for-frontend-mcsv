package com.adidas.bff.exceptions;

import com.adidas.bff.exceptions.customExceptions.*;
import com.adidas.bff.exceptions.dto.GatewayErrorDTO;
import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;


public class FeignErrorDecoder implements ErrorDecoder {

    private static final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        GatewayErrorDTO gatewayErrorDTO = null;
        try {
           gatewayErrorDTO = new Gson().fromJson(IOUtils.toString(response.body().asInputStream(), Charset.defaultCharset().toString()), GatewayErrorDTO.class);
        } catch (Exception e) {
            log.error("could not deserialize response body");
        }
        log.error("Feign error decoder: status - {}", response.status());

        switch (response.status()){
            case 500:
                throw new InternalServerErrorException(response.reason());
            case 404:
                throw new ResourceNotFoundException(response.reason());
            case 403:
                throw new MethodForbidenException(gatewayErrorDTO);
            case 401:
                throw new MethodNotAllowedException(gatewayErrorDTO);
            case 400:
                throw new BadRequestException(gatewayErrorDTO);
            default:
                throw new InternalServerErrorException(response.reason());
        }
    }

}
