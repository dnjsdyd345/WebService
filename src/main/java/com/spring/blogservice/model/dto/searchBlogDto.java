package java.com.spring.blogservice.model.dto;

//@Schema(name = "searchBlogDto", title="searchBlog", description = "Kakao Blog 검색 API DTO")
public class searchBlogDto {

    @getter
    @setter
    public static SEARCH_REQ(){
        private String title;
    }

    @getter
    @setter
    public static SEARCH_RES(){
        private String title;
    }
}
