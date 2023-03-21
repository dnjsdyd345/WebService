package com.spring.blogservice.controller;

import com.spring.blogservice.controller.response.ResponseBuilder;
import com.spring.blogservice.controller.response.ResponseRoot;
import com.spring.blogservice.model.dto.searchBlogDto;
import com.spring.blogservice.model.entity.TbSearchBlog;
import com.spring.blogservice.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("")
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/searchBlog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "다음 블로그 검색 API", description = "다음 블로그 정보를 검색한다.",
            parameters = {
                @Parameter(name = "검색어", description = "검색어"),
                @Parameter(name = "page", description = "default : 10"),
                @Parameter(name = "size", description = "default : 1"),
                @Parameter(name = "sort", description = "default : accuracy"),
            }
                )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
//                            schema = @Schema()
                    )
                })
    })
    public @ResponseBody searchBlogDto.SEARCH_RESPONSE getSearchBlog(
            @ModelAttribute searchBlogDto.SEARCH_CONDITION searchReq
            ){

        PageRequest pageRequest = PageRequest.of(searchReq.getPage().intValue(), searchReq.getSize().intValue());
        return mainService.getSearchBlog(searchReq);

    }

    @RequestMapping(value = "/getSearchLog", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "다음 블로그 검색어 조회 통계 로그", description = "다음 블로그 검색어 조회 통계 로그를 빈도수 높은순으로 조회한다",
            parameters = {
                    @Parameter(name = "page", description = "default : 10"),
                    @Parameter(name = "size", description = "default : 0"),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
//                            schema = @Schema()
                            )
                    })
    })
    public ResponseEntity<ResponseRoot> getSearchLog(
            @RequestParam(value = "page", required = false)BigDecimal page,
            @RequestParam(value = "size", required = false)BigDecimal size
            ){
        if (page == null) page = new BigDecimal(0);
        if (size == null) size = new BigDecimal(10);
        PageRequest pageRequest = PageRequest.of(page.intValue(), size.intValue(),
                Sort.by("count").descending());
        Page<TbSearchBlog> all = mainService.findAll(pageRequest);
        return ResponseBuilder.build(all, HttpStatus.OK);

    }
}
