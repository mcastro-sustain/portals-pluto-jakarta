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

import static jakarta.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETER;
import static jakarta.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERMAP;
import static jakarta.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERNAMES;
import static jakarta.portlet.tck.beans.JSR286DispatcherReqRespTestCaseDetails.V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERVALUES;
import static jakarta.portlet.PortletSession.APPLICATION_SCOPE;
import static jakarta.portlet.tck.constants.Constants.ATTR_PREFIX;
import static jakarta.portlet.tck.constants.Constants.JSP_PREFIX;
import static jakarta.portlet.tck.constants.Constants.JSP_SUFFIX;
import static jakarta.portlet.tck.constants.Constants.PARM_NAME;
import static jakarta.portlet.tck.constants.Constants.PARM_VALUE;
import static jakarta.portlet.tck.constants.Constants.QUERY_STRING;
import static jakarta.portlet.tck.constants.Constants.RESULT_ATTR_PREFIX;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

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
import jakarta.portlet.PortletRequestDispatcher;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.tck.beans.TestSetupLink;

/**
 * This is the event processing portlet for the test cases. This portlet processes events, 
 * but does not publish them. Events are published in the main portlet for the test cases. 
 */
public class DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_event implements Portlet, EventPortlet, ResourceServingPortlet {
   
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

      // Prereq for: V2DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_getAttribute
      portletReq.setAttribute(ATTR_PREFIX +"V2DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_getAttribute", "Value1");

      // Prereq for: V2DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_getAttributeNames
      portletReq.setAttribute(ATTR_PREFIX + "V2DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_getAttributeNames", "Value1");

      // Now do the actual dispatch
      String target = JSP_PREFIX + "DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest" + JSP_SUFFIX + "?" + QUERY_STRING;
      PortletRequestDispatcher rd = portletConfig.getPortletContext()
            .getRequestDispatcher(target);
      rd.include(portletReq, portletResp);

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {
      

      portletResp.setContentType("text/html");
      PrintWriter writer = portletResp.getWriter();
      writer.write("<h3>Event Companion Portlet </h3>\n");
      writer.write("<p>DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest_event</p>\n");

      // TestSetupLink for: DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink(V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETER, rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink(V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERMAP, rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink(V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERNAMES, rurl);
            tl.writeTo(writer);
         }
      }

      // TestSetupLink for: DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest
      {
         String val = portletReq.getParameter(PARM_NAME);
         if (val == null) {
            PortletURL rurl = ((RenderResponse)portletResp).createRenderURL();
            rurl.setParameters(portletReq.getPrivateParameterMap());
            rurl.setParameter(PARM_NAME, PARM_VALUE);
            TestSetupLink tl = new TestSetupLink(V2DISPATCHERREQRESPTESTS3_SPEC2_19_INCLUDEJSPEVENTREQUEST_GETPARAMETERVALUES, rurl);
            tl.writeTo(writer);
         }
      }

      String msg = (String) portletReq.getPortletSession()
            .getAttribute(RESULT_ATTR_PREFIX + "DispatcherReqRespTests3_SPEC2_19_IncludeJSPEventRequest", APPLICATION_SCOPE);
      msg = (msg==null) ? "Not ready. click test case link." : msg;
      writer.write("<p>" + msg + "</p>\n");

   }

}
