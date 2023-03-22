package com.spring.blogservice.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Schema(name = "searchBlogDto", title="searchBlog", description = "Kakao Blog 검색 API DTO")
public class searchBlogDto {

    @Getter
    @Setter
    @Schema(name = "Search Blog Api[SEARCH_CONDITION]"
            , title="Search Blog Api[SEARCH_CONDITION]"
            , description = "KAKAO 블로그 검색 Request DTO")
    public static class SEARCH_CONDITION {
        @Schema(title = "검색어", description = "검색어")
        private String query;
        @Schema(title = "페이지", description = "결과 페이지 번호, 1~50 사이의 값, 기본 값 1")
        private BigDecimal page;
        @Schema(title = "사이즈", description = "한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10")
        private BigDecimal size;
        @Schema(title = "검색어", description = "검색어")
        private String sort;

        public SEARCH_CONDITION(){
            page = BigDecimal.valueOf(1);
            size = BigDecimal.valueOf(10);
            sort = "accuracy";
        }
    }

    @Getter
    @Setter
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @Schema(name = "Search Blog Api[SEARCH_RESPONSE]"
            , title="Search Blog Api[SEARCH_RESPONSE]"
            , description = "KAKAO 블로그 검색 Response DTO")
    public static class SEARCH_RESPONSE{
        public documents[] documents;
        public meta meta;

        @Getter
        @Setter
        public static class documents {
            @Schema(title = "블로그 글 제목", description = "블로그 글 제목")
            private String title;
            @Schema(title = "블로그 글 요약", description = "블로그 글 요약")
            private String contents;
            @Schema(title = "블로그 글 URL", description = "블로그 글 URL")
            private String url;
            @Schema(title = "블로그의 이름", description = "블로그의 이름")
            private String blogname;
            @Schema(title = "미리보기 대표 이미지 url", description = "검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음")
            private String thumbnail;
            @Schema(title = "블로그 글 작성시간", description = "ISO 8601 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]")
            private Date datetime;
        }
        @Getter
        @Setter
        public static class meta {
            @Schema(title = "검색된 문서 수", description = "검색된 문서 수")
            private boolean total_count;
            @Schema(title = "total_count 중 노출 가능 문서 수", description = "total_count 중 노출 가능 문서 수")
            private int pageable_count;
            @Schema(title = "현재 페이지가 마지막 페이지인지 여부", description = "false면 page를 증가시켜 다음 페이지를 요청할 수 있음")
            private Boolean is_end;
        }
    }


}
