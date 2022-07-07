package com.javamentor.backend.controller;

import com.javamentor.backend.mappers.DtoToHubMapper;
import com.javamentor.backend.model.dto.HubDto;
import com.javamentor.backend.service.HubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/hubs")
@Api(value = "HubController", tags = {"Контроллер для работы с хабами"})
public class HubController {

    private final HubService hubService;
    private final DtoToHubMapper dtoToHubMapper;

    public HubController(HubService hubService, DtoToHubMapper dtoToHubMapper) {
        this.hubService = hubService;
        this.dtoToHubMapper = dtoToHubMapper;
    }

    @PostMapping
    @ApiOperation("Создание хаба")
    public ResponseEntity<Void> createHub(@RequestBody HubDto hubDto) {
        hubService.saveHub(dtoToHubMapper.toHub(hubDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    @ApiOperation("Редактирование хаба")
    public ResponseEntity<Void> updateHub(@RequestBody HubDto hubDto) {
        hubService.saveHub(dtoToHubMapper.toHub(hubDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление хаба")
    public ResponseEntity<Void> deleteHub(@PathVariable Long id) {
        hubService.deleteHubById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
