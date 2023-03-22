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
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    public @ResponseBody searchBlogDto.SEARCH_RESPONSE getSearchBlog(
            @ModelAttribute searchBlogDto.SEARCH_CONDITION searchReq
            ){
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
            @ApiResponse(responseCode = "200", description = "조회 성공")
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

    @RequestMapping(value = "/deleteSearchLog/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "다음 블로그 검색어 조회 통계 로그 삭제", description = "다음 블로그 검색어 조회 통계 로그를 ID로 삭제한다.",
            parameters = {
                    @Parameter(name = "id", description = "DB TABLE ID"),
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공"),
            @ApiResponse(responseCode = "404", description = "삭제 대상을 찾을 수 없음"),
    })
    public ResponseEntity<ResponseRoot> getSearchLog(
            @PathVariable("id") Long id
    ){
        final TbSearchBlog byId = mainService.bindById(id);
        if(byId == null) return ResponseBuilder.build(HttpStatus.NOT_FOUND);
        mainService.deleteByid(byId);
        return ResponseBuilder.build(HttpStatus.NO_CONTENT);
    }
}
