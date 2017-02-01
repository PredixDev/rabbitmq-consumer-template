package com.ge.predix.solsvc.consumersample.service;

import com.ge.predix.solsvc.consumersample.handler.SampleCustomObject;

public class SampleCustomService {

	public void businessLogic(Object data)
	{
		if (data instanceof SampleCustomObject)
		{
			SampleCustomObject customObj = (SampleCustomObject) data;
			
			if (customObj.getAttribute1().equals("exception"))
				throw new RuntimeException("Business logic failed. Raising exception");	
		}
	}
}
