package com.ttn.dto;


public class SubscriptionDetails{

	private String seriousness ;
    private Integer topic_id;
	
    
	public Integer getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(Integer topic_id) {
		this.topic_id = topic_id;
	}

	public String getSeriousness() {
		return seriousness;
	}

	public void setSeriousness(String seriousness) {
		this.seriousness = seriousness;
	}
	
	
}
