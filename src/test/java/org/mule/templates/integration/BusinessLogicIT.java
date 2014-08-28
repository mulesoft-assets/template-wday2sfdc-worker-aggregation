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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGatherDataFlow() throws Exception {

        SubflowInterceptingChainLifecycleWrapper flow = getSubFlow("gatherDataFlow");
        flow.initialise();

        MuleEvent event = flow.process(getTestEvent("", MessageExchangePattern.REQUEST_RESPONSE));
        Set<String> flowVariables = event.getFlowVariableNames();

        Assert.assertTrue("The variable workersFromWorkday is missing.", flowVariables.contains(WorkersAndUsersMerge.WORKDAY_WORKERS));
        Assert.assertTrue("The variable usersFromSalesforce is missing.", flowVariables.contains(WorkersAndUsersMerge.SALESFORCE_USERS));

        List<Map<String, String>> workersFromWorkday = event.getFlowVariable(WorkersAndUsersMerge.WORKDAY_WORKERS);
        ConsumerIterator<Map<String, String>> usersFromSalesforce = event.getFlowVariable(WorkersAndUsersMerge.SALESFORCE_USERS);

        Assert.assertTrue("There should be users in the variable workersFromWorkday.", workersFromWorkday.size() != 0);
        Assert.assertTrue("There should be users in the variable usersFromSalesforce.", usersFromSalesforce.size() != 0);
    }

}
