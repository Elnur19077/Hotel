package az.hotel.service.impl;

import az.hotel.dto.response.OtelResp;
import az.hotel.dto.response.RespStatus;
import az.hotel.dto.response.Response;
import az.hotel.entity.OtelInfo;
import az.hotel.exception.CustomerException;
import az.hotel.exception.ExceptionConstant;
import az.hotel.repository.OtelRepository;
import az.hotel.service.OtelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OtelServiceImpl implements OtelService {
    private final OtelRepository otelRepository;

    @Override
    public Response<List<OtelResp>> getOtelInfo() {
        Response<List<OtelResp>> response = new Response<>();
        try {
            List<OtelInfo> otelInfos=otelRepository.findAll();
            if (otelInfos.isEmpty()){
                throw  new CustomerException("Otel information not found",ExceptionConstant.NO_OTEL_INFO);
            }
            List<OtelResp> otlResps=otelInfos.stream().map(this::convert).toList();
            response.setT(otlResps);
            response.setStatus(RespStatus.getSuccessMessage());


        }catch (CustomerException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));
        }catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstant.INTERNAL_EXCEPTION, "Internal error"));
        }

        return response;


    }

    @Override
    public OtelResp convert(OtelInfo otelInfo) {
        return OtelResp.builder()
                .name(otelInfo.getName())
                .adress(otelInfo.getAdress())
                .email(otelInfo.getEmail())
                .dateOfEstablishment(otelInfo.getDateOfEstablishment())
                .email(otelInfo.getEmail())
                .mobilenumber(otelInfo.getMobilenumber())
                .numberOfStars(otelInfo.getNumberOfStars())
                .socialNetworkName(otelInfo.getSocialNetworkName())
                .build();
    }
}
