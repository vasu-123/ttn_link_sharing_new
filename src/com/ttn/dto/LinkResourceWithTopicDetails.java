package com.ttn.dto;

public class LinkResourceWithTopicDetails extends LinkResource {

	private String topic_name;
	private String topicCreatedBy;
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	
	public String getTopicCreatedBy() {
		return topicCreatedBy;
	}
	public void setTopicCreatedBy(String topicCreatedBy) {
		this.topicCreatedBy = topicCreatedBy;
	}
	
	
	
}
