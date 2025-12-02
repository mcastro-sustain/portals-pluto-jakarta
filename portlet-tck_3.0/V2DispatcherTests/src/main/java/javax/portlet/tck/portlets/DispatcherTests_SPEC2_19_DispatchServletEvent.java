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

import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH1;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH2;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH3;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH4;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH5;
import static jakarta.portlet.tck.beans.JSR286DispatcherTestCaseDetails.V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH6;
import static jakarta.portlet.tck.constants.Constants.TCKNAMESPACE;
import static jakarta.portlet.tck.constants.Constants.THREADID_ATTR;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.Portlet;
import jakarta.portlet.PortletConfig;
import jakarta.portlet.PortletException;
import jakarta.portlet.PortletURL;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.ResourceRequest;
import jakarta.portlet.ResourceResponse;
import jakarta.portlet.ResourceServingPortlet;
import jakarta.portlet.tck.beans.TckParameters;
import jakarta.portlet.tck.beans.TestButton;
import javax.xml.namespace.QName;

/**
 * This portlet implements several test cases for the JSR 362 TCK. The test case names
 * are defined in the /src/main/resources/xml-resources/additionalTCs.xml
 * file. The build process will integrate the test case names defined in the 
 * additionalTCs.xml file into the complete list of test case names for execution by the driver.
 *
 * This is the main portlet for the test cases. If the test cases call for events, this portlet
 * will initiate the events, but not process them. The processing is done in the companion 
 * portlet DispatcherTests_SPEC2_19_DispatchServletEvent_event
 *
 */
public class DispatcherTests_SPEC2_19_DispatchServletEvent implements Portlet, ResourceServingPortlet {


   @Override
   public void init(PortletConfig config) throws PortletException {
   }

   @Override
   public void destroy() {
   }

   @Override
   public void processAction(ActionRequest portletReq, ActionResponse portletResp)
         throws PortletException, IOException {


      portletResp.setRenderParameters(portletReq.getParameterMap());
      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      QName eventQName = new QName(TCKNAMESPACE,
             "DispatcherTests_SPEC2_19_DispatchServletEvent");
      portletResp.setEvent(eventQName, new TckParameters(portletReq.getParameterMap()));
   }

   @Override
   public void serveResource(ResourceRequest portletReq, ResourceResponse portletResp)
         throws PortletException, IOException {


      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

   }

   @Override
   public void render(RenderRequest portletReq, RenderResponse portletResp)
         throws PortletException, IOException {


      long tid = Thread.currentThread().getId();
      portletReq.setAttribute(THREADID_ATTR, tid);

      PrintWriter writer = portletResp.getWriter();

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch1  */
      /* Details: "The PortletContext getRequestDispatcher method returns a   */
      /* PortletRequestDispatcher for a path within the portlet               */
      /* application"                                                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH1, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch2  */
      /* Details: "If the path provided to getRequestDispatcher method does   */
      /* not start with \"/\", the method returns null"                       */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH2, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch3  */
      /* Details: "If the path provided to getRequestDispatcher method ends   */
      /* with \"/\", the method returns null"                                 */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH3, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch4  */
      /* Details: "If the path provided to getRequestDispatcher method does   */
      /* not specify a valid path, the method returns null"                   */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH4, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch5  */
      /* Details: "The PortletContext getNamedDispatcher method returns a     */
      /* PortletRequestDispatcher for a servlet within the portlet            */
      /* application"                                                         */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH5, aurl);
         tb.writeTo(writer);
      }

      /* TestCase: V2DispatcherTests_SPEC2_19_DispatchServletEvent_dispatch6  */
      /* Details: "If the name provided to getNamedDispatcher method is not   */
      /* valid, the method returns null"                                      */
      {
         PortletURL aurl = portletResp.createActionURL();
         aurl.setParameters(portletReq.getPrivateParameterMap());
         TestButton tb = new TestButton(V2DISPATCHERTESTS_SPEC2_19_DISPATCHSERVLETEVENT_DISPATCH6, aurl);
         tb.writeTo(writer);
      }

   }

}
