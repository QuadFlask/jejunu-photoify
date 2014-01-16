package ac.jejunu.photify.activity.fragment;

public class SampleEntity {

	private String name;

	public SampleEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SampleEntity{" +
				"name='" + name + '\'' +
				'}';
	}
}
