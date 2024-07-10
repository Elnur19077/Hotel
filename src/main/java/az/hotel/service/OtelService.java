package az.hotel.service;

import az.hotel.dto.response.OtelResp;
import az.hotel.dto.response.Response;
import az.hotel.entity.OtelInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OtelService {
    Response<List<OtelResp>> getOtelInfo();
    public  OtelResp convert(OtelInfo otelInfo);
}
