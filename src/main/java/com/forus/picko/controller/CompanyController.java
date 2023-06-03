package com.forus.picko.controller;

import com.forus.picko.entity.Company;
import com.forus.picko.service.CompanyService;
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

@Tag(name = "company", description = "Company API")
@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private static CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @Operation(summary = "company all", description = "회사 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Company.class))
            )),
    })
    @GetMapping
    @ResponseBody
    public List<Company> companies() {
        return companyService.findCompanies();
    }

    @Operation(summary = "company", description = "회사 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Company.class)
            )),
    })
    @GetMapping("/{id}")
    @ResponseBody
    public Company company(@PathVariable Long id) {
        return companyService.findOne(id).orElse(null);
    }
}
