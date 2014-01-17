package ac.jejunu.photify.activity.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import ac.jejunu.photify.R;

@EFragment(R.layout.fragment_main)
public class JsonTestFragment extends Fragment {

	@ViewById(R.id.section_label)
	TextView section_label;

	@RestService
	MyRestClient myRestClient;

	@AfterViews
	void onBindComplete() {
		try {
			backgroundJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@UiThread
	void setData(String data) {
		section_label.setText("서버로부터 : " + data);
		Log.e("JsonTestFragment", data);
	}

	@Background
	void backgroundJob() {
		try {
			SampleEntity sampleEntity = myRestClient.getSampleEntity();

			setData(URLDecoder.decode(sampleEntity.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}