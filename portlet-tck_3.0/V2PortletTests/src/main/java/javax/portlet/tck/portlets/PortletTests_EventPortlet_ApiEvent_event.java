/*  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package jakarta.portlet.tck.portlets;

import static jakarta.portlet.PortletSession.APPLICATION_SCOPE;
import static jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails.V2PORTLETTESTS_EVENTPORTLET_APIEVENT_PROCESSEVENT;
import static jakarta.portlet.tck.constants.Constants.RESULT_ATTR_PREFIX;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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
import jakarta.portlet.tck.beans.JSR286ApiTestCaseDetails;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.constants.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the event processing portlet for the test cases. This portlet
 * processes events, but does not publish them. Events are published in the main
 * portlet for the test cases.
 */
public class PortletTests_EventPortlet_ApiEvent_event
      implements Portlet, EventPortlet {

   private final Logger LOGGER = LoggerFactory.getLogger(PortletTests_EventPortlet_ApiEvent_event.class);

   @Override
   public void init(PortletConfig config) throws PortletException {

   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq,
         ActionResponse portletResp) throws PortletException, IOException {
      LOGGER.trace("event companion processAction - ERROR!!");
   }

   @Override
   public void processEvent(EventRequest portletReq, EventResponse portletResp)
         throws PortletException, IOException {

      portletResp.setRenderParameters(portletReq);

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      StringWriter writer = new StringWriter();

      JSR286ApiTestCaseDetails tcd = new JSR286ApiTestCaseDetails();

      // Create result objects for the tests

      /* TestCase: V2PortletTests_EventPortlet_ApiEvent_processEvent */
      /* Details: "Method processEvent(EventRequest, EventResponse): is */
      /* called when an event is targeted to the portlet" */
      TestResult tr0 = tcd.getTestResultFailed(
            V2PORTLETTESTS_EVENTPORTLET_APIEVENT_PROCESSEVENT);
      try {
         tr0.setTcSuccess(true);
      } catch (Exception e) {
         tr0.appendTcDetail(e.toString());
      }
      tr0.writeTo(writer);

      portletReq.getPortletSession().setAttribute(
            Constants.RESULT_ATTR_PREFIX + "PortletTests_EventPortlet_ApiEvent",
            writer.toString(), APPLICATION_SCOPE);

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {

      portletResp.setContentType("text/html");
      PrintWriter writer = portletResp.getWriter();
      writer.write("<h3>Event Companion Portlet </h3>\n");
      writer.write("<p>PortletTests_EventPortlet_ApiEvent_event</p>\n");

      String msg = (String) portletReq.getPortletSession().getAttribute(
            RESULT_ATTR_PREFIX + "PortletTests_EventPortlet_ApiEvent",
            APPLICATION_SCOPE);
      msg = (msg == null) ? "Not ready. click test case link." : msg;
      writer.write("<p>" + msg + "</p>\n");

   }

}
