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

import java.util.List;

import ac.jejunu.photify.R;
import ac.jejunu.photify.command.ArticleCommand;
import ac.jejunu.photify.entity.Article;
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
	void appendData(String data) {
		String s = section_label.getText().toString();
		section_label.setText(s.concat(data));
	}

	int i = 100;

	@Background
	void backgroundJob() {
		try {
			int no = 2015 + i++;

			Article article = new Article();
			article.setArticleid(no);
			article.setTitle("article title " + no);
			article.setContents("article contents");
			article.setFbid("10010101010");
			article.setLat(123);
			article.setLng(456);
			article.setPhotoUrl("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash2/c12.12.176.176/374637_189230964497111_247316888_n.jpg");
			article.setWriter("article writer Flask");
			appendData("writing, " + no);
			appendData(" : " + article.toString());

			String result = testCommandRestClient.writeArticle(article);
			appendData(" // write result = " + result);
			List<Article> articles = testCommandRestClient.getArticles(new ArticleCommand(2, 2));

			for (Article a : articles) {
				appendData(a.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}