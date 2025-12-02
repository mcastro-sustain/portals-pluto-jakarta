/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package jakarta.portlet.tck.portlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.EventPortlet;
import jakarta.portlet.EventRequest;
import jakarta.portlet.EventResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails;
import jakarta.portlet.tck.beans.TestResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.portlet.tck.beans.JSR286SpecTestCaseDetails.V2ADDLFILTERTESTS_SPEC2_20_EVENT_FILTERWRAPPER4;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;
import static jakarta.portlet.tck.constants.Constants.RESULT_ATTR_PREFIX;
import static jakarta.portlet.PortletSession.APPLICATION_SCOPE;

/**
 * This is the event processing portlet for the test cases. This portlet processes events, but does
 * not publish them. Events are published in the main portlet for the test cases.
 * 
 * @author ahmed
 */
public class AddlFilterTests_SPEC2_20_Event_event
    implements Portlet, EventPortlet, ResourceServingPortlet {

  private static final Logger LOGGER = LoggerFactory.getLogger(AddlFilterTests_SPEC2_20_Event_event.class);

  @Override
  public void init(PortletConfig config) throws PortletException {

  }

  @Override
  public void destroy() {

  }

  @Override
  public void processAction(ActionRequest portletReq, ActionResponse portletResp)
      throws PortletException, IOException {
    LOGGER.trace("event companion processAction - ERROR!!");
  }

  @Override
  public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
      throws PortletException, IOException {
    LOGGER.trace("event companion serveResource - ERROR!!");
  }

  @Override
  public void processEvent(EventRequest portletReq, EventResponse portletResp)
      throws PortletException, IOException {

    long tid = Thread.currentThread().getId();
    portletReq.setAttribute(THREADID_ATTR, tid);
    LOGGER.trace("event companion processEvent - INFO!!");

  }

  @Override
  public void render(RenderRequest portletReq, RenderResponse portletResp)
      throws PortletException, IOException {

    JSR286SpecTestCaseDetails tcd = new JSR286SpecTestCaseDetails();

    portletResp.setContentType("text/html");
    PrintWriter writer = portletResp.getWriter();
    writer.write("<h3>Event Companion Portlet </h3>\n");
    writer.write("<p>AddlFilterTests_SPEC2_20_Event_event</p>\n");

    if (portletReq.getParameter("tr12") != null && portletReq.getParameter("tr12").equals("true")) {
      TestResult tr12 = tcd.getTestResultFailed(V2ADDLFILTERTESTS_SPEC2_20_EVENT_FILTERWRAPPER4);
      tr12.setTcSuccess(true);
      tr12.writeTo(writer);
    }

    String msg = (String) portletReq.getPortletSession()
        .getAttribute(RESULT_ATTR_PREFIX + "AddlFilterTests_SPEC2_20_Event", APPLICATION_SCOPE);
    msg = (msg == null) ? "Not ready. click test case link." : msg;
    writer.write("<p>" + msg + "</p>\n");

  }

}
