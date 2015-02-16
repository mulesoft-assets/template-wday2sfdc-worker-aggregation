/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.integration;

import org.junit.*;
import org.mule.MessageExchangePattern;
import org.mule.api.MuleEvent;
import org.mule.processor.chain.SubflowInterceptingChainLifecycleWrapper;
import org.mule.streaming.ConsumerIterator;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.templates.transformers.WorkersAndUsersMerge;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The objective of this class is to validate the correct behavior of the flows for this Mule Template that
 * make calls to external systems.
 *
 * @author aurel.medvegy
 */
public class BusinessLogicIT extends AbstractTemplateTestCase {

    @Rule
	public DynamicPort port = new DynamicPort("http.port");

	@Test
	public void testMainFlow() throws Exception {
		MuleEvent event = runFlow("mainFlow");
		Assert.assertTrue("The payload should not be null.", "Please find attached your Users Report".equals(event.getMessage().getPayload()));
	}
	
	@Test
	public void testGatherDataFlow() throws Exception {
		SubflowInterceptingChainLifecycleWrapper flow = getSubFlow("gatherDataFlow");
		flow.setMuleContext(muleContext);
		flow.initialise();
		flow.start();
		MuleEvent event = flow.process(getTestEvent("", MessageExchangePattern.REQUEST_RESPONSE));
		List<Map<String, String>> mergedUserList = (List<Map<String, String>>)event.getMessage().getPayload();
		
		Assert.assertTrue("There should be users from source A or source B.", !mergedUserList.isEmpty());
	} 
}
