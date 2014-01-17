package ac.jejunu.photify.rest;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import ac.jejunu.photify.entity.TestCommand;

@Rest(rootUrl = "http://113.198.164.145:8080/", converters = {FormHttpMessageConverter.class, GsonHttpMessageConverter.class})
public interface TestCommandRestClient {
	@Post("/readtestcommand.photo")
	TestCommand getTestCommand();

	@Post("/writetestcommand.photo")
	@Accept(MediaType.APPLICATION_JSON)
	void sendTestCommand(TestCommand testCommand);
}