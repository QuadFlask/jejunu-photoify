package ac.jejunu.photify.activity.fragment;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://192.168.0.3:8080/", converters = {FormHttpMessageConverter.class, GsonHttpMessageConverter.class})
public interface MyRestClient {
	@Post("/jsontest.photo")
	SampleEntity getSampleEntity();
}