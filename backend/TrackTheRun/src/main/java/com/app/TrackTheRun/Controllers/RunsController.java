package com.app.TrackTheRun.Controllers;

import com.app.TrackTheRun.Dtos.CreateRunDTO;
import com.app.TrackTheRun.Dtos.CreateRunResponseDTO;
import com.app.TrackTheRun.Services.RunsService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/runs")
public class RunsController {

    private final RunsService runsService;

    @PostMapping("/runs")
    public ResponseEntity<CreateRunResponseDTO> createRun(@Validated @RequestBody CreateRunDTO dto) {

        CreateRunResponseDTO res = runsService.createNewRun(dto);

        return ResponseEntity.ok(res);
    }

}
