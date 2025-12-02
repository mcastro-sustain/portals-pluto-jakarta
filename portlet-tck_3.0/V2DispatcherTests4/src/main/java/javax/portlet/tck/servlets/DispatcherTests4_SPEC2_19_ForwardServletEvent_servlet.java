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

package jakarta.portlet.tck.servlets;

import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS4_SPEC2_19_FORWARDSERVLETEVENT_INVOKE3;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;
import java.io.StringWriter;

import jakarta.portlet.PortletRequest;
import jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails;
import jakarta.portlet.tck.beans.TestResult;
import jakarta.portlet.tck.constants.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet for JSR 362 request dispatcher testing. Used by portlet: DispatcherTests4_SPEC2_19_ForwardServletEvent
 * 
 * @author nick
 * 
 */
public class DispatcherTests4_SPEC2_19_ForwardServletEvent_servlet extends HttpServlet {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processTCKReq(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processTCKReq(req, resp);
   }

   // The tck uses only get & post requests
   protected void processTCKReq(HttpServletRequest request, HttpServletResponse response) throws ServletException,
         IOException {

      PortletRequest portletReq = (PortletRequest) request.getAttribute("jakarta.portlet.request");
      request.getAttribute("jakarta.portlet.response");
      request.getAttribute("jakarta.portlet.config");
      Thread.currentThread().getId();
      portletReq.getAttribute(THREADID_ATTR);

      StringWriter writer = new StringWriter();

      JSR286DispatcherTestCaseDetails tcd = new JSR286DispatcherTestCaseDetails();

      // Create result objects for the tests

      /* TestCase: V2DispatcherTests4_SPEC2_19_ForwardServletEvent_invoke3 */
      /* Details: "Parameters to the forward method for a target servlet */
      /* can be wrapped request and response classes from the portlet */
      /* lifecyle method initiating the include" */
      TestResult tr0 = tcd.getTestResultFailed(V2DISPATCHERTESTS4_SPEC2_19_FORWARDSERVLETEVENT_INVOKE3);
      try {
         // If this gets executed, include worked.
         tr0.setTcSuccess(true);
      } catch (Exception e) {
         tr0.appendTcDetail(e.toString());
      }
      tr0.writeTo(writer);

      request.getSession().setAttribute(Constants.RESULT_ATTR_PREFIX + "DispatcherTests4_SPEC2_19_ForwardServletEvent",
            writer.toString());

   }
}
