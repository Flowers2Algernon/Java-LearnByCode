package com.cskaoyan.th58.repository;

import com.cskaoyan.th58.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

// 第二个泛型参数类型  对应的是@Id注解对应成员变量的数据类型
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {


    /*
    1. 通过Query注解定义具体的查询字符串(也可以替换为其他查询)
    2. 字符串中的?0是固定格式，表示第0个参数的占位符，在实际查询时会被方法的第一个参数值title的值替换,
        如果有多个参数，依次类推即可
    3. List<Goods>表示查询到的文档对象列表
*/
    @Query(
            "{ " +
                    "\"match\": {\n" +
                    "      \"title\": \"?0\"\n" +
                    "}" +
                    "}"
    )
    List<Goods> matchSearch(String title);


    /*
      1. 针对一个查询结果，返回对应的一页数据
      2. Pageable参数是当想要获取分页数据的时候，必须携带的参数，表示分页信息
         比如，查询第多少页数据，每页多少条数据等，该参数不会用来替换我们的@Query字符串中的参数
      3. 返回的结果是一个包含一页文档数据的Page对象
 */
    @Query(
            " {" +
                    "   \"match\": {\n" +
                    "      \"title\": \"?0\"\n" +
                    "    }" +
                    "}"
    )
    Page<Goods> testSearchPage(String title, Pageable pageable);


    @Query(
            "  {  \"match\": {\n" +
                    "      \"title\": \"?0\"\n" +
                    "    }" +
                    "}"
    )
    @Highlight(
            fields = {
                    @HighlightField(name = "title",
                            parameters = @HighlightParameters(
                                    preTags = "<font color='red'>", postTags = "</font>"))
            }
    )
    List<SearchHit<Goods>> testHighlight(String title, Pageable pageable);

}
