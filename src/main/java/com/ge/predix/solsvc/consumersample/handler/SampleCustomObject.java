package com.ge.predix.solsvc.consumersample.handler;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SampleCustomObject")
public class SampleCustomObject {
	
	private String attribute1;
	private String attribute2;
	
	@XmlElement
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	
	@XmlElement
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	
	@Override
	public String toString() {
		return "SampleCustomObject [attribute1=" + attribute1 + ", attribute2="
				+ attribute2 + "]";
	}
	
	

}
