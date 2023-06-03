package com.forus.picko.controller;

import com.forus.picko.entity.Question;
import com.forus.picko.service.QuestionService;
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

@Tag(name = "question", description = "Question API")
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private static QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(summary = "question all", description = "질문 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Question.class))
            )),
    })
    @GetMapping
    @ResponseBody
    public List<Question> questions() {
        return questionService.findQuestions();
    }

    @Operation(summary = "question", description = "질문 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Question.class)
            )),
    })
    @GetMapping("/{id}")
    @ResponseBody
    public Question question(@PathVariable Long id) {
        return questionService.findOne(id).orElse(null);
    }
}
