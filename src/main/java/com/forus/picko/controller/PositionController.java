package com.forus.picko.controller;

import com.forus.picko.entity.JobPosition;
import com.forus.picko.service.JobPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "position", description = "Position API")
@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private static JobPositionService jobPositionService;

    @Autowired
    public PositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }


    @Operation(summary = "position all", description = "직종 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = JobPosition.class))
            )),
    })
    @GetMapping
    @ResponseBody
    public List<JobPosition> positions() {
        return jobPositionService.findPositions();
    }

    @Operation(summary = "position", description = "직종 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = JobPosition.class)
            )),
    })
    @GetMapping("/{id}")
    @ResponseBody
    public JobPosition position(@PathVariable Long id) {
        return jobPositionService.findOne(id).orElse(null);
    }
}
