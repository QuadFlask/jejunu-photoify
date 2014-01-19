package ac.jejunu.photify.rest;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

import ac.jejunu.photify.command.ArticleCommand;
import ac.jejunu.photify.entity.Article;

@Rest(rootUrl = "http://192.168.0.3:8080/", converters = {StringHttpMessageConverter.class, ByteArrayHttpMessageConverter.class, FormHttpMessageConverter.class, GsonHttpMessageConverter.class}, interceptors = {ArticleCommandInterceptor.class})
public interface ArticleCommandRestClient {

	@Post("/writearticle.photo")
	String writeArticle(Article article);

	@Post("/getaticles.photo")
	List<Article> getArticles(ArticleCommand articleCommand);

}

