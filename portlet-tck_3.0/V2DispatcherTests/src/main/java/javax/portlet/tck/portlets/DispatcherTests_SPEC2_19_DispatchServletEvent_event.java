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
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH1;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH2;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH3;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH4;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH5;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH6;
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
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails;
import jakarta.portlet.tck.beans.TckParameters;
import jakarta.portlet.tck.beans.TckParameters.Parameter;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.constants.Constants;

/**
 * This is the event processing portlet for the test cases. This portlet processes events, 
 * but does not publish them. Events are published in the main portlet for the test cases. 
 */
public class DispatcherTests_SPEC2_19_DispatchServletEvent_event implements Portlet, EventPortlet, ResourceServingPortlet {


   
   private PortletConfig portletConfig = null;

   @Override
   public void init(PortletConfig config) throws PortletException {
      this.portletConfig = config;
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {

   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {

   }

   @Override
   public void processEvent(EventRequest portletReq, EventResponse portletResp)
         throws PortletException, IOException {



      portletResp.setRenderParameters(portletReq);

      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      StringWriter writer = new StringWriter();

      TckParameters params = (TckParameters)portletReq.getEvent().getValue();
      for (Parameter p : params.getParams()) {
         portletResp.setRenderParameter(p.getName(), p.getVals());
      }

      JSR286DispatcherTestCaseDetails tcd = new JSR286DispatcherTestCaseDetails();

      // Create result objects for the tests

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch1  */
      /* Details: "The PortletContext getRequestDispatcher method returns a   */
      /* PortletRequestDispatcher for a path within the portlet               */
      /* application"                                                         */
      TestResult tr0 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH1);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getRequestDispatcher("/DispatcherTests_SPEC2_19_DispatchServletEvent_servlet");
         tr0.setTcSuccess(rd != null);
      } catch(Exception e) {tr0.appendTcDetail(e.toString());}
      tr0.writeTo(writer);

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch2  */
      /* Details: "If the path provided to getRequestDispatcher method does   */
      /* not start with \"/\", the method returns null"                       */
      TestResult tr1 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH2);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getRequestDispatcher("DispatcherTests_SPEC2_19_DispatchServletEvent_servlet");
         tr1.setTcSuccess(rd == null);
      } catch(Exception e) {tr1.appendTcDetail(e.toString());}
      tr1.writeTo(writer);

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch3  */
      /* Details: "If the path provided to getRequestDispatcher method ends   */
      /* with \"/\", the method returns null"                                 */
      TestResult tr2 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH3);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getRequestDispatcher("DispatcherTests_SPEC2_19_DispatchServletEvent_servlet");
         tr2.setTcSuccess(rd == null);
      } catch(Exception e) {tr2.appendTcDetail(e.toString());}
      tr2.writeTo(writer);

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch4  */
      /* Details: "If the path provided to getRequestDispatcher method does   */
      /* not specify a valid path, the method returns null"                   */
      TestResult tr3 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH4);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getRequestDispatcher(" Invalid path.jsp ");
         tr3.setTcSuccess(rd == null);
      } catch(Exception e) {tr3.appendTcDetail(e.toString());}
      tr3.writeTo(writer);

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch5  */
      /* Details: "The PortletContext getNamedDispatcher method returns a     */
      /* PortletRequestDispatcher for a servlet within the portlet            */
      /* application"                                                         */
      TestResult tr4 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH5);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getNamedDispatcher("DispatcherTests_SPEC2_19_DispatchServletEvent_servlet");
         tr4.setTcSuccess(rd != null);
      } catch(Exception e) {tr4.appendTcDetail(e.toString());}
      tr4.writeTo(writer);

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch6  */
      /* Details: "If the name provided to getNamedDispatcher method is not   */
      /* valid, the method returns null"                                      */
      TestResult tr5 = tcd.getTestResultFailed(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH6);
      try {
         PortletRequestDispatcher rd = portletConfig.getPortletContext()
               .getNamedDispatcher("Invalid Name");
         tr5.setTcSuccess(rd == null);
         
      } catch(Exception e) {tr5.appendTcDetail(e.toString());}
      tr5.writeTo(writer);

      portletReq.getPortletSession().setAttribute(
                   Constants.RESULT_ATTR_PREFIX + "DispatcherTests_SPEC2_19_DispatchServletEvent",
                   writer.toString(), APPLICATION_SCOPE);

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {
      


      portletResp.setContentType("text/html");
      PrintWriter writer = portletResp.getWriter();
      writer.write("<h3>Event Companion Portlet </h3>\n");
      writer.write("<p>DispatcherTests_SPEC2_19_DispatchServletEvent_event</p>\n");

      String msg = (String) portletReq.getPortletSession()
            .getAttribute(RESULT_ATTR_PREFIX + "DispatcherTests_SPEC2_19_DispatchServletEvent", APPLICATION_SCOPE);
      msg = (msg==null) ? "Not ready. click test case link." : msg;
      writer.write("<p>" + msg + "</p>\n");

   }

}
