package az.hotel.controller;

import az.hotel.dto.response.OtelResp;
import az.hotel.dto.response.Response;
import az.hotel.service.OtelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("info")
@RequiredArgsConstructor
public class OtelController {
    private final OtelService otelService;

@GetMapping("/list")
Response<List<OtelResp>> getOtelInfo(){
    return otelService.getOtelInfo();
}



}



