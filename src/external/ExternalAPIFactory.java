package external;

public class ExternalAPIFactory {
	private static final String DEFAULT_PIPELINE = "YelpAPI";
	public static ExternalAPI getExternalAPI(String pipeline) {
		switch(pipeline) {
		case"YelpAPI":
			return new YelpAPI();
		default:
			throw new IllegalArgumentException("Invalid pipeline: " + pipeline);
		}
	}
	
	public static ExternalAPI getExternalAPI() {
		return getExternalAPI(DEFAULT_PIPELINE);
	}
}
