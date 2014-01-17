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

import java.net.URLDecoder;

import ac.jejunu.photify.R;
import ac.jejunu.photify.entity.SampleEntity;
import ac.jejunu.photify.entity.TestCommand;
import ac.jejunu.photify.rest.MyRestClient;
import ac.jejunu.photify.rest.TestCommandRestClient;

@EFragment(R.layout.fragment_main)
public class JsonTestFragment extends Fragment {

	@ViewById(R.id.section_label)
	TextView section_label;

	@RestService
	MyRestClient myRestClient;

	@RestService
	TestCommandRestClient testCommandRestClient;

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

	@UiThread
	void appendData(String data) {
		String s = section_label.getText().toString();
		section_label.setText("서버로부터 : " + s.concat(data));
	}

	int i = 100;

	@Background
	void backgroundJob() {
		try {
			SampleEntity sampleEntity = myRestClient.getSampleEntity();

			setData(URLDecoder.decode(sampleEntity.getName()));

			TestCommand testCommand = testCommandRestClient.getTestCommand();
			appendData(testCommand.toString());

			testCommand = new TestCommand();
			testCommand.setArticleid(100 + i++);
			testCommand.setTitle("hello from android " + testCommand.getArticleid());
			testCommandRestClient.sendTestCommand(testCommand);
			appendData("sending : " + testCommand.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}